package com.form;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class GetAction {
	private String urlString="";
	private String action="";
	private Document doc;
	private List<String> formParameter;
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	public List<String> getFormParameter(){
		return formParameter;
	}
	public GetAction(String urlString){
		this.urlString=urlString;
		formParameter=new ArrayList<String>();
		try {
			doc=Jsoup.connect(this.urlString).get();
			action();
			parameter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void action(){
		Elements acs=doc.select("form[action]");
		for(Element form : acs){
			if(form.attr("abs:action")!=null)
				setAction(form.attr("abs:action"));
		}
	}
	public void parameter(){
		Elements acs=doc.select("input");
		
		for(Element form : acs){
			if(form.attr("type").equals("")||form.attr("type").equals("text")||form.attr("type").equals("password")){
				formParameter.add(form.attr("name"));
			}
		}
	}
}	
