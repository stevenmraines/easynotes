package easynotes.controllers;

import easynotes.models.Card;
import easynotes.views.CardTemplate;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

/*
 * This class acts as the controller facilitating communication
 * between the card view and the card model.
 */
// TODO extend mouselistener and mousemotionlistener so I don't have to implement unused methods?
public class CardController implements MouseListener, MouseMotionListener, ActionListener {
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
		cardTemplate.addMouseMotionListener(this);
		cardTemplate.getEditCardMenuItem().addActionListener(this);
		cardTemplate.getDeleteCardMenuItem().addActionListener(this);
		cardTemplate.getDuplicateCardMenuItem().addActionListener(this);
	}

	public CardController(MainController mainController, Card card) {
		// Initialize properties
		this.mainController = mainController;
		cardTemplate = new CardTemplate();
		this.card = card;
		
		// Add action listeners
		cardTemplate.addMouseListener(this);
		cardTemplate.addMouseMotionListener(this);
		cardTemplate.getEditCardMenuItem().addActionListener(this);
		cardTemplate.getDeleteCardMenuItem().addActionListener(this);
		cardTemplate.getDuplicateCardMenuItem().addActionListener(this);
		
		// Prepare the card template
		cardTemplate.setBackground(card.getColor());
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
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Hide any other currently visible context menus
		// TODO have an event fire to automatically do this when any context menu is shown?
		mainController.hideAllContextMenus();
		
		// Click and drag
		// TODO figure out how to make sure card being replaced isn't this card
		if(SwingUtilities.isLeftMouseButton(e)) {
			// Remove this card
			mainController.getCardControllers().remove(mainController.getCardControllers().indexOf(this));
			
			// Get the card that the user is trying to swap with the current card
			CardController swapCard = mainController.getCardControllerInCoordinates(e.getX(), e.getY());
			
			// Get index of swapCard
			int index = mainController.getCardControllers().indexOf(swapCard);
			
			if(index >= 0) {
				// Re-add it in new index
				mainController.addCardController(index, this);				
			}
		}
		
		// CTRL + left click to flip a specific card
		if(SwingUtilities.isLeftMouseButton(e) && e.getModifiersEx() == MouseEvent.CTRL_DOWN_MASK) {
			flip();
		}
		
		// Show context menu on card
		if(SwingUtilities.isRightMouseButton(e)) {
			// Get locations of main JFrame of application and this cardTemplate
			Point frameLocation = mainController.getFrame().getLocation();
			Point cardTemplateLocation = cardTemplate.getLocation();
			Point menuLocation = new Point();
			
			// Get location for context menu relative to JFrame, cardTemplate, and click location
			menuLocation.translate((int)frameLocation.getX(), (int)frameLocation.getY());
			menuLocation.translate((int)cardTemplateLocation.getX(), (int)cardTemplateLocation.getY());
			menuLocation.translate(e.getX(), e.getY());
			
			// Set X and Y for context menu and display it
			cardTemplate.getContextMenu().setLocation(menuLocation);
			cardTemplate.getContextMenu().setVisible(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	/*
	 * MouseMotionListener methods for handling click and drag events
	 * for reordering cards.
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {}
	
	/*
	 * ActionListener methods for handling context menu option clicks.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Hide any other visible context menus
		mainController.hideAllContextMenus();
		
		// Duplicate a card menu item clicked
		if(e.getSource() == cardTemplate.getDuplicateCardMenuItem()) {
			mainController.duplicateCardController(this);
		}
		
		// Edit a card menu item clicked
		if(e.getSource() == cardTemplate.getEditCardMenuItem()) {
			// TODO make this an instance variable of the class?
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
