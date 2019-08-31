package easynotes.controllers;

import easynotes.models.Card;
import easynotes.views.CardTemplate;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;

public class CardController implements MouseListener {
	// Register parent controller
	private MainController mainController;
	
	// Register models
	private Card card;
	
	// Register views
	private CardTemplate cardTemplate;
	
	public CardController(MainController mainController) {
		this.mainController = mainController;
		cardTemplate = new CardTemplate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
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

}
