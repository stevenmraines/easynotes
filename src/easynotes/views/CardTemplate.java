package easynotes.views;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import easynotes.models.Card;

public class CardTemplate extends JPanel implements PropertyChangeListener {
	private static final long serialVersionUID = -7500615444440173683L;
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
		backLabel.setVisible(false);
	}
	
	public void flip() {
		frontLabel.setVisible(!frontLabel.isVisible());
		backLabel.setVisible(!backLabel.isVisible());
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

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if(e.getSource() instanceof Card) {
			Card card = (Card) e.getSource();
			
			if(e.getPropertyName() == "front") {
				frontLabel.setText(card.getFront());
			}
			
//			if(e.getPropertyName() == "back") {
//				label.setText(card.getBack());
//			}
		}
	}
}
