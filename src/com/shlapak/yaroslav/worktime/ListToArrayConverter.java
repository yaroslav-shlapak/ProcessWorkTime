package com.shlapak.yaroslav.worktime;

import java.util.Iterator;
import java.util.List;

final public class ListToArrayConverter {

	public static <T extends Number> double[] convertListToArray(List<T> list) {
		double[] array = new double[list.size()];
		Iterator<T> it = list.iterator();
		for (int i = 0; i < array.length; i++) {
			array[i] = (it.next()).doubleValue();
			System.out.print(array[i] + " ");
		}
		System.out.println();
		return array;
	}

}
