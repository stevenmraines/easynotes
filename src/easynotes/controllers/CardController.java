package easynotes.controllers;

import easynotes.models.Card;
import easynotes.views.CardTemplate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;

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
		card.addPropertyChangeListener(cardTemplate);
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
		cardTemplate.getLabel().setText(card.getFront());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mainController.hideAllContextMenus();
		
		if(SwingUtilities.isRightMouseButton(e)) {
			cardTemplate.getContextMenu().setLocation(e.getX(), e.getY());
			cardTemplate.getContextMenu().setVisible(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

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

	@Override
	public void actionPerformed(ActionEvent e) {
		mainController.hideAllContextMenus();
		
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
		
		if(e.getSource() == cardTemplate.getDeleteCardMenuItem()) {
			
		}
	}

}
