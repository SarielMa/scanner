package com.graphicaluserinterface;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.datastructure.Configuration;
import com.datastructure.Result;
import com.netspider.MySpider;

public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<Configuration> configList=new ArrayList<Configuration>();
	public Result result;
	public MainMenuBar mainMenuBar;
	public MainToolBar mainToolBar;
	public MainPanel mainPan;
	public MySpider spider;
	public int focusWhere=0;
	public boolean focusChangeable=true;
	public MainFrame() {
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
		
		//���ڳ�ʼ��  ��С��λ�ã��Ƿ���Ըı䴰�ڴ�С
		setSize(1000, 700);
		setLocation(200, 100);
		setResizable(true);
		setTitle("WebӦ������ɨ����");
		
		//�˵���
		mainMenuBar=new MainMenuBar(this);
		setJMenuBar(mainMenuBar);
		
		//������
		mainToolBar=new MainToolBar(this);
		this.add(mainToolBar,BorderLayout.NORTH);
		
		//�����	
		mainPan=new MainPanel(this);
		this.add(mainPan,BorderLayout.CENTER);
		
		this.configList=new ArrayList<Configuration>();
		setVisible(true);
	}
	public void addConfig(Configuration config){
		this.configList.add(config);
	}
}
