package easynotes.controllers;

import java.awt.Color;
import java.awt.event.ActionListener;

import easynotes.models.Card;
import easynotes.templates.CardTextTemplate;

public abstract class InsertAdjacentCardController extends ChildController implements ActionListener {
	
	// Register models
	protected Card oldCard;
	protected Card newCard;
	
	// Register template
	protected CardTextTemplate cardTextTemplate;

	public InsertAdjacentCardController(WindowController windowController)
	{
		
		// Initialize properties
		super(windowController);
		oldCard = new Card();
		newCard = new Card();
		
	}
	
	protected void prepareNewCard()
	{
		
		// Get the user supplied front and back text from add card view
		String frontText = cardTextTemplate.getFrontText().getText();
		String backText = cardTextTemplate.getBackText().getText();
		
		// TODO get font color
		
		// Get user RGB for background color
		Color backgroundColor = new Color(
			cardTextTemplate.getRedSpinnerModel().getNumber().intValue(),
			cardTextTemplate.getGreenSpinnerModel().getNumber().intValue(),
			cardTextTemplate.getBlueSpinnerModel().getNumber().intValue()
		);
		
		// Create and add the new card
		newCard = new Card(frontText, backText, Color.darkGray, backgroundColor);
		
	}
	
	protected abstract void addCard();

	/*
	 * Setters and getters
	 */
	public Card getOldCard() {
		return oldCard;
	}

	public void setOldCard(Card oldCard) {
		this.oldCard = oldCard;
	}

	public Card getNewCard() {
		return newCard;
	}

	public void setNewCard(Card newCard) {
		this.newCard = newCard;
	}

	public CardTextTemplate getCardTextTemplate() {
		return cardTextTemplate;
	}

	public void setCardTextTemplate(CardTextTemplate cardTextTemplate) {
		this.cardTextTemplate = cardTextTemplate;
	}
	
}
