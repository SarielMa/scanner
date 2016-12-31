package com.graphicaluserinterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JToolBar;
import javax.swing.tree.TreePath;

import com.datastructure.Configuration;
import com.datastructure.Result;
import com.netspider.CheckAdmin;
import com.netspider.MySpider;
import com.xss.XssFrame;

public class MainToolBar extends JToolBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NewMain proj = null;
	private ArrayList<Configuration> list = null;
	public JButton jtb_new;
	public JButton jtb_open;
	public JButton jtb_save;
	public JButton jtb_play;
	public JButton jtb_pause;
	public JButton jtb_stop;
	public JButton jtb_help;
	boolean flag = false;
	SqlInjectionDetection sqlInjection;
	CheckAdmin ca;
	Form form;
	XssFrame xss;
	ObjectInputStream ois;
	Configuration con;
	public MainToolBar(final MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		jtb_new = new JButton(new ImageIcon("img/jtb_new.png"));
		jtb_new.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				proj = new NewMain(mainFrame);
			}
		});
		jtb_open = new JButton(new ImageIcon("img/jtb_open.png"));
		jtb_open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser openConfig=new JFileChooser();
				int result=openConfig.showOpenDialog(mainFrame);
				//如果选择打开...
				if(result==JFileChooser.APPROVE_OPTION){
					String path=openConfig.getSelectedFile().getAbsolutePath();
					try {
						File f=new File(path);
						ois=new ObjectInputStream(new FileInputStream(f));
						con=(Configuration)ois.readObject();
						CheckBoxTreeNode site=new CheckBoxTreeNode(con.getURL());
						site.setAllowsChildren(false);
						mainFrame.addConfig(con);
						mainFrame.mainPan.websiteList.root.add(site);
						//更新主窗体
						mainFrame.mainPan.websiteList.websiteListTree.updateUI();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		jtb_save = new JButton(new ImageIcon("img/jtb_save.png"));
		jtb_play = new JButton(new ImageIcon("img/jtb_play.png"));
		jtb_play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				if(mainFrame.focusWhere==0||mainFrame.focusWhere==1){
					mainFrame.focusChangeable=false;
					if (flag == false) {
						if(!mainFrame.configList.isEmpty()){
							jtb_play.setEnabled(false);
							jtb_stop.setEnabled(true);
							list = mainFrame.configList;
							Iterator<Configuration> it = list.iterator();
							TreePath path = mainFrame.mainPan.websiteList.websiteListTree
									.getSelectionPath();
							CheckBoxTreeNode start_node = (CheckBoxTreeNode) path
									.getLastPathComponent();
							String nodeName = (String) start_node.getUserObject();
							Configuration start = null;
							while (it.hasNext()) {
								start = it.next();
								if (start.getURL().equals(nodeName))
									break;
							}
							start.result=new Result();
							mainFrame.result=start.result;
							mainFrame.spider = new MySpider(start.getURL(), start
									.getScanLayer(), start.getThreadNum(), mainFrame);
							ca=new CheckAdmin(start.getURL(),mainFrame);
							Thread ttt=new Thread(ca);
							ttt.start();
						}
					} else{
						jtb_play.setEnabled(false);
						jtb_stop.setEnabled(true);
						mainFrame.spider.go.restart();
					}
				}
				if(mainFrame.focusWhere==2)	{
					HashSet<String> urlList=new HashSet<String>();
					urlList=getSelected(mainFrame.mainPan.websiteFileList.websiteOfGetTree);
					if(urlList!=null){
						sqlInjection=new SqlInjectionDetection(urlList);
					}
				}
				if(mainFrame.focusWhere==3){
					HashSet<String> urlList=new HashSet<String>();
					urlList=getSelected(mainFrame.mainPan.websiteFileList.websiteOfPostTree);
					if(urlList!=null){
						xss=new XssFrame(urlList);
					}
				}
				if(mainFrame.focusWhere==4){
					HashSet<String> urlList=new HashSet<String>();
					urlList=getSelected(mainFrame.mainPan.websiteFileList.websiteOfAdmin);
					if(urlList!=null){
						form=new Form(urlList);
					}
				}
			}
		});
		jtb_pause = new JButton(new ImageIcon("img/jtb_pause.png"));
		jtb_pause.setEnabled(false);
		jtb_stop = new JButton(new ImageIcon("img/jtb_stop.png"));
		jtb_stop.setEnabled(false);
		jtb_stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jtb_play.setEnabled(true);
				jtb_stop.setEnabled(false);
				if(mainFrame.focusWhere==0||mainFrame.focusWhere==1){
					mainFrame.spider.go.stop();
					mainFrame.spider.fresh();
					ca.stop();
					flag = false;
				}
				mainFrame.focusChangeable=true;
			}
		});
		jtb_help = new JButton(new ImageIcon("img/jtb_help.png"));

		this.add(jtb_new);
		this.add(jtb_open);
		this.add(jtb_save);
		this.addSeparator();
		this.add(jtb_play);
		this.add(jtb_pause);
		this.add(jtb_stop);
		this.addSeparator();
		this.add(jtb_help);
	}
	private HashSet<String> getSelected(CheckBoxTreeNode website){
		HashSet<String> urlList=new HashSet<String>();
		if (website.getChildCount()!= 0) {
			for(int i=0;i<website.getChildCount();i++){
				CheckBoxTreeNode cn=(CheckBoxTreeNode)website.getChildAt(i);
				if(cn.isChecked())
					urlList.add(cn.toString());
			}
			if(urlList.isEmpty())
				return null;
			else
				return urlList;
		}
		else
			return null;
	}
}
