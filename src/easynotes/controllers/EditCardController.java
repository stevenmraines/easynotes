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
			// TODO save font color
			
			Color backgroundColor = new Color(
				editCardTemplate.getRedSpinnerModel().getNumber().intValue(),
				editCardTemplate.getGreenSpinnerModel().getNumber().intValue(),
				editCardTemplate.getBlueSpinnerModel().getNumber().intValue()
			);
			
			int index = windowController.getCards().indexOf(card);
			
			if(index >= 0) {
				
				windowController.getCards().get(index).setFrontText(frontText);
				windowController.getCards().get(index).setBackText(backText);
				windowController.getCards().get(index).setBackgroundColor(backgroundColor);
				
			}
			
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
		// TODO set RGB for font color
		editCardTemplate.getRedSpinnerModel().setValue(card.getBackgroundColor().getRed());
		editCardTemplate.getGreenSpinnerModel().setValue(card.getBackgroundColor().getGreen());
		editCardTemplate.getBlueSpinnerModel().setValue(card.getBackgroundColor().getBlue());
		
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
