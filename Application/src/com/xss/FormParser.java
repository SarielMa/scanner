package com.xss;



import javax.swing.text.html.HTMLEditorKit;

public class FormParser extends HTMLEditorKit {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HTMLEditorKit.Parser getParser(){
		return super.getParser();
	}
}
