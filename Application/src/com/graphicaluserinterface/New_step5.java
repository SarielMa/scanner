package com.graphicaluserinterface;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class New_step5 extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public New_step5() {
		// TODO Auto-generated constructor stub
		setLayout(new FlowLayout(FlowLayout.LEFT,30,40));
		
		JPanel layoutControl=new JPanel();
		layoutControl.setLayout(new BoxLayout(layoutControl, BoxLayout.Y_AXIS));
		
		JPanel autoLoginPan=new JPanel();
		autoLoginPan.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel notLoginPan=new JPanel();
		notLoginPan.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		ButtonGroup loginModeGroup=new ButtonGroup();
		final JRadioButton autoLogin=new JRadioButton("×Ô¶¯µÇÂ¼");
		final JRadioButton notLogin=new JRadioButton("²»µÇÂ½");
		loginModeGroup.add(autoLogin);
		loginModeGroup.add(notLogin);
		autoLoginPan.add(autoLogin);
		notLoginPan.add(notLogin);
		
		
		JPanel autoLoginConfig=new JPanel();
		autoLoginConfig.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
		final JButton recordLogin=new JButton("Â¼ÖÆ");
		final JButton editLogin=new JButton("±à¼­");
		recordLogin.setEnabled(false);
		editLogin.setEnabled(false);
		autoLoginConfig.add(recordLogin);
		autoLoginConfig.add(editLogin);
		
		autoLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(autoLogin.isSelected()){
					recordLogin.setEnabled(true);
					editLogin.setEnabled(true);
				}
			}
		});
		notLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(notLogin.isSelected()){
					recordLogin.setEnabled(false);
					editLogin.setEnabled(false);
				}
			}
		});
		layoutControl.add(autoLoginPan);
		layoutControl.add(autoLoginConfig);
		layoutControl.add(notLoginPan);
		
		this.add(layoutControl);
	}
}
