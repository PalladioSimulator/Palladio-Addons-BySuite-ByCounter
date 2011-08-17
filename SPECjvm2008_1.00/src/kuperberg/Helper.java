package kuperberg;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import kuperberg.JFreeChartPDFWriter;

public class Helper {
	public static void plotDurationsHistory(
			List<Long> values, 
			String xAxis, 
			String yAxis, 
			String title, 
			String filename){
		XYSeries datasetA = new XYSeries("A");
		int i=1;
		for(Long value : values){
			datasetA.add(i,new Double(value));
			i++;
		}
		XYSeriesCollection collA = new XYSeriesCollection();
		collA.addSeries(datasetA);
		JFreeChart chartA = ChartFactory.createScatterPlot(//TODO
				title, 
				xAxis, 
				yAxis, 
				collA, 
				PlotOrientation.VERTICAL, false, false, false);
		File f = new File("results");
		f.mkdirs();
		try {
			ChartUtilities.saveChartAsPNG(
					new File("results"+File.separator+filename+".png"), 
					chartA, 800, 600);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try {
			JFreeChartPDFWriter.saveChartAsPDF(
					new File("results"+File.separator+filename+".pdf"),
					chartA, 800, 600);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
//		return null;
	}
}
