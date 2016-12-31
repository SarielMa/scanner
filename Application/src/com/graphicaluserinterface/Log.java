package com.graphicaluserinterface;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Log extends StandardPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Log(String title) {
		super(title);
		// TODO Auto-generated constructor stub
		final JTabbedPane logTabbed=new JTabbedPane(JTabbedPane.BOTTOM);
		JPanel agentPan =new JPanel();
		JPanel logPan=new JPanel();
		logTabbed.addTab("日志", logPan);
		logTabbed.addTab("代理", agentPan);
		bodyPanel.add(logTabbed);
		
		logTabbed.addChangeListener(new ChangeListener() {
			int index=0;
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				index = logTabbed.getSelectedIndex(); 
				titleLable.setText(logTabbed.getTitleAt(index));
			}
		});
	}
}
