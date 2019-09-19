package easynotes.templates;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import easynotes.components.CardLabel;

// TODO when cardLabels are removed, their mouseListeners should also be removed
public class CorkboardTemplate extends JPanel
{
	
	private static final long serialVersionUID = -3447234042759312451L;
	private JPopupMenu corkboardMenu;
	private JPopupMenu cardMenu;
	private JMenuItem addCardMenuItem;
	private JMenuItem flipAllCardsMenuItem;
	private JCheckBoxMenuItem showBackgroundMenuItem;	
	private JMenuItem flipCardMenuItem;
	private JMenuItem insertBeforeMenuItem;
	private JMenuItem insertAfterMenuItem;
	private JMenuItem editCardMenuItem;
	private JMenuItem duplicateCardMenuItem;
	private JMenuItem deleteCardMenuItem;
	private BufferedImage background;
	private ArrayList<CardLabel> cardLabels;  // TODO not sure if this is necessary
	
	// TODO add constructor that takes cardLabels for when loading a file
	public CorkboardTemplate()
	{
		
		// Initialize components
		corkboardMenu = new JPopupMenu();
		cardMenu = new JPopupMenu();
		addCardMenuItem = new JMenuItem("Add a card");
		flipAllCardsMenuItem = new JMenuItem("Flip all cards");
		showBackgroundMenuItem = new JCheckBoxMenuItem("Show background image");
		flipCardMenuItem = new JMenuItem("Flip this card");
		insertBeforeMenuItem = new JMenuItem("Insert before");
		insertAfterMenuItem = new JMenuItem("Insert after");
		editCardMenuItem = new JMenuItem("Edit this card");
		duplicateCardMenuItem = new JMenuItem("Duplicate this card");
		deleteCardMenuItem = new JMenuItem("Delete this card");
		cardLabels = new ArrayList<CardLabel>();
		
		// Add components
		this.add(corkboardMenu);
		corkboardMenu.add(addCardMenuItem);
		corkboardMenu.add(flipAllCardsMenuItem);
		corkboardMenu.addSeparator();
		corkboardMenu.add(showBackgroundMenuItem);
		cardMenu.add(flipCardMenuItem);
		cardMenu.add(insertBeforeMenuItem);
		cardMenu.add(insertAfterMenuItem);
		cardMenu.add(editCardMenuItem);
		cardMenu.add(duplicateCardMenuItem);
		cardMenu.add(deleteCardMenuItem);
		
		// Prepare background image
		try {
			background = ImageIO.read(new File("img/cork.jpg"));
		} catch(IOException e) {
			// TODO How to get WindowTemplate reference for JOptionPane here?
		}
		
		// Set showBackgroundMenuItem checked by default
		showBackgroundMenuItem.setSelected(true);
		
	}
	
	/*
	 * Override paintComponent to draw corkboard image background.
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		
		super.paintComponent(g);
		
		if(showBackgroundMenuItem.isSelected() && background != null) {
			
			// Get width and height of this panel and background image
			int w = this.getWidth();
			int h = this.getHeight();
			int bw = background.getWidth();
			int bh = background.getHeight();
			
			// Find out how many times the image needs to be repeated in each direction
			int rx = (int) Math.ceil((double) w / bw);
			int ry = (int) Math.ceil((double) h / bh);
			
			// Draw the image
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
	 * Setters and getters
	 */
	public JPopupMenu getCorkboardMenu()
	{
		return corkboardMenu;
	}

	public void setCorkboardMenu(JPopupMenu corkboardMenu)
	{
		this.corkboardMenu = corkboardMenu;
	}

	public JPopupMenu getCardMenu()
	{
		return cardMenu;
	}

	public void setCardMenu(JPopupMenu cardMenu)
	{
		this.cardMenu = cardMenu;
	}

	public JMenuItem getAddCardMenuItem()
	{
		return addCardMenuItem;
	}

	public void setAddCardMenuItem(JMenuItem addCardMenuItem)
	{
		this.addCardMenuItem = addCardMenuItem;
	}

	public JMenuItem getFlipAllCardsMenuItem()
	{
		return flipAllCardsMenuItem;
	}

	public void setFlipAllCardsMenuItem(JMenuItem flipAllCardsMenuItem)
	{
		this.flipAllCardsMenuItem = flipAllCardsMenuItem;
	}

	public JCheckBoxMenuItem getShowBackgroundMenuItem()
	{
		return showBackgroundMenuItem;
	}

	public void setShowBackgroundMenuItem(JCheckBoxMenuItem showBackgroundMenuItem)
	{
		this.showBackgroundMenuItem = showBackgroundMenuItem;
	}

	public JMenuItem getFlipCardMenuItem()
	{
		return flipCardMenuItem;
	}

	public void setFlipCardMenuItem(JMenuItem flipCardMenuItem)
	{
		this.flipCardMenuItem = flipCardMenuItem;
	}

	public JMenuItem getInsertBeforeMenuItem()
	{
		return insertBeforeMenuItem;
	}

	public void setInsertBeforeMenuItem(JMenuItem insertBeforeMenuItem)
	{
		this.insertBeforeMenuItem = insertBeforeMenuItem;
	}

	public JMenuItem getInsertAfterMenuItem()
	{
		return insertAfterMenuItem;
	}

	public void setInsertAfterMenuItem(JMenuItem insertAfterMenuItem)
	{
		this.insertAfterMenuItem = insertAfterMenuItem;
	}

	public JMenuItem getEditCardMenuItem()
	{
		return editCardMenuItem;
	}

	public void setEditCardMenuItem(JMenuItem editCardMenuItem)
	{
		this.editCardMenuItem = editCardMenuItem;
	}

	public JMenuItem getDuplicateCardMenuItem()
	{
		return duplicateCardMenuItem;
	}

	public void setDuplicateCardMenuItem(JMenuItem duplicateCardMenuItem)
	{
		this.duplicateCardMenuItem = duplicateCardMenuItem;
	}

	public JMenuItem getDeleteCardMenuItem()
	{
		return deleteCardMenuItem;
	}

	public void setDeleteCardMenuItem(JMenuItem deleteCardMenuItem)
	{
		this.deleteCardMenuItem = deleteCardMenuItem;
	}

	public ArrayList<CardLabel> getCardLabels()
	{
		return cardLabels;
	}

	public void setCardLabels(ArrayList<CardLabel> cardLabels)
	{
		this.cardLabels = cardLabels;
	}
	
}
