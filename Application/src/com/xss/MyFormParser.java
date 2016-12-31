package com.xss;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;

public class MyFormParser extends ParserCallback {

	protected String base;
	protected boolean isLink = false;
	protected boolean isForm = false;
	protected static String reserve = "";
	List<String> name;
	 String action;
	 List<String> value;
	 Map<String, String> nameValue=new HashMap<String,String>();
	 public MyFormParser() {
	
		 // TODO Auto-generated constructor stub
		 name=new ArrayList<String>();
		 value=new ArrayList<String>();
	 }
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public List<String> getValue() {
		return value;
	}
	public void setValue(List<String> value) {
		this.value = value;
	}
	
	public Map<String, String> getNameValue() {
		return nameValue;
	}
	//	protected MySpider mySpider;
	public void handleEndTag(HTML.Tag t, int pos) {
		if (t == HTML.Tag.A) {
			if (isLink) {
				isLink = false;
			}
		}
		if (t == HTML.Tag.FORM) {
			if (isForm) {
				isForm = false;
			}
		}
	}

	public void handleError(String errorMsg, int pos) {
	}

	public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos) {
		handleStartTag(t, a, pos);
	}

	@Override
	public void handleStartTag(Tag t, MutableAttributeSet a, int pos) {
		// TODO Auto-generated method stub
		if(t == HTML.Tag.INPUT) {
			if (a.getAttribute(HTML.Attribute.NAME) != null)
				if (a.getAttribute(HTML.Attribute.TYPE) != null){
//					if(!a.getAttribute(HTML.Attribute.TYPE).toString().equals("password")
//							&&!a.getAttribute(HTML.Attribute.TYPE).toString().equals("reset")
//							&&!a.getAttribute(HTML.Attribute.TYPE).toString().equals("submit")
//							&&!a.getAttribute(HTML.Attribute.TYPE).toString().equals("file")
//							&&!a.getAttribute(HTML.Attribute.TYPE).toString().equals("image")
//							&&!a.getAttribute(HTML.Attribute.TYPE).toString().equals("radio")
//							&&!a.getAttribute(HTML.Attribute.TYPE).toString().equals("hidden"))
					if(a.getAttribute(HTML.Attribute.TYPE).toString().equals("text"))
					{
						name.add (a.getAttribute(HTML.Attribute.NAME).toString());
						if(a.getAttribute(HTML.Attribute.VALUE)!=null)
						{
							nameValue.put(a.getAttribute(HTML.Attribute.NAME).toString(),a.getAttribute(HTML.Attribute.VALUE).toString());
						}
						else
						{
							nameValue.put(a.getAttribute(HTML.Attribute.NAME).toString(), null);
						}
					}
				}
				else{
					if(a.getAttribute(HTML.Attribute.VALUE)!=null)
					{
						name.add (a.getAttribute(HTML.Attribute.NAME).toString());
						if(a.getAttribute(HTML.Attribute.VALUE)!=null)
						{
							nameValue.put(a.getAttribute(HTML.Attribute.NAME).toString(),a.getAttribute(HTML.Attribute.VALUE).toString());
						}
						else
						{
							nameValue.put(a.getAttribute(HTML.Attribute.NAME).toString(), null);
						}
					}
				}
				
			}
		if(t == HTML.Tag.TEXTAREA)
		{
			if(a.getAttribute(HTML.Attribute.NAME)!=null)
			{
				
			}
		}
		
			if (t == HTML.Tag.FORM) {
				if(a.getAttribute(HTML.Attribute.ACTION)!=null)
				{
					action =  a.getAttribute(HTML.Attribute.ACTION).toString();
				}
			
				System.out.println("action"+action);
			}
			if(t==HTML.Tag.INPUT){
				if(a.getAttribute(HTML.Attribute.VALUE)!=null&&a.getAttribute(HTML.Attribute.TYPE)!=null)
				{
					if(a.getAttribute(HTML.Attribute.TYPE).toString().equals("submit")||a.getAttribute(HTML.Attribute.TYPE).toString().equals("Submit"))
					{
						value.add(a.getAttribute(HTML.Attribute.VALUE).toString());
					}
				}
			}
	}

}
		
