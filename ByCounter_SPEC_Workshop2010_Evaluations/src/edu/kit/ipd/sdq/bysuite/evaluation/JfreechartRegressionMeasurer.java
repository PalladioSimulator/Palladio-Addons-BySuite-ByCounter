package edu.kit.ipd.sdq.bysuite.evaluation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.Regression;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class JfreechartRegressionMeasurer {
	private static int initialProblemSize = 1024;
	
	private static double nsPerHighResCounterTick; 
	
	private static int numberOfProblemSizeIncreases = 0;
	
	private static int numberOfWarmups = 30000;
	
	private static final sun.misc.Perf perf;
	
	private static int problemSizeIncreaseStep = 512;
	
	private static Random rd;
	
	private static int repetitionsPerProblemSize = 100;
	
	private static boolean useNanoTime = false;
	
	static{
		rd = new Random();
		perf = sun.misc.Perf.getPerf();
		nsPerHighResCounterTick = 1000000000.0D/(double) perf.highResFrequency();
	}
	
	public static void main(String[] args) {
		double[] resultsForWarmup = new double[]{0.0D,0.0D};
		int warmupProblemSize = 200;
		XYSeries linearWarmup = new XYSeries("Linear regression warmup"); // (constant problem size: "+warmupProblemSize+")");
		XYSeries powerWarmup = new XYSeries("Power regression warmup"); // (constant problem size: "+warmupProblemSize+")");
		int numberOfPrintings = 4;
		boolean testLinear = true;
		boolean testPower = false;
		warmup(numberOfWarmups, resultsForWarmup, warmupProblemSize,
				linearWarmup, powerWarmup, numberOfPrintings, testLinear,
				testPower);
		System.out.println("Warmup finished, control variables: "+Arrays.toString(resultsForWarmup));
		
		
		XYSeries linear = new XYSeries("Linear regression");
		XYSeries power = new XYSeries("Power regression");
		XYSeries linearMedian = new XYSeries("Linear regression MEDIAN");
		XYSeries powerMedian = new XYSeries("Power regression MEDIAN");
		runMeasurements(resultsForWarmup, true, testLinear, testPower,
				numberOfProblemSizeIncreases, initialProblemSize,
				problemSizeIncreaseStep, repetitionsPerProblemSize, 
				linear,
				linearMedian,
				power,
				powerMedian);
		System.out.println("Main run finished, control variables: "+Arrays.toString(resultsForWarmup));
		
		String common = "."+File.separator+"results"+File.separator+System.currentTimeMillis()+".";
		System.out.println("Linear medians: "+linearMedian.toString());//TODO toString method is unimplemented...
		for(int i=0; i<linearMedian.getItemCount(); i++){
			System.out.println(linearMedian.getX(i)+": "+linearMedian.getY(i));
		}
		
		if(testLinear){
			writeLinearCharts(linearWarmup, linear, linearMedian, common);
		}
		if(testPower){
			writePowerCharts(powerWarmup, power, powerMedian, common);
		}
		System.out.println("Finished writing charts");
	}
	
	/**
	 * TODO add facilities for plotting
	 * @param linearNotPower
	 * @param a
	 * @param b
	 * @param numberOfSets
	 * @return
	 */
	public static final double[][] produceRandomizedInputData(boolean linearNotPower, double a, double b, int numberOfSets){
		double[][] ret = new double [numberOfSets][2];
		double x, y;
		for(int i=0; i<numberOfSets; i++){
			x = rd.nextDouble()*rd.nextInt(numberOfSets/4);
			if(linearNotPower){
				y=a*x+b+rd.nextDouble()/4; 
			}else{
				y=a*Math.pow(x, b)+rd.nextDouble()/4; 
			}
			ret[i][0]=x;
			ret[i][1]=y;
		}
		return ret;
	}

	private static Long[][] runMeasurements(double[] resultsForDeadCodeEliminationPrevention, 
			boolean performMeasurements, 
			boolean testLinear,
			boolean testPower, 
			int numberOfProblemSizeIncreases,
			int initialProblemSize, 
			int problemSizeIncreaseStep,
			int repetitionsPerProblemSize, 
			XYSeries linear,
			XYSeries linearMedian,
			XYSeries power, 
			XYSeries powerMedian) {
		double[][] res;
		double[] res2;
		long start=0L;
		long stopp=0L;
		int numberOfInputs;
		Long[][] ret = new Long[numberOfProblemSizeIncreases+1][8];
		for(int i=0; i<=numberOfProblemSizeIncreases; i++){
			List<Long> linearValuesOfCurrentProblemSize = new ArrayList<Long>();
			List<Long> powerValuesOfCurrentProblemSize = new ArrayList<Long>();
			long linearSum = 0L;
			long powerSum = 0L;
			long linearDiff = 0L;
			long powerDiff = 0L;
			numberOfInputs = initialProblemSize+i*problemSizeIncreaseStep;
//			linearValuesOfCurrentProblemSize.clear();
//			powerValuesOfCurrentProblemSize.clear();
			for(int k=0; k<repetitionsPerProblemSize; k++){
				if(testLinear){
					res = produceRandomizedInputData(true,rd.nextDouble(),rd.nextDouble(),numberOfInputs);
					if(performMeasurements){
						start = timeInNs();
					}
					res2 = Regression.getOLSRegression(res);
					if(performMeasurements){
						stopp = timeInNs();
						linearDiff = (stopp-start);
						linearSum += linearDiff;
						linear.add(numberOfInputs, linearDiff);
						linearValuesOfCurrentProblemSize.add(linearDiff);
					}
					resultsForDeadCodeEliminationPrevention[0]+=res2[0];
					resultsForDeadCodeEliminationPrevention[1]+=res2[1];
				}
				if(testPower){
					res = produceRandomizedInputData(false,rd.nextDouble(),rd.nextDouble(),numberOfInputs);
					if(performMeasurements){
						start = timeInNs();
					}
					res2 = Regression.getPowerRegression(res);
					if(performMeasurements){
						stopp = timeInNs();
						powerDiff = (stopp-start);
						powerSum += powerDiff;
						power.add(numberOfInputs, powerDiff);
						powerValuesOfCurrentProblemSize.add(powerDiff);
					}
					resultsForDeadCodeEliminationPrevention[0]+=res2[0];
					resultsForDeadCodeEliminationPrevention[1]+=res2[1];
				}
			}
			Collections.sort(linearValuesOfCurrentProblemSize);
			Collections.sort(powerValuesOfCurrentProblemSize);
			if(performMeasurements && testLinear){
				ret[i][0] = linearValuesOfCurrentProblemSize.get(0);
				ret[i][1] = linearValuesOfCurrentProblemSize.get(linearValuesOfCurrentProblemSize.size()/2);
				ret[i][2] = linearSum / repetitionsPerProblemSize;
				ret[i][3] = linearValuesOfCurrentProblemSize.get(linearValuesOfCurrentProblemSize.size()-1);
			}
			if(performMeasurements && testPower){
				ret[i][4] = powerValuesOfCurrentProblemSize.get(0);
				ret[i][5] = powerValuesOfCurrentProblemSize.get(powerValuesOfCurrentProblemSize.size()/2);
				ret[i][6] = powerSum / repetitionsPerProblemSize;
				ret[i][7] = powerValuesOfCurrentProblemSize.get(powerValuesOfCurrentProblemSize.size()-1);
			}
			if(performMeasurements){
				if(testLinear) linearMedian.add(numberOfInputs,ret[i][1]);
				if(testPower) powerMedian.add(numberOfInputs,ret[i][5]);
			}
		}
		return ret;
	}

	private static long timeInNs(){
		if(useNanoTime){
			return System.nanoTime();
		}else{
			return new Double((double) perf.highResCounter()*(double) nsPerHighResCounterTick).longValue();
		}
	}

	private static void warmup(int resultsForDeadCodeEliminationPrevention, 
			double[] resultsForWarmup,
			int warmupProblemSize, 
			XYSeries linearWarmup, 
			XYSeries powerWarmup,
			int numberOfPrintings, 
			boolean testLinear, 
			boolean testPower) {
		double[][] res;
		double[] res2;
		long start;
		long stopp;
		int currProblemSize;
		for(int i=0; i<resultsForDeadCodeEliminationPrevention; i++){
			currProblemSize = warmupProblemSize+rd.nextInt(warmupProblemSize);
			int outlierThreasholdInNs = 100000;
			if(testLinear){
				res = produceRandomizedInputData(true,rd.nextDouble(),rd.nextDouble(),
						currProblemSize);
				if(i<numberOfPrintings) System.out.println(Arrays.toString(res));
				start = timeInNs();
				res2 = Regression.getOLSRegression(res);
				stopp = timeInNs();
				if(i<numberOfPrintings) System.out.println(Arrays.toString(res2));
				if((stopp-start)<outlierThreasholdInNs){
					linearWarmup.add(currProblemSize, (stopp-start));
				}else{
					System.out.println("OUTLIER: "+currProblemSize+":"+(stopp-start));
				}
				resultsForWarmup[0]+=res2[0];
				resultsForWarmup[1]+=res2[1];
			}
			if(testPower){
				res = produceRandomizedInputData(false,rd.nextDouble(),rd.nextDouble(),
						currProblemSize);
				if(i<numberOfPrintings) System.out.println(Arrays.toString(res));
				start = timeInNs();
				res2 = Regression.getPowerRegression(res);
				stopp = timeInNs();
				if(i<numberOfPrintings) System.out.println(Arrays.toString(res2));
				if((stopp-start)<outlierThreasholdInNs){
					powerWarmup.add(currProblemSize, (stopp-start));
				}else{
					System.out.println("OUTLIER: "+currProblemSize+":"+(stopp-start));
				}
				resultsForWarmup[0]+=res2[0];
				resultsForWarmup[1]+=res2[1];
			}
		}
	}

	private static void writeLinearCharts(XYSeries linearWarmup,
			XYSeries linear, XYSeries linearMedian, String common) {
		XYSeriesCollection datasetLinearWarmup = new XYSeriesCollection();
		datasetLinearWarmup.addSeries(linearWarmup);
		XYSeriesCollection datasetLinear = new XYSeriesCollection();
		datasetLinear.addSeries(linear);
		datasetLinear.addSeries(linearMedian);
		XYSeriesCollection datasetLinearMedians = new XYSeriesCollection();
		datasetLinearMedians.addSeries(linearMedian);
//			linearMedian.getRenderer
		JFreeChart linearWarmupChart = ChartFactory.createScatterPlot(
				"Linear regression: warmup", 
				"problem size", 
				"duration in ns", 
				datasetLinearWarmup, 
				PlotOrientation.VERTICAL, false, false, false);
		
		JFreeChart linearChart = ChartFactory.createXYLineChart(
				"Linear regression", 
				"problem size", 
				"duration in ns", 
				datasetLinear, 
				PlotOrientation.VERTICAL, false, false, false);
//		XYPlot plot = (XYPlot) linearChart.getPlot();
//			XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) 
//		XYItemRenderer renderer = plot.getRenderer(); 
//			renderer.setsetShapesVisible(true); 
//			renderer.setShapesFilled(true);
		
		
//			XYLineAndShapeRenderer renderer3 = new XYLineAndShapeRenderer();
//	        renderer.setSeriesLinesVisible(0, true);
//	        renderer.setSeriesShapesVisible(0, false);
//	        renderer.setSeriesLinesVisible(1, false);
//	        renderer.setSeriesShapesVisible(1, true); 
//
//	        XYItemRenderer renderer2 = new XYLineAndShapeRenderer();
//	        plot.setRenderer(renderer2);
		JFreeChart linearChartMedian = ChartFactory.createScatterPlot(
				"Linear regression (MEDIANS)", 
				"problem size", 
				"duration in ns", 
				datasetLinearMedians, 
				PlotOrientation.VERTICAL, false, false, false);
//			XYPlot plot = (XYPlot) linearChart.getPlot();
//			XYItemRenderer renderer = plot.getRenderer(1);
//			renderer.set TODO make lines visible
		try {
			ChartUtilities.saveChartAsPNG(new File(common+"linearRegressionWarmup.png"), linearWarmupChart, 1024, 768);
			ChartUtilities.saveChartAsPNG(new File(common+"linearRegression.png"), linearChart, 1024, 768);
			ChartUtilities.saveChartAsPNG(new File(common+"linearRegressionMedian.png"), linearChartMedian, 1024, 768);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writePowerCharts(XYSeries powerWarmup, XYSeries power,
			XYSeries powerMedian, String common) {
		XYSeriesCollection datasetPowerWarmup = new XYSeriesCollection();
		XYSeriesCollection datasetPower = new XYSeriesCollection();
		datasetPowerWarmup.addSeries(powerWarmup);
		datasetPower.addSeries(power);
		datasetPower.addSeries(powerMedian);
		JFreeChart powerWarmupChart = ChartFactory.createScatterPlot("Power regression: warmup", "problem size", "duration in ns", datasetPowerWarmup, PlotOrientation.VERTICAL, false, false, false);
		JFreeChart powerChart = ChartFactory.createScatterPlot("Power regression", "problem size", "duration in ns", datasetPower, PlotOrientation.VERTICAL, false, false, false);
		try {
			ChartUtilities.saveChartAsPNG(new File(common+"powerRegressionWarmup.png"), powerWarmupChart, 1024, 768);
			ChartUtilities.saveChartAsPNG(new File(common+"powerRegression.png"), powerChart, 1024, 768);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
