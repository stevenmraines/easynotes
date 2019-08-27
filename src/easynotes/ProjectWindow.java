package easynotes;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

public class ProjectWindow implements MouseListener, ActionListener {
	private JFrame window;
	private JPanel mainPanel;
	private JPopupMenu contextMenu;
	private JPopupMenu cardMenu;
	private JMenuItem addCardItem;
	private JMenuItem deleteCardItem;
	private JMenuItem editCardItem;
	private IndexCardController cards;
	private IndexCard clickedCard;
	
	public ProjectWindow() {
		cards = new IndexCardController();
		initGui();
	}
	
	public void initGui() {
		// Init components
		window = new JFrame("New Project");
		mainPanel = new JPanel();
		contextMenu = new JPopupMenu();
		cardMenu = new JPopupMenu();
		addCardItem = new JMenuItem("Add a card");
		editCardItem = new JMenuItem("Edit this card");
		deleteCardItem = new JMenuItem("Delete this card");
		
		// Register event listeners
		mainPanel.addMouseListener(this);
		addCardItem.addMouseListener(this);
		editCardItem.addMouseListener(this);
		deleteCardItem.addMouseListener(this);
		
		// Add components
		window.add(mainPanel);
		mainPanel.add(contextMenu);
		mainPanel.add(cardMenu);
		contextMenu.add(addCardItem);
		cardMenu.add(editCardItem);
		cardMenu.add(deleteCardItem);
		
		// Prepare and display main window
		window.setMinimumSize(new Dimension(500, 500));
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	private void addCard() {
		// TODO Auto-generated method stub
		cards.addCard(new IndexCard("Testing", "This is a test."));
		addCardsToProjectWindow();
	}
	
	private void editCard() {
		
	}
	
	private void deleteCard(IndexCard card) {
		cards.removeCard(cards.getCards().indexOf(card));
		addCardsToProjectWindow();
	}
	
	public void addCardsToProjectWindow() {
		// Remove everything
		for(int i = 0; i < cards.getSize(); i++) {
			mainPanel.remove(cards.getCards().get(i));
		}
		
		// Then re-add it
		for(int i = 0; i < cards.getSize(); i++) {
			mainPanel.add(cards.getCards().get(i));
		}
		
		// Repaint the frame
		mainPanel.validate();
		
		// Re-add mouse listeners
		addMouseListenerToCards();
	}
	
	public void addMouseListenerToCards() {
		for(int i = 0; i < cards.getSize(); i++) {
			cards.getCards().get(i).addMouseListener(this);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		contextMenu.setVisible(false);
		cardMenu.setVisible(false);
		
		// Right click on main panel
		if(SwingUtilities.isRightMouseButton(e) && e.getSource() == mainPanel) {
			// Show context menu
			contextMenu.setLocation(e.getX(), e.getY());
			contextMenu.setVisible(true);
		}
		
		// Right click on a card
		if(SwingUtilities.isRightMouseButton(e) && e.getSource() instanceof IndexCard) {
			clickedCard = (IndexCard) e.getSource();
			cardMenu.setLocation(e.getX(), e.getY());
			cardMenu.setVisible(true);
		}
		
		// Add card
		if(SwingUtilities.isLeftMouseButton(e) && e.getSource() == addCardItem) {
			addCard();
			contextMenu.setVisible(false);
		}
		
		// Edit card
		if(SwingUtilities.isLeftMouseButton(e) && e.getSource() == editCardItem) {
			editCard();
			cardMenu.setVisible(false);
		}
		
		// Delete card
		if(SwingUtilities.isLeftMouseButton(e) && e.getSource() == deleteCardItem) {
			deleteCard(clickedCard);
			cardMenu.setVisible(false);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
