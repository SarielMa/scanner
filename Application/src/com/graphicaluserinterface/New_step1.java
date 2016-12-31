package com.graphicaluserinterface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class New_step1 extends JPanel{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	JRadioButton simpleMode;
	JRadioButton massMode;
	public New_step1() {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout(20,20));
		
		/*模式选择*/
		JPanel modePan=new JPanel();
		modePan.setLayout(new BoxLayout(modePan, BoxLayout.Y_AXIS));
		
			//单选钮组
		ButtonGroup mode=new ButtonGroup();
			/*简单模式*/
		JPanel simpleModePan=new JPanel();
		simpleModePan.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
		simpleMode=new JRadioButton("简单模式");
		simpleMode.setSelected(true);
		mode.add(simpleMode);
		simpleModePan.add(simpleMode);
			/*简单模式*/
			/*批量模式*/
		JPanel massModePan=new JPanel();
		massModePan.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
		massMode=new JRadioButton("批量模式");
		mode.add(massMode);
				/*批量模式选项*/
		final JPanel btnModePan=new JPanel();
		final JButton importFromFile=new JButton("从文件导入");
		final JButton importFromDatabase=new JButton("从数据库导入");
		final JButton selectAll=new JButton("全选");
		final JButton selectNull=new JButton("全不选");
		final JButton IPsegment=new JButton("IP段");	
		btnModePan.add(importFromFile);
		btnModePan.add(importFromDatabase);
		btnModePan.add(selectAll);
		btnModePan.add(selectNull);
		btnModePan.add(IPsegment);
		importFromFile.setEnabled(false);
		importFromDatabase.setEnabled(false);
		selectAll.setEnabled(false);
		selectNull.setEnabled(false);
		IPsegment.setEnabled(false);
		importFromFile.addActionListener(btnImportFromFile);
				/*批量模式选项*/
		massModePan.add(massMode);
		massModePan.add(btnModePan);
			/*批量模式*/
		simpleMode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(simpleMode.isSelected()){			
					importFromFile.setEnabled(false);
					importFromDatabase.setEnabled(false);
					selectAll.setEnabled(false);
					selectNull.setEnabled(false);
					IPsegment.setEnabled(false);
				}
			}
		});
		massMode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(massMode.isSelected()){
					importFromFile.setEnabled(true);
					importFromDatabase.setEnabled(true);
					selectAll.setEnabled(true);
					selectNull.setEnabled(true);
					IPsegment.setEnabled(true);
				}
			}
		});
		modePan.add(simpleModePan);
		modePan.add(massModePan);
		this.add(modePan,BorderLayout.NORTH);
		/*模式选择*/
		
		/*URL列表*/
		JPanel URLPan=new JPanel(new BorderLayout(20,20));
		Object[][] cells={};
		String[] colnames={"URL"};
		//重载实现格式化
		JTable URLTable =new JTable(cells,colnames){
			 /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
	         public JTableHeader getTableHeader() {

	            JTableHeader tableHeader = super.getTableHeader();
	            tableHeader.setReorderingAllowed(true);	//表格列不可移动
	            DefaultTableCellRenderer hr = (DefaultTableCellRenderer) tableHeader
	                    .getDefaultRenderer();
	            hr.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);//列名居左
	            return tableHeader;
	        }

		};
		JScrollPane scrollPan=new JScrollPane(URLTable);
		URLPan.add(scrollPan);
		URLPan.setBorder(BorderFactory.createLoweredBevelBorder());
		/*URL列表*/
		
		
		this.add(URLPan,BorderLayout.CENTER);
		this.add(Box.createHorizontalStrut(3),BorderLayout.WEST);
		this.add(Box.createHorizontalStrut(3),BorderLayout.EAST);
	}
	ActionListener btnImportFromFile=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFileChooser jfc=new JFileChooser();
			int result = jfc.showOpenDialog(New_step1.this);
			
			if(result == 0){
				File f = jfc.getSelectedFile();
				if(f!=null && f.exists()){
				}
			}
		}
	};
	public boolean isMassMode(){
		return massMode.isSelected();
	}
}
