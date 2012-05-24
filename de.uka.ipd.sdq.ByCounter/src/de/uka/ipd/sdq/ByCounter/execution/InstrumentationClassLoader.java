package de.uka.ipd.sdq.ByCounter.execution;

import java.util.logging.Logger;

import javassist.ByteArrayClassPath;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Loader;
import javassist.NotFoundException;

/**
 * {@link ClassLoader} for ByCounter that handles loading of instrumented 
 * classes.
 * <p>Interfaces will be loaded using the default class loader instead, so
 * that objects of instrumented classes can be used by their interfaces in 
 * external code.
 * </p>
 * @author Martin Krogmann
 *
 */
public class InstrumentationClassLoader extends java.lang.ClassLoader {
	
	/**
	 * A logger instance (java.util Logging)
	 */
	private static Logger log;
	
	/**
	 * When a method is instrumented by ByCounter, the class that holds the 
	 * method is changed. So if the unchanged class has been loaded by a 
	 * {@link InstrumentationClassLoader}, we need to make sure that the instrumented version
	 * of the class is used. 
	 * This Javassist {@link ClassPool} holds the instrumented classes that 
	 * are then used instead of their unmodified versions.
	 */
	private ClassPool classPool;

	/**
	 * Parent of this classloader to delegate to.
	 */
	private ClassLoader parentClassLoader;
		
	/**
	 * Construct the class loader with a default class pool.
	 * This constructor is defined so the class loader can be used as the 
	 * system class loader.
	 * @see ClassLoader#getSystemClassLoader()
	 * @param parentClassLoader System class loader parent.
	 */
	public InstrumentationClassLoader(ClassLoader parentClassLoader) {
		this.parentClassLoader = parentClassLoader;
		this.classPool = ClassPool.getDefault();
		log = Logger.getLogger(this.getClass().getCanonicalName());
	}
	
	/**
	 * Updates the class definition for the class given by className
	 * and bytes by adding it to the Javassist {@link ClassPool}.
	 * @param className Fully qualified name of the given class.
	 * @param bytes Byte array representing the class.
	 */
	public void updateClassInClassPool(String className, byte[] bytes) {
		log.fine("Updating class pool");
		if(className == null || className.length() == 0) {
			throw new RuntimeException(
					new ClassNotFoundException("Cannot update class pool because the given classname "
					+ "was null or invalid."));
		} else if(bytes == null) {
			throw new RuntimeException(
				new ClassNotFoundException("Cannot update class pool as the given byte[] for the class"
					+ "was null."));
		}
		// make the class known to the class pool
		this.classPool.insertClassPath(new ByteArrayClassPath(className, bytes));
	}
	

	/**
	 * @param name Canonical class name.
	 */
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		Class<?> pClass = this.getClass().getClassLoader().loadClass(name);
		if(pClass.isInterface()) {
			return pClass;
		}
				
		CtClass ctClassToExecute = null;
		// get the CtClass from the pool
		try {
			ctClassToExecute = this.classPool.get(name);
		} catch (NotFoundException e) {
			throw new ClassNotFoundException("Class pool cannot find the class " + name + ".", e);
		}
		
		// use the supplied classloader if necessary (i.e. for eclipse plugin)
		Loader loader;
		if(parentClassLoader == null) {
			loader = new Loader(this.classPool);
		} else {
			loader = new Loader(parentClassLoader, this.classPool);
		}
		// make sure that the CountingResultCollector (important!) and all other 
		// ByCounter classes do not get reloaded.
		loader.delegateLoadingOf("de.uka.ipd.sdq.ByCounter.execution.");
		
		// delegate interfaces as well
		try {
			for(CtClass iFace : ctClassToExecute.getInterfaces()) {
				delegateLoadingOfInterfaces(loader, iFace);
			}
		} catch (NotFoundException e1) {
			throw new RuntimeException(e1);
		}
		
		// use the ClassLoader loader to get the Class<?> object
		// use a standard protection domain
		try {
			return ctClassToExecute.toClass(
					loader, 
					BytecodeCounter.class.getProtectionDomain()
					);
		} catch (CannotCompileException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Delegate the loading of the given interface and all interfaces that
	 * are referenced in the interface recursively.
	 * @param loader {@link Loader} instance used for class loading.
	 * @param iFace Interface class to delegate the loading of.
	 * @throws NotFoundException
	 */
	private void delegateLoadingOfInterfaces(Loader loader, CtClass iFace)
			throws NotFoundException {
		loader.delegateLoadingOf(iFace.getName());
		for(CtMethod iMethod : iFace.getMethods()) {
			for(CtClass pType : iMethod.getParameterTypes()) {
				if(pType.isInterface()) {
					loader.delegateLoadingOf(pType.getName());
				}
			}
		}
	}
	
	/**
	 * @param parentClassLoader2 The parent class loader to use.
	 */
	public void setParentClassLoader(ClassLoader parentClassLoader2) {
		this.parentClassLoader = parentClassLoader2;
	}
}
