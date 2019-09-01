package easynotes.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import easynotes.models.Card;
import easynotes.views.AddCardTemplate;

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
			String frontText = addCardTemplate.getFrontText().getText();
			String backText = addCardTemplate.getBackText().getText();
			addNewCard(frontText, backText);
		}
	}

	public AddCardTemplate getAddCardTemplate() {
		return addCardTemplate;
	}

	public void setAddCardTemplate(AddCardTemplate addCardTemplate) {
		this.addCardTemplate = addCardTemplate;
	}
	
	private void addNewCard(String frontText, String backText) {
		Card newCard = new Card(frontText, backText);
		CardController cc = new CardController(mainController);
		cc.setCard(newCard);
		cc.getCardTemplate().getLabel().setText(newCard.getFront());
		cc.getCardTemplate().addMouseListener(cc);
		mainController.getCardControllers().add(cc);
		mainController.getProjectController().getProjectTemplate().add(cc.getCardTemplate());
		addCardTemplate.hideModal();
	}

}
