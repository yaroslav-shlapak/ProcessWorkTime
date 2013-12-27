package com.shlapak.yaroslav.worktime;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYStepRenderer;
import org.jfree.data.xy.XYSeriesCollection;

public class XYChart implements MyChart {
	private JFreeChart chart;
	private Set<String> namesSet;
	private XYSeriesCollection dataSet;

	public XYChart(String name) {
		// Create a simple XY chart
		dataSet = new XYSeriesHelper(name);
		// Generate the graph

		chart = ChartFactory.createXYLineChart("XY Chart", // Title
				"Index", // x-axis Label
				"Minutes", // y-axis Label
				dataSet, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				true, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);
		chart = configureChart();
		// try {
		// ChartUtilities.saveChartAsJPEG(new File("C:\\chart.jpg"), chart,
		// 500, 300);
		// } catch (IOException e) {
		// System.err.println("Problem occurred creating chart.");
		// }
	}

	private JFreeChart configureChart() {
		chart.setBackgroundPaint(Color.LIGHT_GRAY);
		XYPlot xyplot = (XYPlot) chart.getPlot();
		xyplot.setForegroundAlpha(1F);
		xyplot.setBackgroundPaint(Color.WHITE);
		xyplot.setDomainGridlinePaint(Color.DARK_GRAY);
		xyplot.setRangeGridlinePaint(Color.DARK_GRAY);

		XYStepRenderer renderer = new XYStepRenderer();
		renderer.setBaseShapesVisible(true);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));
		renderer.setSeriesStroke(1, new BasicStroke(2.0f));
		renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
		renderer.setDefaultEntityRadius(6);
		xyplot.setRenderer(renderer);
		return chart;
	}

	public JFreeChart getChart() {
		return chart;
	}

	public <T extends Number> void addSeries(String fileName,
			List<T> inputList, int binNumber) {
		if (!namesSet.add(fileName))
			return;
		double[] v1 = ListToArrayConverter.convertListToArray(inputList);
		// dataSet.addSeries(fileName, v1, binNumber, 0, 12);
		// Generate the graph
		chart = ChartFactory.createXYLineChart("XY Chart", // Title
				"Index", // x-axis Label
				"Minutes", // y-axis Label
				dataSet, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				true, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);
	}

}
