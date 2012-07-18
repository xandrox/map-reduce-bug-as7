package com.example.mapreducebugas7;

import org.infinispan.distexec.mapreduce.Collector;
import org.infinispan.distexec.mapreduce.Mapper;

/**
 * @author Sandro Sonntag
 */
public class CountMapper implements Mapper<String, TestEntry, String, Integer> {

	private static final long serialVersionUID = 1L;

	@Override
	public void map(String key, TestEntry value, Collector<String, Integer> collector) {
		collector.emit("count", value.getAmount());
	}
}
