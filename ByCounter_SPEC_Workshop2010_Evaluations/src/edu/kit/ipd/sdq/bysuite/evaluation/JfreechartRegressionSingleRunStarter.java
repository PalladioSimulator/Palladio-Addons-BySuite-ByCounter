package edu.kit.ipd.sdq.bysuite.evaluation;

import java.util.Arrays;
import java.util.Random;

public class JfreechartRegressionSingleRunStarter {
	public static void main(String[] args) {
//		JfreechartRegressionMeasurer measurer = new JfreechartRegressionMeasurer();
		Random rd = new Random();
		int numberOfSets = 1024;
		double usedA = rd.nextDouble();
		double usedB = rd.nextDouble();
		int indexOfElementToShow = rd.nextInt(numberOfSets);
		double[][] data = JfreechartRegressionMeasurer.produceRandomizedInputData(true, usedA, usedB, numberOfSets);
		double[] ret = JfreechartRegression.getOLSRegression(data);
		System.out.println("Linear regression: "+ret[0]+"*x+"+ret[1]+", " +
				"out of "+usedA+"*x+"+usedB+" with "+numberOfSets+" randomly created and distorted elements: \n"+
				"element at index "+indexOfElementToShow+": "+Arrays.toString(data[indexOfElementToShow]));
	}
}
