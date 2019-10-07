package easynotes.controllers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import easynotes.components.CardLabel;
import easynotes.models.Card;
import easynotes.templates.WindowTemplate;

public class WindowController implements ActionListener, KeyListener
{
	
	// Register child controllers
	private CorkboardController corkboardController;
	private AddCardController addCardController;
	private EditCardController editCardController;
	// TODO these classes are probably not needed, could get by with just AddCardTemplate
	private InsertAfterCardController insertAfterCardController;
	private InsertBeforeCardController insertBeforeCardController;
	
	// Register models
	private ArrayList<Card> cards;
	
	// Register template
	private WindowTemplate windowTemplate;
	
	public WindowController()
	{
		
		// Initialize properties
		corkboardController = new CorkboardController(this);
		addCardController = new AddCardController(this);
		editCardController = new EditCardController(this);
		insertAfterCardController = new InsertAfterCardController(this);
		insertBeforeCardController = new InsertBeforeCardController(this);
		windowTemplate = new WindowTemplate();
		cards = new ArrayList<Card>();
		
		// Add action listeners
		windowTemplate.getNewProjectMenuItem().addActionListener(this);
		windowTemplate.getSaveProjectMenuItem().addActionListener(this);
		windowTemplate.getLoadProjectMenuItem().addActionListener(this);
		windowTemplate.getAboutMenuItem().addActionListener(this);
		windowTemplate.getFileChooser().addActionListener(this);
		windowTemplate.addKeyListener(this);
		
		// Prepare window for display
		windowTemplate.add(corkboardController.getCorkboardTemplate());
		
	}
	
	/*
	 * Card management methods for views
	 */
	private void syncCardsWithViews()
	{
		
		// Remove all cards from all views
		removeCardsFromViews();
		
		// Add all cards to all views
		addCardsToViews();
		
		// Repaint everything
		repaintViews();
		
	}
	
	private void removeCardsFromViews()
	{
		
		// Remove cards from corkboard view
		removeCardsFromCorkboardView();
		
	}
	
	private void removeCardsFromCorkboardView()
	{
		
		Component[] components =
				corkboardController
					.getCorkboardTemplate()
					.getComponents();
		
		// Iterate through all the currently added components
		for(Component component : components) {
			
			// If it's a CardLabel, remove it
			if(component instanceof CardLabel) {
				
				removeCardLabel(((CardLabel) component));
				
			}
			
		}
		
	}
	
	private void removeCardLabel(CardLabel cardLabel)
	{
		
		// Remove it from the template
		corkboardController.getCorkboardTemplate().remove(cardLabel);
		
		// Remove mouse listeners
		cardLabel.removeMouseListener(corkboardController);
		
	}
	
	private void addCardsToViews()
	{
		
		// Add the cards to the corkboard view
		addCardsToCorkboardView();
		
	}
	
	private void addCardsToCorkboardView()
	{
		
		// Add all the current cards to the CorkboardTemplate
		for(Card card : cards) {
			
			addCardLabel(card);
			
		}
		
	}
	
	private void addCardLabel(Card card)
	{
		
		// Create new CardLabel
		CardLabel cardLabel = new CardLabel(card);
		
		// Add event listeners
		cardLabel.addMouseListener(corkboardController);
		
		// Add cardLabel to the corkboardTemplate
		corkboardController.getCorkboardTemplate().add(cardLabel);
		
	}
	
	private void repaintViews()
	{
		
		// Repaint the CorkboardTemplate
		corkboardController
			.getCorkboardTemplate()
			.getScrollPane()
			.getCorkboardPanel()
			.revalidate();
		
		corkboardController
			.getCorkboardTemplate()
			.getScrollPane()
			.getCorkboardPanel()
			.repaint();
		
	}
	
	/*
	 * Methods for adding, deleting, editing, duplicating, flipping cards
	 */
	public void addCard(Card card)
	{
		
		// Add the new Card to the list
		cards.add(card);
		
		// Remove and re-add all cards
		syncCardsWithViews();
		
	}
	
	public void addCard(int index, Card card)
	{
		
		// Add the new Card to the list
		cards.add(index, card);
		
		// Remove and re-add all cards
		syncCardsWithViews();
		
	}
	
	public void addCards(ArrayList<Card> cards)
	{
		
		// Replace cards list
		this.cards = cards;
		
		// Remove and re-add all cards
		syncCardsWithViews();
		
	}
	
	public void deleteCard(Card card)
	{
		
		// Delete from Card list
		cards.remove(card);
		
		// Remove and re-add all cards
		syncCardsWithViews();
		
	}
	
	// TODO make this public?
	private void deleteAllCards()
	{
		
		// Empty the Card ArrayList
		cards.clear();
		
		// Update the views
		syncCardsWithViews();
		
	}
	
	public void duplicateCard(Card originalCard)
	{
		
		// Get index of the given card
		int index = cards.indexOf(originalCard);
		
		// If it's found, add it again at that index
		if(index >= 0) {
			
			addCard(index, new Card(originalCard));
			
			// Remove and re-add all cards
			syncCardsWithViews();
			
		}
		
	}
	
	public void editCard(Card oldCard, Card newCard)
	{
		
		// Get index of current Card
		int index = cards.indexOf(oldCard);
		
		// If it's found, replace it
		if(index >= 0) {
			
			this.cards.set(index, newCard);
			
			// Remove and re-add all cards
			syncCardsWithViews();
			
		}
		
	}
	
	public void flipCard(Card card)
	{
		
		// Get index of flipped card
		int index = cards.indexOf(card);
		
		// If it's found, flip it
		if(index >= 0) {
			
			((Card) cards.get(index)).flip();
			
			// Remove and re-add all cards
			syncCardsWithViews();
			
		}
		
	}
	
	public void flipAllCards()
	{
		
		for(Card card : cards) {
			
			flipCard(card);
			
		}
		
	}
	
	public void insertAfter(Card newCard, Card oldCard)
	{
		
		// Get index of card to insert after
		int index = cards.indexOf(oldCard);
		
		// If it's found, add new card
		if(index >= 0) {
			
			addCard(index + 1, newCard);
			
			// Remove and re-add all cards
			syncCardsWithViews();
			
		}
		
	}
	
	public void insertBefore(Card newCard, Card oldCard)
	{
		
		// Get index of card to insert before
		int index = cards.indexOf(oldCard);
		
		// If it's found, add the new card
		if(index >= 0) {
			
			addCard(index, newCard);
			
			// Remove and re-add all cards
			syncCardsWithViews();
			
		}
		
	}
	
	/*
	 * Displays a JFileChooser window to allow the user to save the current project.
	 */
	private void saveProject()
	{
		
		int fileChooserReturnValue =
			windowTemplate
				.getFileChooser()
				.showSaveDialog(windowTemplate);
		
		if(fileChooserReturnValue == JFileChooser.APPROVE_OPTION) {
			
			// Get the file that the user selected
			File file = windowTemplate.getFileChooser().getSelectedFile();
			
			try {
				
				// Open the output streams
				FileOutputStream fileOutput = new FileOutputStream(file.getAbsolutePath());
				ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			
				// Write the objects
				for(int i = 0; i < cards.size(); i++) {
					objectOutput.writeObject(cards.get(i));
				}
				
				// Close the output streams
				objectOutput.close();
				fileOutput.close();
				
				// Update the title of the frame to reflect the file name
				windowTemplate.setTitle(file.getName());
				
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(windowTemplate, "Could not save the file.");
			}
			
		}
		
	}
	
	/*
	 * Displays a JFileChooser window to allow the user to load a project from a file.
	 */
	private void loadProject()
	{
		
		int fileChooserReturnValue =
			windowTemplate
				.getFileChooser()
				.showOpenDialog(windowTemplate);
		
		if(fileChooserReturnValue == JFileChooser.APPROVE_OPTION) {
			// Delete the current cards
			deleteAllCards();
			
			// Get the file that the user selected
			File file = windowTemplate.getFileChooser().getSelectedFile();
			
			try {
				
				// Open the input streams
				FileInputStream fileInput = new FileInputStream(file.getAbsolutePath());
				ObjectInputStream objectInput = new ObjectInputStream(fileInput);
				
				// Get ready to save the cards
				ArrayList<Card> newCards = new ArrayList<Card>();
				
				// Get the objects
				try {
					
					Object inputObject = objectInput.readObject();
					
					while(inputObject instanceof Card) {
						
						// Add the card
						newCards.add((Card) inputObject);
						
						// Read the next object
						inputObject = objectInput.readObject();
						
					}
					
				} catch(EOFException e2) {
					
					// We've reached the end of the file, time to add the new project
					addCards(newCards);
					
				}
				
				// Close the input streams
				objectInput.close();
				fileInput.close();
				
				// Update the title of the frame to reflect the current project
				windowTemplate.setTitle(file.getName());
				
			} catch (ClassNotFoundException | IOException e1) {
				JOptionPane.showMessageDialog(windowTemplate, "Could not open the file.");
			}
		}
		
	}
	
	private void newProject()
	{
		
		String message = "Are you sure you want to discard this project?";
		int confirmNew = JOptionPane.showConfirmDialog(windowTemplate, message);
		
		if(confirmNew == JOptionPane.OK_OPTION) {
			windowTemplate.setTitle("New Project");
			deleteAllCards();
		}
		
	}

	/*
	 * ActionListener method for handling menu bar clicks.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		// New project clicked
		if(e.getSource() == windowTemplate.getNewProjectMenuItem()) {
			newProject();
		}
		
		// Save project clicked
		if(e.getSource() == windowTemplate.getSaveProjectMenuItem()) {
			saveProject();
		}
		
		// Load project clicked
		if(e.getSource() == windowTemplate.getLoadProjectMenuItem()) {
			loadProject();
		}
		
		// About clicked
		if(e.getSource() == windowTemplate.getAboutMenuItem()) {
			String message = "Easynotes v1.0\nCreated by: Steven Raines\nCreated on: 8/28/2019";
			JOptionPane.showMessageDialog(windowTemplate, message);
		}
		
	}
	
	/*
	 * KeyListener methods for saving, loading, and new project.
	 */
	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e)
	{
		
		// Save project as
		if(e.getKeyCode() == KeyEvent.VK_S && e.isControlDown() && e.isShiftDown()) {
			saveProject();
		}
		
		// Load project
		if(e.getKeyCode() == KeyEvent.VK_O && e.isControlDown()) {
			loadProject();
		}
		
		// New project
		if(e.getKeyCode() == KeyEvent.VK_N && e.isControlDown()) {
			newProject();
		}
		
	}

	/*
	 * Setters and getters
	 */
	public ArrayList<Card> getCards()
	{
		return cards;
	}
	
	public void setCards(ArrayList<Card> cards)
	{
		this.cards = cards;
	}
	
	public CorkboardController getCorkboardController()
	{
		return corkboardController;
	}

	public void setCorkboardController(CorkboardController corkboardController)
	{
		this.corkboardController = corkboardController;
	}

	public AddCardController getAddCardController()
	{
		return addCardController;
	}

	public void setAddCardController(AddCardController addCardController)
	{
		this.addCardController = addCardController;
	}

	public EditCardController getEditCardController()
	{
		return editCardController;
	}

	public void setEditCardController(EditCardController editCardController)
	{
		this.editCardController = editCardController;
	}

	public WindowTemplate getWindowTemplate()
	{
		return windowTemplate;
	}

	public void setWindowTemplate(WindowTemplate windowTemplate)
	{
		this.windowTemplate = windowTemplate;
	}

	public InsertAfterCardController getInsertAfterCardController() {
		return insertAfterCardController;
	}

	public void setInsertAfterCardController(InsertAfterCardController insertAfterCardController) {
		this.insertAfterCardController = insertAfterCardController;
	}

	public InsertBeforeCardController getInsertBeforeCardController() {
		return insertBeforeCardController;
	}

	public void setInsertBeforeCardController(InsertBeforeCardController insertBeforeCardController) {
		this.insertBeforeCardController = insertBeforeCardController;
	}
	
}
