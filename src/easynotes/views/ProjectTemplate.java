package easynotes.views;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class ProjectTemplate extends JPanel {
	private JPopupMenu contextMenu;
	private JMenuItem addCardMenuItem;
	
	public ProjectTemplate() {
		// Initialize components
		contextMenu = new JPopupMenu();
		addCardMenuItem = new JMenuItem("Add a card");
		
		// Add components
		this.add(contextMenu);
		contextMenu.add(addCardMenuItem);
	}

	public JPopupMenu getContextMenu() {
		return contextMenu;
	}

	public void setContextMenu(JPopupMenu contextMenu) {
		this.contextMenu = contextMenu;
	}

	public JMenuItem getAddCardMenuItem() {
		return addCardMenuItem;
	}

	public void setAddCardMenuItem(JMenuItem addCardMenuItem) {
		this.addCardMenuItem = addCardMenuItem;
	}
}
