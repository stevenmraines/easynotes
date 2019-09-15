package easynotes.controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import easynotes.models.Card;
import easynotes.views.AddCardTemplate;

/*
 * This class acts as the controller for the add card view.
 */
public class AddCardController implements ActionListener {
	// Register parent controller
	private MainController mainController;
	
	// Register views
	private AddCardTemplate addCardTemplate;
	
	public AddCardController(MainController mainController) {
		// Initialize properties
		this.mainController = mainController;
		addCardTemplate = new AddCardTemplate();
		
		// Add action listeners
		addCardTemplate.getActionButton().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Add a card
		if(e.getSource() == addCardTemplate.getActionButton()) {
			// Get the user supplied front and back text from add card view
			String frontText = addCardTemplate.getFrontText().getText();
			String backText = addCardTemplate.getBackText().getText();
			
			// Get user RGB from add card view
			Color color = new Color(
				addCardTemplate.getRedSpinnerModel().getNumber().intValue(),
				addCardTemplate.getGreenSpinnerModel().getNumber().intValue(),
				addCardTemplate.getBlueSpinnerModel().getNumber().intValue()
			);
			
			// Create the new Card and CardController and add them
			Card newCard = new Card(frontText, backText, color);
			CardController newCardController = new CardController(mainController, newCard);
			mainController.addCardController(newCardController);
			
			// Hide the add card window
			addCardTemplate.setVisible(false);
		}
	}

	/*
	 * Setters and getters.
	 */
	public AddCardTemplate getAddCardTemplate() {
		return addCardTemplate;
	}

	public void setAddCardTemplate(AddCardTemplate addCardTemplate) {
		this.addCardTemplate = addCardTemplate;
	}

}
