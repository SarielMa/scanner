package com.graphicaluserinterface;

import javax.swing.JTable;

public class MyTable extends JTable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}
}
