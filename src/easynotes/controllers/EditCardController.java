package easynotes.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import easynotes.models.Card;
import easynotes.views.EditCardTemplate;

public class EditCardController implements ActionListener {
	// Register main controller
	private MainController mainController;
	
	// Register models
	private Card card;
	
	// Register template
	private EditCardTemplate editCardTemplate;

	public EditCardController(MainController mainController) {
		// Initialize properties
		this.mainController = mainController;
		editCardTemplate = new EditCardTemplate();
		card = new Card();
		
		// Add action listeners
		editCardTemplate.getActionButton().addActionListener(this);
	}

	public EditCardTemplate getEditCardTemplate() {
		return editCardTemplate;
	}

	public void setEditCardTemplate(EditCardTemplate editCardTemplate) {
		this.editCardTemplate = editCardTemplate;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == editCardTemplate.getActionButton()) {
			card.setFront(editCardTemplate.getFrontText().getText());
			card.setBack(editCardTemplate.getBackText().getText());
			// Now we need some hook into the cardController that is being edited...
			
			editCardTemplate.hideModal();
		}
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

}
