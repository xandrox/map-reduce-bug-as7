package com.example.mapreducebugas7;

import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.infinispan.Cache;
import org.infinispan.distexec.mapreduce.Collator;
import org.infinispan.distexec.mapreduce.MapReduceTask;

@Path("/count")
@Singleton
public class CountResource {

	@Resource(mappedName = "java:jboss/infinispan/cache/websocket/mrchache")
	private Cache<String, TestEntry> mrchache;

	@GET
	public Integer count() {
		for (int i = 0; i < 100; i++) {
			mrchache.put(UUID.randomUUID().toString(), new TestEntry(i));
		}

		MapReduceTask<String, TestEntry, String, Integer> task = new MapReduceTask<String, TestEntry, String, Integer>(
				mrchache);
		Integer count = task.mappedWith(new CountMapper()).reducedWith(new CountReducer())
				.execute(new Collator<String, Integer, Integer>() {

					@Override
					public Integer collate(Map<String, Integer> counterPrimesPerSecond) {
						Integer c = counterPrimesPerSecond.get("count");
						if (c != null) {
							return c / 60;
						} else {
							return 0;
						}
					}
				});
		return count;
	}

}
