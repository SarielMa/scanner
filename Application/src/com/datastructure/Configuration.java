package com.datastructure;

import java.io.Serializable;

public class Configuration implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final boolean SIMPLEMODE=false;
	public static final boolean MASSMODE=true;
	public static final boolean DISTINCT=true;
	public static final boolean NODISTINCT=false;
	public static final boolean SUPPORT=true;
	public static final boolean NOSUPPORT=false;
	public static final boolean SIMPLECRAWLMODE=true;
	public boolean isSpidered() {
		return isSpidered;
	}
	public void setSpidered(boolean isSpidered) {
		this.isSpidered = isSpidered;
	}
	public boolean isSqlTested() {
		return isSqlTested;
	}
	public void setSqlTested(boolean isSqlTested) {
		this.isSqlTested = isSqlTested;
	}
	public boolean isFormed() {
		return isFormed;
	}
	public void setFormed(boolean isFormed) {
		this.isFormed = isFormed;
	}
	public static final boolean MASSCRAWLMODE=false;
	private boolean isSpidered;
	private boolean isSqlTested;
	private boolean isFormed;
	public Result result;
	private boolean mode;
	private int scanScale;
	private int scanLayer;
	private boolean isDistinct;
	private boolean isSupport;
	private int threadNum;
	private boolean crawlMode;
	private String URL;	
	private boolean loginMode;
	private int[] module;
	public int[] getModule() {
		return module;
	}
	public void setModule(int[] module) {
		this.module = module;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public boolean isMode() {
		return mode;
	}
	public void setMode(boolean mode) {
		this.mode = mode;
	}
	public int getScanScale() {
		return scanScale;
	}
	public void setScanScale(int scanScale) {
		this.scanScale = scanScale;
	}
	public int getScanLayer() {
		return scanLayer;
	}
	public void setScanLayer(String scanLayer) {
		Integer sl=new Integer(scanLayer);
		this.scanLayer = sl;
	}
	public boolean isDistinct() {
		return isDistinct;
	}
	public void setDistinct(boolean isDistinct) {
		this.isDistinct = isDistinct;
	}
	public boolean isSupport() {
		return isSupport;
	}
	public void setSupport(boolean isSupport) {
		this.isSupport = isSupport;
	}
	public int getThreadNum() {
		return threadNum;
	}
	public void setThreadNum(String threadNum) {
		Integer tn=new Integer(threadNum);
		this.threadNum = tn;
	}
	public boolean isCrawlMode() {
		return crawlMode;
	}
	public void setCrawlMode(boolean crawlMode) {
		this.crawlMode = crawlMode;
	}
	public boolean isLoginMode() {
		return loginMode;
	}
	public void setLoginMode(boolean loginMode) {
		this.loginMode = loginMode;
	}

}
