package easynotes.views;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class ProjectTemplate extends JPanel {
	private static final long serialVersionUID = -3447234042759312451L;
	private JPopupMenu contextMenu;
	private JMenuItem addCardMenuItem;
	private JMenuItem flipAllCardsMenuItem;
	
	public ProjectTemplate() {
		// Initialize components
		contextMenu = new JPopupMenu();
		addCardMenuItem = new JMenuItem("Add a card");
		flipAllCardsMenuItem = new JMenuItem("Flip all cards");
		
		// Add components
		this.add(contextMenu);
		contextMenu.add(addCardMenuItem);
		contextMenu.add(flipAllCardsMenuItem);
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

	public JMenuItem getFlipAllCardsMenuItem() {
		return flipAllCardsMenuItem;
	}

	public void setFlipAllCardsMenuItem(JMenuItem flipAllCardsMenuItem) {
		this.flipAllCardsMenuItem = flipAllCardsMenuItem;
	}
	
}
