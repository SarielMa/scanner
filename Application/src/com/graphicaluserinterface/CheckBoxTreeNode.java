package com.graphicaluserinterface;

import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;

public class CheckBoxTreeNode extends DefaultMutableTreeNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isChecked = false;
	private boolean isCheckbox = true;

	public CheckBoxTreeNode() {
		super();
	}

	public CheckBoxTreeNode(Object userObject, boolean allowsChildren) {
		super(userObject, allowsChildren);
	}

	public CheckBoxTreeNode(Object userObject) {
		super(userObject);
	}

	// �豸�ڵ���ӽڵ�Ϊѡ��״̬
	@SuppressWarnings("unchecked")
	public void setCascadeChecked(boolean isChecked) {
		this.isChecked = isChecked;
		if (children != null) {
			Enumeration<CheckBoxTreeNode> e = children.elements(); // ��õ�ǰ�ڵ�������ӽڵ�
			while (e.hasMoreElements()) {
				CheckBoxTreeNode node = e.nextElement();
				node.setCascadeChecked(isChecked);
			}
		}
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public boolean isCheckbox() {
		return isCheckbox;
	}

	public void setCheckbox(boolean isCheckbox) {
		this.isCheckbox = isCheckbox;
	}

}
