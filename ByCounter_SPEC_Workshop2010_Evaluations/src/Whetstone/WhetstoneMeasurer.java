package Whetstone;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class WhetstoneMeasurer {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Whetstone w = new Whetstone();
		int prm1_routine;
		int prm2_xtra;
		int prm3_x100;//a multiplicative factor set to 100 in the implementation, never changed
		int prm4_calibrate;
		
		int prm3_x100_basevalue = 100;
		
		int prm1_routine_numberOfValues = 8;//for each, two datasets (i.e. 16 diagrams)
		int prm2_xtra_numberOfValues = 10;//... x-axis of the first diagram (y axis: median in ns)
		int prm3_x100_numberOfValues = 1;
		int prm4_calibrate_numberOfValues = 10;//... x-axis of the second diagram (y axis: median in ns)
		boolean prm2_xtra_increasesExponentiallyNotLinearly = false;
		boolean prm4_calibrate_increasesExponentiallyNotLinearly = false;
		int valueBase = 2;
		
		int nrOfMeasurements = 512;
		
		long[][][][] medians = new long[prm1_routine_numberOfValues][prm2_xtra_numberOfValues][prm3_x100_numberOfValues][prm4_calibrate_numberOfValues];
		
		XYSeriesCollection masterCollection = new XYSeriesCollection();

		XYSeriesCollection[] seriesCollections;
		seriesCollections = new XYSeriesCollection[prm1_routine_numberOfValues];
		JFreeChart[] charts = new JFreeChart[prm1_routine_numberOfValues];
		String whetstoneResultsDir = "WhetstoneResults";
		File f = new File(whetstoneResultsDir);
		f.mkdirs();
		long timestamp = System.currentTimeMillis();
		for(int i=1; i<=prm1_routine_numberOfValues; i++){
			XYSeries masterSeries = new XYSeries("routine "+i);
			seriesCollections[i-1] = new XYSeriesCollection();
			prm1_routine = i;
			w.setRoutine(prm1_routine);
			for(int j=0; j<prm2_xtra_numberOfValues; j++){
				if(prm2_xtra_increasesExponentiallyNotLinearly){
					prm2_xtra=new Double(Math.pow(valueBase, j)).intValue();
				}else{
					prm2_xtra=valueBase*j;
				}
				XYSeries series = new XYSeries("xtra="+prm2_xtra);
				for(int k=0; k<prm3_x100_numberOfValues; k++){//currently fixed
					prm3_x100=(k+1)*prm3_x100_basevalue/*new Double(Math.pow(powerBase, k)).intValue()*/;
					for(int m=0; m<prm4_calibrate_numberOfValues; m++){//has no influence - could be fixed to speedup the measurement
						if(prm4_calibrate_increasesExponentiallyNotLinearly){
							prm4_calibrate=new Double(Math.pow(valueBase, m)).intValue();
						}else{
							prm4_calibrate=valueBase*m;
						}
						List<Long> measurements = new ArrayList<Long>();
						for(int n=0; n<nrOfMeasurements; n++){
							long start = System.nanoTime();
							w.whetstones(prm2_xtra, prm3_x100, prm4_calibrate);
							long stopp = System.nanoTime();
							measurements.add(stopp-start);
						}
						Collections.sort(measurements);
						medians[i-1][j][k][m] = measurements.get(measurements.size()/2);
						series.add(prm4_calibrate,medians[i-1][j][k][m]);
						System.out.println(medians[i-1][j][k][m]+"=median in ns of: " +
								"routine="+prm1_routine+
								", xtra="+prm2_xtra+
								", x100="+prm3_x100+
								", calibrate="+prm4_calibrate+
								", nrOfMeasurements="+nrOfMeasurements);
						
					}
				}
				seriesCollections[i-1].addSeries(series);
				masterSeries.add(prm2_xtra, medians[i-1][j][0][prm4_calibrate_numberOfValues-1]);
			}
			masterCollection.addSeries(masterSeries);
			charts[i-1] = ChartFactory.createXYLineChart(
					("Routine is "+prm1_routine), 
					"value of calibrate", 
					"wall-clock time in ns", 
					seriesCollections[i-1], 
					PlotOrientation.VERTICAL, true, false, false);	
			try {
				ChartUtilities.writeChartAsPNG(
						new FileOutputStream(whetstoneResultsDir+File.separator+timestamp+"."+i+".png"), 
						charts[i-1], 1000, 800);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		JFreeChart masterChart = ChartFactory.createXYLineChart(
				("All routines"), 
				"value of xtra", 
				"wall-clock time in ns", 
				masterCollection, 
				PlotOrientation.VERTICAL, true, false, false);	
		try {
			ChartUtilities.writeChartAsPNG(
					new FileOutputStream(whetstoneResultsDir+File.separator+timestamp+".master.png"), 
					masterChart, 1000, 800);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
//		long start = System.nanoTime();
//		w.whetstones(prm2_xtra, prm3_x100, prm4_calibrate);
//		long stopp = System.nanoTime();
//		System.out.println(prm2_xtra+","+prm3_x100+","+prm4_calibrate+": "+(stopp-start));
	}
	

}
