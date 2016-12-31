package com.xss;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetMethod {
	private String urlString="";
	private String method="";
	public String getUrlString() {
		return urlString;
	}
	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public GetMethod(String url){
		this.urlString=url;
	}
	
	public void method(){
		Document doc;
		try {
			doc = Jsoup.connect(urlString).get();
			Elements acs=doc.select("form[method]");
			for(Element form : acs){
				if(form.attr("method")!=null)
					setMethod(form.attr("method"));
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
