package com.graphicaluserinterface;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.tree.TreePath;


public class WebsiteFileList extends StandardPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTree websiteFileTree=null;
	public CheckBoxTreeNode root;
	public CheckBoxTreeNode	websiteOfGetTree;
	public CheckBoxTreeNode websiteOfPostTree;
	public CheckBoxTreeNode otherWebsiteTree;
	public CheckBoxTreeNode websiteOfAdmin;
	public TreePath path;
	public WebsiteFileList(String title,final MainFrame mainFrame) {
		super(title);
		// TODO Auto-generated constructor stub
		JToolBar imagePanel=new JToolBar();
		JButton gear_warn=new JButton(new ImageIcon("img/gear_warn_32.png"));
		JButton gear_checked=new JButton(new ImageIcon("img/gear_checked_32.png"));
		JButton gear_warn2=new JButton(new ImageIcon("img/gear_warn2_32.png"));
		imagePanel.add(gear_warn);
		imagePanel.addSeparator();
		imagePanel.add(gear_checked);		
		imagePanel.addSeparator();
		imagePanel.add(gear_warn2);	
		imagePanel.setFloatable(false);
		bodyPanel.add(imagePanel,BorderLayout.NORTH);
		
		
		root=new CheckBoxTreeNode("网站文件列表");
		websiteOfGetTree=new CheckBoxTreeNode("待检测网页");
		websiteOfPostTree=new CheckBoxTreeNode("包含form网页");
		websiteOfAdmin=new CheckBoxTreeNode("后台页面");
		otherWebsiteTree=new CheckBoxTreeNode("其他");
		root.add(websiteOfGetTree);
		root.add(websiteOfPostTree);
		root.add(websiteOfAdmin);
		root.add(otherWebsiteTree);
		websiteFileTree=new JTree(root);

		websiteFileTree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int flag=2;
				path = websiteFileTree.getPathForLocation(e.getX(),e.getY());
				if(path!=null){
					websiteFileTree.setSelectionPath(path);
					CheckBoxTreeNode cn = (CheckBoxTreeNode)path.getLastPathComponent();
					if(e.getButton()==MouseEvent.BUTTON1){
						if(cn==websiteOfPostTree||cn.getParent()==websiteOfPostTree){
							System.out.println("websiteOfPostTree");
							flag=3;
						}
						else if(cn==websiteOfAdmin||cn.getParent()==websiteOfAdmin){
							System.out.println("websiteOfAdmin");
							flag=4;
						}
						else
							flag=2;
						cn.setChecked(!cn.isChecked());
						cn.setCascadeChecked(cn.isChecked());
					}
					if(e.getButton()==MouseEvent.BUTTON3)
					{				
					}
					websiteFileTree.updateUI();
				}
				if(mainFrame.focusChangeable=true)
					mainFrame.focusWhere=flag;
			}
		});
		websiteFileTree.setShowsRootHandles(true);
		websiteFileTree.setCellRenderer(new CheckBoxTreeCellRenderer());
		JScrollPane scrollPan=new JScrollPane(websiteFileTree);
		websiteFileTree.updateUI();
		bodyPanel.add(scrollPan,BorderLayout.CENTER);
	}
}
