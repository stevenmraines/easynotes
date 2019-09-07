package easynotes.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import easynotes.views.EditCardTemplate;

/*
 * The EditCardController is tied to an instance of a CardController,
 * which it takes in the constructor. Because of this, it does not
 * need it's own model, or a MainController instance.
 */
public class EditCardController implements ActionListener {
	// Register CardController
	private CardController cardController;
	
	// Register template
	private EditCardTemplate editCardTemplate;

	public EditCardController(CardController cardController) {
		// Initialize properties
		this.cardController = cardController;
		editCardTemplate = new EditCardTemplate();
		
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
			cardController.getCard().setFront(editCardTemplate.getFrontText().getText());
			cardController.getCard().setBack(editCardTemplate.getBackText().getText());
			// do this bit manually for now
			cardController.getCardTemplate().getFrontLabel().setText(editCardTemplate.getFrontText().getText());
			cardController.getCardTemplate().getBackLabel().setText(editCardTemplate.getBackText().getText());
			editCardTemplate.hideModal();
		}
	}

}
