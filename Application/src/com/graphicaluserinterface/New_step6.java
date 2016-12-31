package com.graphicaluserinterface;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class New_step6 extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCheckBox sql;
	private JCheckBox horse;
	private JCheckBox form;
	private JCheckBox xss;
	private JCheckBox xpath;
	private JCheckBox web2;
	private JCheckBox crack;
	private JCheckBox forge;
	private JCheckBox other;
	public New_step6() {
		// TODO Auto-generated constructor stub
		setLayout(new FlowLayout(FlowLayout.LEFT,30,40));
		
		JPanel layoutControl=new JPanel();
		layoutControl.setLayout(new BoxLayout(layoutControl, BoxLayout.Y_AXIS));
		
		JPanel defaultAppproachPan=new JPanel();
		defaultAppproachPan.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel defaultAppproachDesc=new JLabel("Ĭ�Ϸ���:");
		JComboBox defaultAppproach=new JComboBox();
		defaultAppproach.addItem("Auto");
		defaultAppproachPan.add(defaultAppproachDesc);
		defaultAppproachPan.add(defaultAppproach);
		layoutControl.add(defaultAppproachPan);

		JPanel testModule=new JPanel();
		testModule.setLayout(new GridLayout(3, 3));
		testModule.setBorder(BorderFactory.createTitledBorder("���ģ��"));
		sql=new JCheckBox("SQLע��");
		horse=new JCheckBox("��ҳľ��");
		form=new JCheckBox("���ƹ�");
		xss=new JCheckBox("��վ�ű�");
		xpath=new JCheckBox("XPathע��");
		web2=new JCheckBox("Web2.0���");
		crack=new JCheckBox("��¼�����ƽ�");
		forge=new JCheckBox("��վ������α��");
		other=new JCheckBox("����");
		testModule.add(sql);
		testModule.add(horse);
		testModule.add(form);
		testModule.add(xss);
		testModule.add(xpath);
		testModule.add(web2);
		testModule.add(crack);
		testModule.add(forge);
		testModule.add(other);
		layoutControl.add(testModule);
		
		JPanel parameterNoTestPan=new JPanel();
		parameterNoTestPan.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton parameterNoTest=new JButton("�����Ĳ���");
		parameterNoTestPan.add(parameterNoTest);
		layoutControl.add(parameterNoTestPan);
		
		this.add(layoutControl);
	}
	public int[] getModule(){
		int chosenModule[]=new int [9];
		if(sql.isSelected())
			chosenModule[0]=1;
		if(horse.isSelected())
			chosenModule[1]=1;
		if(form.isSelected())
			chosenModule[2]=1;
		if(xss.isSelected())
			chosenModule[3]=1;
		if(xpath.isSelected())
			chosenModule[4]=1;
		if(web2.isSelected())
			chosenModule[5]=1;
		if(crack.isSelected())
			chosenModule[6]=1;
		if(forge.isSelected())
			chosenModule[7]=1;
		if(other.isSelected())
			chosenModule[8]=1;	
		return chosenModule;
	}
}
