package com.graphicaluserinterface;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

public class WebsiteList extends StandardPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CheckBoxTreeNode root;
	public JTree websiteListTree;
	public TreePath path;
	public Popup treePopup;
	public WebsiteList(String title,final MainFrame mainFrame) {
		super(title);
		// TODO Auto-generated constructor stub
		root=new CheckBoxTreeNode("ÍøÕ¾ÁÐ±í");
		websiteListTree=new JTree(root,true);
		path= null;
		treePopup=null;
		websiteListTree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				path = websiteListTree.getPathForLocation(e.getX(),e.getY());
				treePopup=new Popup(websiteListTree,path, root,mainFrame);
				if(path!=null){
					websiteListTree.setSelectionPath(path);
					CheckBoxTreeNode cn = (CheckBoxTreeNode)path.getLastPathComponent();
					if(e.getButton()==MouseEvent.BUTTON1){
						cn.setChecked(!cn.isChecked());
						cn.setCascadeChecked(cn.isChecked());
					}
					websiteListTree.updateUI();
				}
				if(path!=null)
				{
					if(e.getButton()==MouseEvent.BUTTON3)
					{
						treePopup.show(websiteListTree,e.getX(),e.getY());
					}
				}
				if(mainFrame.focusChangeable=true)
					mainFrame.focusWhere=1;
			}
		});
		websiteListTree.setShowsRootHandles(true);
		websiteListTree.setCellRenderer(new CheckBoxTreeCellRenderer());
		bodyPanel.add(websiteListTree);
	}
}
