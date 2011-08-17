package de.uka.ipd.sdq.ByCounter.parsing;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Logger;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * This class has the method {@link #parseClass(CallGraph, String)} that can be 
 * used to create a {@link CallGraph} for a given class.
 * @author Martin Krogmann
 *
 */
public final class CallGraphClassAdapter {
	
	private static Logger log = Logger.getLogger(CallGraphClassAdapter.class.getCanonicalName());
	
	private String[] ignoredPackagePrefixes;
	
	public CallGraphClassAdapter(String[] ignoredPackagePrefixes) {
		this.ignoredPackagePrefixes = ignoredPackagePrefixes;
	}
		
	/**
	 * @param callGraph This is the {@link CallGraph} that will be extended with the method calls
	 * found in the class named className.
	 * @param className The name of the class holding the methods that shall be parsed.
	 * Needs to be fully qualified as this is used to find the correct class.
	 * @return True, if the class could be found and parsed successfully.
	 */
	public boolean parseClass(CallGraph callGraph, final String className) {

		ClassReader cr = null;
		try {
			cr = new ClassReader(className);
		} catch (IOException e) {
			log.severe("Could not parse class with name '" + className + "'. Skipping.");
			return false;
		}

		return parseClass(callGraph, cr);
	}
		
	/**
	 * @param callGraph This is the {@link CallGraph} that will be extended with the method calls
	 * found in the class named className.
	 * @param classBytes The class holding the methods that shall be parsed.
	 * Needs to be fully qualified as this is used to find the correct class.
	 * @return True, if the class could be found and parsed successfully.
	 */
	public boolean parseClass(CallGraph callGraph, final byte[] classBytes) {

		ClassReader cr = null;
		cr = new ClassReader(classBytes);

		return parseClass(callGraph, cr);
	}
	
	/**
	 * @param callGraph This is the {@link CallGraph} that will be extended with the method calls
	 * found in the class named className.
	 * @param classReader An initialised {@link ClassReader} for the class holding the methods that shall be parsed.
	 * Needs to be fully qualified as this is used to find the correct class.
	 * @return True, if the class could be found and parsed successfully.
	 */
	@SuppressWarnings("unchecked")
	protected boolean parseClass(CallGraph callGraph, ClassReader classReader) {
		if(callGraph == null) {
			log.severe("CallGraph was null. Aborting parsing.");
			return false;
		}
		
		// check if the class was already parsed to avoid endless recursion
		if(callGraph.getParsedClasses().contains(classReader.getClassName())) {
			log.finer("Class already parsed");
			return true;
		}
		
		// check if the class is to be ignored
		for(String prefix : ignoredPackagePrefixes) {
			if (classReader.getClassName().startsWith(prefix)) {
				log.finer("Class is in ignored package: " + classReader.getClassName());				
				return true;
			}
		}
		
		log.info("Parsing class: " + classReader.getClassName());
		// We can now parse the class 
		callGraph.addParsedClass(classReader.getClassName());
		
		// transformation chain:
		ClassNode cn = new ClassNode();		
		ClassReader cr = classReader;
		
		cr.accept(cn, 0);
		
		boolean success = true;
		// go through all methods
		Iterator<MethodNode> it = cn.methods.iterator();
		while(it.hasNext()) {
			MethodNode m = (MethodNode)it.next();
			CallGraphMethod m1 = new CallGraphMethod(cr.getClassName(), m.name, m.desc);
			if(cr.getClassName().startsWith("[")) {
				continue;
			}
			log.fine("Parsing method: " + m.name + m.desc);
			
			// go through all instructions in the method
			Iterator<AbstractInsnNode> iterator = m.instructions.iterator();
			while(iterator.hasNext()) {
				AbstractInsnNode insn = iterator.next();
				if(insn instanceof MethodInsnNode) {
					// A method invocation
					MethodInsnNode methodCall = ((MethodInsnNode)insn);

		        	if(methodCall.owner.charAt(0) == '[') {					        	                                                                 
		        		// this case happens for example for [I.clone()
		        		continue;
		        	}
					
					if(insn.getOpcode() == Opcodes.INVOKEINTERFACE) {
						// use a class finder tool to find all implementing classes
						IImplementingClassesFinder finder = new ClapperImplementingClassesFinder();
						try {
							Class<?> interfaceClass = Class.forName(methodCall.owner.replace('/', '.'));
							String[] foundClasses = finder.findImplementingClasses(interfaceClass);
					        for(String foundClassName : foundClasses) {
					        	if(methodCall.owner.charAt(0) == '[' || foundClassName.charAt(0) == '[') {					        	                                                                 
					        		// this case happens for example for [I.clone()
					        		continue;
					        	}
					     		// Add an edge from the node representing this method to the the node
					     		// for methodCall
					     		CallGraphMethod m2 = 
					     			new CallGraphMethod(foundClassName, methodCall.name, methodCall.desc);

					     		// this method (m1) can call the method m2 since a MethodInsnNode for m2 was found
					     		// add the call to the graph
					     		callGraph.addMethodCall(m1, m2);
					     		
					     		// now parse the class that contains m2
					     		success = success && parseClass(callGraph, methodCall.owner);
					        }
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}

					// Add an edge from the node representing this method to the the node
					// for methodCall
					CallGraphMethod m2 = 
						new CallGraphMethod(methodCall.owner, methodCall.name, methodCall.desc);

					// this method (m1) can call the method m2 since a MethodInsnNode for m2 was found
					// add the call to the graph
					callGraph.addMethodCall(m1, m2);
					
					// now parse the class that contains m2
					success = success && parseClass(callGraph, methodCall.owner);
				}
			}
		}
		
		return success;
	}
}
