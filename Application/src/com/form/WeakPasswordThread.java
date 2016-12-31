package com.form;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WeakPasswordThread implements Runnable{
	public WeakPassword wake;
	public String name;
	public String action;
	public List<String> passwordRight ;
	List<Integer> wrongLength;
	public WeakPasswordThread(WeakPassword wake,String name,String action) {
		// TODO Auto-generated constructor stub
		this.wake=wake;
		this.name=name;
		this.action=action;
		this.passwordRight=new ArrayList<String>();
	}
	public String doPost(String urlString,
			Map<String, String> nameValuePairs) throws IOException {
	
		URL url = new URL(urlString);
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		
		PrintWriter out = new PrintWriter(connection.getOutputStream());

		boolean first = true;
		
		String name;
		String value = null;
		for (Map.Entry<String, String> pair : nameValuePairs.entrySet()) {
			if (first)
				first = false;
			else
				out.print('&');
			name = pair.getKey();
			value = pair.getValue();
			out.print(name);
			out.print('=');
			out.print(URLEncoder.encode(value, "UTF-8"));
		}

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
		int lengthOfWebpage = response.length();
		wrongLength.add(lengthOfWebpage);
		if (wrongLength.size() == wake.lengthOfPasswordRecord) {

			if (wrongLength.get(0).equals(wrongLength.get(1))) {

				int index = 2;
				while (wrongLength.get(index).equals(wrongLength.get(0))) {
					index++;
					if (index == wrongLength.size())
						break;
				}
				if (index != wrongLength.size()) {
					wake.name.add(this.name);
					wake.pass.add(wake.passwordList.get(index));
					wake.isExist = true;
				}
			} else {
				if (wrongLength.get(2).equals(wrongLength.get(0))) { // get(1)dui
					wake.name.add(this.name);
					wake.pass.add(wake.passwordList.get(1));
					wake.isExist = true;
				}
				if (wrongLength.get(2).equals(wrongLength.get(1))) { // get(2)dui
					wake.name.add(this.name);
					wake.pass.add(wake.passwordList.get(0));
					wake.isExist = true;
				}

			}
			wrongLength.clear();
		}

		in.close();
		return response.toString();
	}
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		wrongLength=new ArrayList<Integer>();
		Map<String, String> post = new HashMap<String, String>();
		try {
			Iterator<String> it=wake.passwordList.iterator();
			while (it.hasNext()){
				post.put(wake.nameOfName, name);
				post.put(wake.nameOfPassword, it.next());
				wake.form.isSucceed.setText("ÕýÔÚ³¢ÊÔ£º"+name+",");		
				doPost(action, post);
			}
			wake.active--;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
