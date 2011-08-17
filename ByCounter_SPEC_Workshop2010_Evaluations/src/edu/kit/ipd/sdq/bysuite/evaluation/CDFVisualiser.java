package edu.kit.ipd.sdq.bysuite.evaluation;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class CDFVisualiser {
	public static void main(String[] args) {
		Random rd = new Random();
		SortedSet<Double> valuesA = new TreeSet<Double>();
		SortedSet<Double> valuesB = new TreeSet<Double>();
		double newMeanA = 3.0D;
		double newMeanB = 6.0D;
		double newSdA = 2.0D;
		double newSdB = 0.5D;
		double currValue = 0D;
		double currValueA = 0D;
		double currValueB = 0D;
		double xMax = 0.0D;
		for(int i=0; i<1000; i++){
			currValue = rd.nextGaussian();
			currValueA = currValue*newSdA+newMeanA;
			if(currValueA>xMax){
				xMax = currValueA;
			}
			currValueB = currValue*newSdB+newMeanB;
			if(currValueB>xMax){
				xMax = currValueB;
			}
			if(currValueA>0){
				valuesA.add(currValueA);
			}
			if(currValueB>0){
				valuesB.add(currValueB);
			}
		}

		String common = "."+File.separator+"results"+File.separator+System.currentTimeMillis()+".";
		
		XYSeries datasetA = new XYSeries("A");
		int nrOfPosAValues = valuesA.size();
		double contribA = 1.0D/(double) nrOfPosAValues;
		SortedMap<Double,Double> mapA = new TreeMap<Double,Double>();
		double sumA=0.0D;
		mapA.put(0.0D,0.0D);
		for(Double value : valuesA){
			sumA += contribA;
			mapA.put(value, sumA);
			datasetA.add(new Double(value),new Double(sumA));
		}
		mapA.put(xMax, 1.0D);
		XYSeriesCollection collA = new XYSeriesCollection();
		collA.addSeries(datasetA);
		JFreeChart chartA = ChartFactory.createScatterPlot(
				"Response time CDF", 
				"Response time", 
				"probability", 
				collA, 
				PlotOrientation.VERTICAL, false, false, false);
		try {
			ChartUtilities.saveChartAsPNG(new File(common+"chartA.png"), chartA, 300, 200);
		} catch (IOException e) {
			e.printStackTrace();
		}	

		XYSeries datasetB = new XYSeries("B");
		int nrOfPosBValues = valuesB.size();
		double contribB = 1.0D/(double) nrOfPosBValues;
		SortedMap<Double,Double> mapB = new TreeMap<Double,Double>();
		double sumB=0.0D;
		mapB.put(0.0D,0.0D);
		for(Double value : valuesB){
			sumB += contribB;
			mapB.put(value, sumB);
			datasetB.add(new Double(value),new Double(sumB));
		}
		mapB.put(xMax, 1.0D);
		XYSeriesCollection collB = new XYSeriesCollection();
		collB.addSeries(datasetB);
		JFreeChart chartB = ChartFactory.createScatterPlot(
				"Response time CDF", 
				"Response time", 
				"probability", 
				collB,  
				PlotOrientation.VERTICAL, false, false, false);
		try {
			ChartUtilities.saveChartAsPNG(new File(common+"chartB.png"), chartB, 300, 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Chart writing finished");
	}
	

}
