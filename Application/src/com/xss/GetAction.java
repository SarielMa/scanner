package com.xss;
import java.io.IOException;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class GetAction {
	private String urlString="";
	private String action="";
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public GetAction(String urlString){
		this.urlString=urlString;
	}
	
	public void action(){
		Document doc;
		try {
			doc = Jsoup.connect(urlString).get();
			Elements acs=doc.select("form[action]");
			for(Element form : acs){
				if(form.attr("abs:action")!=null)
					setAction(form.attr("abs:action"));
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}	
