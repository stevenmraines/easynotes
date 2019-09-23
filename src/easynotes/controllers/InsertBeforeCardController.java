package easynotes.controllers;

import java.awt.event.ActionEvent;

import easynotes.templates.InsertBeforeCardTemplate;

public class InsertBeforeCardController extends InsertAdjacentCardController {

	public InsertBeforeCardController(WindowController windowController)
	{
		
		// Initialize properties
		super(windowController);
		cardTextTemplate = new InsertBeforeCardTemplate();
		
		// Add action listeners
		cardTextTemplate.getActionButton().addActionListener(this);
		
	}
	
	// TODO DRY this up a bit in InsertAdjacentCardController somehow
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
		windowController.insertBefore(newCard, oldCard);
	}
	
}
