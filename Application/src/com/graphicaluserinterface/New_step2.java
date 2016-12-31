package com.graphicaluserinterface;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;


public class New_step2 extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox startURL;
	public New_step2() {
		// TODO Auto-generated constructor stub
		setLayout(new FlowLayout());
		
		JPanel startURLPan=new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 5));
		
		startURL=new JComboBox();
		startURL.setPreferredSize(new Dimension(600,20));
		startURL.setEditable(true);
		
		JButton visit=new JButton("∑√Œ ");
		startURLPan.add(startURL);
		startURLPan.add(visit);
		
		startURLPan.setBorder(BorderFactory.createTitledBorder("∆ ºURL£∫"));
		
		
		this.add(Box.createVerticalStrut(400));
		this.add(startURLPan);
	}
	public String getStartURL(){
		if(startURL.getSelectedItem()!=null)
			return startURL.getSelectedItem().toString();
		else
			return null;
	}
}
