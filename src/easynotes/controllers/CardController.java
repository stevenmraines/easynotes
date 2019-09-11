package easynotes.controllers;

import easynotes.models.Card;
import easynotes.views.CardTemplate;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;

/*
 * This class acts as the controller facilitating communication
 * between the card view and the card model.
 */
public class CardController implements MouseListener, ActionListener {
	// Register parent controller
	private MainController mainController;
	
	// Register models
	private Card card;
	
	// Register views
	private CardTemplate cardTemplate;
	
	public CardController(MainController mainController) {
		// Initialize properties
		this.mainController = mainController;
		cardTemplate = new CardTemplate();
		card = new Card();
		
		// Add action listeners
		cardTemplate.addMouseListener(this);
		cardTemplate.getEditCardMenuItem().addActionListener(this);
		cardTemplate.getDeleteCardMenuItem().addActionListener(this);
	}

	public CardController(MainController mainController, Card card) {
		// Initialize properties
		this.mainController = mainController;
		cardTemplate = new CardTemplate();
		this.card = card;
		
		// Add action listeners
		cardTemplate.addMouseListener(this);
		cardTemplate.getEditCardMenuItem().addActionListener(this);
		cardTemplate.getDeleteCardMenuItem().addActionListener(this);
		
		// Prepare the card template
		cardTemplate.getFrontLabel().setText(card.getFront());
		cardTemplate.getBackLabel().setText(card.getBack());
	}
	
	/*
	 * Toggle the visibility of the card template front text label and back text label.
	 */
	public void flip() {
		cardTemplate.getFrontLabel().setVisible(!cardTemplate.getFrontLabel().isVisible());
		cardTemplate.getBackLabel().setVisible(!cardTemplate.getBackLabel().isVisible());
	}

	/*
	 * MouseListener methods for handling display of context menu
	 * and flipping specific cards.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Hide any other currently visible context menus
		// TODO have an event fire to automatically do this when any context menu is shown?
		mainController.hideAllContextMenus();
		
		// CTRL + left click to flip a specific card
		if(SwingUtilities.isLeftMouseButton(e) && e.getModifiersEx() == MouseEvent.CTRL_DOWN_MASK) {
			flip();
		}
		
		// Show context menu on card
		if(SwingUtilities.isRightMouseButton(e)) {
			Point menuLocation = cardTemplate.getLocation();
			menuLocation.translate(e.getX(), e.getY());
			cardTemplate.getContextMenu().setLocation(menuLocation);
			cardTemplate.getContextMenu().setVisible(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	/*
	 * ActionListener methods for handling context menu option clicks.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Hide any other visible context menus
		mainController.hideAllContextMenus();
		
		// Edit a card menu item clicked
		if(e.getSource() == cardTemplate.getEditCardMenuItem()) {
			EditCardController editCardController = new EditCardController(this);
			
			editCardController
				.getEditCardTemplate()
				.getFrontText()
				.setText(card.getFront());
			
			editCardController
				.getEditCardTemplate()
				.getBackText()
				.setText(card.getBack());
			
			editCardController.getEditCardTemplate().showModal();
		}
		
		// Delete a card menu item clicked
		if(e.getSource() == cardTemplate.getDeleteCardMenuItem()) {
			mainController.deleteCardController(this);
		}
	}

	/*
	 * Setters and getters.
	 */
	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public CardTemplate getCardTemplate() {
		return cardTemplate;
	}

	public void setCardTemplate(CardTemplate cardTemplate) {
		this.cardTemplate = cardTemplate;
	}

}
