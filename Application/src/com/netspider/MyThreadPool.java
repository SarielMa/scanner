package com.netspider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadPool {

	public int num = 0;
	
	public MyThreadPool(int num) {
		this.num = num;
	}
	
	public ExecutorService getPool() {
		ExecutorService pool = Executors.newFixedThreadPool(this.num);
		return pool;
	}
}
