package com.graphicaluserinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

public class StandardPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel titlePanel;
	protected JPanel bodyPanel;
	public JButton close;
	public JLabel titleLable;
	public StandardPanel(String title) {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		setSize(100, 100);
		
		/*标准框-标题栏*/
		titlePanel=new JPanel();
		titlePanel.setLayout( new GridLayout(1,2));
		JPanel p1=new JPanel(new FlowLayout(FlowLayout.LEFT,5,3));//标题面板
		JPanel p2=new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));//关闭面板
		p1.setBackground(new Color(234,234,234));
		p2.setBackground(new Color(234,234,234));
		
		titleLable= new JLabel(title);//标题名
		
		/*以JToolBar形式完成关闭按钮*/
		JToolBar closeBar=new JToolBar();
		closeBar.setPreferredSize(new Dimension(30,25));
		closeBar.setAlignmentX(0);
		closeBar.setBackground(new Color(234,234,234));
		closeBar.setFloatable(false);
		close=new JButton(new ImageIcon("img/jsp_close_white.png"));
		closeBar.add(close);
		
		p1.add(titleLable,BorderLayout.WEST);
		p2.add(closeBar,BorderLayout.EAST);
		
		titlePanel.add(p1);
		titlePanel.add(p2);
		this.add(titlePanel,BorderLayout.NORTH);
		/*标准框-标题栏*/
		
		bodyPanel=new JPanel();
		bodyPanel.setLayout(new BorderLayout());
		bodyPanel.setBorder(new EmptyBorder(1,1,1,1));
		
		this.add(bodyPanel,BorderLayout.CENTER);
	}
}

