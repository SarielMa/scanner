package com.netspider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;

import com.graphicaluserinterface.CheckBoxTreeNode;
import com.graphicaluserinterface.MainFrame;

public class CheckAdmin implements Runnable {

	public HashSet<String> listModel = new HashSet<String>();// ������������Ǹ����������������������������������������

	private String lineTxt;
	private String tmpUrl;
	public boolean flag = true;
	public MainFrame mainFrame;
	private String dstUrl;
	public boolean stopFlag=false;
	public HashSet<String> getlist()// ������õ����õĳ���url��list����������������������������������������
	{
		return listModel;
	}

	public CheckAdmin(String url,MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.dstUrl = url;
		this.mainFrame=mainFrame;
	}

	/**
	 * ����������ַ����Ϊ�漰���飬��ʱ��searchAdmin()����ϵͳ���뺯���ݴ�����������Ӧ�����¼�����֮��
	 */
	/*
	 * public String getDstUrl() { return (String) cb.getSelectedItem(); }
	 */

	public void run() {

		// ��ȡ����
		try {
			URL url=new URL(dstUrl);
			dstUrl=url.getHost();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dstUrl = "http://" + dstUrl;
		
		// ��ʼ���
		File file = new File("admin.txt");
		if (file.isFile() && file.exists()) {
			// �ж��ļ��Ƿ����
			InputStreamReader read = null;
			try {
				read = new InputStreamReader(new FileInputStream(file));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			BufferedReader bufferedReader = new BufferedReader(read);
			try {
				int i = 0;
				listModel.clear();
				lineTxt = bufferedReader.readLine();

				while (lineTxt != null&&!stopFlag) {
					flag = true;

					while (flag == true) {

						if (lineTxt.indexOf("zhangyang") != -1 && i == 0) {
							lineTxt = lineTxt.replaceAll("zhangyang", "asp");
						} else {
							if ((lineTxt.indexOf("asp") != -1) && i == 1) {
								lineTxt = lineTxt.replaceAll("asp", "html");
							}
						}
						// ta_bottom_right.setText(lineTxt);
						tmpUrl = dstUrl + "/" + lineTxt; // ................................
						String result = checkURL(tmpUrl);
						if (!"".equals(result)) {
							i = 0;
							listModel.add(result);
							// jsbar.setValue(jsbar.getMaximum());
							flag = false;
						} else {
							if (i < 2) {
								i++;
							} else {
								i = 0;
								flag = false;
							}
						}

						/*
						 * else{ if(i<3) { i++; } else { i=0; flag=false; } }
						 */
					}
					lineTxt = bufferedReader.readLine();
				}

			} catch (IOException e2) {

				e2.printStackTrace();
			}
		}
		
		HashSet<String> websiteOfAdmin=(HashSet<String>)this.getlist();
		mainFrame.result.setWebsiteOfAdmin(websiteOfAdmin);
		Iterator<String> itll=websiteOfAdmin.iterator();
		while(itll.hasNext())
			mainFrame.mainPan.websiteFileList.websiteOfAdmin.add(new CheckBoxTreeNode(itll.next()));
	}

	public String checkURL(String url) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		GetMethod gettMethod = new GetMethod(url);
		int statusCode = client.executeMethod(gettMethod);
		SimpleHttpConnectionManager shcm = new SimpleHttpConnectionManager();
		shcm.closeIdleConnections(statusCode);
		if (statusCode == 200 || statusCode == 403)
			return url;
		else
			return "";
	}
	public void stop(){
		stopFlag=true;
	}
	/*
	 * public void shutDown(){ flag2=false; flag=false; }
	 */
}
