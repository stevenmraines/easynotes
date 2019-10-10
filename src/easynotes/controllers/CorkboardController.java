package easynotes.controllers;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import easynotes.components.CardLabel;
import easynotes.models.Card;
import easynotes.templates.CorkboardTemplate;

public class CorkboardController extends ChildController
	implements ActionListener, ChangeListener, MouseListener
{
	
	// Register template
	private CorkboardTemplate corkboardTemplate;
	
	// Register last CardLabel that was clicked
	// TODO find a better way to do this (look into findComponentAt method on corkboardTemplate)
	private CardLabel lastClickedCardLabel;
	
	// Register variable to keep track of CardLabel scale set by zoom JSlider
	private int cardLabelScale;
	
	public CorkboardController(WindowController windowController)
	{
		
		// Initialize properties
		super(windowController);
		corkboardTemplate = new CorkboardTemplate();
		cardLabelScale = 50;
		
		// Add action listeners
		corkboardTemplate.getScrollPane().addMouseListener(this);
		corkboardTemplate.getAddCardMenuItem().addActionListener(this);
		corkboardTemplate.getEditCardMenuItem().addActionListener(this);
		corkboardTemplate.getFlipAllCardsMenuItem().addActionListener(this);
		corkboardTemplate.getShowBackgroundMenuItem().addActionListener(this);
		corkboardTemplate.getDeleteCardMenuItem().addActionListener(this);
		corkboardTemplate.getInsertAfterMenuItem().addActionListener(this);
		corkboardTemplate.getInsertBeforeMenuItem().addActionListener(this);
		corkboardTemplate.getFlipCardMenuItem().addActionListener(this);
		corkboardTemplate.getDuplicateCardMenuItem().addActionListener(this);
		corkboardTemplate.getZoomSlider().addChangeListener(this);
		
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
				
				String message = "Are you sure you want to delete this card?";
				
				int confirmDelete = JOptionPane.showConfirmDialog(
					windowController.getWindowTemplate(),
					message
				);
				
				// TODO add check box to let user skip this confirmation
				if(confirmDelete == JOptionPane.OK_OPTION) {
					
					windowController.deleteCard(lastClickedCardLabel.getCard());
					
				}
				
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
				
				editCard(lastClickedCardLabel.getCard());
				
			}
			
		}
		
		// Flip a card
		if(e.getSource() == corkboardTemplate.getFlipCardMenuItem()) {
			
			if(lastClickedCardLabel != null) {
				
				windowController.flipCard(lastClickedCardLabel.getCard());
				
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
			
			boolean paintBackgroundImage =
				corkboardTemplate
					.getShowBackgroundMenuItem()
					.isSelected();
			
			corkboardTemplate
				.getCorkboardPanel()
				.setBackgroundPainted(paintBackgroundImage);
			
			corkboardTemplate.getCorkboardPanel().revalidate();
			corkboardTemplate.getCorkboardPanel().repaint();
			
		}
		
	}
	
	/*
	 * ChangeListener methods for controlling zoom slider.
	 */
	@Override
	public void stateChanged(ChangeEvent e)
	{
		
		if(e.getSource() == corkboardTemplate.getZoomSlider()) {
			
			// Get slider value
			this.cardLabelScale = ((JSlider) e.getSource()).getValue();
			
			// Resize the CardLabels
			resizeCardLabels();
			
			// Update the label which shows the current zoom percentage
			// TODO figure out how to deal with 100% label pushing slider to the left
			corkboardTemplate.getZoomPercentLabel().setText(cardLabelScale + "%");
			
		}
		
	}

	/*
	 * MouseListener methods for showing context menu on right click.
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		
		showPopup(e);
		
		// Double click on CardLabel
		if(
			e.getClickCount() == 2
			&& SwingUtilities.isLeftMouseButton(e)
			&& e.getSource() instanceof CardLabel
		) {
			
			editCard( ((CardLabel) e.getSource()).getCard() );
			
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		showPopup(e);
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		showPopup(e);
	}

	@Override
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
		
		Point popupLocation = getContextMenuLocation(e);
		
		if(e.isPopupTrigger() && e.getSource() == corkboardTemplate.getScrollPane()) {
			
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
	
	private Point getContextMenuLocation(MouseEvent e)
	{
		return new Point(e.getX(), e.getY());
	}
	
	private void resizeCardLabels()
	{
		
		Component[] components =
			corkboardTemplate
				.getCorkboardPanel()
				.getComponents();
		
		for(Component component : components) {
			
			if(component instanceof CardLabel) {
				
				((CardLabel) component).resize(cardLabelScale);
				
			}
			
		}
		
	}
	
	private void editCard(Card card)
	{
		
		// Set the card that we're trying to edit
		windowController
			.getEditCardController()
			.setCard(card);
		
		// Display the "Edit a card" window
		windowController
			.getEditCardController()
			.getEditCardTemplate()
			.setVisible(true);
		
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

	public int getCardLabelScale()
	{
		return cardLabelScale;
	}

	public void setCardLabelScale(int cardLabelScale)
	{
		this.cardLabelScale = cardLabelScale;
	}

}
