package com.xss;


import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLEditorKit;


class PostTestFrame extends Thread{
	
	private int count=0;
	private XssFrame testFrame=null;
	private String urlString="";
	private String methodString="";
	

	public  String doPost(String urlString, Map<String, String> nameValuePairs)
     {
	try {
		System.out.println(urlString);
		URL url;
		url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(methodString);
		connection.setDoOutput(true);
		PrintWriter out = new PrintWriter(connection.getOutputStream());
		boolean first = true;
		if(nameValuePairs.size()!=0)
		{
			for (Map.Entry<String, String> pair : nameValuePairs.entrySet())
			{
				if (first) first = false;
				else out.print('&');
				String name = pair.getKey();
				String value = pair.getValue();
				out.print(name);
				out.print('=');
				//        out.print(URLEncoder.encode(value,"utf-8"));
				out.print(value);
				System.out.print(name);
				System.out.print('=');
				try {
					System.out.println(URLEncoder.encode(value,"utf-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return urlString;
				}
			}
		}
		out.close();
		Scanner in;
		StringBuilder response = new StringBuilder();
		
		try
		{
			in = new Scanner(connection.getInputStream());
			while (in.hasNextLine())
			{
				response.append(in.nextLine());
				response.append("\n");
			}
			in.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return urlString;
		}
		return response.toString();
	} catch (MalformedURLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		return urlString;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return urlString;
	}
   }


	public PostTestFrame(String url, XssFrame testFrame2) {
		// TODO Auto-generated constructor stub
		this.testFrame=testFrame2;
		this.urlString=url;
	}

	public void run(){
		URL url = null;
		HttpURLConnection conn = null;
		InputStream is;
		String abcd = "";
		String aa = null;
		try {
			String str = urlString;
			// String str="http://bj.58.com/";

			int ii = str.lastIndexOf(0x2f);
			if(ii<10)
			{
				str=str+"/";
			}
			ii=str.lastIndexOf(0x2f);
			aa = str.substring(0, ii + 1);
			System.out.println(aa);
			url = new URL(str);
			conn = (HttpURLConnection) url.openConnection();
			is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			HTMLEditorKit.Parser parser = new FormParser().getParser();
			MyFormParser p = new MyFormParser();

			parser.parse(br, p, true);

			List<String> form = p.getName();
			// Iterator<String> it=form.iterator();
			List<String> value = p.getValue();
			// Iterator<String> it_va=value.iterator();
			Map<String, String> nameValue = p.getNameValue();
			if(value.size()<form.size())
			{
				for(int i=0;i<form.size()-value.size();i++)
				{
					value.add("未命名");
				}
			}
			System.out.println("HOST"+url.getHost());
			GetMethod method=new GetMethod(str);
			method.method();
			System.out.println("method"+method.getMethod());
			methodString=method.getMethod();
			methodString=methodString.toUpperCase();
			System.out.println(methodString);
			
			GetAction action = new GetAction(str);
			action.action();
			abcd = action.getAction();
			String urlString = "";
			if (abcd != null) {
				urlString = abcd;
			} else {
				urlString = str;
			}
			System.out.println("abcd=" + abcd);
			
			
			
			String injection = "<script>alert(\"ttttt\")</script>";
//			 String injection="aaaaaaaa";
			Map<String, String> xss=new HashMap<String, String>();
			final Map<String, String> post = nameValue;
			testFrame.getJtable().setModel(testFrame.getMod());
			for (int i = 0; i < form.size(); i++) {
				post.put(form.get(i), injection);
				if(post.size()!=0)
				{
					String result = doPost(urlString, post);
					if (result.indexOf(injection) != -1) {
						count++;
						xss.put(value.get(i).toString(), injection);
					}
				}
			}
			if(count==0)
			{
				testFrame.getTextField2().setText("否");
			}
			else
			{
				testFrame.getTextField2().setText("是");
			}
			testFrame.getTextField3().setText(Integer.toString(count));
			for(Map.Entry<String, String> name_re : xss.entrySet())
			{
				Object ob[] = {name_re.getKey(), name_re.getValue()};
				testFrame.getMod().addRow(ob);
			}
			testFrame.getJtable().setModel(testFrame.getMod());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(testFrame, "链接超时", "提示", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(testFrame, "链接超时", "提示", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
	}
}

