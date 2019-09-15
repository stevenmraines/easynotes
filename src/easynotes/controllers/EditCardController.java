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
		editCardTemplate.getFrontText().setText(cardController.getCard().getFront());
		editCardTemplate.getBackText().setText(cardController.getCard().getBack());
		
		int red = this.cardController.getCard().getColor().getRed();
		int green = this.cardController.getCard().getColor().getGreen();
		int blue = this.cardController.getCard().getColor().getBlue();
		
		editCardTemplate.getRedSpinnerModel().setValue(red);
		editCardTemplate.getGreenSpinnerModel().setValue(green);
		editCardTemplate.getBlueSpinnerModel().setValue(blue);
	}

	public EditCardTemplate getEditCardTemplate() {
		return editCardTemplate;
	}

	public void setEditCardTemplate(EditCardTemplate editCardTemplate) {
		this.editCardTemplate = editCardTemplate;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Save edits
		if(e.getSource() == editCardTemplate.getActionButton()) {
			String frontText = editCardTemplate.getFrontText().getText();
			String backText = editCardTemplate.getBackText().getText();
			
			cardController.getCard().setFront(frontText);
			cardController.getCard().setBack(backText);
			cardController.getCard().setColor(new Color(
				editCardTemplate.getRedSpinnerModel().getNumber().intValue(),
				editCardTemplate.getGreenSpinnerModel().getNumber().intValue(),
				editCardTemplate.getBlueSpinnerModel().getNumber().intValue()
			));
			
			// TODO Do this manually until I can figure out the propertyChangeListener thing
			cardController.getCardTemplate().getFrontLabel().setText(editCardTemplate.getFrontText().getText());
			cardController.getCardTemplate().getBackLabel().setText(editCardTemplate.getBackText().getText());
			cardController.getCardTemplate().setBackground(cardController.getCard().getColor());
			
			// Hide the edit card window
			editCardTemplate.getFrame().setVisible(false);
		}
	}

}
