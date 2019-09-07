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
	private JLabel label;
	private JPopupMenu contextMenu;
	private JMenuItem deleteCardMenuItem;
	private JMenuItem editCardMenuItem;
	
	public CardTemplate() {
		// Initialize properties
		label = new JLabel();
		contextMenu = new JPopupMenu();
		editCardMenuItem = new JMenuItem("Edit this card");
		deleteCardMenuItem = new JMenuItem("Delete this card");
		
		// Add components
		this.add(label);
		this.add(contextMenu);
		contextMenu.add(editCardMenuItem);
		contextMenu.add(deleteCardMenuItem);
		
		// Add border
		LineBorder line = new LineBorder(Color.black);
		EmptyBorder empty = new EmptyBorder(5,5,5,5);
		this.setBorder(new CompoundBorder(line, empty));
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

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel Label) {
		this.label = Label;
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if(e.getSource() instanceof Card) {
			Card card = (Card) e.getSource();
			
			if(e.getPropertyName() == "front") {
				label.setText(card.getFront());
			}
			
//			if(e.getPropertyName() == "back") {
//				label.setText(card.getBack());
//			}
		}
	}
}
