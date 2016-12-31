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
		//��Ĭ�ϴ��ڷ������ΪWINDOWS���
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
		
		
		setTitle("��Ŀ������");
		setSize(800, 600);
		setLocation(300, 150);
		setResizable(false);
		setLayout(new BorderLayout());
		setModal(true);
		
		config=new Configuration();
		
		/*�μ�������*/	
		JPanel subTitlePanel=new JPanel(){//�μ������������ػ�
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
        
        subTitlePanel.setLayout(new BoxLayout(subTitlePanel, BoxLayout.Y_AXIS));//�μ����������岼��
        
        JPanel subTitlePan=new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 5));//��������
        JLabel stepName=new JLabel("����");
		final JLabel subTitle=new JLabel("1/6-ģʽ");
		stepName.setFont(new Font("����",Font.BOLD,25));
		subTitle.setFont(new Font("����",Font.BOLD,25));
		subTitlePan.add(stepName);
		subTitlePan.add(subTitle);
		subTitlePan.setOpaque(false);
		
	    JPanel subTitleDescPan=new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 15));//��������
		final JLabel subTitleDescription=new JLabel("��ѡ������ģʽ");
		subTitleDescPan.add(subTitleDescription);
		subTitleDescPan.setOpaque(false);
		
		subTitlePanel.add(subTitlePan);
		subTitlePanel.add(subTitleDescPan);
		
		this.add(subTitlePanel,BorderLayout.NORTH);
		/*�μ�������*/
		
		JPanel choosePanel=new JPanel();
		choosePanel.setLayout(new FlowLayout(FlowLayout.RIGHT,20,10));
		JPanel stepChange=new JPanel();
		final JButton previous=new JButton("����һ��");
		final JButton next=new JButton("��һ����");
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
		
		//Ϊ��ť-"��һ��"��Ӽ���
		previous.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//��⵱ǰ���裬ˢ�±�����
				stepRemind--;
				cl.previous(cardPanel);
				switch(stepRemind){
				case 1:
					subTitle.setText("1/6-ģʽ");
					subTitleDescription.setText("��ѡ������ģʽ");
					previous.setEnabled(false);	
					break;
				case 2:
					subTitle.setText("2/6-����");
					subTitleDescription.setText("������Ҫ��ʼ��URL");
					break;
				case 3:
					subTitle.setText("3/6-�߼�����");
					subTitleDescription.setText("�߼����й�������");
					break;
				case 4:
					subTitle.setText("4/6-�߼�����");
					subTitleDescription.setText("����֤���ļ�");
					break;
				case 5:
					subTitle.setText("5/6-����");
					subTitleDescription.setText("ѡ���¼ģʽ��������Ҫ�Զ���¼��վ��ʱ��");
					next.setText("��һ����");	
					break;
				case 6:
					subTitle.setText("6/6-����");
					subTitleDescription.setText("��ѡ��Ҫ���м���ģ��");
					break;
				}
			}
		});
		//Ϊ��ť-"��һ��"��Ӽ���
		next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//��⵱ǰ���裬��(config)���и�ֵ���ڵ�6����(config)���д�ֵ
				if(stepRemind==6){
					//���ļ���������
					JFileChooser saveConfig=new JFileChooser();
					saveConfig.setSelectedFile(new File("Unamed.proj"));
					int result=saveConfig.showSaveDialog(NewMain.this);
					//���ѡ�񱣴�...
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
					//Ϊ��վ�б���ӽڵ�
					CheckBoxTreeNode site=new CheckBoxTreeNode(config.getURL());
					site.setAllowsChildren(false);
					mainFrame.addConfig(config);
					mainFrame.mainPan.websiteList.root.add(site);
					//����������
					mainFrame.mainPan.websiteList.websiteListTree.updateUI();
					//�ر��½�
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
							errDesc=errDesc+"url��ַ�����ϱ�׼";
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
							errDesc=errDesc+"������ɨ�����!";
							flag=true;
						}
						String exampleThreadNum="[1-9]|[1-3][0-9]|40";
						if(step3.getThreadNum().matches(exampleThreadNum)){
							config.setThreadNum(step3.getThreadNum());
						}
						else{
							errDesc=errDesc+"�������߳���!";
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
							subTitle.setText("1/6-ģʽ");
							subTitleDescription.setText("��ѡ������ģʽ");
							break;
						case 2:
							subTitle.setText("2/6-����");
							subTitleDescription.setText("������Ҫ��ʼ��URL");
							previous.setEnabled(true);
							break;
						case 3:
							subTitle.setText("3/6-�߼�����");
							subTitleDescription.setText("�߼����й�������");
							break;
						case 4:
							subTitle.setText("4/6-�߼�����");
							subTitleDescription.setText("����֤���ļ�");
							break;
						case 5:
							subTitle.setText("5/6-����");
							subTitleDescription.setText("ѡ���¼ģʽ��������Ҫ�Զ���¼��վ��ʱ��");
							break;
						case 6:
							subTitle.setText("6/6-����");
							subTitleDescription.setText("��ѡ��Ҫ���м���ģ��");
							next.setText("���");	
							break;
						}
					}
					errDesc="";
					flag=false;
				}
			}
		});
		
		JButton cancle=new JButton("ȡ��");
		//Ϊ��ť-"ȡ��"��Ӽ���
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
