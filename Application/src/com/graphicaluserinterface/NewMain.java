package com.graphicaluserinterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.datastructure.Configuration;

public class NewMain extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int stepRemind;
	public Configuration config;
	private String errDesc="";
	private boolean flag=false;
	private File f;
	private ObjectOutputStream oos = null;
	public NewMain(final MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		stepRemind=1;
		//将默认窗口风格设置为WINDOWS风格
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		setTitle("项目配置向导");
		setSize(800, 600);
		setLocation(300, 150);
		setResizable(false);
		setLayout(new BorderLayout());
		setModal(true);
		
		config=new Configuration();
		
		/*次级标题栏*/	
		JPanel subTitlePanel=new JPanel(){//次级标题栏背景重绘
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {  
                ImageIcon icon = new ImageIcon("img/guidtitlebg.jpg");  
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, 800,200 ,icon.getImageObserver());  
            }  
        };  
        
        subTitlePanel.setLayout(new BoxLayout(subTitlePanel, BoxLayout.Y_AXIS));//次级标题栏整体布局
        
        JPanel subTitlePan=new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 5));//步骤名称
        JLabel stepName=new JLabel("步骤");
		final JLabel subTitle=new JLabel("1/6-模式");
		stepName.setFont(new Font("黑体",Font.BOLD,25));
		subTitle.setFont(new Font("黑体",Font.BOLD,25));
		subTitlePan.add(stepName);
		subTitlePan.add(subTitle);
		subTitlePan.setOpaque(false);
		
	    JPanel subTitleDescPan=new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 15));//步骤描述
		final JLabel subTitleDescription=new JLabel("请选择设置模式");
		subTitleDescPan.add(subTitleDescription);
		subTitleDescPan.setOpaque(false);
		
		subTitlePanel.add(subTitlePan);
		subTitlePanel.add(subTitleDescPan);
		
		this.add(subTitlePanel,BorderLayout.NORTH);
		/*次级标题栏*/
		
		JPanel choosePanel=new JPanel();
		choosePanel.setLayout(new FlowLayout(FlowLayout.RIGHT,20,10));
		JPanel stepChange=new JPanel();
		final JButton previous=new JButton("《上一步");
		final JButton next=new JButton("下一步》");
		previous.setEnabled(false);
		
		
		final JPanel cardPanel=new JPanel();
		final CardLayout cl=new CardLayout();
		cardPanel.setLayout(cl);
		cardPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.gray));
		final New_step1 step1=new New_step1();
		cardPanel.add(step1,"step1");
		final New_step2 step2=new New_step2();
		cardPanel.add(step2,"step2");
		final New_step3 step3=new New_step3();
		cardPanel.add(step3,"step3");
		final New_step4 step4=new New_step4();
		cardPanel.add(step4,"step4");
		final New_step5 step5=new New_step5();
		cardPanel.add(step5,"step5");
		final New_step6 step6=new New_step6();
		cardPanel.add(step6,"step6");
		this.add(cardPanel,BorderLayout.CENTER);
		
		//为按钮-"上一步"添加监听
		previous.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//检测当前步骤，刷新标题栏
				stepRemind--;
				cl.previous(cardPanel);
				switch(stepRemind){
				case 1:
					subTitle.setText("1/6-模式");
					subTitleDescription.setText("请选择设置模式");
					previous.setEnabled(false);	
					break;
				case 2:
					subTitle.setText("2/6-设置");
					subTitleDescription.setText("输入你要开始的URL");
					break;
				case 3:
					subTitle.setText("3/6-高级设置");
					subTitleDescription.setText("高级爬行规则设置");
					break;
				case 4:
					subTitle.setText("4/6-高级设置");
					subTitleDescription.setText("设置证书文件");
					break;
				case 5:
					subTitle.setText("5/6-设置");
					subTitleDescription.setText("选择登录模式，当你想要自动登录网站的时候");
					next.setText("下一步》");	
					break;
				case 6:
					subTitle.setText("6/6-设置");
					subTitleDescription.setText("请选择要进行检测的模块");
					break;
				}
			}
		});
		//为按钮-"下一步"添加监听
		next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//检测当前步骤，对(config)进行赋值，在第6步对(config)进行传值
				if(stepRemind==6){
					//打开文件保存配置
					JFileChooser saveConfig=new JFileChooser();
					saveConfig.setSelectedFile(new File("Unamed.proj"));
					int result=saveConfig.showSaveDialog(NewMain.this);
					//如果选择保存...
					if(result==JFileChooser.APPROVE_OPTION){
						String fileName=saveConfig.getName(saveConfig.getSelectedFile());
						String filePath=saveConfig.getCurrentDirectory().getAbsolutePath();
						fileName=fileName.toLowerCase().endsWith(".proj")?fileName:fileName+".proj";
						f=new File(filePath+"/"+fileName);
						try {
							oos=new ObjectOutputStream(new FileOutputStream(f));
							oos.writeObject(config);
							oos.close();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					//为网站列表添加节点
					CheckBoxTreeNode site=new CheckBoxTreeNode(config.getURL());
					site.setAllowsChildren(false);
					mainFrame.addConfig(config);
					mainFrame.mainPan.websiteList.root.add(site);
					//更新主窗体
					mainFrame.mainPan.websiteList.websiteListTree.updateUI();
					//关闭新建
					dispose();
				}
				else{
					switch(stepRemind){
					case 1:
						config.setMode(step1.isMassMode());
						break;
					case 2:
						String exampleURL="http://[a-zA-Z0-9//_//.//-//&//%//?//://=//-]+";
						if(step2.getStartURL()!=null&&step2.getStartURL().matches(exampleURL)){
							config.setURL(step2.getStartURL());
						}
						else{
							errDesc=errDesc+"url地址不符合标准";
							flag=true;
						}
						break;
					case 3:
						config.setScanScale(step3.getScanScale());
						String exampleLayer="[0-9]+";
						if(step3.getScanLayer().matches(exampleLayer)){
							config.setScanLayer(step3.getScanLayer());
						}
						else{
							errDesc=errDesc+"请重设扫描层数!";
							flag=true;
						}
						String exampleThreadNum="[1-9]|[1-3][0-9]|40";
						if(step3.getThreadNum().matches(exampleThreadNum)){
							config.setThreadNum(step3.getThreadNum());
						}
						else{
							errDesc=errDesc+"请重设线程数!";
							flag=true;
						}
						break;
					case 4:
						break;
					case 5:
						break;
					case 6:
						config.setModule(step6.getModule());
						break;
					}
					if(flag)
						JOptionPane.showMessageDialog(cardPanel, errDesc);
					else{
						stepRemind++;
						cl.next(cardPanel);
						switch(stepRemind){
						case 1:
							subTitle.setText("1/6-模式");
							subTitleDescription.setText("请选择设置模式");
							break;
						case 2:
							subTitle.setText("2/6-设置");
							subTitleDescription.setText("输入你要开始的URL");
							previous.setEnabled(true);
							break;
						case 3:
							subTitle.setText("3/6-高级设置");
							subTitleDescription.setText("高级爬行规则设置");
							break;
						case 4:
							subTitle.setText("4/6-高级设置");
							subTitleDescription.setText("设置证书文件");
							break;
						case 5:
							subTitle.setText("5/6-设置");
							subTitleDescription.setText("选择登录模式，当你想要自动登录网站的时候");
							break;
						case 6:
							subTitle.setText("6/6-设置");
							subTitleDescription.setText("请选择要进行检测的模块");
							next.setText("完成");	
							break;
						}
					}
					errDesc="";
					flag=false;
				}
			}
		});
		
		JButton cancle=new JButton("取消");
		//为按钮-"取消"添加监听
		cancle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		stepChange.add(previous);
		stepChange.add(next);
		choosePanel.add(stepChange);
		choosePanel.add(cancle);
		
		this.add(choosePanel,BorderLayout.SOUTH);
		
		setVisible(true);
	}
}
