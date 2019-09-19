package easynotes.controllers;

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
				
				// Get the objects
				try {
					
					Object inputObject = objectInput.readObject();
					
					while(inputObject instanceof Card) {
						
						// Create the new Card and CardLabel objects
						Card card = (Card) inputObject;
						CardLabel cardLabel = new CardLabel(card);
						
						// Add the Card and CardLabel objects
						cards.add(card);
						corkboardController
							.getCorkboardTemplate()
							.getCardLabels()
							.add(cardLabel);
						
						// Read the next object
						inputObject = objectInput.readObject();
						
					}
					
				} catch(EOFException e2) {
					// Exception signals we've reached the end of the file
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
	
	private void deleteAllCards()
	{
		cards.clear();
		corkboardController = new CorkboardController(this);
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
	
}
