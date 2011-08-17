package edu.kit.ipd.sdq.bysuite.evaluation;

import java.util.logging.Level;

public class ImportantForLogging {
	public static final Level levelFinest;
	public static final Level levelFiner;
	public static final Level levelFine;
	public static final Level levelInfo;
	public static final Level levelWarning;
	public static final Level levelSevere;
	public static final Level levelAll;
	public static final Level levelConfig;
	public static final Level levelOff;
	static{
		String message = "java.util.logging level is also set in the " +
				"logging.properties file in the top directory of " +
				"an Eclipse project; \n"+
				"this file serves as a hint on this fact, since searching for " +
				"Level.WARNING, Level.SEVERE, or Level.INFO will not return "+
				"the settings in logging.properties."; 
		System.out.println(message);
		System.err.println(message);
		levelFinest= Level.FINEST;
		levelFiner = Level.FINER;
		levelFine = Level.FINE;
		levelWarning = Level.WARNING;
		levelSevere = Level.SEVERE;
		levelInfo = Level.INFO;
		levelAll = Level.ALL;
		levelConfig = Level.CONFIG;
		levelOff = Level.OFF;
	}
}
