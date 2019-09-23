package easynotes.controllers;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;

import easynotes.components.CardLabel;
import easynotes.templates.CorkboardTemplate;

public class CorkboardController implements ActionListener, MouseListener
{
	
	// Register parent controller
	private WindowController windowController;
	
	// Register template
	private CorkboardTemplate corkboardTemplate;
	
	// Register last CardLabel that was clicked
	// TODO find a better way to do this (look into findComponentAt method on corkboardTemplate)
	private CardLabel lastClickedCardLabel;
	
	public CorkboardController(WindowController windowController)
	{
		
		// Initialize properties
		this.windowController = windowController;
		corkboardTemplate = new CorkboardTemplate();
		
		// Add action listeners
		corkboardTemplate.addMouseListener(this);
		corkboardTemplate.getAddCardMenuItem().addActionListener(this);
		corkboardTemplate.getEditCardMenuItem().addActionListener(this);
		corkboardTemplate.getFlipAllCardsMenuItem().addActionListener(this);
		corkboardTemplate.getShowBackgroundMenuItem().addActionListener(this);
		corkboardTemplate.getDeleteCardMenuItem().addActionListener(this);
		corkboardTemplate.getInsertAfterMenuItem().addActionListener(this);
		corkboardTemplate.getInsertBeforeMenuItem().addActionListener(this);
		corkboardTemplate.getFlipCardMenuItem().addActionListener(this);
		corkboardTemplate.getDuplicateCardMenuItem().addActionListener(this);
		
	}
	
	/*
	 * ActionListener methods for handling context menu item clicks.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		// Add a card
		if(e.getSource() == corkboardTemplate.getAddCardMenuItem()) {
			
			windowController
				.getAddCardController()
				.getAddCardTemplate()
				.setVisible(true);
			
		}
		
		// Delete a card
		if(e.getSource() == corkboardTemplate.getDeleteCardMenuItem()) {
			
			if(lastClickedCardLabel != null) {
				
				windowController.deleteCard(lastClickedCardLabel.getCard());
				
			}
			
		}
		
		// Duplicate a card
		if(e.getSource() == corkboardTemplate.getDuplicateCardMenuItem()) {
			
			if(lastClickedCardLabel != null) {
				
				windowController.duplicateCard(lastClickedCardLabel.getCard());
				
			}
			
		}
		
		// Edit a card
		if(e.getSource() == corkboardTemplate.getEditCardMenuItem()) {
			
			if(lastClickedCardLabel != null) {
				
				// Set the card that we're trying to edit
				windowController
					.getEditCardController()
					.setCard(lastClickedCardLabel.getCard());
				
				// Display the "Edit a card" window
				windowController
					.getEditCardController()
					.getEditCardTemplate()
					.setVisible(true);
				
			}
			
		}
		
		// Flip all cards
		if(e.getSource() == corkboardTemplate.getFlipAllCardsMenuItem()) {
			windowController.flipAllCards();
		}
		
		// Insert after
		if(e.getSource() == corkboardTemplate.getInsertAfterMenuItem()) {
			
		}
		
		// Insert before
		if(e.getSource() == corkboardTemplate.getInsertBeforeMenuItem()) {
			
		}
		
		// Show background image
		if(e.getSource() == corkboardTemplate.getShowBackgroundMenuItem()) {
			corkboardTemplate.repaint();
		}
		
	}

	/*
	 * MouseListener methods for showing context menu on right click.
	 */
	public void mouseClicked(MouseEvent e)
	{
		showPopup(e);
	}

	public void mousePressed(MouseEvent e)
	{
		showPopup(e);
	}
	
	public void mouseEntered(MouseEvent e)
	{
		showPopup(e);
	}

	public void mouseExited(MouseEvent e)
	{
		showPopup(e);
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		
		showPopup(e);
		
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
			
		}
		
		// Ctrl + Left click on a CardLabel
		if(SwingUtilities.isLeftMouseButton(e) && e.getSource() instanceof CardLabel
				&& e.getModifiersEx() == MouseEvent.CTRL_DOWN_MASK) {
			
			CardLabel cardLabel = (CardLabel) e.getSource();
			windowController.flipCard(cardLabel.getCard());
			
		}
		
	}
	
	private void showPopup(MouseEvent e)
	{
		
		Point popupLocation = getAdjustedPointerLocation(e);
		
		if(e.isPopupTrigger() && e.getSource() instanceof CorkboardTemplate) {
			
			corkboardTemplate
				.getCorkboardMenu()
				.show(e.getComponent(), popupLocation.x, popupLocation.y);
			
		}
		
		if(e.isPopupTrigger() && e.getSource() instanceof CardLabel) {
			
			corkboardTemplate
				.getCardMenu()
				.show(e.getComponent(), popupLocation.x, popupLocation.y);
			
		}
		
	}
	
	// TODO WHAT MORE DO YOU WANT?! GIVE ME THE CORRECT POINTER LOCATION!!!
	private Point getAdjustedPointerLocation(MouseEvent e)
	{
		
		// Get the WindowTemplate JFrame location
		Point frameLocation = windowController.getWindowTemplate().getLocation();
		
		// Get the corkboardTemplate JPanel location
		Point panelLocation = corkboardTemplate.getLocation();
		
		// Get the mouse click location relative to window location
		Point popupLocation = new Point(e.getX(), e.getY());
		popupLocation.translate(frameLocation.x, frameLocation.y);
		popupLocation.translate(panelLocation.x, panelLocation.y);
		
		if(e.getSource() instanceof CardLabel) {
			
			// Get the CardLabel JLabel location
			Point labelLocation = ((CardLabel) e.getSource()).getLocation();
			popupLocation.translate(labelLocation.x, labelLocation.y);
			
		}
		
		return popupLocation;
		
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
