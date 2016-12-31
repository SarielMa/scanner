package com.graphicaluserinterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.database.ConnectDatabase;
import com.form.Crack;
import com.form.FormByPass;
import com.form.WeakPassword;

public class Form extends JDialog{
	/**
	 * 
	 */
	private HashSet<String> urlList;
	JComboBox website;
	private static final long serialVersionUID = 1L;
	public FormByPass formByPass;
	public WeakPassword wake;
	public Crack crack;
	public JLabel isSucceed;
	public JComboBox username;
	public JTextField password;
	public List<String> name;
	public List<String> pass;
	public JButton startTestWakeCrack;
	public JButton startTestCircumvent;
	public ConnectDatabase connect;
	JComboBox changeableName;
	List<String> nameAlternative;
	JComboBox digitNum;
	public JButton crackBtn;
	public JTextField resultPass;
	public Form(HashSet<String> url) {
		this.urlList=url;
		// TODO Auto-generated constructor stub
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
		
		connect=ConnectDatabase.getInstance();
		setTitle("Form表单");
		setLocation(400,300);
		setLayout(new BorderLayout());
		setSize(450,250);
		setResizable(false);
		setModal(true);
		
		
	
		JPanel websitePan=new JPanel();
		websitePan.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 5));
		JLabel desc=new JLabel("请选择网站:");
		website=new JComboBox();
		website.setPreferredSize(new Dimension(250, 20));
		websiteListAdd();
		websitePan.add(desc);
		websitePan.add(website);
		add(websitePan,BorderLayout.NORTH);
		
		JTabbedPane functionTab=new JTabbedPane();
		JPanel transitPan=new JPanel();
		JPanel funtionPan=new JPanel();
		funtionPan.setLayout(new BoxLayout(funtionPan,BoxLayout.Y_AXIS));
		
		JPanel buttonPan=new JPanel();
		startTestCircumvent=new JButton("尝试表单绕过");
		startTestWakeCrack=new JButton("尝试口令破解");
		buttonPan.add(startTestCircumvent);
		buttonPan.add(startTestWakeCrack);
		
		
		
		isSucceed=new JLabel();
		isSucceed.setText("测试结果:");
		
		JPanel infoPan=new JPanel();
		infoPan.setLayout(new BoxLayout(infoPan,BoxLayout.Y_AXIS));
		
		JPanel usernamePan=new JPanel();
		JLabel usernameDesc=new JLabel("用户名：");
		username=new JComboBox();
		username.setPreferredSize(new Dimension(100,20));
		usernamePan.add(usernameDesc);
		usernamePan.add(username);
		
		
		JPanel passwordPan=new JPanel();
		JLabel passwordDesc=new JLabel("密  码：");
		password=new JTextField();
		password.setPreferredSize(new Dimension(100,20));
		passwordPan.add(passwordDesc);
		passwordPan.add(password);
		
		infoPan.add(usernamePan);
		infoPan.add(passwordPan);
		
		funtionPan.add(buttonPan);
		funtionPan.add(isSucceed);
		funtionPan.add(infoPan);
		
		transitPan.add(funtionPan);
		functionTab.addTab("表单绕过及弱口令", transitPan);
		
		JPanel transit2Pan=new JPanel();
		JPanel funtion2Pan=new JPanel();
		funtion2Pan.setLayout(new BoxLayout(funtion2Pan, BoxLayout.Y_AXIS));
		JPanel changeableNamePan=new JPanel();
		JLabel changeableNameDesc=new JLabel("备选(可编辑):");
		changeableName=new JComboBox();
		changeableName.setPreferredSize(new Dimension(100,20));
		changeableName.setEditable(true);
		nameAlternativeAdd();
		crackBtn=new JButton("破解");
		changeableNamePan.add(changeableNameDesc);
		changeableNamePan.add(changeableName);
		changeableNamePan.add(crackBtn);
		JPanel resultPassPan=new JPanel();
		JLabel resultPassDesc=new JLabel("     密码:");
		resultPass=new JTextField();
		resultPass.setPreferredSize(new Dimension(100, 20));
		digitNum=new JComboBox();
		digitNum.setPreferredSize(new Dimension(40, 20));
		for(Integer i=1;i<7;i++){
			digitNum.addItem(i.toString());
		}
		resultPassPan.add(resultPassDesc);
		resultPassPan.add(resultPass);
		resultPassPan.add(digitNum);
		JLabel processInform=new JLabel("");
		funtion2Pan.add(changeableNamePan);
		funtion2Pan.add(resultPassPan);
		funtion2Pan.add(processInform);
		transit2Pan.add(funtion2Pan);
		
		functionTab.addTab("暴力破解", transit2Pan);
		
		add(functionTab,BorderLayout.CENTER);
		
		JPanel chosePanel=new JPanel();
		JButton ok=new JButton("确定");
		JButton cancle=new JButton("取消");
		chosePanel.add(ok);
		chosePanel.add(cancle);
		startTestCircumvent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				formByPass=new FormByPass(website.getSelectedItem().toString(),Form.this);
				Thread t1=new Thread(formByPass);
				t1.start();
			}
		});
		startTestWakeCrack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Thread t2;
				nameAlternative=connect.selectByTableName("usernamealternative");
				List<String> passwordAlternative=connect.selectByTableName("passwordalternative");
				if(startTestWakeCrack.getText().equals("尝试口令破解")){
					wake=new WeakPassword(website.getSelectedItem().toString(),Form.this,nameAlternative,passwordAlternative);
					t2=new Thread(wake);
					t2.start();
				}else{
					wake.stop();
					startTestWakeCrack.setText("尝试口令破解");
				}
					
			}
		});
		username.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(username.getSelectedIndex()!=-1)
					password.setText(pass.get(username.getSelectedIndex()));
			}
		});
		crackBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(crackBtn.getText().equals("破解")){
					if(!changeableName.getSelectedObjects().toString().equals("")&&changeableName.getSelectedObjects().toString()!=null){
						crack=new Crack(changeableName.getSelectedItem().toString(),digitNum.getSelectedIndex()+1,website.getSelectedItem().toString(),Form.this);
						Thread t3=new Thread(crack);
						t3.start();
					}
				}else{
					crack.stop();
					crackBtn.setText("破解");
				}
						
			}
		});
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		cancle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		add(chosePanel,BorderLayout.SOUTH);

		
		
//		JPanel crackPan=
		
	
		this.add(functionTab);
		setVisible(true);
	}
	private void websiteListAdd() {
		// TODO Auto-generated method stub
		if(this.urlList!=null){
			Iterator<String> it=this.urlList.iterator();
			while(it.hasNext()){
				website.addItem(it.next().toString());
			}
		}
	}
	private void nameAlternativeAdd(){
		nameAlternative=connect.selectByTableName("usernamealternative");
		if(nameAlternative.size()>0){
			Iterator<String> it=this.nameAlternative.iterator();
			while(it.hasNext()){
				changeableName.addItem(it.next().toString());
			}
		}
			
	}
}
