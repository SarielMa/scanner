package com.netspider;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class RemoveDumplicates {

	public static boolean equal1(String s1, String s2) throws IOException {
		boolean judge = false;
		URL u1 = new URL(s1);
		URL u2 = new URL(s2);
		String ss1;
		String ss2;
		ss1 = u1.getHost() + u1.getPath();
		ss2 = u2.getHost() + u2.getPath();
		if (ss1.equals(ss2)) {
			ss1 = u1.getQuery();
			ss2 = u2.getQuery();
			if (split(ss1).equals(split(ss2))) {
				return true;
			}
		}
		return judge;
	}

	public static boolean equal2(String s1,String s2) throws MalformedURLException{
		boolean judge = false;
		String ss1[]=s1.split("[?]");
		String ss2[]=s2.split("[?]");
		if(ss1[0].equals(ss2[0])){
			judge=true;
			return judge;
		}
		return judge;
	}
	public static String split(String s) {
		String tmp = "";
		String result = "";
		int n;
		int m;
		n = s.indexOf('=');
		while (n > 0) {
			tmp = s.substring(0, n);
			result += tmp;
			s = s.substring(n);
			if ((m = s.indexOf('&')) > 0) {
				s = s.substring(m + 1);
				n = s.indexOf('=');
			} else {
				break;
			}
		}
		return result;
	}
}
