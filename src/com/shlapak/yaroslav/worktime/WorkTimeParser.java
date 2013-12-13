package com.shlapak.yaroslav.worktime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorkTimeParser {
	List<String> timeList;
	List<Integer> timeListInt;
	WorkTimeParser() {
		timeList = new ArrayList<String>();
		timeListInt = new ArrayList<Integer>();
	}
	
	public List<String> parseWorkTime(String ls) {
		for(int i = 0; i < ls.length() - 9; i++) {
			if(ls.substring(i, i + 1).equals("|") && ls.substring(i + 4, i + 5).equals(":") && 
					ls.substring(i + 8, i + 9).equals("|"))
				timeList.add(ls.substring(i + 2, i + 7));	
		}
		return timeList;
	}
	
	public List<Integer> convertToMinutes() {
		for(String e : timeList)
			timeListInt.add(Integer.parseInt(e.substring(0, 2)) * 60 + Integer.parseInt(e.substring(3, 5)));
		Collections.sort(timeListInt);
		return timeListInt;
	}
}
