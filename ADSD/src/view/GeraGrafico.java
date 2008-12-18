package view;

import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import util.Point;
import util.Util;
import util.XMLManager;

/**
 * Classe respons�vel gerar os agrumentos do gr�fico de an�lise
 * 
 * @author Everton, Diego, Leonardo
 * 
 */
public class GeraGrafico {

	public static ChartPanel getGrafico(String xName, String yName) {

		JFreeChart chart = ChartFactory.createXYLineChart("An�lise", // titulo do grafico
				xName, // eixo X
				yName, // eixo Y
				createDataset(), // dados para o gr�fico
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(chart, false);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		chartPanel.setMouseZoomable(true, false);

		return chartPanel;
	}

	/**
	 * M�todo que constrou os dados que ser�o plotados no gr�fico
	 * 
	 * @return Base de dados para o gr�fico
	 */
	private static XYDataset createDataset() {
		XMLManager xmlManager = XMLManager.getInstance();
		List<Point> points = xmlManager.getListPointXML(Util.OUTPUT);

		XYSeriesCollection series = new XYSeriesCollection();
		if (JanelaComGrafico.getArg().equals("mediaCaixa")) {
			XYSeries serie1 = new XYSeries("Perda por estouro de paci�ncia");
			for (Point p : points) {
				serie1.add(p.getX(), p.getY1());
			}
			series.addSeries(serie1);
		} else {
			XYSeries serie1 = new XYSeries("Perda por nao ter estoque");
			XYSeries serie2 = new XYSeries("Perda por validade");
			for (Point p : points) {
				serie1.add(p.getX(), p.getY1());
				serie2.add(p.getX(), p.getY2());
			}
			series.addSeries(serie1);
			series.addSeries(serie2);

		}

		return series;

	}

}
