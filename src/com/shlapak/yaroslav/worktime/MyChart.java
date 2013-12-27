package com.shlapak.yaroslav.worktime;

import java.util.List;

import org.jfree.chart.JFreeChart;

public interface MyChart {
	public JFreeChart getChart() ;
	public <T extends Number> void addSeries(String fileName, List<T> inputList, int binNumber);
}
