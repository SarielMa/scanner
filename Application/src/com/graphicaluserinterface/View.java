package com.graphicaluserinterface;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class View extends StandardPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextArea conclusionText;
	public JScrollPane conclusionScroll;
	public View(String title) {
		super(title);
		// TODO Auto-generated constructor stub
		final JTabbedPane viewTabbed=new JTabbedPane(JTabbedPane.BOTTOM);
		JPanel conclusionView=new JPanel();
		JPanel jp_tab2 = new JPanel();
		JPanel jp_tab3 = new JPanel();
		JPanel jp_tab4 = new JPanel();
		JPanel jp_tab5 = new JPanel();
		JPanel jp_tab6 = new JPanel();
		conclusionText=new JTextArea();
		conclusionScroll=new JScrollPane(conclusionText);
		conclusionView.setLayout(new BorderLayout());
		conclusionView.add(conclusionScroll);
		
		viewTabbed.addTab(null, new ImageIcon("img/hand_file_16.png"), null);
		viewTabbed.addTab("结果视图",null, conclusionView);
		viewTabbed.addTab("概述", null, jp_tab2);
		viewTabbed.addTab("审计", null, jp_tab3);
		viewTabbed.addTab("渗透", null, jp_tab4);
		viewTabbed.addTab("验证码测试", null, jp_tab5);
		viewTabbed.addTab("劫持", null, jp_tab6);
		bodyPanel.add(viewTabbed);
		viewTabbed.setSelectedComponent(conclusionView);
		viewTabbed.setEnabledAt(0, false);
		
		viewTabbed.addChangeListener(new ChangeListener() {
			int index=0;
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				index = viewTabbed.getSelectedIndex(); 
				titleLable.setText(viewTabbed.getTitleAt(index));	

			}
		});
		
		
		
	}
}
