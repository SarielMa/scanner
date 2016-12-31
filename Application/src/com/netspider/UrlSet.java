package com.netspider;

import java.util.HashSet;

public class UrlSet {

	public HashSet<String> set = new HashSet<String>();

	public synchronized void addUrl(String url) {
		set.add(url);
	}

	public synchronized void removeUrl(String url) {
		set.remove(url);
	}

	public boolean contains(String url) {
		return set.contains(url);
	}
}
