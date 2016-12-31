package com.graphicaluserinterface;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class New_step4 extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public New_step4() {
		// TODO Auto-generated constructor stub
		setLayout(new  FlowLayout(FlowLayout.LEFT,20,10));
		
		JPanel layoutControl=new JPanel();
		layoutControl.setLayout(new GridLayout(4, 1));
		
		JPanel layoutControl1=new JPanel();
		layoutControl1.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
		final JCheckBox isUseClientCertification=new JCheckBox("使用客户端证书");
		layoutControl1.add(isUseClientCertification);
		layoutControl.add(layoutControl1);
		
		
		JPanel layoutControl2=new JPanel();
		layoutControl2.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel certificationFileDesc=new JLabel("    证书文件:");
		final JTextField certificationFile=new JTextField();
		certificationFile.setBorder(BorderFactory.createLoweredBevelBorder());
		certificationFile.setPreferredSize(new Dimension(300, 20));
		certificationFile.setEnabled(false);
		final JButton browseCertificationFile=new JButton("浏览");
		browseCertificationFile.setEnabled(false);
		layoutControl2.add(certificationFileDesc);
		layoutControl2.add(certificationFile);
		layoutControl2.add(browseCertificationFile);
		layoutControl.add(layoutControl2);
		
		JPanel layoutControl3=new JPanel();
		layoutControl3.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel certificationKeyFileDesc=new JLabel("证书密钥文件:");
		final JTextField certificationKeyFile=new JTextField();
		certificationKeyFile.setPreferredSize(new Dimension(300, 20));
		certificationKeyFile.setBorder(BorderFactory.createLoweredBevelBorder());
		certificationKeyFile.setEnabled(false);
		final JButton browseCertificationKeyFile=new JButton("浏览");
		browseCertificationKeyFile.setEnabled(false);
		layoutControl3.add(certificationKeyFileDesc);
		layoutControl3.add(certificationKeyFile);
		layoutControl3.add(browseCertificationKeyFile);
		layoutControl.add(layoutControl3);
		
		JPanel layoutControl4=new JPanel();
		layoutControl4.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel certificationKeyDesc=new JLabel("    证书密码:");
		final JTextField certificationKey=new JTextField();
		certificationKey.setBorder(BorderFactory.createLoweredBevelBorder());
		certificationKey.setPreferredSize(new Dimension(100, 20));
		certificationKey.setEnabled(false);
		layoutControl4.add(certificationKeyDesc);
		layoutControl4.add(certificationKey);
		layoutControl.add(layoutControl4);
		
		isUseClientCertification.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(isUseClientCertification.isSelected()){
					certificationFile.setEnabled(true);
					certificationKeyFile.setEnabled(true);
					certificationKey.setEnabled(true);
					browseCertificationFile.setEnabled(true);
					browseCertificationKeyFile.setEnabled(true);
				}
				else{
					certificationFile.setEnabled(false);
					certificationKeyFile.setEnabled(false);
					certificationKey.setEnabled(false);
					browseCertificationFile.setEnabled(false);
					browseCertificationKeyFile.setEnabled(false);
				}
			}
		});
		
		browseCertificationFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jfc = new JFileChooser();
				int result = jfc.showOpenDialog(New_step4.this);
				
				if(result == 0){
					File f = jfc.getSelectedFile();
					if(f!=null && f.exists()){
						certificationFile.setText(f.getAbsolutePath());
					}
				}
			}
		});
		browseCertificationKeyFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jfc = new JFileChooser();
				int result = jfc.showOpenDialog(New_step4.this);
				
				if(result == 0){
					File f = jfc.getSelectedFile();
					if(f!=null && f.exists()){
						certificationKeyFile.setText(f.getAbsolutePath());
					}
				}
			}
		});
		
		this.add(layoutControl);
	}

}
