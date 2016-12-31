package com.netspider;

import javax.swing.text.html.HTMLEditorKit;

public class MyParser extends HTMLEditorKit{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HTMLEditorKit.Parser getParser(){
		return super.getParser();
	}
	
}
