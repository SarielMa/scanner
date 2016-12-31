package com.netspider;

import java.util.Vector;

public class UrlVector {

	public Vector<String> vector = new Vector<String>();

	public synchronized void enQueue(String t) {
		vector.add(t);
	}

	public synchronized String deQueue() {
		return vector.remove(0);
	}

	public boolean contains(String url) {
		return vector.contains(url);
	}

}
