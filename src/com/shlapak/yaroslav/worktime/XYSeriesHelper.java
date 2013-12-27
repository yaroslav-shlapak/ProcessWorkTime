package com.shlapak.yaroslav.worktime;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class XYSeriesHelper extends XYSeriesCollection {
	private XYSeries series;
	
	public XYSeriesHelper(String name) {
		series = new XYSeries(name);
		// Add the series to your data set
		addSeries(series);
	}



}
