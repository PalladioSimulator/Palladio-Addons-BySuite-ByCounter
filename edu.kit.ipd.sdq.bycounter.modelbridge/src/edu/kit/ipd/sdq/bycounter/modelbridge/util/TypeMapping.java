package edu.kit.ipd.sdq.bycounter.modelbridge.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.emf.common.util.EList;

import de.fzi.gast.accesses.DeclarationTypeAccess;
import de.fzi.gast.core.Root;
import de.fzi.gast.functions.Constructor;
import de.fzi.gast.functions.Function;
import de.fzi.gast.functions.Method;
import de.fzi.gast.types.GASTClass;
import de.fzi.gast.types.GASTType;
import de.fzi.gast.variables.FormalParameter;
import de.uka.ipd.sdq.ByCounter.utils.JavaType;
import de.uka.ipd.sdq.ByCounter.utils.JavaTypeEnum;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;
import edu.kit.ipd.sdq.bycounter.output.ArrayType;

/**
 * Utility functions for mapping various type representations.
 * @author Martin Krogmann
 *
 */
public class TypeMapping {
	// Field descriptor #263 I
	private static final int T_BOOLEAN = 4;
	  
	// Field descriptor #263 I
	private static final int T_CHAR = 5;
	  
	// Field descriptor #263 I
	private static final int T_FLOAT = 6;
	 
	// Field descriptor #263 I
	private static final int T_DOUBLE = 7;
	 
	// Field descriptor #263 I
	private static final int T_BYTE = 8;
	  
	// Field descriptor #263 I
	private static final int T_SHORT = 9;
	  
	// Field descriptor #263 I
	private static final int T_INT = 10;
	  
	// Field descriptor #263 I
	private static final int T_LONG = 11;

	/** A map that maps the string representation of simple java 
	 * types in the GAST to the JavaTypeEnum. */
	public static Map<String, JavaTypeEnum> gastTypeJavaTypeMap;
	
	static {
		// initialize the gastTypeJavaTypeMap
		gastTypeJavaTypeMap = new HashMap<String, JavaTypeEnum>();
		gastTypeJavaTypeMap.put("boolean", JavaTypeEnum.Boolean);
		gastTypeJavaTypeMap.put("byte", JavaTypeEnum.Byte);
		gastTypeJavaTypeMap.put("char", JavaTypeEnum.Char);
		gastTypeJavaTypeMap.put("double", JavaTypeEnum.Double);
		gastTypeJavaTypeMap.put("float", JavaTypeEnum.Float);
		gastTypeJavaTypeMap.put("int", JavaTypeEnum.Int);
		gastTypeJavaTypeMap.put("long", JavaTypeEnum.Long);
		gastTypeJavaTypeMap.put("short", JavaTypeEnum.Short);
		gastTypeJavaTypeMap.put("void", JavaTypeEnum.Void);
	}

	/**
	 * @param typeOpcode Array type opcode.
	 * @return Type in the {@link ArrayType} enum.
	 */
	public static ArrayType mapArrayType(int typeOpcode) {
		switch(typeOpcode) {
		case(T_BOOLEAN):
			return ArrayType.BOOLEAN;
		case(T_BYTE):
			return ArrayType.BYTE;
		case(T_CHAR):
			return ArrayType.CHAR;
		case(T_FLOAT):
			return ArrayType.FLOAT;
		case(T_DOUBLE):
			return ArrayType.DOUBLE;
		case(T_SHORT):
			return ArrayType.SHORT;
		case(T_INT):
			return ArrayType.INT;
		case(T_LONG):
			return ArrayType.LONG;
		default:
			return ArrayType.INVALID;
		}
	}

	/**Find the Function which belongs to the provided BytecodeCounter output string.
	 * @param bcName BytecodeCounter representation of the function name.
	 * @param availableGastRootNodes GAST root nodes to search for the name.
	 * @return GAST Function referenced by the name.
	 */
	public static Function findFunctionForString(String bcName, LinkedList<Root> availableGastRootNodes) {
		int sigStartIndex = bcName.indexOf('(');
		String fqmn = bcName.substring(0, sigStartIndex); // fully qualified method name
		String sig = bcName.substring(sigStartIndex);	  // signature part including parameters in braces and return type (== desc)
		
		String fqcn = fqmn.substring(0, fqmn.lastIndexOf('.'));	// FullyQualifiedClassName (== owner)
		String fn = fqmn.substring(fqmn.lastIndexOf('.') + 1, fqmn.length()).replace('/', '.'); // FunctionName (== name)
		// use the MethodDescriptor class to consider the details
		MethodDescriptor methodDesc = MethodDescriptor._constructMethodDescriptorFromASM(fqcn, fn, sig); 
		String fqpn = methodDesc.getPackageName(); // FullyQualifiedPackageName 
		
		for (Root node : availableGastRootNodes) {
			// find Package
			de.fzi.gast.core.Package pkg = node.getPackageByQualifiedName(fqpn);
			if (pkg != null) {
				for (GASTClass clazz : pkg.getClasses()) {
					// find Class
					if (clazz.getQualifiedName().equals(fqcn)) {
						// find and return Method/Constructor
						for (Constructor constructor : clazz.getConstructors()) {
							if (methodDesc.getSimpleMethodName().equals(constructor.getSimpleName())) {
								if(doSignaturesMatch(constructor, sig)) {
									return constructor;
								}
							}
						}
						for (Method method : clazz.getMethods()) {
							if (methodDesc.getSimpleMethodName().equals(method.getSimpleName())) {
								if(doSignaturesMatch(method, sig)) {
									return method;
								}
							}
						}
					}
				}
			}
		}
		throw new IllegalArgumentException("Could not find a method for the given BytecodeCounter name '" + bcName + "'. ");
	}

	/**
	 * @param fn GAST function to check.
	 * @param sig Signature of a method as given by ByCounter. This starts 
	 * with '(' and ends with the return type. Example: <code>()V<code> for a 
	 * method without parameters and a void return type.
	 * @return When the formal parameters and the return type of fn match the 
	 * description of sig, true is returned. False in all other cases.
	 */
	public static boolean doSignaturesMatch(Function fn, String sig) {
		EList<FormalParameter> formalParams = fn.getFormalParameters();
		DeclarationTypeAccess retTypeDec = fn.getReturnTypeDeclaration();
		JavaType retType = MethodDescriptor.getReturnTypeFromDesc(sig);
		JavaType[] paramTypes = MethodDescriptor.getParametersTypesFromDesc(sig);
		
		// compare return type
		if(retTypeDec == null) { // TODO: verify null==void?
			if(retType.getType() != JavaTypeEnum.Void) {
				return false;
			} else {
				// return types match; continue with parameter tests
			}
		} else {
			if(!doTypesMatch(retTypeDec.getTargetType(), retType)) {
				return false;
			} else {
				// return types match; continue with parameter tests
			}
		}
		
		// compare parameters
		if(formalParams.size() != paramTypes.length) {
			return false;
		}		
		if(paramTypes.length == 0) {
			// no parameters: no need to do more tests
			return true;
		} else {
			// compare all parameters
			int i = 0;
			for(FormalParameter p : formalParams) {
				if(doTypesMatch(p.getType(), paramTypes[i])) {
					i++;
					continue;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @param type GAST type
	 * @param javaType Java type as parsed by ByCounter
	 * @return True when the types match. False otherwise.
	 */
	public static boolean doTypesMatch(GASTType type, JavaType javaType) {
		String typeName = type.getQualifiedName();
		if(gastTypeJavaTypeMap.containsKey(typeName)) {
			// we have a simple java type
			if(gastTypeJavaTypeMap.get(typeName).equals(javaType.getType())) {
				return true;
			} else {
				return false;
			}
		} else {
			// gast array types are identical to the Java notation, ie. int[][]
			int arrayDim = 0;	// the dimension of the array if type is an array type.
			int ind = typeName.indexOf("[");
			String innerTypeFQCN = "";
			if(ind >= 0) {
				innerTypeFQCN = typeName.substring(0, ind);
				do {
					ind = typeName.indexOf("[", ind+2);
					arrayDim++;
				} while(ind >= 0);
				
				// check all dimensions of the array
				JavaType currentType = javaType;
				for(int i = 0; i < arrayDim; i++) {
					if(!currentType.getType().equals(JavaTypeEnum.Array)) {
						return false;
					}
					currentType = currentType.getChildElementType();
				}
				// check the inner type (int for the example above)
				if(currentType.getType().equals(JavaTypeEnum.Object)) {
					return currentType.getCanonicalClassName().equals(innerTypeFQCN);
				} else {
					if(gastTypeJavaTypeMap.containsKey(innerTypeFQCN)) {
						// we have a simple java type
						if(gastTypeJavaTypeMap.get(innerTypeFQCN).equals(currentType.getType())) {
							return true;
						} else {
							return false;
						}
					}
					throw new RuntimeException("Unknown type: " + innerTypeFQCN);
				}
			} else if(javaType.getType().equals(JavaTypeEnum.Object)) {
				if(typeName.contains("$")) {
					// assume a generic type
					return javaType.getCanonicalClassName().equals(Object.class.getCanonicalName());
				} else {
					// object type
					return typeName.equals(javaType.getCanonicalClassName());
				}
			}
			throw new RuntimeException("The type matching for this type ('" 
					+ type.getQualifiedName() + "') is not implemented yet.");
		}
	}
}
