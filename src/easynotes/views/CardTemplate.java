package easynotes.views;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class CardTemplate extends JPanel {
	private JLabel frontLabel;
	private JLabel backLabel;
	private JPopupMenu contextMenu;
	private JMenuItem deleteCardMenuItem;
	private JMenuItem editCardMenuItem;
	
	public CardTemplate() {
		// Initialize properties
		frontLabel = new JLabel();
		backLabel = new JLabel();
		contextMenu = new JPopupMenu();
		editCardMenuItem = new JMenuItem("Edit this card");
		deleteCardMenuItem = new JMenuItem("Delete this card");
		
		// Add components
		this.add(frontLabel);
		this.add(backLabel);
		this.add(contextMenu);
		contextMenu.add(editCardMenuItem);
		contextMenu.add(deleteCardMenuItem);
		
		// Prepare for display
		LineBorder line = new LineBorder(Color.black);
		EmptyBorder empty = new EmptyBorder(5,5,5,5);
		this.setBorder(new CompoundBorder(line, empty));
		this.setBackground(Color.white);
		backLabel.setVisible(false);
	}

	public JPopupMenu getContextMenu() {
		return contextMenu;
	}

	public void setContextMenu(JPopupMenu contextMenu) {
		this.contextMenu = contextMenu;
	}

	public JMenuItem getDeleteCardMenuItem() {
		return deleteCardMenuItem;
	}

	public void setDeleteCardMenuItem(JMenuItem deleteCardMenuItem) {
		this.deleteCardMenuItem = deleteCardMenuItem;
	}

	public JMenuItem getEditCardMenuItem() {
		return editCardMenuItem;
	}

	public void setEditCardMenuItem(JMenuItem editCardMenuItem) {
		this.editCardMenuItem = editCardMenuItem;
	}

	public JLabel getFrontLabel() {
		return frontLabel;
	}

	public void setFrontLabel(JLabel frontLabel) {
		this.frontLabel = frontLabel;
	}
	
	public JLabel getBackLabel() {
		return backLabel;
	}

	public void setBackLabel(JLabel backLabel) {
		this.backLabel = backLabel;
	}
	
}
