package easynotes.views;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class ProjectTemplate extends JPanel {
	private static final long serialVersionUID = -3447234042759312451L;
	private JPopupMenu contextMenu;
	private JMenuItem addCardMenuItem;
	private JMenuItem flipAllCardsMenuItem;
	private BufferedImage background;
	
	public ProjectTemplate() {
		// Initialize components
		contextMenu = new JPopupMenu();
		addCardMenuItem = new JMenuItem("Add a card");
		flipAllCardsMenuItem = new JMenuItem("Flip all cards");
		
		// Add components
		this.add(contextMenu);
		contextMenu.add(addCardMenuItem);
		contextMenu.add(flipAllCardsMenuItem);
		
		// Prepare background image
		try {
			background = ImageIO.read(new File("img/cork.jpg"));
		} catch(IOException e) {
			// Do nothing?
		}
	}
	
	/*
	 * Override paintComponent to draw corkboard image background.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
//		if(background != null) {
//			int w = this.getWidth();
//			int h = this.getHeight();
//			int bw = background.getWidth();
//			int bh = background.getHeight();
//			int rx = Math.round(w / bw);
//			int ry = Math.round(h / bh);
//			
//			for(int i = 1; i <= rx; i++) {
//				for(int j = 1; j <= ry; j++) {
//					g.drawImage(background, i * bw, j * bh, null);
//				}
//			}
//		}
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
	
}
