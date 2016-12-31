package com.netspider;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.text.html.HTMLEditorKit;

public class ParserTool {

	String urlStr;
	MySpider mySpider;
	public ParserTool(String url,MySpider mySpider) {
		this.urlStr = url;
		this.mySpider=mySpider;
	}

	public void process() {
		URL url = null;
		HttpURLConnection conn = null;
		try {
			url = new URL(this.urlStr);
			conn = (HttpURLConnection) url.openConnection();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			mySpider.blackList.addUrl(url.toString());
			return;
		}
		try {
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				mySpider.blackList.addUrl(url.toString());
				return;
			}
			InputStream is;
			is = conn.getInputStream();
			String changed=conn.toString().substring(conn.toString().indexOf(":")+1);
			if(changed.equals(this.urlStr)){
				HTMLEditorKit.Parser parser = new MyParser().getParser();
				MyParserCallback p = new MyParserCallback(this.urlStr,this.mySpider);
				parser.parse(new BufferedReader(new InputStreamReader(is)), p, true);
			}
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
