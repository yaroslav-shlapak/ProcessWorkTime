package com.shlapak.yaroslav.worktime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorkTimeParser {
	private List<String> timeList;
	private List<String> dateList;
	private List<Double> timeListNum;
	private List<SimpleDate> dateListNum;
	String ls;
	WorkTimeParser(String ls) {
		timeList = new ArrayList<String>();
		timeListNum = new ArrayList<Double>();
		dateListNum = new ArrayList<SimpleDate>();
		dateList = new ArrayList<String>();
		this.ls = new String(ls);
		parseWorkTime();
	}
	
	private void parseWorkTime() {
		for(int i = 0; i < ls.length() - 9; i++) {
			if(ls.substring(i, i + 1).equals("|") && ls.substring(i + 4, i + 5).equals(":") && 
					ls.substring(i + 8, i + 9).equals("|"))
				timeList.add(ls.substring(i + 2, i + 7));
			if(ls.substring(i + 2, i + 3).equals("/") && ls.substring(i + 6, i + 7).equals("|"))
				dateList.add(ls.substring(i, i + 5));	
		}
		convertToMinutes();
		convertToDate();
	}
	
	
	private void convertToMinutes() {
		for(String e : timeList)
			timeListNum.add(Double.parseDouble(e.substring(0, 2)) + Double.parseDouble(e.substring(3, 5)) / 60);
	}
	
	private void convertToDate() {
		for(String e : dateList)
			dateListNum.add(new SimpleDate(Integer.parseInt(e.substring(0, 2)), Integer.parseInt(e.substring(3, 5))));
	}
	
	public double getMedian() {
		Collections.sort(timeListNum);
		
		return timeListNum.get(timeListNum.size() / 2);
	}
	
	public double getAverage() {
		return getSum() / timeListNum.size();
	}
	
	public List<Double> getWorkTime() {
		return timeListNum;
	}
	
	public List<SimpleDate> getWorkDate() {
		return dateListNum;
	}
	
	private double getSum() {
		double sum = 0;
		for(Double e : timeListNum) 
			sum += e;
		return sum;
	}
}
