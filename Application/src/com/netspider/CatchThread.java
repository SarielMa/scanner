package com.netspider;

import java.util.concurrent.locks.ReentrantLock;

public class CatchThread implements Runnable {


	public static ReentrantLock lock = new ReentrantLock();
	public MySpider mySpider;
	public CatchThread(MySpider mySpider) {
		// TODO Auto-generated constructor stub
		this.mySpider=mySpider;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String url = "";
		if (mySpider.unvisitedUrl.vector.size() == 0) {
			return;
		}
		url = mySpider.unvisitedUrl.deQueue().toString();
		if (mySpider.visitedUrl.contains(url)
				|| mySpider.blackList.contains(url)) {
			return;
		}
		mySpider.visitedUrl.addUrl(url);
		if (url == null) {
			return;
		} else {
			try {
				new ParserTool(url,mySpider).process();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				lock.lock();
				mySpider.active--;
			} finally {
				lock.unlock();
			}
		}

	}
}
