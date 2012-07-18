package com.example.mapreducebugas7;

import org.infinispan.distexec.mapreduce.Reducer;

import java.util.Iterator;

public class CountReducer implements Reducer<String, Integer> {

	private static final long serialVersionUID = 1L;

	@Override
	public Integer reduce(String arg0, Iterator<Integer> arg1) {
		int i = 0;
		while (arg1.hasNext()) {
			Integer next = arg1.next();
			i=i + next;
		}
		return i;
	}
}
