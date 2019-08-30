package easynotes.controllers;

import easynotes.models.Card;
import easynotes.views.CardTemplate;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

/*
 * If this needs to keep an arraylist of cardtemplates,
 * then this functionality seems to be duplicated from
 * projectcontroller. Perhaps that class should be
 * updated to take an arraylist of cardtemplates instead
 * of an arraylist of cards?
 * 
 * This class may be redundant.
 */
public class CardController implements MouseListener {
	// Register parent controller
	private MainController mainController;
	
	// Register models
	private Card card;
	
	// Register views
	private ArrayList<CardTemplate> cardTemplates;
	
	public CardController(MainController mainController) {
		this.mainController = mainController;
		cardTemplates = new ArrayList<CardTemplate>();
		setMouseHandlers();
	}
	
	private void setMouseHandlers() {
		for(int i = 0; i < cardTemplates.size(); i++) {
			cardTemplates.get(i).addMouseListener(this);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e)) {
			try {
				int index = cardTemplates.indexOf(e.getSource());
				cardTemplates.get(index).getContextMenu().setLocation(e.getX(), e.getY());
				cardTemplates.get(index).getContextMenu().setVisible(true);
			} catch(ArrayIndexOutOfBoundsException ev) {}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
