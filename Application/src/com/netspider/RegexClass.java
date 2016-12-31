package com.netspider;


import java.net.MalformedURLException;


public class RegexClass {

	public static boolean unwanted(String s,MySpider mySpider) throws MalformedURLException {
		boolean judge = false;
		String reg = "\\S+[.]((jpg)|(gif)|(bmp)|(txt)|(mp3)|(mp4)|(mv))";
		String reg1 = "\\S+[.](html)\\S+";
		String reg2 = "\\S+[.](htm)\\S+";
		String reg3 = mySpider.domain;
		if (s.indexOf(reg3) == -1) {
			return true;
		}
		int n;
		n = s.lastIndexOf('/');
		if (n < 0) {
			return false;
		}
		s = s.substring(n).substring(1);
		// s=s.substring(n+1);
		if (s.matches(reg) || s.matches(reg1) || s.matches(reg2)) {
			judge = true;
		}
		return judge;
	}





}
