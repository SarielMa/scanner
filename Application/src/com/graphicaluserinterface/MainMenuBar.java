package com.graphicaluserinterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


public class MainMenuBar extends JMenuBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NewMain proj=null;
	public JCheckBoxMenuItem showWebsiteList;
	public JCheckBoxMenuItem showWebsiteFileList;
	public JCheckBoxMenuItem showView;
	public JCheckBoxMenuItem showLog;
	public JMenuItem xss;
	public MainMenuBar(final MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		/*菜单栏*/
		//菜单栏-项目
		JMenu menu_File=new JMenu("项目(F)");
		
		JMenuItem menu_FileNew=new JMenuItem("新建(N)",new ImageIcon("img/jmenu_item_newpg.png"));
		menu_FileNew.setAccelerator(KeyStroke.getKeyStroke('N', java.awt.event.InputEvent.CTRL_DOWN_MASK )); 
		menu_FileNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				proj=new NewMain(mainFrame);
			}
		});
		JMenuItem menu_FileAdd=new JMenuItem("增加站点(W)", new ImageIcon("img/jmenu_item_siteadd.png"));
		menu_FileAdd.setAccelerator(KeyStroke.getKeyStroke('W', java.awt.event.InputEvent.CTRL_DOWN_MASK ));
		JMenuItem menu_FileOpen=new JMenuItem("打开(O)", new ImageIcon("img/jmenu_item_openpg.png"));
		menu_FileOpen.setAccelerator(KeyStroke.getKeyStroke('O', java.awt.event.InputEvent.CTRL_DOWN_MASK )); 
		JMenuItem menu_FileSave=new JMenuItem("保存(S)", new ImageIcon("img/jmenu_item_save.png"));
		menu_FileSave.setAccelerator(KeyStroke.getKeyStroke('S', java.awt.event.InputEvent.CTRL_DOWN_MASK )); 
		JMenuItem menu_FileSaveas=new JMenuItem("另存为(A)", new ImageIcon("img/jmenu_item_saveas.png"));
		menu_FileSaveas.setAccelerator(KeyStroke.getKeyStroke('A', java.awt.event.InputEvent.CTRL_DOWN_MASK )); 
		menu_File.add(menu_FileNew);
		menu_File.add(menu_FileAdd);
		menu_File.add(menu_FileOpen);
		menu_File.add(menu_FileSave);
		menu_File.add(menu_FileSaveas);
		this.add(menu_File);
		//菜单栏-编辑
		JMenu menu_Edit=new JMenu("编辑(E)");
		this.add(menu_Edit);
		//菜单栏-视图
		JMenu menu_View=new JMenu("视图(V)");
		
		JMenu menu_ViewOpen=new JMenu("显示        ");
		
		showWebsiteList=new JCheckBoxMenuItem("网站列表");
		showWebsiteList.setSelected(true);
		showWebsiteList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(showWebsiteList.isSelected()){
					mainFrame.mainPan.jspLeft.setVisible(true);
					mainFrame.mainPan.websiteList.setVisible(true);	
					if(mainFrame.mainPan.jspRight.isVisible())
						mainFrame.mainPan.jspMain.setDividerLocation(200);
					if(mainFrame.mainPan.websiteFileList.isVisible())
						mainFrame.mainPan.jspLeft.setDividerLocation(300);
				}
				else{
					mainFrame.mainPan.websiteList.setVisible(false);
					if(!mainFrame.mainPan.websiteFileList.isVisible()){
						mainFrame.mainPan.jspLeft.setVisible(false);
						mainFrame.mainPan.updateUI();
					}
				}
			}
		});
		showWebsiteFileList=new JCheckBoxMenuItem("网站文件列表");
		showWebsiteFileList.setSelected(true);
		showWebsiteFileList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(showWebsiteFileList.isSelected()){
					mainFrame.mainPan.jspLeft.setVisible(true);
					mainFrame.mainPan.websiteFileList.setVisible(true);	
					if(mainFrame.mainPan.jspRight.isVisible())
						mainFrame.mainPan.jspMain.setDividerLocation(200);
					if(mainFrame.mainPan.websiteList.isVisible())
						mainFrame.mainPan.jspLeft.setDividerLocation(300);
				}
				else{
					mainFrame.mainPan.websiteFileList.setVisible(false);
					if(!mainFrame.mainPan.websiteList.isVisible()){
						mainFrame.mainPan.jspLeft.setVisible(false);
						mainFrame.mainPan.updateUI();
					}
				}
			}
		});
		showView=new JCheckBoxMenuItem("视图");
		showView.setSelected(true);
		showView.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(showView.isSelected()){
					mainFrame.mainPan.jspRight.setVisible(true);
					mainFrame.mainPan.view.setVisible(true);	
					if(mainFrame.mainPan.jspLeft.isVisible())
						mainFrame.mainPan.jspMain.setDividerLocation(200);
					if(mainFrame.mainPan.log.isVisible())
						mainFrame.mainPan.jspRight.setDividerLocation(400);
				}
				else{
					mainFrame.mainPan.view.setVisible(false);
					if(!mainFrame.mainPan.log.isVisible()){
						mainFrame.mainPan.jspRight.setVisible(false);
						mainFrame.mainPan.updateUI();
					}
				}
			}
		});
		showLog=new JCheckBoxMenuItem("日志");
		showLog.setSelected(true);
		showLog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(showLog.isSelected()){
					mainFrame.mainPan.jspRight.setVisible(true);
					mainFrame.mainPan.log.setVisible(true);	
					if(mainFrame.mainPan.jspLeft.isVisible())
						mainFrame.mainPan.jspMain.setDividerLocation(200);
					if(mainFrame.mainPan.log.isVisible())
						mainFrame.mainPan.jspRight.setDividerLocation(400);
				}
				else{
					mainFrame.mainPan.log.setVisible(false);
					if(!mainFrame.mainPan.view.isVisible()){
						mainFrame.mainPan.jspRight.setVisible(false);
						mainFrame.mainPan.updateUI();
					}
				}
			}
		});

		menu_ViewOpen.add(showWebsiteList);
		menu_ViewOpen.add(showWebsiteFileList);
		menu_ViewOpen.add(showView);
		menu_ViewOpen.add(showLog);
		
		menu_View.add(menu_ViewOpen);
		
		this.add(menu_View);
		//菜单栏-工具
		JMenu menu_Tool=new JMenu("工具(T)");
		this.add(menu_Tool);
		//菜单栏-报表
		JMenu menu_Report=new JMenu("报表(R)");
		this.add(menu_Report);
		//菜单栏-帮助
		JMenu menu_Help=new JMenu("帮助(H)");
		this.add(menu_Help);
		/*菜单栏*/
	}

}
