package com.sql;

import java.util.ArrayList;
import java.util.List;

public class SqlObject {//������ץȡ���������ݵĶ���
	private String tableName;
	private List<String> fieldName;
	public List<List<String>> fieldValue;
	
	public SqlObject(){
        }
	
	public SqlObject(String tableName){
		this.tableName = tableName;
		this.fieldName = new ArrayList<String>();
		this.fieldValue = new ArrayList<List<String>>();
	}
	public void setFieldName(List<String> fieldName){
		this.fieldName = fieldName;
	}
	public String getTableName(){
		return this.tableName;
	}
	public List<String> getFieldName(){
		return this.fieldName;
	}
}
