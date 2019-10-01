package easynotes.controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import easynotes.models.Card;
import easynotes.templates.EditCardTemplate;

public class EditCardController implements ActionListener
{
	
	// Register parent controller
	private WindowController windowController;
	
	// Register model
	private Card card;
	
	// Register template
	private EditCardTemplate editCardTemplate;

	public EditCardController(WindowController windowController)
	{
		
		// Initialize properties
		this.windowController = windowController;
		editCardTemplate = new EditCardTemplate();
		card = new Card();
		
		// Add action listeners
		editCardTemplate.getActionButton().addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		// Save edits
		if(e.getSource() == editCardTemplate.getActionButton()) {
			String frontText = editCardTemplate.getFrontText().getText();
			String backText = editCardTemplate.getBackText().getText();
			
			Color fontColor =
				editCardTemplate
					.getFontColorDisplayPanel()
					.getBackground();
			
			Color backgroundColor =
				editCardTemplate
					.getBackgroundColorDisplayPanel()
					.getBackground();
			
			Card newCard = new Card(frontText, backText, fontColor, backgroundColor);
			
			windowController.editCard(card, newCard);
			
			// Hide the edit card window
			editCardTemplate.setVisible(false);
		}
		
	}

	/*
	 * Setters and getters
	 */
	public Card getCard()
	{
		return card;
	}
	
	public void setCard(Card card)
	{
		
		// Update the card model
		this.card = card;
		
		// Set the editCardTemplate fields to hold the given card's data
		editCardTemplate.getFrontText().setText(card.getFrontText());
		editCardTemplate.getBackText().setText(card.getBackText());
		
		editCardTemplate
			.getFontColorDisplayPanel()
			.setBackground(card.getFontColor());
		
		editCardTemplate
			.getBackgroundColorDisplayPanel()
			.setBackground(card.getBackgroundColor());
		
	}
	
	public EditCardTemplate getEditCardTemplate()
	{
		return editCardTemplate;
	}

	public void setEditCardTemplate(EditCardTemplate editCardTemplate)
	{
		this.editCardTemplate = editCardTemplate;
	}

}
