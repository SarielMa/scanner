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
		
		/*扫描范围控制*/
		JPanel scanScalePan=new JPanel();
		scanScalePan.setBorder(BorderFactory.createTitledBorder("扫描范围控制"));
		ButtonGroup scanScaleGroup=new ButtonGroup();
		scanScale_URLSelected=new JRadioButton("扫描当前URL");
		scanScale_URL=new JRadioButton("扫描任何URL");
		scanScale_Subdomin=new JRadioButton("扫描当前子域名");
		scanScale_Domin=new JRadioButton("扫描整个域");
		scanScaleGroup.add(scanScale_URLSelected);
		scanScaleGroup.add(scanScale_URL);
		scanScaleGroup.add(scanScale_Subdomin);
		scanScaleGroup.add(scanScale_Domin);
		scanScalePan.add(scanScale_URLSelected);
		scanScalePan.add(scanScale_URL);
		scanScalePan.add(scanScale_Subdomin);
		scanScalePan.add(scanScale_Domin);
		this.add(scanScalePan);
		/*扫描范围控制*/
		
		/*扫描层数*/
		JPanel scanLayerPan=new JPanel();
		scanLayerPan.setBorder(BorderFactory.createTitledBorder("扫描层数"));
		scanLayerPan.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		JLabel scanLayerDesc=new JLabel("层数：");
		scanLayer=new JTextField();
		scanLayer.setPreferredSize(new Dimension(40, 20));
		JLabel scanLayerInfo=new JLabel("(如果为0则扫描任意多层)");
		scanLayerInfo.setEnabled(false);
		scanLayerPan.add(scanLayerDesc);
		scanLayerPan.add(scanLayer);
		scanLayerPan.add(scanLayerInfo);
		this.add(scanLayerPan);
		/*扫描层数*/
		
		/*扩展配置*/
		JPanel extendConfigpan=new JPanel();
		extendConfigpan.setBorder(BorderFactory.createTitledBorder("扩展配置"));
		isDistinct=new JCheckBox("URL区分大小写");
		isSupport=new JCheckBox("Flash导航支持");
		extendConfigpan.add(isDistinct);
		extendConfigpan.add(isSupport);
		this.add(extendConfigpan);
		/*扩展配置*/
		
		/*线程数控制*/
		JPanel threadNumPan=new JPanel();
		threadNumPan.setBorder(BorderFactory.createTitledBorder("线程数控制"));
		threadNumPan.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		JLabel threadNumDesc=new JLabel("数量：");
		threadNum=new JTextField();
		threadNum.setPreferredSize(new Dimension(40, 20));
		JLabel threadNumInfo=new JLabel("(1~40)");
		threadNumInfo.setEnabled(false);
		threadNumPan.add(threadNumDesc);
		threadNumPan.add(threadNum);
		threadNumPan.add(threadNumInfo);
		this.add(threadNumPan);
		/*线程数控制*/
		
		/*爬行模式*/
		JPanel crawlModePan=new JPanel();
		crawlModePan.setBorder(BorderFactory.createTitledBorder("爬行模式"));
		crawlModePan.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		isSimple=new JCheckBox("简单模式");
		JLabel isSimpleInfo=new JLabel("(爬行时忽略参数值不同)");
		isSimpleInfo.setEnabled(false);
		crawlModePan.add(isSimple);
		crawlModePan.add(isSimpleInfo);
		this.add(crawlModePan);
		/*爬行模式*/
		
		/*额外按钮*/
		JPanel extraButtonPan=new JPanel();
		extraButtonPan.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton addAddress=new JButton("增加地址...");
		JButton otherDirectory=new JButton("例外目录...");
		JButton otherForm=new JButton("例外文件类型");
		extraButtonPan.add(addAddress);
		extraButtonPan.add(otherDirectory);
		extraButtonPan.add(otherForm);
		this.add(extraButtonPan);
		/*额外按钮*/
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
