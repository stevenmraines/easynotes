package easynotes.controllers;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

import easynotes.components.CardLabel;
import easynotes.models.Card;
import easynotes.templates.CorkboardTemplate;

public class CorkboardController extends TransferHandler
		implements ActionListener, MouseListener, MouseMotionListener
{

	private static final long serialVersionUID = -626676050305634497L;

	// Register parent controller
	private WindowController windowController;
	
	// Register template
	private CorkboardTemplate corkboardTemplate;
	
	// Register last CardLabel that was clicked
	// TODO find a better way to do this (look into findComponentAt method on corkboardTemplate)
	private CardLabel lastClickedCardLabel;
	
	public CorkboardController(WindowController windowController)
	{
		
		/*
		 * Call the TransferHandler constructor with
		 * the CardLabel property we want to transfer
		 */
		super("card");
		
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
	 * TransferHandler methods
	 */
	@Override
	public boolean canImport(JComponent component, DataFlavor[] transferFlavors)
	{
		return component instanceof CardLabel
				&& transferFlavors[0] == CardLabel.getCardLabelFlavor();
	}
	
	@Override
	public Transferable createTransferable(JComponent component)
	{
		
		if(component instanceof CardLabel) {
			return (CardLabel) component;
		}
		
		return null;
		
	}
	
	@Override
	public boolean importData(JComponent component, Transferable transferable)
	{
		
		if(component instanceof CardLabel) {
			
			try {
				
				// Get the CardLabel DataFlavor
				DataFlavor cardLabelFlavor = CardLabel.getCardLabelFlavor();
				
				if(transferable.isDataFlavorSupported(cardLabelFlavor)) {
					
					// Get the source of the drag
					/*
					 * TODO seems like deserialization is creating a new Card object
					 * which is changing the object ID, causing it not to match it's
					 * counterpart in the WindowController Card ArrayList
					 * 
					 * Maybe getting the entire CardLabel as the transfer data will help?
					 */
					Card dragCard = (Card) transferable.getTransferData(cardLabelFlavor);
					
					// Get the card that's being dropped onto
					Card dropCard = ((CardLabel) component).getCard();
					
					// Add the dragged card in the drop card's position and delete the duplicate
//					windowController.deleteCard(dragCard);
//					windowController.insertBefore(dragCard, dropCard);
					windowController.dragAndDropCard(dragCard, dropCard);
					
					return true;
					
				}
				
			} catch (UnsupportedFlavorException | IOException e) {
				return false;
			}
			
		}
		
		return false;
		
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
			
			windowController
				.getInsertAfterCardController()
				.setOldCard(this.lastClickedCardLabel.getCard());
			
			windowController
				.getInsertAfterCardController()
				.getCardTextTemplate()
				.setVisible(true);
			
		}
		
		// Insert before
		if(e.getSource() == corkboardTemplate.getInsertBeforeMenuItem()) {
			
			windowController
				.getInsertBeforeCardController()
				.setOldCard(this.lastClickedCardLabel.getCard());
			
			windowController
				.getInsertBeforeCardController()
				.getCardTextTemplate()
				.setVisible(true);
			
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
	
	/*
	 * MouseMotionListener methods
	 */
	public void mouseMoved(MouseEvent e) {}
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		
		// CardLabel drag event
		if(e.getSource() instanceof CardLabel) {
			
			// TransferHandler needs to export this event as a drag
			this.exportAsDrag(((CardLabel) e.getSource()), e, TransferHandler.COPY);
			
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
	
	private Point getAdjustedPointerLocation(MouseEvent e)
	{
		
		// Get the mouse click location relative to window location
		return new Point(e.getX(), e.getY());
		
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
