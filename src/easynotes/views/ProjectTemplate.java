package easynotes.views;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class ProjectTemplate extends JPanel {
	private static final long serialVersionUID = -3447234042759312451L;
	private JPopupMenu contextMenu;
	private JMenuItem addCardMenuItem;
	private JMenuItem flipAllCardsMenuItem;
	private JCheckBoxMenuItem showBackgroundMenuItem;
	private BufferedImage background;
	
	public ProjectTemplate() {
		// Initialize components
		contextMenu = new JPopupMenu();
		addCardMenuItem = new JMenuItem("Add a card");
		flipAllCardsMenuItem = new JMenuItem("Flip all cards");
		showBackgroundMenuItem = new JCheckBoxMenuItem("Show background image");
		
		// Add components
		this.add(contextMenu);
		contextMenu.add(addCardMenuItem);
		contextMenu.add(flipAllCardsMenuItem);
		contextMenu.addSeparator();
		contextMenu.add(showBackgroundMenuItem);
		
		// Prepare background image
		try {
			background = ImageIO.read(new File("img/cork.jpg"));
		} catch(IOException e) {
			// Do nothing?
		}
		
		// Prepare showBackgroundMenuItem
		showBackgroundMenuItem.setSelected(true);
	}
	
	/*
	 * Override paintComponent to draw corkboard image background.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(showBackgroundMenuItem.isSelected() && background != null) {
			int w = this.getWidth();
			int h = this.getHeight();
			int bw = background.getWidth();
			int bh = background.getHeight();
			
			// Find out how many times the image needs to be repeated in each direction
			int rx = (int) Math.ceil((double) w / bw);
			int ry = (int) Math.ceil((double) h / bh);
			
			for(int i = 0; i < rx; i++) {
				for(int j = 0; j < ry; j++) {
					int x = this.getX() + i * bw;
					int y = this.getY() + j * bh;
					g.drawImage(background, x, y, null);
				}
			}
		}
	}

	/*
	 * Setters and getters.
	 */
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

	public JCheckBoxMenuItem getShowBackgroundMenuItem() {
		return showBackgroundMenuItem;
	}

	public void setShowBackgroundMenuItem(JCheckBoxMenuItem showBackgroundMenuItem) {
		this.showBackgroundMenuItem = showBackgroundMenuItem;
	}
	
}
