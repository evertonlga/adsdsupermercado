package util;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GeraGrafico {

	public static ChartPanel getGrafico(String xName, String yName){
		
		JFreeChart chart = ChartFactory.createXYLineChart
		                     ("Analise", // titulo do grafico
		                      xName, // eixo X
		                      yName,            // eixo Y
		                      createDataset(),     // dados para o gráfico
		                      PlotOrientation.VERTICAL,
		                      true,
		                      true,
		                      false);
		
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setMouseZoomable(true, false);
	
		return chartPanel;
	}
	
	
	  private static XYDataset createDataset() {
		  XMLManager xmlManager = XMLManager.getInstance();
		  List<Point> points = xmlManager.getListPointXML(Util.OUTPUT);
		  
		  XYSeries serie = new XYSeries("");
		  for (Point p : points){
			  serie.add(p.getY(), p.getX());
		  }
//		  List<Integer> pontos = new ArrayList<Integer>();
//		  	pontos.add(15);
//		  	pontos.add(2);
//		  	pontos.add(125);
//	        TimeSeries s1 = new TimeSeries("Perdas");
//	        s1.add(null, pontos.get(0));
//	        s1.add(null, pontos.get(1));
//	        s1.add(null, pontos.get(2));
//	        s1.add(new Month(2, 2005), 181.8);
//	        s1.add(new Month(3, 2005), 167.3);
//	        s1.add(new Month(4, 2005), 153.8);
//	        s1.add(new Month(5, 2005), 167.6);
//	        s1.add(new Month(6, 2005), 158.8);

//	        TimeSeries s2 = new TimeSeries("Frísias", Month.class);
//	        s2.add(new Month(1, 2005), 129.6);
//	        s2.add(new Month(2, 2005), 129.6);
//	        s2.add(new Month(3, 2005), 123.2);
//	        s2.add(new Month(4, 2005), 117.2);
//	        s2.add(new Month(5, 2005), 124.1);
//	        s2.add(new Month(6, 2005), 122.6);

//	        TimeSeriesCollection dataset = new TimeSeriesCollection();
//	        dataset.addSeries(s1);
//	        dataset.addSeries(s2);
//	        dataset.setDomainIsPointsInTime(true);
	        
		  	return new XYSeriesCollection(serie);

	    }	

}
