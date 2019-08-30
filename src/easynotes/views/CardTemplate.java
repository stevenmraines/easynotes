package easynotes.views;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class CardTemplate extends JPanel {
	private JPopupMenu contextMenu;
	private JMenuItem deleteCardMenuItem;
	private JMenuItem editCardMenuItem;
	
	public CardTemplate() {
		// Initialize properties
		contextMenu = new JPopupMenu();
		editCardMenuItem = new JMenuItem("Edit this card");
		deleteCardMenuItem = new JMenuItem("Delete this card");
		
		// Add components
		this.add(contextMenu);
		contextMenu.add(editCardMenuItem);
		contextMenu.add(deleteCardMenuItem);
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
}
