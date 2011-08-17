package de.uka.ipd.sdq.ByCounter.execution;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.uka.ipd.sdq.ByCounter.utils.InvocationResultData;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * A set of tools that help with the invocation of methods at runtime.
 * Allows to check for matching parameters and the calling itself.
 *
 * @author Martin Krogmann
 * @author Michael Kuperberg
 * @since 0.1
 * @version 1.2
 */
public final class MethodInvocationHelper {

	/**
	 * Build a string consisting of method name and parameter types.
	 * Example: "incrementInt(int)".
	 * @param method method to build signature for.
	 * @return Signature String .
	 */
	@SuppressWarnings("unused")
	private static String buildMethodSignature(Method method) {
		StringBuilder methodName = new StringBuilder(method.getName());
		methodName.append("(");
		for(int j = 0; j < method.getParameterTypes().length; j++) {
			methodName.append(method.getParameterTypes()[j].toString());
			if(j < method.getParameterTypes().length - 1) {
				methodName.append(", ");
			}
		}
		methodName.append(")");
		return methodName.toString();
	}

	/** 
	 * Call the specified methods using reflection.
	 * @param log A log where information on the progress is written to.
	 * @param clazz The class that contains the methods to execute.
	 * @param parentObject Class that contains the methods to call.
	 * @param methodsToCall List of method names to call.
	 * @param params Parameters for the method calls. This means one instance of RuntimeMethodParameters for each method. If null, no parameters is assumed.
	 * @return Result and duration of execution. 
	 */
	@SuppressWarnings("deprecation")
	public static InvocationResultData callMethods(
			Logger log,
			Class<? extends Object> clazz,
			Object parentObject, 
			List<MethodDescriptor> methodsToCall, 
			List<RuntimeMethodParameters> params) {
		InvocationResultData invocationResult = new InvocationResultData();
		log.fine("Calling methods "+methodsToCall+
				"\n\t\t on object instance "+parentObject+" " +
				"\n\t\t using parameters "+params);
		try {
			Method[] methods = clazz.getDeclaredMethods();
			
			// assure that params is correct
			if(params == null) {
				params = new ArrayList<RuntimeMethodParameters>(methodsToCall.size());
			} else if (params.size() < methodsToCall.size()){
				log.warning("Warning: callMethods called with less " +
						"parameterlists than methods. " +
						"Assuming no parameters for those methods.");
				// fill up the list
				for(int i = params.size(); i < methodsToCall.size(); i++) {
					params.add(new RuntimeMethodParameters());
				}
			}
			
			// try to call all methods
			for(int i = 0; i < methodsToCall.size(); i++) {
				// look for a matching method
				boolean matchFound = false;
				// is the method a constructor?
				if(methodsToCall.get(i).getIsConstructor()) {
					Constructor<?>[] constructors = 
						clazz.getDeclaredConstructors();
					for(Constructor<?> c : constructors) {
						MethodDescriptor descriptor = new MethodDescriptor(c);
						// The descriptors method name is fully qualifyied.
						// Since we already search the correct class, this info
						// is not necessary, so cut the package names.
						int startSplitIndex = descriptor.getSimpleMethodName().lastIndexOf('.')+1;
						// startSplitIndex is correctly 0 (=-1+1) if no '.' is found.
						String methodName = descriptor.getSimpleMethodName().substring(startSplitIndex);
						if(methodName.equals(methodsToCall.get(i).getSimpleMethodName())		// matching name
								&& descriptor.getDescriptor().equals(methodsToCall.get(i).getDescriptor()) ) {	// matching parameters
							// constructor found: invoke it and break the loop
							long startMethod = System.nanoTime();
							log.fine("Invoking method "+c.getName());
							invocationResult.returnValue = c.newInstance(params.get(i).getParameters());
							long stopMethod = System.nanoTime();
							invocationResult.duration = stopMethod - startMethod;
							log.info("MethodInvocationHelper: Calling method "+c.getName()+" over reflection. Duration: "+ invocationResult.duration +" ns");
							matchFound = true;
							break;
						}
					}
				} else { // no constructor; look for normal method
					for(Method m : methods) {
						MethodDescriptor descriptor = new MethodDescriptor(m);
						if(descriptor.getSimpleMethodName().equals(methodsToCall.get(i).getSimpleMethodName())		// matching name
								&& descriptor.getDescriptor().equals(methodsToCall.get(i).getDescriptor()) ) {	// matching parameters
							// method found: invoke method and break the loop
	//						log.fine(">>> Invoking method '" + buildMethodSignature(m) + "'...");
							long startMethod = System.nanoTime();
							log.fine("Invoking method "+m.getName());
							try {
								invocationResult.returnValue = m.invoke(parentObject, params.get(i).getParameters());
							} catch(InvocationTargetException ei) {
								log.severe("Invocation error while trying to execute method.");
								if(ei.getCause().getClass() == StackOverflowError.class) {
									String msg = "Stack overflow on method execution. " 
										+ "Please try to increase the stack size"
										+ "(VM option: \"-Xss1M\")"; 
									log.log(Level.SEVERE, msg, ei);
									throw new InvocationTargetException(ei, msg);
								} else {
									throw ei;
								}
							}
							long stopMethod = System.nanoTime();
							invocationResult.duration = stopMethod - startMethod;
							log.info("MethodInvocationHelper: Calling method "+m.getName()+" over reflection. Duration: "+ invocationResult.duration +" ns");
							matchFound = true;
							break;
						}
					}
				}
				
				if(!matchFound) {
					log.severe("ERROR: Could not find a matching method with the name " 
							+ clazz.getCanonicalName() + "." + methodsToCall.get(i).getSimpleMethodName());
				}
			}
			
		} catch (IllegalAccessException e) {
			log.log(Level.SEVERE, "Exception lead to abortion of execution.", e);
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			log.log(Level.SEVERE, "Exception lead to abortion of execution.", e);
			e.printStackTrace();
		} catch (SecurityException e) {
			log.log(Level.SEVERE, "Exception lead to abortion of execution.", e);
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			log.log(Level.SEVERE, "Exception lead to abortion of execution.", e);
			e.printStackTrace();
		} catch (InstantiationException e) {
			log.log(Level.SEVERE, "Exception lead to abortion of execution.", e);
			e.printStackTrace();
		}
		return invocationResult;
	}
	
	/**
	 * Call the specfied methods that take no arguments using reflection.
	 * Short for calling callMethods with params == null. 
	 * @param log A log where information on the progress is written to.
	 * @param clazz The class that contains the methods to execute.
	 * @param parentObject Class that contains the methods to call.
	 * @param methodsToCall List of method names to call. Must not have arguments.
	 * @return Result and duration of execution.
	 */
	public static InvocationResultData callMethodsNoArgs(Logger log,
			Class<? extends Object> clazz,
			Object parentObject, 
			List<MethodDescriptor> methodsToCall) {
		return callMethods(log, clazz, parentObject, methodsToCall, null);
	}
	

	/**
	 * Checks if the given methods arguments match to the given parameters.
	 * @param m Method with the parameter description
	 * @param params Parameters to compare to the methods arguments.
	 * @return True if the parameters fit.
	 */
	@Deprecated
	public static boolean checkParametersMatch(Method m, 
			RuntimeMethodParameters params) {
		
		// check for number of parameters
		if(m.getParameterTypes().length == params.getParameters().length) {

			// now compare the type for each parameter
			for(int i = 0; i < params.getParameters().length; i++) {
				Class<?> type = m.getParameterTypes()[i];
				Class<?> givenType = params.getParameters()[i].getClass();

				// check whether a primitive is expected
				// in case it is do the specific comparison with its class type
				if(type == Boolean.TYPE) {
					if(!givenType.equals(Boolean.class)) {
						return false;
					}
				} else if(type == Byte.TYPE) {
					if(!givenType.equals(Byte.class)) {
						return false;
					}
				} else if(type == Character.TYPE) {
					if(!givenType.equals(Character.class)) {
						return false;
					}
				} else if(type == Short.TYPE) {
					if(!givenType.equals(Short.class)) {
						return false;
					}
				} else if(type == Integer.TYPE) {
					if(!givenType.equals(Integer.class)) {
						return false;
					}
				} else if(type == Long.TYPE) {
					if(!givenType.equals(Long.class)) {
						return false;
					}
				} else if(type == Float.TYPE) {
					if(!givenType.equals(Float.class)) {
						return false;
					}
				} else if(type == Double.TYPE) {
					if(!givenType.equals(Double.class)) {
						return false;
					}
				} else { // no primitive type expected, do class check
					if(!type.isAssignableFrom(givenType)) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}
}
