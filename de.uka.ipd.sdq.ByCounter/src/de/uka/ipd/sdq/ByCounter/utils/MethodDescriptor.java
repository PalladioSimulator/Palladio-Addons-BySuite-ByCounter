package de.uka.ipd.sdq.ByCounter.utils;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.UUID;
import java.util.logging.Logger;

import org.objectweb.asm.Type;

import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;
import de.uka.ipd.sdq.ByCounter.parsing.LineNumberRange;

/**
 * Unambiguous description for a method in bytecode compatible format. Such a 
 * signature can be constructed through the constructor <code>MethodDescriptor(..)</code>
 * and for constructor descriptions through the method <code>forConstructor(..)</code>.
 * The descriptor returned by getDescriptor() does not contain a reference to 
 * the containing class anymore.
 * 
 * @see #MethodDescriptor(String, String)
 * @author Michael Kuperberg
 * @author Martin Krogmann
 * @since 0.1
 * @version 1.2
 */
public final class MethodDescriptor implements Comparable<MethodDescriptor>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final boolean IS_COUNTED_SEPARATELY 	= false;
	public static final boolean IS_IMMEDIATELY_INLINED = true;
	public static final boolean IS_INVARIANT 			= false;
	public static final boolean IS_VARIANT 			= true;
	
	// have a static log for the class to use
	private static transient Logger log = 
		Logger.getLogger(MethodDescriptor.class.getCanonicalName());
	
	private static final char TYPE_ARRAY		= '[';
	private static final char TYPE_BOOLEAN	= 'Z';
	private static final char TYPE_BYTE		= 'B';
	private static final char TYPE_CHAR		= 'C';
	private static final char TYPE_DOUBLE		= 'D';
	private static final char TYPE_FLOAT		= 'F';
	private static final char TYPE_INT		= 'I';
	private static final char TYPE_LONG		= 'J';
	private static final char TYPE_OBJECT		= 'L';
	private static final char TYPE_SHORT		= 'S';
	private static final char TYPE_VOID		= 'V';
	
	/**
	 * Creates a new MethodDescriptor for the constructor method specified
	 * by the signature parameter.
	 * @see #MethodDescriptor(String, String)
	 * @param className The canonical name of the class declaring the constructor.
	 * @param signature The signature of the constructor. 
	 * @return The created MethodDescriptor
	 * @deprecated use {@link #forConstructor(String, String)} instead
	 */
	@Deprecated
	public static MethodDescriptor forConstructor(
			String packageName, 
			String className, 
			String signature) {
		MethodDescriptor d = new MethodDescriptor(
				packageName, 
				className, 
				signature, 
				true, 
				false, 
				false);
		d.descriptor = d.descriptor + "V";	// Java reflection treats constructors as void returning methods.
		return d;
	}
	
	/**
	 * Creates a new MethodDescriptor for the constructor method specified
	 * by the signature parameter.
	 * @see #MethodDescriptor(String, String)
	 * @param className The canonical name of the class declaring the constructor.
	 * @param signature The signature of the constructor. 
	 * @return The created MethodDescriptor
	 */
	public static MethodDescriptor forConstructor( 
			String className, 
			String signature) {
		String[] ret = canonicalClassNameToPackageAndSimpleName(className);
		MethodDescriptor d = new MethodDescriptor(
				ret[0],	// package name
				ret[1], // simple class name
				signature,
				true,
				false, 
				false);
		d.descriptor = d.descriptor + "V";	// Java reflection treats constructors as void returning methods.
		return d;
	}
	
	/**
	 * Parse a type string as written in a Java signature into a type descriptor.
	 * @param typeString String of a type definition. For example: "void".
	 * Leading or trailing whitespaces are not allowed!
	 * @return A type descriptor string representing the type defined by typeString.
	 * For example: "V" for "void".
	 */
	private static String parseType(String typeString) {
		String tStr = typeString;
		StringBuilder returnString = new StringBuilder();
		
		while (tStr.endsWith("]")) {
			returnString.append(TYPE_ARRAY);
			// cut off "[]" and parse again
			tStr = typeString.substring(0, tStr.length() - 2);
		}
		
		if (tStr.equals("boolean")) {
			returnString.append(TYPE_BOOLEAN);
		} else if (tStr.equals("byte")) {
			returnString.append(TYPE_BYTE);
		} else if (tStr.equals("char")) {
			returnString.append(TYPE_CHAR);
		} else if (tStr.equals("double")) {
			returnString.append(TYPE_DOUBLE);
		} else if (tStr.equals("float")) {
			returnString.append(TYPE_FLOAT);
		} else if (tStr.equals("int")) {
			returnString.append(TYPE_INT);
		} else if (tStr.equals("long")) {
			returnString.append(TYPE_LONG);
		} else if (tStr.equals("short")) {
			returnString.append(TYPE_SHORT);
		} else if (tStr.equals("void")) {
			returnString.append(TYPE_VOID);
		} else {
			if(!tStr.contains(".") && !tStr.contains("/")) {//TODO what about the packageless ones?
				log.warning("Signature warning " +
						"Object type (\"" + tStr + "\")"+
						"possibly not given as full canonical name.");
			}//else{
				returnString.append(TYPE_OBJECT + (tStr.replace('.', '/') + ";"));
			//}
		}
		
		return returnString.toString();
	}
	
	/**
	 * Used for parsing from a Java signature.
	 * Since generic types are not respected at bytecode signature level,
	 * remove those declarations from the signature.
	 * @param signature A Java signature.
	 * @return The cleaned signature.
	 */
	public static String removeGenericTyping(String signature) {
		String sig = signature;
		int braceLevel = 0;
		int cutStart = -1;
		int cutEnd = -1;
		while (sig.contains("<")) {
			for(int i = 0; i < sig.length(); i++) {
				if(sig.charAt(i) == '<') {
					braceLevel++;
					if(braceLevel == 1) {
						// first brace
						cutStart = i;
					}
				} else if(sig.charAt(i) == '>') {
					braceLevel--;
					if(braceLevel == 0) {
						cutEnd = i;
						// cut the part from cutStart to cutEnd out
						sig = sig.substring(0, cutStart) + sig.substring(cutEnd+1, sig.length());
						// start to search again
						i = 0;
					} else if(braceLevel < 0) {

						throw new RuntimeException(new IllegalArgumentException(
								"Error in method signature " + signature + 
								". Number of '<' does not match number of '>'"));
					}
				}
			}
		}
		return sig;
	}
	
	/**
	 * TODO
	 */
	private String className = null;
	
	/**
	 * TODO
	 */
	private UUID context = null;
	
	/**
	 * TODO
	 */
	private String descriptor = null;
	
	/**
	 * Some methods (e.g. those invariant w.r.t. bytecode counts, 
	 * irrespective of parameters, if any) may not warrant an own 
	 * CountingResult object, and their counts should be "inlined" 
	 * immediately instead.
	 */
	private boolean inlineImmediately = false;

	/**
	 * Signals whether the method underlying this MethodDescriptor is a
	 * constructor. 
	 */
	private boolean isConstructor;
	
	/**
	 * Some methods are invariant w.r.t. bytecode counts, 
	 * i.e. their runtime bytecode counts  is constant (invariant) 
	 * irrespective of method input parameters (if any).
	 */
	private boolean isInvariant = false;
	
	private String simpleMethodName = null;
	
	private String packageName = null;
	
	private LineNumberRange[] codeAreasToInstrument;
	
	/**
	 * When true, the method described is static.
	 */
	private boolean methodIsStatic;

	/**
	 * Constructs a MethodDescriptor directly from a Java reflection Constructor.
	 * @param c {@link Constructor} that is described by the MethodDescriptor.
	 * @deprecated
	 */
	public MethodDescriptor(Constructor<?> c) {
		this(
				c.getDeclaringClass().getPackage().getName(), 
				c.getDeclaringClass().getSimpleName(), 
				c.getName(), 
				Type.getConstructorDescriptor(c),
				false, //isInvariant
				false  //inlineImmediately
			);
	}
	
	/**
	 * Constructs a MethodDescriptor directly from a Java reflection Constructor.
	 * @param c {@link Constructor} that is described by the MethodDescriptor.
	 * @param isInvariant
	 * @param inlineImmediately
	 */
	public MethodDescriptor(
			Constructor<?> c, 
			boolean isInvariant, 
			boolean inlineImmediately) {
		this(
				c.getDeclaringClass().getPackage().getName(), 
				c.getDeclaringClass().getSimpleName(), 
				c.getName(), 
				Type.getConstructorDescriptor(c),
				isInvariant, 
				inlineImmediately
			);
	}
	

	/**
	 * Constructs a MethodDescriptor directly from a Java reflection Method.
	 * @param m Method that is described by the MethodDescriptor.
	 * TODO delegate to another constructor
	 */
	public MethodDescriptor(Method m) {
		Package p = m.getDeclaringClass().getPackage();
		String packageName = "";
		String simpleClassName = "";
		if(p == null) {
			// this can happen if the class was not loaded yet
			// we can parse the canonical classname though.
			String[] names = canonicalClassNameToPackageAndSimpleName(m.getDeclaringClass().getCanonicalName());
			packageName = names[0];
			simpleClassName = names[1];
		} else {
			packageName = p.getName();
			simpleClassName = m.getDeclaringClass().getSimpleName();
		}
		this.construct(
				packageName, 
				simpleClassName, 
				m.getName(), 
				Type.getMethodDescriptor(m),
				false, 
				false,
				false);
//		this(m.getDeclaringClass().getPackage().getName(), m.getDeclaringClass().getSimpleName(), m.getName(), Type.getMethodDescriptor(m));
	}
	
	/**
	 * Constructs a MethodDescriptor directly from a Java reflection Method.
	 * @param m Method that is described by the MethodDescriptor.
	 */
	public MethodDescriptor(Method m, boolean isInvariant, boolean inlineImmediately) {
		Package p = m.getDeclaringClass().getPackage();
		String packageName = "";
		if(p == null) {
			log.warning("Could not get package for method " + m);
		} else {
			packageName = p.getName();
		}
		this.construct(
				packageName, 
				m.getDeclaringClass().getSimpleName(), 
				m.getName(), 
				Type.getMethodDescriptor(m),
				false, 
				isInvariant,
				inlineImmediately);
//		this(m.getDeclaringClass().getPackage().getName(), m.getDeclaringClass().getSimpleName(), m.getName(), Type.getMethodDescriptor(m));
	}
	

	/**
	 * Construct a MethodDescriptor from a Java method signature.
	 * @param className The canonical name of the class declaring the method.
	 * @param signature A string containing a standard Java method signature 
	 * as for example:
	 * <code>public static de.uka.ipd.sdq.ByCounter.MethodDescriptor getFromJavaSignature(String sig)</code>
	 * Object types need to be specified with the full canonical name.
	 * Specifically, only the two tokens before the first '(', as well as 
	 * everything between '(' and ')' is evaluated.
	 * Whitespaces and qualifiers like "public", "static" are ignored. Generic
	 * types may be ommited (and are ignored) so that "List" and "List<Integer>"
	 * are treated as the same since bytecode signatures ignore generics.
	 * For method parameters, only one or two tokens are allowed 
	 * (example: "int[]" or "int[] abc").
	 * It is advised to take the method declaration from sourcecode or from 
	 * documentation and only adapt it, if necessary. The thing that needs to 
	 * be adapted is type names for object types. So instead of giving the String 
	 * "String myString", this has to be expanded to "java.lang.String".
	 * Note that inner/nested classes need to be specified using the '$' symbol
	 * as in the following example: <code>my.packagename.OutClass$InnerClass</code>.
	 */
	public MethodDescriptor(String className, String signature) {
		this(className,signature,false,false);
//		int i = className.lastIndexOf('.');
//		String packageN = "";
//		String classN = "";
//		if(i >= 0) {
//			packageN = className.substring(0, i);
//			classN = className.substring(i+1);
//		} else {
//			classN = className;
//		}
//		fromSignature(packageN, classN, signature, false);
	}
	
	/**
	 * Construct a MethodDescriptor from a Java method signature.
	 * @param className The canonical name of the class declaring the method.
	 * @param signature A string containing a standard Java method signature 
	 * as for example:
	 * <code>public static de.uka.ipd.sdq.ByCounter.MethodDescriptor getFromJavaSignature(String sig)</code>
	 * Object types need to be specified with the full canonical name.
	 * Specifically, only the two tokens before the first '(', as well as 
	 * everything between '(' and ')' is evaluated.
	 * Whitespaces and qualifiers like "public", "static" are ignored. Generic
	 * types may be ommited (and are ignored) so that "List" and "List<Integer>"
	 * are treated as the same since bytecode signatures ignore generics.
	 * For method parameters, only one or two tokens are allowed 
	 * (example: "int[]" or "int[] abc").
	 * It is advised to take the method declaration from sourcecode or from 
	 * documentation and only adapt it, if necessary. The thing that needs to 
	 * be adapted is type names for object types. So instead of giving the String 
	 * "String myString", this has to be expanded to "java.lang.String".
	 * Note that inner/nested classes need to be specified using the '$' symbol
	 * as in the following example: <code>my.packagename.OutClass$InnerClass</code>.
	 * @param isInvariant
	 * @param inlineImmediately
	 */
	public MethodDescriptor(String className, String signature, boolean isInvariant, boolean inlineImmediately) {
		String[] ret = canonicalClassNameToPackageAndSimpleName(className);
		String packageName = ret[0];
		String simpleClassName = ret[1];
		fromSignature(packageName, simpleClassName, signature, false, isInvariant, inlineImmediately);
	}

	/**
	 * Splits the canonical classname to the package name and the simple class
	 * name.
	 * @param className Canonical class name.
	 * @return An array of two string. The first element is the package name.
	 * The second element is the simple class name.
	 */
	public static String[] canonicalClassNameToPackageAndSimpleName(String className) {
		final int i = className.lastIndexOf('.');
		String packageN = "";
		String classN = "";
		if(i >= 0) {
			packageN = className.substring(0, i);
			classN = className.substring(i+1);
		} else {
			classN = className;
		}
		String[] ret = new  String[] {packageN, classN};
		return ret;
	}
	
	/**
	 * Construct a MethodDescriptor from a Java method signature.
	 * @param packageName The package name of the containing class.
	 * @param className The simple name of the class declaring the method.
	 * @param signature A string containing a standard Java method signature 
	 * as for example:
	 * <code>public static de.uka.ipd.sdq.ByCounter.MethodDescriptor getFromJavaSignature(String sig)</code>
	 * Object types need to be specified with the full canonical name.
	 * Specifically, only the two tokens before the first '(', as well as 
	 * everything between '(' and ')' is evaluated.
	 * Whitespaces and qualifiers like "public", "static" are ignored. Generic
	 * types may be ommited (and are ignored) so that "List" and "List<Integer>"
	 * are treated as the same since bytecode signatures ignore generics.
	 * For method parameters, only one or two tokens are allowed 
	 * (example: "int[]" or "int[] abc").
	 * It is advised to take the method declaration from sourcecode or from 
	 * documentation and only adapt it, if necessary. The thing that needs to 
	 * be adapted is type names for object types. So instead of giving the String 
	 * "String myString", this has to be expanded to "java.lang.String".
	 * Note that inner/nested classes need to be specified using the '$' symbol
	 * as in the following example: <code>my.packagename.OutClass$InnerClass</code>.
	 * @deprecated
	 */
	public MethodDescriptor(String packageName, String className, String signature) {
		this(packageName, className, signature, false, false, false);
	}
	
	/**
	 * Construct a MethodDescriptor from a Java method signature.
	 * @param packageName The package name of the containing class.
	 * @param className The simple name of the class declaring the method.
	 * @param signature A string containing a standard Java method signature 
	 * as for example:
	 * <code>public static de.uka.ipd.sdq.ByCounter.MethodDescriptor getFromJavaSignature(String sig)</code>
	 * Object types need to be specified with the full canonical name.
	 * Specifically, only the two tokens before the first '(', as well as 
	 * everything between '(' and ')' is evaluated.
	 * Whitespaces and qualifiers like "public", "static" are ignored. Generic
	 * types may be ommited (and are ignored) so that "List" and "List<Integer>"
	 * are treated as the same since bytecode signatures ignore generics.
	 * For method parameters, only one or two tokens are allowed 
	 * (example: "int[]" or "int[] abc").
	 * It is advised to take the method declaration from sourcecode or from 
	 * documentation and only adapt it, if necessary. The thing that needs to 
	 * be adapted is type names for object types. So instead of giving the String 
	 * "String myString", this has to be expanded to "java.lang.String".
	 * Note that inner/nested classes need to be specified using the '$' symbol
	 * as in the following example: <code>my.packagename.OutClass$InnerClass</code>.
	 * @param isInvariant
	 * @param inlineSystematically
	 */
	public MethodDescriptor(String packageName, String className, String signature, boolean isInvariant, boolean inlineSystematically) {
		this(packageName, className, signature, false, isInvariant, inlineSystematically);
	}
	
	/**
	 * Main constructor for MethodDescriptor.
	 * @see #MethodDescriptor(String, String)
	 * @param packageName Package name.
	 * @param className Simple class name.
	 * @param signature Signature.
	 * @param isConstructor When true, the signature is treated as the 
	 * signature of a constructor. When false, the signature is treated as the 
	 * signature of a normal method.
	 */
	private MethodDescriptor(
			String packageName, 
			String className, 
			String signature, 
			final boolean isConstructor, 
			boolean isInvariant, 
			boolean inlineImmediately) {
		String retType = "";	// initialise since this is not set for constructors 
		String simpleMethodName = null;
		ArrayList<String> argTypes = new ArrayList<String>();
		this.isConstructor = isConstructor;
		this.methodIsStatic = false;
		
		// do some error checks
		if(className == null || className.length() <= 0) {
			throw new RuntimeException(new IllegalArgumentException(
					"The classname for the methoddescriptor was not supplied."));
		}
		if(signature == null || signature.length() <= 0) {
			throw new RuntimeException(new IllegalArgumentException(
					"The signature for the methoddescriptor was not supplied."));
		}
		
		String sig = removeGenericTyping(signature);
		
		final int split1 = sig.indexOf('(');
		final int split2 = sig.lastIndexOf(')');

		// make sure we found something
		if(split1 < 0 || split2 < 0) {
			throw new RuntimeException(new IllegalArgumentException(
					"The signature supplied for the methoddescriptor does not have the correct braces '(' or ')'"));
		}

		final String returnStr = sig.substring(0, split1);	 // the first part contains the return type
		final String inBracesStr = sig.substring(split1+1, split2);	// the part in braces contains the parameters
		
		String[] tokens = null;
		tokens = returnStr.split("(\\s)+");	// split at whitespaces
		if(tokens.length < 1) {
			throw new RuntimeException(new IllegalArgumentException(
					"No tokens before '(' in signature."));
		} else {
			// the last token should be the method name.
			simpleMethodName = tokens[tokens.length-1];
			if(!isConstructor) {
				// try to parse out the return type
				if(tokens.length < 2) {
					throw new RuntimeException(new IllegalArgumentException(
							"Error parsing return type for Java signature. " +
							"Expecting at least two tokens before '(' " +
							"for non-constructor methods."));
				} else {
					// the second last token should be the type.
					// example: "public static void main"
					retType = parseType(tokens[tokens.length-2]);
				}
			}
			// look for modifiers
			for(int i = 0; i < tokens.length-2; i++) {
				if(tokens[i].equals("static")) {
					this.methodIsStatic = true;
				}
			}
		}
		
		if(inBracesStr.length() > 0) {
			// try to parse the parameters
			String[] params = inBracesStr.split(",");
			for(String s : params) {
				s = s.trim();	// remove leading and trailing whitespaces 
				tokens = s.split("(\\s)+");	// split at whitespaces
				if(tokens.length < 1 || tokens.length > 2) {
					throw new RuntimeException(new IllegalArgumentException(
						"Error parsing Java signature \"" + signature + "\": "
						+"Parameter definition does not contain 1 or 2 tokens"));
				} else {
					// if the array braces "[]" are appended to the parameter name,
					// cut them of and append them to the type string:
					String parameterName = tokens[tokens.length-1];
					String arrayBraces = "";
					int end;
					for(end = parameterName.length();
						end > 0 && parameterName.substring(0, end).endsWith("[]");
						end -= 2) {
						arrayBraces += "[]";
					}
					
					// the second last token should be the type.
					// example: "int xyz"					
					argTypes.add(parseType(tokens[tokens.length - 2] + arrayBraces));	
				}
			}
		}

		// construct the descriptor
		StringBuilder constructedDescriptor = new StringBuilder();
		constructedDescriptor.append("(");
		for(String arg : argTypes) {
			constructedDescriptor.append(arg);
		}
		constructedDescriptor.append(")");
		constructedDescriptor.append(retType);
		this.construct(packageName, 
				className, 
				simpleMethodName, 
				constructedDescriptor.toString(), 
				isConstructor, isInvariant, inlineImmediately);
	}
	
	/**
	 * Constructs a MethodDesciptor from the bytecode-compatible descriptor string.
	 * For Construction from a standard Java method signature, see the referenced
	 * constructor.
	 * @see #MethodDescriptor(String, String, String)
	 * @param packageName The package name of the containing class.
	 * @param className The simple name of the class containing the method.
	 * @param methodName The simple name of the method.
	 * @param descriptor Method descriptor string as used in Java bytecode.
	 * @deprecated
	 */
	public MethodDescriptor(String packageName, String className, String methodName, String descriptor) {
		construct(packageName, className, methodName, descriptor, false, false, false);
	}
	
	public MethodDescriptor(
			String packageName, 
			String className, 
			String methodName, 
			String descriptor, 
			boolean isInvariant, 
			boolean inlineImmediately) {
		construct(
				packageName, 
				className, 
				methodName, 
				descriptor, 
				false, 
				isInvariant, 
				inlineImmediately);
	}
	
	/**
	 * Only used for {@link #_constructMethodDescriptorFromASM(String, String, String)}
	 */
	private MethodDescriptor() {};
	
	/**
	 * Construct a {@link MethodDescriptor} instance from the details known by 
	 * ASM.<br />
	 * <b>Build for internal use only.</b>
	 * @param owner
	 * @param name
	 * @param desc
	 * @return The MethodDescriptor for the specified method.
	 */
	public static MethodDescriptor _constructMethodDescriptorFromASM(
			String owner,
			String name,
			String desc) {
		
		String fqcn = owner.replace('/', '.');	// fully qualified class name
		int packageClassSeperationPlace = fqcn.lastIndexOf('.');
		String packageName = "";
		String className;
		if(packageClassSeperationPlace >= 0) {
			packageName = fqcn.substring(0, packageClassSeperationPlace);
			className = fqcn.substring(packageClassSeperationPlace + 1);
		} else {
			className = fqcn;
		}
		

		MethodDescriptor result = new MethodDescriptor();
		boolean isConstructor = name.equalsIgnoreCase("<init>");
		
		result.construct(packageName, className, name, desc, 
				isConstructor, 
				false, false);
		
		return result;
	}
	
	
	/**
	 * @param packageName
	 * @param className
	 * @param methodName
	 * @param descriptor
	 * @deprecated
	 */
	protected void construct(
			String packageName, 
			String className, 
			String methodName, 
			String descriptor,
			boolean isConstructor) {
		this.construct(
				packageName, 
				className, 
				methodName, 
				descriptor, 
				isConstructor, 
				false, 
				false);
	}
	
	protected void construct(
			String packageName, 
			String className, 
			String methodName, 
			String descriptor,
			boolean isConstructor,  
			boolean isInvariant, 
			boolean inlineImmediately) {
		this.packageName = packageName;
		this.setSimpleClassName(className);

		if(isConstructor) {		// we have a constructor
			this.simpleMethodName = getConstructorName(this.className);
		} else {	// normal method; use the given name
			this.simpleMethodName =  methodName;
		}
		this.descriptor = descriptor;
		this.isConstructor = isConstructor;
		this.inlineImmediately = inlineImmediately;
		this.isInvariant = isInvariant;

		// For inner/nested classes, an otherwise hidden parameter 
		// (the outer class) needs to be added to the constructor:
		if(isConstructor && this.className.contains("$")) {
			int i = this.descriptor.indexOf("(");	// '(' is always in the descriptor
			int j = this.className.lastIndexOf("$");
			this.descriptor = this.descriptor.substring(0, i+1) 
							+ "L" + packageName.replace(".", "/") + "/" + this.className.substring(0, j) + ";"	// outer class 
							+ this.descriptor.substring(i+1); 
		}
	}
	
	public void setPackageName(final String packageName) {
		this.packageName = packageName;
	}

	public void setSimpleClassName(final String className) {
		this.className = className.replace(".", "$");
	}

	public static String getConstructorName(final String className) {
		int startSplitIndex = className.lastIndexOf('.')+1;
		return className.substring(startSplitIndex);
	}
	
	/**
	 * Copy hack for constructor above as Java does only allow constructors 
	 * to be called as the first thing in another constructor.
	 * @param packageName
	 * @param className
	 * @param signature
	 * @param isConstructor
	 * @deprecated
	 */
	@SuppressWarnings("unused")
	private void fromSignature(
			String packageName, 
			String className, 
			String signature, 
			boolean isConstructor) {
		fromSignature(packageName, className, signature, isConstructor, false, false);
//		MethodDescriptor d = new MethodDescriptor(packageName, className, signature, isConstructor);
//		this.packageName = d.packageName;
//		this.className = d.className;
//		this.descriptor = d.descriptor;
//		this.isConstructor = d.isConstructor;
//		this.methodName = d.methodName;
//		this.isInvariant = d.isInvariant;
//		this.inlineImmediately = d.inlineImmediately;
	}

	/**
	 * Copy hack for constructor above as Java does only allow constructors 
	 * to be called as the first thing in another constructor.
	 * @param packageName
	 * @param className
	 * @param signature
	 * @param isConstructor
	 */
	private void fromSignature(
			String packageName, 
			String className, 
			String signature, 
			boolean isConstructor, 
			boolean isInvariant, 
			boolean inlineImmediately) {
		MethodDescriptor d = new MethodDescriptor(
				packageName, 
				className, 
				signature, 
				isConstructor, 
				isInvariant, 
				inlineImmediately);
		this.packageName = d.packageName;
		this.className = d.className;
		this.descriptor = d.descriptor;
		this.isConstructor = d.isConstructor;
		this.simpleMethodName = d.simpleMethodName;
		this.isInvariant = d.isInvariant;
		this.inlineImmediately = d.inlineImmediately;
		this.methodIsStatic = d.methodIsStatic;
	}

	/**
	 * @return The canonical name of the class declaring the method.
	 */
	public String getCanonicalClassName() {
		if(this.packageName.length() > 0) {
			return this.packageName + "." + this.className;
		} else {
			return this.className;
		}
	}

	public String getClassName() {
		return this.className;
	}

	public UUID getContext() {
		return this.context;
	}
	
	/**
	 * Gets the descriptor string as used in Java bytecode for descriptor.
	 * The descriptor returned does not contain a reference to 
	 * the containing class anymore.
	 * @return The method descriptor string.
	 */
	public String getDescriptor() {
		return this.descriptor;
	}

	/**
	 * 
	 * @return True if the described method is a constructor; false otherwise.
	 */
	public boolean getIsConstructor() {
		return this.isConstructor;
	}
	
	/**
	 * @deprecated Use {@link #getSimpleMethodName()} or 
	 * {@link #getCanonicalMethodName()} instead.
	 */
	public String getMethodName() {
		return this.simpleMethodName;
	}
	
	/**
	 * Gets the simple method name of the described method.
	 * @return The method name.
	 */
	public String getSimpleMethodName() {
		return this.simpleMethodName;
	}

	/**
	 * Gets the simple method signature of the described method (i.e. without package and/or class names).
	 * @return The method signature.
	 */
	public String getMethodSignature() {
		return this.simpleMethodName+this.descriptor;
	}

	/**
	 * @return The package name.
	 */
	public String getPackageName() {
		return this.packageName;
	}

	/**
	 * Gets the qualifying method name of the described method 
	 * (yet without the descriptors, i.e. without the input/output parameters).
	 * @return The method name.
	 */
	public String getQualifyingMethodName() {
		return this.getCanonicalClassName() + "." + this.simpleMethodName;
	}

	/**
	 * Gets the qualifying method signature of the described method
	 * (i.e. with the descriptors, i.e. with the types of input/output parameters).
	 * @return The method signature.
	 */
	public String getQualifyingMethodSignature() {
		return this.getQualifyingMethodName() + this.descriptor;
	}

	/**
	 * @return The simple name of the class declaring the method.
	 */
	public String getSimpleClassName() {
		return className;
	}

	public boolean isInlineImmediately() {
		return inlineImmediately;
	}

	public boolean isInvariant() {
		return isInvariant;
	}

	public void setContext(UUID context) {
		if(this.context!=null){
			log.fine("Replacing context "+this.context+
					" with context "+context);
		}
		this.context = context;
	}

	/**
	 * 
	 */
	public String toString(){
		String ret = 
			"Package: "+this.packageName+
			"  Class: "+this.className+
			"  Method name: "+this.simpleMethodName+
			"  Descriptor: "+this.descriptor+
			"  isConstructor="+isConstructor+
			"  isInvariant="+isInvariant+
			"  inlineImmediately="+inlineImmediately;
		return ret;
	}

	/**
	 * 
	 */
	public String toString_Linebreaks(){
		String ret = 
			"Package: "+this.packageName+
			"  Class: "+this.className+"\n"+
			"Method name: "+this.simpleMethodName+
			"  Descriptor: "+this.descriptor+"\n"+
			"isConstructor="+isConstructor+
			"  isInvariant="+isInvariant+
			"  inlineImmediately="+inlineImmediately;
		return ret;
	}

	/**
	 * Check whether a method matching the given description is contained 
	 * in the list of methods to instrument and return it's index.
	 * @param listToSearch The {@link MethodDescriptor} list to search.
	 * @param canonicalClassName The fully qualified class name of the 
	 * class containing the method.
	 * @param name Simple method name (not qualified). 
	 * For example: <code>getIndexOfMethodMatch</code>.
	 * @param desc A method descriptor as used by Java bytecode. 
	 * For example: <code>(Ljava/lang/String;Ljava/lang/String;)Z</code>
	 * @return The index of the method if it matches a method description in 
	 * the {@link InstrumentationParameters}. When no matching method is found,
	 * -1 is returned.
	 */
	public static int getIndexOfMethodMatch(
			final List<MethodDescriptor> listToSearch,
			final String canonicalClassName,
			final String name, 
			final String desc) {
		if(listToSearch == null) {
			return -1;
		}
				
		String mName;
		if(name.equals("<init>")) {
			mName = getConstructorName(canonicalClassName);
		} else {
			mName = name;
		}
		for(int i = 0; i < listToSearch.size(); i++) {
			if (listToSearch.get(i).getCanonicalClassName().equals(canonicalClassName)
					&& listToSearch.get(i).getSimpleMethodName().equals(mName) 
					&& listToSearch.get(i).getDescriptor().equals(desc)) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * @param desc A parameter descriptor string as used in Java bytecode.
	 * @return An array with length=(nr of parameters) where the entry at 
	 * index i reflects the type of the parameter at index i.
	 */
	public static JavaType[] getParametersTypesFromDesc(String desc) {
		int indOpeningBrace = desc.indexOf('(');
		int indClosingBrace = desc.lastIndexOf(')');
		String croppedDesc = desc.substring(indOpeningBrace+1, indClosingBrace);
		return parseTypesFromBytecodeDesc(croppedDesc);
	}


	/**
	 * @param desc A return value descriptor string as used in Java bytecode.
	 * @return The type that of the return value.
	 */
	public static JavaType getReturnTypeFromDesc(String desc) {
		int indClosingBrace = desc.lastIndexOf(')');
		String croppedDesc = desc.substring(indClosingBrace+1);
		return parseTypesFromBytecodeDesc(croppedDesc)[0];
	}

	/**
	 * @param desc A type descriptor string as used in Java bytecode. May 
	 * either be the string of the parameter list between '(' and ')' (but 
	 * without the braces) or the return type descriptor.
	 * @return An array with length=(nr of parameters) where the entry at 
	 * index i reflects the type of the parameter at index i.
	 */
	private static JavaType[] parseTypesFromBytecodeDesc(String desc) {
		ArrayList<JavaType> types = new ArrayList<JavaType>();		
		Stack<JavaType> unfinishedTypes = new Stack<JavaType>(); // array types need more than one parse iteration
		boolean newUnfinishedType = false;
		JavaType lastParsedType = null;
		
		for(int i = 0; i < desc.length(); i++) {
			switch(desc.charAt(i)) {
			case TYPE_ARRAY:
				unfinishedTypes.push(new JavaType((JavaType)null));
				newUnfinishedType = true;
				break;
			case TYPE_BOOLEAN:
				lastParsedType = new JavaType(JavaTypeEnum.Boolean);
				break;
			case TYPE_BYTE:
				lastParsedType = new JavaType(JavaTypeEnum.Byte);
				break;
			case TYPE_CHAR:
				lastParsedType = new JavaType(JavaTypeEnum.Char);
				break;
			case TYPE_DOUBLE:
				lastParsedType = new JavaType(JavaTypeEnum.Double);
				break;
			case TYPE_FLOAT:
				lastParsedType = new JavaType(JavaTypeEnum.Float);
				break;
			case TYPE_INT:
				lastParsedType = new JavaType(JavaTypeEnum.Int);
				break;
			case TYPE_LONG:
				lastParsedType = new JavaType(JavaTypeEnum.Long);
				break;
			case TYPE_OBJECT:
				{
					// read the class name of the object
					i++;
					int iStart = i;
					do {i++;} while(desc.charAt(i) != ';');
					lastParsedType = new JavaType(desc.substring(iStart, i).replace('/', '.'));
				}
				break;
			case TYPE_SHORT:
				lastParsedType = new JavaType(JavaTypeEnum.Short);
				break;
			case TYPE_VOID:
				lastParsedType = new JavaType(JavaTypeEnum.Void);
				break;
			}

			if(!newUnfinishedType){
				// we have a non-array type
				while(!unfinishedTypes.isEmpty()) {
					// the last type is the type of the array elements
					JavaType stackedType = unfinishedTypes.pop();
					stackedType.setChildElementType(lastParsedType);
					lastParsedType = stackedType;
				}
				types.add(lastParsedType);
			} else {
				newUnfinishedType = false;
			}
		}
		return types.toArray(new JavaType[types.size()]);
	}

	/**
	 * When the specified areas are not null and not empty, only these areas 
	 * of the method will be instrumented.
	 * @see InstrumentationParameters#getUseBasicBlocks()
	 * @param codeAreasToInstrument Line numbers that define the instruction 
	 * blocks that are to be considered as range blocks.
	 */
	public void setCodeAreasToInstrument(LineNumberRange[] codeAreasToInstrument) {
		this.codeAreasToInstrument = codeAreasToInstrument;
	}
	
	/**
	 * When the specified areas are not null and not empty, only these areas 
	 * of the method will be instrumented.
	 * @see InstrumentationParameters#getUseBasicBlocks()
	 * @return Line numbers that define the instruction blocks that are 
	 * to be considered as range blocks.
	 */
	public LineNumberRange[] getCodeAreasToInstrument() {
		return codeAreasToInstrument;
	}

	/**
	 * @return True iff the method was found to be a constructor.
 	 */
	public boolean isConstructor() {
		return isConstructor;
	}

	/**
	 * @return When true, the method described is static.
	 */
	public boolean getMethodIsStatic() {
		return methodIsStatic;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(MethodDescriptor o) {
		// TODO 
		int comparisonResult = this.packageName.compareTo(o.packageName);
		if(comparisonResult!=0){
			return comparisonResult;
		}else{
			comparisonResult = this.className.compareTo(o.className);
			if(comparisonResult!=0){
				return comparisonResult;
			}else{
				comparisonResult = this.simpleMethodName.compareTo(o.simpleMethodName);
				if(comparisonResult!=0){
					return comparisonResult;
				}else{
					comparisonResult = this.descriptor.compareTo(o.descriptor);
					//TODO support code areas to instrument!
					return comparisonResult;
				}
			}
		}
	}

	/**
	 * @return A descriptor string to uniquely identify methods consisting of 
	 * the canonical classname, the method name and the method descriptor.
	 */
	public String getCanonicalMethodName() {
		return this.getCanonicalClassName()+"."+this.simpleMethodName+this.descriptor;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MethodDescriptor) {
			MethodDescriptor other = (MethodDescriptor) obj;
			if (this.className.equals(other.className)
					&& this.simpleMethodName.equals(other.simpleMethodName)
					&& this.packageName.equals(other.packageName)) {
				return true;
			} else {
				return false;
			}
		} else {
			return super.equals(obj);
		}
	}
}
