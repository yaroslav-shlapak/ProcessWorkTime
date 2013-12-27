package com.shlapak.yaroslav.worktime;

import java.awt.BasicStroke;

import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.renderer.xy.XYStepRenderer;

public class TestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XYStepRenderer renderer = new XYStepRenderer();
		  renderer.setBaseShapesVisible(true);
		  renderer.setSeriesStroke(0, new BasicStroke(2.0f));
		  renderer.setSeriesStroke(1, new BasicStroke(2.0f));
		  renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
		  renderer.setDefaultEntityRadius(6);
		  //plot.setRenderer(renderer);
		  
		  String[] grade =  new String[3];
		  grade[0] = "";
		  grade[1] = "Status A";
		  grade[2] = "Status B";
		  SymbolAxis rangeAxis = new SymbolAxis("", grade);
		  rangeAxis.setTickUnit(new NumberTickUnit(1));
		  rangeAxis.setRange(0,3);
		  //plot.setRangeAxis(rangeAxis);

	}

}
