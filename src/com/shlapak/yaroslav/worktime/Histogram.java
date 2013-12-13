package com.shlapak.yaroslav.worktime;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;

public class Histogram extends JFrame {
 
  private static final long serialVersionUID = 1L;
  private HistogramDataset dataSet;
  private JFreeChart chart;


  public Histogram() {
	  dataSet = new HistogramDataset();
	  chart = ChartFactory.createHistogram(
              "Hystogram",
              null,
              null,
              dataSet, 
              PlotOrientation.VERTICAL,
              true,
              false,
              false
          );
  }
  
  public JFreeChart configureChart() {
    chart.setBackgroundPaint(Color.LIGHT_GRAY);
    XYPlot xyplot = (XYPlot)chart.getPlot();
    xyplot.setForegroundAlpha(1F);
    xyplot.setBackgroundPaint(Color.WHITE);
    xyplot.setDomainGridlinePaint(Color.DARK_GRAY);
    xyplot.setRangeGridlinePaint(Color.DARK_GRAY);
    XYBarRenderer xybarrenderer = (XYBarRenderer)xyplot.getRenderer();
    xybarrenderer.setShadowVisible(false);
    xybarrenderer.setBarPainter(new StandardXYBarPainter());
    return chart;
  }
  
  public <T extends Number> void fillChart(List<T> inputList, int binNumber) {
	double[] v1 = convertListToArray(inputList);
    dataSet.addSeries("Minutes", v1, binNumber, 0, 1000);
    chart = ChartFactory.createHistogram(
            "Hystogram",
            null,
            null,
            dataSet,
            PlotOrientation.VERTICAL,
            true,
            false,
            false
        );
  }
  
  private static <T extends Number> double[] convertListToArray(List<T> list) {
	  double[] array = new double[list.size()];
	  Iterator<T> it = list.iterator();
	  for(int i = 0; i < array.length; i++) {
		  array[i] = (it.next()).doubleValue();
		  System.out.print(array[i] + " ");
	  }
	  System.out.println();
	  return array;
  }
 
}
