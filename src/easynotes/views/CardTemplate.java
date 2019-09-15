package easynotes.views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import components.JLabelHtml;

/*
 * This class is the template for the card view.
 * It contains a context menu for several card actions.
 */
public class CardTemplate extends JPanel {
	private JLabelHtml frontLabel;
	private JLabelHtml backLabel;
	private JPopupMenu contextMenu;
	private JMenuItem deleteCardMenuItem;
	private JMenuItem editCardMenuItem;
	private JMenuItem duplicateCardMenuItem;
	
	public CardTemplate() {
		// Initialize properties
		frontLabel = new JLabelHtml();
		backLabel = new JLabelHtml();
		contextMenu = new JPopupMenu();
		duplicateCardMenuItem = new JMenuItem("Duplicate this card");
		editCardMenuItem = new JMenuItem("Edit this card");
		deleteCardMenuItem = new JMenuItem("Delete this card");
		
		// Add components
		this.add(frontLabel);
		this.add(backLabel);
		this.add(contextMenu);
		contextMenu.add(duplicateCardMenuItem);
		contextMenu.add(editCardMenuItem);
		contextMenu.add(deleteCardMenuItem);
		
		// Prepare for display
		this.setPreferredSize(new Dimension(200, 120));
		LineBorder line = new LineBorder(Color.black);
		EmptyBorder empty = new EmptyBorder(5,5,5,5);
		this.setBorder(new CompoundBorder(line, empty));
		this.setBackground(Color.white);
		backLabel.setVisible(false);
	}

	/*
	 * Setters and getters
	 */
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
	
	public JMenuItem getDuplicateCardMenuItem() {
		return duplicateCardMenuItem;
	}

	public void setDuplicateCardMenuItem(JMenuItem duplicateCardMenuItem) {
		this.duplicateCardMenuItem = duplicateCardMenuItem;
	}

	public JMenuItem getEditCardMenuItem() {
		return editCardMenuItem;
	}

	public void setEditCardMenuItem(JMenuItem editCardMenuItem) {
		this.editCardMenuItem = editCardMenuItem;
	}

	public JLabelHtml getFrontLabel() {
		return frontLabel;
	}

	public void setFrontLabel(JLabelHtml frontLabel) {
		this.frontLabel = frontLabel;
	}
	
	public JLabelHtml getBackLabel() {
		return backLabel;
	}

	public void setBackLabel(JLabelHtml backLabel) {
		this.backLabel = backLabel;
	}
	
}
