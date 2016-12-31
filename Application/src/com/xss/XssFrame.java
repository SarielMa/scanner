package com.xss;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class XssFrame extends JDialog{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//	private JTextField textField1=new JTextField(10);
	private JComboBox jcom=new JComboBox();
	private JTextField textField2=new JTextField(3);
	private JTextField textField3=new JTextField(3);
	
	private JLabel lblurl_1 = new JLabel("ÇëÊäÈëURL:  ");
	private JLabel label_2 = new JLabel("ÊÇ·ñ´æÔÚ¿çÕ¾Â©¶´£º");
	private JLabel lblform = new JLabel("´æÔÚ¿çÕ¾Â©¶´µÄFORM±íµ¥¸öÊý£º");
	private JLabel jla=new JLabel("¿çÕ¾µØµã  ");
	private JLabel jla2=new JLabel("¿çÕ¾Óï¾ä  ");
	
	private Object[ ][ ] cells = {};
	private String[] columns={"¿çÕ¾µã","¿çÕ¾Óï¾ä"};
	private JTable jtable = new MyJTable(cells,columns);
	private JScrollPane jscp=new JScrollPane(jtable);
	private DefaultTableModel mod = new DefaultTableModel(cells,columns); 
	
	private JPanel jp_main=new JPanel();
	private JPanel jp_null=new JPanel();
	private JPanel jp_null2=new JPanel();
	private JPanel jp1=new JPanel();
	private JPanel jp2=new JPanel();
	private JPanel jp3=new JPanel();
	private JPanel jp4=new JPanel();
	private JPanel jp4_left=new JPanel();

	private JButton j_btn=new JButton("¼ì²â");
	
	private String urlString="";
	
	
	class MyJTable extends JTable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public MyJTable(Object[][] values, String[] columns) {
            super(values, columns);
        }
         public boolean isCellEditable(int row, int column) { 
        	 return false; 
        	 } 
	}
	
	
	
	public JTable getJtable() {
		return jtable;
	}
	public void setJtable(JTable jtable) {
		this.jtable = jtable;
	}
	public DefaultTableModel getMod() {
		return mod;
	}
	public void setMod(DefaultTableModel mod) {
		this.mod = mod;
	}
	
	public JTextField getTextField2() {
		return textField2;
	}
	public void setTextField2(JTextField textField2) {
		this.textField2 = textField2;
	}
	public JTextField getTextField3() {
		return textField3;
	}
	public void setTextField3(JTextField textField3) {
		this.textField3 = textField3;
	}
	
	public JComboBox getJcom() {
		return jcom;
	}
	public void setJcom(JComboBox jcom) {
		this.jcom = jcom;
	}
	
	public XssFrame(HashSet<String> url){
		setSize(600,500);
		
		
		List<String> urlList=new ArrayList<String>();
		Iterator<String> it=url.iterator();
		String as;
		while(it.hasNext()){
			as=it.next();
			urlList.add(as);
			jcom.addItem(as);
		}
		jp_main.setLayout(new BoxLayout(jp_main, BoxLayout.Y_AXIS));
		
		jp1.setLayout(new BoxLayout(jp1, BoxLayout.X_AXIS));
		jp1.add(Box.createHorizontalStrut(10));
		jp1.add(lblurl_1);
		jp1.add(jcom);
		jcom.setEditable(true);
		jp1.add(Box.createHorizontalStrut(10));
		jp1.add(j_btn);
		jp1.add(Box.createHorizontalStrut(10));
		jp1.setPreferredSize(new Dimension(600,30));
		
		jp2.setLayout(new BoxLayout(jp2, BoxLayout.X_AXIS));
		jp2.add(Box.createHorizontalStrut(10));
		jp2.add(label_2);
		textField2.setEditable(false);
		jp2.add(textField2);
		jp2.add(Box.createHorizontalStrut(400));
		jp2.setPreferredSize(new Dimension(600,30));
		
		jp3.setLayout(new BoxLayout(jp3, BoxLayout.X_AXIS));
		jp3.add(Box.createHorizontalStrut(10));
		jp3.add(lblform);
		textField3.setEditable(false);
		jp3.add(textField3);
		jp3.add(Box.createHorizontalStrut(350));
		jp3.setPreferredSize(new Dimension(600,30));
		
		jp4.setLayout(new BoxLayout(jp4, BoxLayout.X_AXIS));
		jp4.add(Box.createHorizontalStrut(10));
		jp4_left.setLayout(new BoxLayout(jp4_left,BoxLayout.Y_AXIS));
		jp4_left.add(jla);
		jp4_left.add(jla2);
		jp4_left.add(Box.createVerticalStrut(300));
		jp4.add(jp4_left);
		jscp.setPreferredSize(new Dimension(500,400));
		jp4.add(jscp);
		jp4.add(Box.createHorizontalStrut(10));
		
		
		
		
		jp_main.add(jp_null,BorderLayout.NORTH);
		jp_main.add(jp1);
		jp_main.add(Box.createVerticalStrut(10));
		jp_main.add(jp2);
		jp_main.add(Box.createVerticalStrut(10));
		jp_main.add(jp3);
		jp_main.add(Box.createVerticalStrut(10));
		jp_main.add(jp4);
		jp_main.add(jp_null2,BorderLayout.SOUTH);
		
		j_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				urlString=(String) jcom.getSelectedItem();
				
				new PostTestFrame(urlString,XssFrame.this).start();
				textField2.setText("");
				textField3.setText("");
				
				for(int i = 0; i < mod.getRowCount(); i++)
				{
					mod.removeRow(i);
				}
				jtable.setModel(mod);
			}
		});
		
		
		add(jp_main);
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}
	
}
