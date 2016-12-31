package com.graphicaluserinterface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JSplitPane;


public class MainPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public WebsiteList websiteList;
	public WebsiteFileList websiteFileList;
	public View view;
	public Log log;
	public JSplitPane jspLeft;
	public JSplitPane jspRight;
	public JSplitPane jspMain;
	public MainFrame mainFrame;
	public MainPanel(final MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame=mainFrame;
		
		setLayout(new BorderLayout());
		
		websiteList=new WebsiteList("网站列表",mainFrame);
		websiteFileList=new WebsiteFileList("网站文件列表",mainFrame);
		view=new View("视图");
		log=new Log("日志");
		
		jspLeft=new JSplitPane(JSplitPane.VERTICAL_SPLIT, true,websiteList, websiteFileList);
		jspLeft.setDividerLocation(300);
		jspLeft.setDividerSize(1);
		
		
		jspRight=new JSplitPane(JSplitPane.VERTICAL_SPLIT, true,view, log);
		jspRight.setDividerLocation(400);
		jspRight.setDividerSize(1);
		
		
		jspMain=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, jspLeft, jspRight);
		jspMain.setDividerLocation(200);
		jspMain.setDividerSize(1);
		
		websiteList.close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				websiteList.setVisible(false);
				mainFrame.mainMenuBar.showWebsiteList.setSelected(false);
				if(!websiteFileList.isVisible()){
					jspLeft.setVisible(false);
					MainPanel.this.updateUI();
				}
			}
		});
		
		websiteFileList.close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				websiteFileList.setVisible(false);
				mainFrame.mainMenuBar.showWebsiteFileList.setSelected(false);
				if(!websiteList.isVisible()){
					jspLeft.setVisible(false);
					MainPanel.this.updateUI();
				}
			}
		});
		
		view.close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				view.setVisible(false);
				mainFrame.mainMenuBar.showView.setSelected(false);
				if(!log.isVisible()){
					jspRight.setVisible(false);
					MainPanel.this.updateUI();
				}
			}
		});
		
		log.close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				log.setVisible(false);
				mainFrame.mainMenuBar.showLog.setSelected(false);
				if(!view.isVisible()){
					jspRight.setVisible(false);
					MainPanel.this.updateUI();
				}
			}
		});
		
		this.add(jspMain);
	}
}
