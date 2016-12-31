package com.graphicaluserinterface;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

import com.datastructure.Configuration;

public class Popup extends JPopupMenu{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem popup_Edit=new JMenuItem("编辑项目");
	private JMenuItem popup_SelectAll=new JMenuItem("全选");
	private JMenuItem popup_SelectNo=new JMenuItem("全不选");
	private JMenuItem popup_Copy=new JMenuItem("复制地址");
	private JMenuItem popup_Open=new JMenuItem("用浏览器打开");
	private JMenuItem popup_Start=new JMenuItem("开始");
	private JMenuItem popup_Pause=new JMenuItem("暂停");
	private JMenuItem popup_Stop=new JMenuItem("停止");
	private JMenuItem popup_Delete=new JMenuItem("删除项目");
	private ArrayList<Configuration> list=null;
	public JTree websiteListTree;
	public TreePath path=null;
	public CheckBoxTreeNode root;
	public MainFrame mainFrame;
	public Popup(JTree websiteListTree,TreePath path,CheckBoxTreeNode root,MainFrame mainFrame)
	{
		this.websiteListTree=websiteListTree;
		this.path=path;
		this.root=root;
		this.mainFrame=mainFrame;
		form();
	}
	public void form()
	{
		this.add(popup_Edit);
		this.add(new JSeparator());
		this.add(popup_SelectAll);
		this.add(popup_SelectNo);
		this.add(popup_Copy);
		this.add(popup_Open);
		this.add(new JSeparator());
		this.add(popup_Start);
		this.add(popup_Pause);
		this.add(popup_Stop);
		this.add(new JSeparator());
		this.add(popup_Delete);
		
		//编辑
		popup_Edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String input=JOptionPane.showInputDialog(websiteListTree,"请输入网址","编辑项目",JOptionPane.PLAIN_MESSAGE);
				if(input!=null)
				{
					String regex = "^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
					+ "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
					+ "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
					+ "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
					+ "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
					+ "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
					+ "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
					+ "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$";
					boolean flag=input.matches(regex);
					if(flag)
					{
						CheckBoxTreeNode node=(CheckBoxTreeNode) path.getLastPathComponent();
						
						list=mainFrame.configList;
						Iterator<Configuration> it=list.iterator();
						while(it.hasNext()){
							if(it.next().getURL().equals((String)node.getUserObject()))
								break;
						}
						it.next().setURL(input);
						node.setUserObject(input);
						websiteListTree.updateUI();
					}
					else
					{
						ImageIcon joptionIcon=new ImageIcon("img/info_red.png");
						JOptionPane.showMessageDialog(websiteListTree,"请输入正确的网址","输入提示",JOptionPane.ERROR_MESSAGE,joptionIcon);
					}
				}
			}
		});
		
		
		//全选
		popup_SelectAll.addActionListener(new ActionListener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				root.setChecked(true);
				if(root.children()!=null)
				{
					Enumeration<CheckBoxTreeNode> en=root.children();
					while(en.hasMoreElements())
					{
						CheckBoxTreeNode node=en.nextElement();
						node.setChecked(true);
						node.setCascadeChecked(true);
					}
				}
				websiteListTree.updateUI();
			}
		});
		
		//全不选
		popup_SelectNo.addActionListener(new ActionListener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				root.setChecked(false);
				if(root.children()!=null)
				{
					Enumeration<CheckBoxTreeNode> en=root.children();
					while(en.hasMoreElements())
					{
						CheckBoxTreeNode node=en.nextElement();
						node.setChecked(false);
						node.setCascadeChecked(false);
					}
				}
				websiteListTree.updateUI();
			}
		});
		
		//复制地址
		popup_Copy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CheckBoxTreeNode node=(CheckBoxTreeNode)path.getLastPathComponent();
				String nodeName=(String)node.getUserObject();
				StringSelection nodeNameSelection=new StringSelection(nodeName);
				Clipboard clip=websiteListTree.getToolkit().getSystemClipboard();
				clip.setContents(nodeNameSelection, null);
			}
		});
		
		//用浏览器打开
		popup_Open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CheckBoxTreeNode node=(CheckBoxTreeNode) path.getLastPathComponent();
				String nodeName=(String) node.getUserObject();
				try {
					Runtime.getRuntime().exec("explorer.exe "+nodeName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//开始
		popup_Start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainFrame.mainToolBar.jtb_play.doClick();
			}
		});
		//停止
		popup_Stop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainFrame.mainToolBar.jtb_stop.doClick();
			}
		});
		//删除JTree节点
		popup_Delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				list=mainFrame.configList;
				Iterator<Configuration> it=list.iterator();
				CheckBoxTreeNode delete_node=(CheckBoxTreeNode) path.getLastPathComponent();
				String nodeName=(String) delete_node.getUserObject();
				while(it.hasNext()){
					if(it.next().getURL().equals(nodeName))
						break;
					
				}
				it.remove();
				delete_node.removeFromParent();
				websiteListTree.updateUI();
			}
		});
	}
}
