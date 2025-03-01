package kuperberg;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.DefaultFontMapper;
import com.itextpdf.text.pdf.FontMapper;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

	/**
	 * A simple demonstration showing how to write a chart to PDF format using
	 * JFreeChart and iText.
	 * <P>
	 * You can download iText from http://www.lowagie.com/iText.
	 */
	public class JFreeChartPDFWriter {

	    /**
	     * Creates a dataset, consisting of two series of monthly data. * *
	     *
	     * @return the dataset.
	     */
	    public static XYDataset createDataset() {

	        TimeSeries s1 = new TimeSeries("L&G European Index Trust");
	        s1.add(new Month(2, 2001), 181.8);
	        s1.add(new Month(3, 2001), 167.3);
	        s1.add(new Month(4, 2001), 153.8);
	        s1.add(new Month(5, 2001), 167.6);
	        s1.add(new Month(6, 2001), 158.8);
	        s1.add(new Month(7, 2001), 148.3);
	        s1.add(new Month(8, 2001), 153.9);
	        s1.add(new Month(9, 2001), 142.7);
	        s1.add(new Month(10, 2001), 123.2);
	        s1.add(new Month(11, 2001), 131.8);
	        s1.add(new Month(12, 2001), 139.6);
	        s1.add(new Month(1, 2002), 142.9);
	        s1.add(new Month(2, 2002), 138.7);
	        s1.add(new Month(3, 2002), 137.3);
	        s1.add(new Month(4, 2002), 143.9);
	        s1.add(new Month(5, 2002), 139.8);
	        s1.add(new Month(6, 2002), 137.0);
	        s1.add(new Month(7, 2002), 132.8);

	        TimeSeries s2 = new TimeSeries("L&G UK Index Trust");
	        s2.add(new Month(2, 2001), 129.6);
	        s2.add(new Month(3, 2001), 123.2);
	        s2.add(new Month(4, 2001), 117.2);
	        s2.add(new Month(5, 2001), 124.1);
	        s2.add(new Month(6, 2001), 122.6);
	        s2.add(new Month(7, 2001), 119.2);
	        s2.add(new Month(8, 2001), 116.5);
	        s2.add(new Month(9, 2001), 112.7);
	        s2.add(new Month(10, 2001), 101.5);
	        s2.add(new Month(11, 2001), 106.1);
	        s2.add(new Month(12, 2001), 110.3);
	        s2.add(new Month(1, 2002), 111.7);
	        s2.add(new Month(2, 2002), 111.0);
	        s2.add(new Month(3, 2002), 109.6);
	        s2.add(new Month(4, 2002), 113.2);
	        s2.add(new Month(5, 2002), 111.6);
	        s2.add(new Month(6, 2002), 108.8);
	        s2.add(new Month(7, 2002), 101.6);

	        TimeSeriesCollection dataset = new TimeSeriesCollection();
	        dataset.addSeries(s1);
	        dataset.addSeries(s2);

	        return dataset;
	    }

	    /**
	     * Starting point for the demo application.
	     *
	     * @param args  command line arguments (ignored).
	     */
	    public static void main(String[] args) {
	        try {
	            // create a chart...
	            XYDataset dataset = createDataset();
	            JFreeChart chart = ChartFactory.createTimeSeriesChart(
	                "Legal & General Unit Trust Prices",
	                "Date",
	                "Price Per Unit",
	                dataset,
	                true,
	                true,
	                false
	            );

	            // some additional chart customisation here...
	            XYPlot plot = (XYPlot) chart.getPlot();
	            XYLineAndShapeRenderer renderer
	                = (XYLineAndShapeRenderer) plot.getRenderer();
	            renderer.setBaseShapesVisible(true);
	            DateAxis axis = (DateAxis) plot.getDomainAxis();
	            axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));

	            // write the chart to a PDF file...
	            File fileName = new File(System.getProperty("user.home")
	                                     + "/jfreechart1.pdf");
	            saveChartAsPDF(fileName, chart, 400, 300, new DefaultFontMapper());
	        }
	        catch (IOException e) {
	                System.out.println(e.getMessage());
	        }
	    }

	    /**
	     * Saves a chart to a PDF file.
	     *
	     * @param file  the file.
	     * @param chart  the chart.
	     * @param width  the chart width.
	     * @param height  the chart height.
	     * @param mapper  the font mapper.
	     *
	     * @throws IOException if there is an I/O problem.
	     */
	    public static void saveChartAsPDF(File file,
	                                      JFreeChart chart,
	                                      int width, //TODO what's the unit? pixel?
	                                      int height) throws IOException {

	        OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
	        writeChartAsPDF(out, chart, width, height, new DefaultFontMapper());
	        out.close();
	        out = null;

	    }

	    /**
	     * Saves a chart to a PDF file.
	     *
	     * @param file  the file.
	     * @param chart  the chart.
	     * @param width  the chart width.
	     * @param height  the chart height.
	     * @param mapper  the font mapper.
	     *
	     * @throws IOException if there is an I/O problem.
	     */
	    public static void saveChartAsPDF(File file,
	                                      JFreeChart chart,
	                                      int width, //TODO what's the unit? pixel?
	                                      int height,
	                                      FontMapper mapper) throws IOException {

	        OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
	        writeChartAsPDF(out, chart, width, height, mapper);
	        out.close();
	        out = null;
	    }

	    /**
	     * Writes a chart to an output stream in PDF format.
	     *
	     * @param out  the output stream.
	     * @param chart  the chart.
	     * @param width  the chart width.
	     * @param height  the chart height.
	     * @param mapper  the font mapper.
	     *
	     * @throws IOException if there is an I/O problem.
	     */
	    public static void writeChartAsPDF(OutputStream out,
	                                       JFreeChart chart,
	                                       int width,
	                                       int height,
	                                       FontMapper mapper) throws IOException {

	        Rectangle pagesize = new Rectangle(width, height);
	        Document document = new Document(pagesize, 50, 50, 50, 50);
	        PdfWriter writer;
	        PdfContentByte cb;
	        PdfTemplate tp;
	        Graphics2D g2;
	        Rectangle2D r2D;
	        try {
	            writer = PdfWriter.getInstance(document, out);
	            document.addAuthor("JFreeChart");
	            document.addSubject("Demonstration");
	            document.open();
	            cb = writer.getDirectContent();
	            tp = cb.createTemplate(width, height);
	            g2 = tp.createGraphics(width, height, mapper);
	            r2D = new Rectangle2D.Double(0, 0, width, height);
	            chart.draw(g2, r2D);
	            g2.dispose();
	            cb.addTemplate(tp, 0, 0);
	        }catch (DocumentException de) {
	            System.err.println(de.getMessage());
	        }
	        document.close();
	        
	        pagesize = null;
	        document = null;
	        writer = null;
	        cb = null;
	        tp = null;
	        g2 = null;
	        r2D = null;
	    }
}
