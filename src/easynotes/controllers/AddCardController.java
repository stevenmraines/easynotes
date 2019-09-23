package easynotes.controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import easynotes.models.Card;
import easynotes.templates.AddCardTemplate;

public class AddCardController implements ActionListener
{
	
	// Register parent controller
	private WindowController windowController;
	
	// Register template
	private AddCardTemplate addCardTemplate;
	
	public AddCardController(WindowController windowController)
	{
		
		// Initialize properties
		this.windowController = windowController;
		addCardTemplate = new AddCardTemplate();
		
		// Add action listeners
		addCardTemplate.getActionButton().addActionListener(this);
		
	}

	/*
	 * Action listener method for handling create card button click
	 * TODO clean this up with some abstraction along with EditCardController
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		// Add a card
		if(e.getSource() == addCardTemplate.getActionButton()) {
			
			// Get the user supplied front and back text from add card view
			String frontText = addCardTemplate.getFrontText().getText();
			String backText = addCardTemplate.getBackText().getText();
			
			// TODO get font color
			
			// Get user RGB for background color
			Color backgroundColor = new Color(
				addCardTemplate.getRedSpinnerModel().getNumber().intValue(),
				addCardTemplate.getGreenSpinnerModel().getNumber().intValue(),
				addCardTemplate.getBlueSpinnerModel().getNumber().intValue()
			);
			
			// Create and add the new card
			Card card = new Card(frontText, backText, Color.darkGray, backgroundColor);
			windowController.addCard(card);
			
			// Hide the add card window
			addCardTemplate.setVisible(false);
			
		}
		
	}

	/*
	 * Setters and getters
	 */
	public AddCardTemplate getAddCardTemplate()
	{
		return addCardTemplate;
	}

	public void setAddCardTemplate(AddCardTemplate addCardTemplate)
	{
		this.addCardTemplate = addCardTemplate;
	}

}
