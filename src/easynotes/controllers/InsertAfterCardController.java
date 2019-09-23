package easynotes.controllers;

import java.awt.event.ActionEvent;

import easynotes.templates.InsertAfterCardTemplate;

public class InsertAfterCardController extends InsertAdjacentCardController {

	public InsertAfterCardController(WindowController windowController)
	{
		
		// Initialize properties
		super(windowController);
		cardTextTemplate = new InsertAfterCardTemplate();
		
		// Add action listeners
		cardTextTemplate.getActionButton().addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource() == cardTextTemplate.getActionButton()) {
			
			prepareNewCard();
			addCard();
			cardTextTemplate.setVisible(false);
			
		}
		
	}

	@Override
	protected void addCard() {
		windowController.insertAfter(newCard, oldCard);
	}
	
}
