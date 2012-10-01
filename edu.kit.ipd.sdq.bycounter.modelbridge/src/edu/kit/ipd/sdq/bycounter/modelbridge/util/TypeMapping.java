package edu.kit.ipd.sdq.bycounter.modelbridge.util;

import java.util.HashMap;
import java.util.Map;

import de.uka.ipd.sdq.ByCounter.utils.JavaTypeEnum;
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

}
