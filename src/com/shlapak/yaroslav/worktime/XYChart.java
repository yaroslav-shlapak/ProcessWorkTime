package com.shlapak.yaroslav.worktime;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class XYChart {
	XYSeriesCollection dataset;
	XYSeries series;
	public XYChart() {
		// Create a simple XY chart
		dataset = new XYSeriesCollection();
		series = new XYSeries("XYGraph");
		// Add the series to your data set
		dataset.addSeries(series);
		// Generate the graph
		JFreeChart chart = ChartFactory.createXYLineChart("XY Chart", // Title
				"x-axis", // x-axis Label
				"y-axis", // y-axis Label
				dataset, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				true, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);
		try {
			ChartUtilities.saveChartAsJPEG(new File("C:\\chart.jpg"), chart,
					500, 300);
		} catch (IOException e) {
			System.err.println("Problem occurred creating chart.");
		}
	}
}
