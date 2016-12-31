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
		
		
		//将默认窗口风格设置为WINDOWS风格
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
		
		//窗口初始化  大小，位置，是否可以改变窗口大小
		setSize(1000, 700);
		setLocation(200, 100);
		setResizable(true);
		setTitle("Web应用弱点扫描器");
		
		//菜单栏
		mainMenuBar=new MainMenuBar(this);
		setJMenuBar(mainMenuBar);
		
		//工具栏
		mainToolBar=new MainToolBar(this);
		this.add(mainToolBar,BorderLayout.NORTH);
		
		//主面板	
		mainPan=new MainPanel(this);
		this.add(mainPan,BorderLayout.CENTER);
		
		this.configList=new ArrayList<Configuration>();
		setVisible(true);
	}
	public void addConfig(Configuration config){
		this.configList.add(config);
	}
}
