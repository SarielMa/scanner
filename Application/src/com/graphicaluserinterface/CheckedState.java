package com.graphicaluserinterface;

import java.io.Serializable;

public class CheckedState implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url=null;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public CheckedState(String url){
		this.url=url;
	}
	public CheckedState(){
		
	}
	
	
}
