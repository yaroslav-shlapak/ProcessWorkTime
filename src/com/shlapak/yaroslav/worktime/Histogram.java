package com.shlapak.yaroslav.worktime;

import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;

public class Histogram implements MyChart{

	private HistogramDataset dataSet;
	private JFreeChart chart;
	private Set<String> namesSet;

	public Histogram(){
		dataSet = new HistogramDataset();
		chart = ChartFactory.createHistogram("Hystogram", "Hours",
				"Number of appears", dataSet, PlotOrientation.VERTICAL, true,
				false, false);
		namesSet = new HashSet<String>();
		configureChart();
	}

	private void configureChart() {
		chart.setBackgroundPaint(Color.LIGHT_GRAY);
		XYPlot xyplot = (XYPlot) chart.getPlot();
		xyplot.setForegroundAlpha(1F);
		xyplot.setBackgroundPaint(Color.WHITE);
		xyplot.setDomainGridlinePaint(Color.DARK_GRAY);
		xyplot.setRangeGridlinePaint(Color.DARK_GRAY);
		XYBarRenderer xybarrenderer = (XYBarRenderer) xyplot.getRenderer();
		xybarrenderer.setShadowVisible(false);
		xybarrenderer.setBarPainter(new StandardXYBarPainter());
	}

	public JFreeChart getChart() {
		return chart;
	}

	public <T extends Number> void addSeries(String fileName, List<T> inputList, int binNumber) {
		if (!namesSet.add(fileName))
			return;
		double[] dataArray = ListToArrayConverter.convertListToArray(inputList);
		dataSet.addSeries(fileName, dataArray, binNumber, 0, 12);
		chart = ChartFactory.createHistogram("Hystogram", "Hours",
				"Number of appears", dataSet, PlotOrientation.VERTICAL, true,
				false, false);
	}

}
