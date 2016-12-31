package com.graphicaluserinterface;


import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class New_step3 extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JRadioButton scanScale_URLSelected;
	private JRadioButton scanScale_URL;
	private JRadioButton scanScale_Subdomin;
	private JRadioButton scanScale_Domin;
	private JTextField scanLayer;	
	private JTextField threadNum;
	private JCheckBox isDistinct;
	private JCheckBox isSupport;
	private JCheckBox isSimple;

	public New_step3() {
		// TODO Auto-generated constructor stub
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		/*ɨ�跶Χ����*/
		JPanel scanScalePan=new JPanel();
		scanScalePan.setBorder(BorderFactory.createTitledBorder("ɨ�跶Χ����"));
		ButtonGroup scanScaleGroup=new ButtonGroup();
		scanScale_URLSelected=new JRadioButton("ɨ�赱ǰURL");
		scanScale_URL=new JRadioButton("ɨ���κ�URL");
		scanScale_Subdomin=new JRadioButton("ɨ�赱ǰ������");
		scanScale_Domin=new JRadioButton("ɨ��������");
		scanScaleGroup.add(scanScale_URLSelected);
		scanScaleGroup.add(scanScale_URL);
		scanScaleGroup.add(scanScale_Subdomin);
		scanScaleGroup.add(scanScale_Domin);
		scanScalePan.add(scanScale_URLSelected);
		scanScalePan.add(scanScale_URL);
		scanScalePan.add(scanScale_Subdomin);
		scanScalePan.add(scanScale_Domin);
		this.add(scanScalePan);
		/*ɨ�跶Χ����*/
		
		/*ɨ�����*/
		JPanel scanLayerPan=new JPanel();
		scanLayerPan.setBorder(BorderFactory.createTitledBorder("ɨ�����"));
		scanLayerPan.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		JLabel scanLayerDesc=new JLabel("������");
		scanLayer=new JTextField();
		scanLayer.setPreferredSize(new Dimension(40, 20));
		JLabel scanLayerInfo=new JLabel("(���Ϊ0��ɨ��������)");
		scanLayerInfo.setEnabled(false);
		scanLayerPan.add(scanLayerDesc);
		scanLayerPan.add(scanLayer);
		scanLayerPan.add(scanLayerInfo);
		this.add(scanLayerPan);
		/*ɨ�����*/
		
		/*��չ����*/
		JPanel extendConfigpan=new JPanel();
		extendConfigpan.setBorder(BorderFactory.createTitledBorder("��չ����"));
		isDistinct=new JCheckBox("URL���ִ�Сд");
		isSupport=new JCheckBox("Flash����֧��");
		extendConfigpan.add(isDistinct);
		extendConfigpan.add(isSupport);
		this.add(extendConfigpan);
		/*��չ����*/
		
		/*�߳�������*/
		JPanel threadNumPan=new JPanel();
		threadNumPan.setBorder(BorderFactory.createTitledBorder("�߳�������"));
		threadNumPan.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		JLabel threadNumDesc=new JLabel("������");
		threadNum=new JTextField();
		threadNum.setPreferredSize(new Dimension(40, 20));
		JLabel threadNumInfo=new JLabel("(1~40)");
		threadNumInfo.setEnabled(false);
		threadNumPan.add(threadNumDesc);
		threadNumPan.add(threadNum);
		threadNumPan.add(threadNumInfo);
		this.add(threadNumPan);
		/*�߳�������*/
		
		/*����ģʽ*/
		JPanel crawlModePan=new JPanel();
		crawlModePan.setBorder(BorderFactory.createTitledBorder("����ģʽ"));
		crawlModePan.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		isSimple=new JCheckBox("��ģʽ");
		JLabel isSimpleInfo=new JLabel("(����ʱ���Բ���ֵ��ͬ)");
		isSimpleInfo.setEnabled(false);
		crawlModePan.add(isSimple);
		crawlModePan.add(isSimpleInfo);
		this.add(crawlModePan);
		/*����ģʽ*/
		
		/*���ⰴť*/
		JPanel extraButtonPan=new JPanel();
		extraButtonPan.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton addAddress=new JButton("���ӵ�ַ...");
		JButton otherDirectory=new JButton("����Ŀ¼...");
		JButton otherForm=new JButton("�����ļ�����");
		extraButtonPan.add(addAddress);
		extraButtonPan.add(otherDirectory);
		extraButtonPan.add(otherForm);
		this.add(extraButtonPan);
		/*���ⰴť*/
	}
	public int getScanScale(){
		if(scanScale_URLSelected.isSelected())
			return 1;
		else if(scanScale_URL.isSelected())
			return 2;
		else if(scanScale_Subdomin.isSelected())
			return 3;
		else if(scanScale_Domin.isSelected())
			return 4;
		else
			return 0;
	}
	public String getScanLayer(){
		return scanLayer.getText();
	}
	public String getThreadNum(){
		return threadNum.getText();
	}
}
