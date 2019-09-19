package easynotes.controllers;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import easynotes.components.CardLabel;
import easynotes.models.Card;
import easynotes.templates.CorkboardTemplate;

public class CorkboardController implements ActionListener, MouseListener
{
	
	// Register parent controller
	private WindowController windowController;
	
	// Register template
	private CorkboardTemplate corkboardTemplate;
	
	// Register last CardLabel that was clicked
	private CardLabel lastClickedCardLabel;
	
	public CorkboardController(WindowController windowController)
	{
		
		// Initialize properties
		this.windowController = windowController;
		corkboardTemplate = new CorkboardTemplate();
		
		// Add action listeners
		corkboardTemplate.addMouseListener(this);
		corkboardTemplate.getAddCardMenuItem().addActionListener(this);
		corkboardTemplate.getFlipAllCardsMenuItem().addActionListener(this);
		corkboardTemplate.getShowBackgroundMenuItem().addActionListener(this);
		
	}
	
	private void flipAllCards()
	{
		
		// Get all instances of the Card model from the parent controller
		ArrayList<Card> cards = windowController.getCards();
		
		// Flip the cards
		for(int i = 0; i < cards.size(); i++) {
			cards.get(i).flip();
		}
		
	}
	
	private void hideAllContextMenus()
	{
		corkboardTemplate.getCorkboardMenu().setVisible(false);
		corkboardTemplate.getCardMenu().setVisible(false);
	}
	
	/*
	 * ActionListener methods for handling context menu item clicks.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		// Hide any currently visible context menus
		hideAllContextMenus();
		
		// Add a card
		if(e.getSource() == corkboardTemplate.getAddCardMenuItem()) {
			windowController
				.getAddCardController()
				.getAddCardTemplate()
				.setVisible(true);
		}
		
		// Edit a card
		if(e.getSource() == corkboardTemplate.getEditCardMenuItem()) {
			
			if(lastClickedCardLabel != null
					&& lastClickedCardLabel instanceof CardLabel) {
				
				// Set the card that we're trying to edit
				// TODO find a better way to do this
				windowController.getEditCardController().setCard(lastClickedCardLabel.getCard());
				
				// Display the "Edit a card" window
				windowController
					.getEditCardController()
					.getEditCardTemplate()
					.setVisible(true);
				
			}
			
		}
		
		// Flip all cards
		if(e.getSource() == corkboardTemplate.getFlipAllCardsMenuItem()) {
			flipAllCards();
		}
		
		// Show background image
		if(e.getSource() == corkboardTemplate.getShowBackgroundMenuItem()) {
			corkboardTemplate.repaint();
		}
		
	}

	/*
	 * MouseListener methods for showing context menu on right click.
	 */
	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		
		// Hide any currently visible context menus
		hideAllContextMenus();
		
		// Right click on corkboard view
		if(SwingUtilities.isRightMouseButton(e) && e.getSource() instanceof CorkboardTemplate) {
			
			// Get location of click relative to the main JFrame of the application
			Point menuLocation = windowController.getWindowTemplate().getLocation();
			menuLocation.translate(e.getX(), e.getY());
			
			// Set location of context menu and make it visible
			corkboardTemplate.getCorkboardMenu().setLocation(menuLocation);
			corkboardTemplate.getCorkboardMenu().setVisible(true);
			
		}
		
		// Right click on a CardLabel
		if(SwingUtilities.isRightMouseButton(e) && e.getSource() instanceof CardLabel) {
			
			// Record the last CardLabel which received a right click
			lastClickedCardLabel = (CardLabel) e.getSource();
		
			// TODO DRY up some of this code
			// Get location of click relative to the main JFrame of the application
			Point menuLocation = windowController.getWindowTemplate().getLocation();
			menuLocation.translate(e.getX(), e.getY());
			
			// Set location of CardLabel context menu and make it visible
			corkboardTemplate.getCardMenu().setLocation(menuLocation);
			corkboardTemplate.getCardMenu().setVisible(true);
			
		}
		
		// Ctrl + Left click on a CardLabel
		if(SwingUtilities.isLeftMouseButton(e) && e.getSource() instanceof CardLabel
				&& e.getModifiersEx() == MouseEvent.CTRL_DOWN_MASK) {
			
			CardLabel cardLabel = (CardLabel) e.getSource();
			cardLabel.getCard().flip();
			
		}
		
	}

	/*
	 * Setters and getters
	 */
	public CorkboardTemplate getCorkboardTemplate()
	{
		return corkboardTemplate;
	}

	public void setCorkboardTemplate(CorkboardTemplate corkboardTemplate)
	{
		this.corkboardTemplate = corkboardTemplate;
	}

}
