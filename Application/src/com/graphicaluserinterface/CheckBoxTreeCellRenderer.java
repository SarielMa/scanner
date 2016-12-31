package com.graphicaluserinterface;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

public class CheckBoxTreeCellRenderer extends JPanel implements
		TreeCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lab;
	private JCheckBox checkbox;
	// Icons
	/** Icon used to show non-leaf nodes that aren't expanded. */
	transient protected Icon closedIcon;

	/** Icon used to show leaf nodes. */
	transient protected Icon leafIcon;

	/** Icon used to show non-leaf nodes that are expanded. */
	transient protected Icon openIcon;

	public CheckBoxTreeCellRenderer() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(checkbox = new JCheckBox());
		add(lab = new JLabel());
		lab.setOpaque(false);
		checkbox.setOpaque(false);
		closedIcon = new ImageIcon("img/tree_16.png");
		leafIcon = new ImageIcon("img/tree_weblist_website_16.png");
		openIcon = new ImageIcon("img/tree_16.png");
		setBackground(null);
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		
		CheckBoxTreeNode cn = (CheckBoxTreeNode) value;
		String vs = cn.getUserObject().toString();
		if (selected) {
			this.setBackground(new Color(49, 106, 107));
		} else {
			this.setBackground(null);
		}
		if(!hasFocus){
			this.setBackground(new Color(255, 255, 255));
		}
		if (cn.isCheckbox()) {
			if (cn.isChecked()) {
				checkbox.setSelected(true);
			} else {
				checkbox.setSelected(false);
			}
			checkbox.setVisible(true);
		} else {
			checkbox.setVisible(false);
		}
		lab.setText(vs);
		Icon icon = null;
		if (leaf) {
			icon = getLeafIcon();
		} else if (expanded) {
			icon = getOpenIcon();
		} else {
			icon = getClosedIcon();
		}
		lab.setIcon(icon);
		return this;
	}

	public Icon getClosedIcon() {
		return closedIcon;
	}

	public void setClosedIcon(Icon closedIcon) {
		this.closedIcon = closedIcon;
	}

	public Icon getLeafIcon() {
		return leafIcon;
	}

	public void setLeafIcon(Icon leafIcon) {
		this.leafIcon = leafIcon;
	}

	public Icon getOpenIcon() {
		return openIcon;
	}

	public void setOpenIcon(Icon openIcon) {
		this.openIcon = openIcon;
	}

}
