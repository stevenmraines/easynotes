package easynotes.controllers;

import java.awt.Color;
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
		
		// Prepare template
		editCardTemplate.getRedText().setText(Integer.toString(this.cardController.getCard().getColor().getRed()));
		editCardTemplate.getGreenText().setText(Integer.toString(this.cardController.getCard().getColor().getGreen()));
		editCardTemplate.getBlueText().setText(Integer.toString(this.cardController.getCard().getColor().getBlue()));
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
			cardController.getCard().setColor(new Color(
				Integer.parseInt(editCardTemplate.getRedText().getText()),
				Integer.parseInt(editCardTemplate.getGreenText().getText()),
				Integer.parseInt(editCardTemplate.getBlueText().getText())
			));
			// Do this manually until I can figure out the propertyChangeListener thing
			cardController.getCardTemplate().getFrontLabel().setText(editCardTemplate.getFrontText().getText());
			cardController.getCardTemplate().getBackLabel().setText(editCardTemplate.getBackText().getText());
			cardController.getCardTemplate().setBackground(cardController.getCard().getColor());
			editCardTemplate.hideModal();
		}
	}

}
