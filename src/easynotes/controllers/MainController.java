package easynotes.controllers;

import java.awt.Point;
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
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import easynotes.models.Card;

/*
 * This class mainly acts as a hook for the child
 * controllers to communicate with each other.
 * 
 * It also creates the main JFrame of the application
 * and handles events for the main menu bar.
 */
public class MainController implements ActionListener, KeyListener {
	// Register controllers
	private ProjectController projectController;
	private ArrayList<CardController> cardControllers;
	private AddCardController addCardController;
	
	// Register GUI components
	private JFrame frame;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu infoMenu;
	private JMenuItem newProjectMenuItem;
	private JMenuItem saveProjectMenuItem;
	private JMenuItem loadProjectMenuItem;
	private JMenuItem aboutMenuItem;
	private JFileChooser fileChooser;
	private FileNameExtensionFilter esnFilter;
	
	public MainController() {
		// Initialize properties
		projectController = new ProjectController(this);
		cardControllers = new ArrayList<CardController>();
		addCardController = new AddCardController(this);
		frame = new JFrame("New Project");
		panel = new JPanel();
		scrollPane = new JScrollPane();
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		infoMenu = new JMenu("Info");
		newProjectMenuItem = new JMenuItem("New Project");
		saveProjectMenuItem = new JMenuItem("Save Project");
		loadProjectMenuItem = new JMenuItem("Load Project");
		aboutMenuItem = new JMenuItem("About");
		fileChooser = new JFileChooser();
		esnFilter = new FileNameExtensionFilter("Easynotes files", "esn");
		
		// Add components
		frame.add(scrollPane);
		scrollPane.add(panel);
		panel.add(projectController.getProjectTemplate());
		frame.setJMenuBar(menuBar);
		frame.add(projectController.getProjectTemplate());
		menuBar.add(fileMenu);
		menuBar.add(infoMenu);
		fileMenu.add(newProjectMenuItem);
		fileMenu.add(saveProjectMenuItem);
		fileMenu.add(loadProjectMenuItem);
		infoMenu.add(aboutMenuItem);
		
		// Add action listeners
		newProjectMenuItem.addActionListener(this);
		saveProjectMenuItem.addActionListener(this);
		loadProjectMenuItem.addActionListener(this);
		aboutMenuItem.addActionListener(this);
		fileChooser.addActionListener(this);
		frame.addKeyListener(this);
		
		// Prepare the fileChooser
		fileChooser.addChoosableFileFilter(esnFilter);
		fileChooser.setFileFilter(esnFilter);
		
		// Prepare and display the frame
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/*
	 * Toggles all cards in the current project.
	 */
	public void flipAllCards() {
		for(int i = 0; i < cardControllers.size(); i++) {
			cardControllers.get(i).flip();
		}
	}
	
	/*
	 * Hides all of the application right click context menus.
	 */
	public void hideAllContextMenus() {
		// Hide the project context menu
		projectController.getProjectTemplate().getContextMenu().setVisible(false);
		
		// Hide any and all CardController context menus
		for(int i = 0; i < cardControllers.size(); i++) {
			cardControllers.get(i).getCardTemplate().getContextMenu().setVisible(false);
		}
	}
	
	/*
	 * Adds a new card to the project.
	 */
	public void addCardController(CardController cardController) {
		// Add the new CardController to the list
		cardControllers.add(cardController);
		
		// Add the template of the new CardController to the project template
		projectController.getProjectTemplate().add(cardController.getCardTemplate());
		
		// Force the project panel to show the changes
		projectController.getProjectTemplate().revalidate();
	}
	
	/*
	 * Add a card at a certain index.
	 */
	public void addCardController(int index, CardController cardController) {
		// Add the new CardController to the list
		cardControllers.add(index, cardController);
		
		// Add back all the cards in the new order
		replaceAllCardControllers(cardControllers);
	}
	
	/*
	 * Adds all cards from an array list of cards to a project.
	 */
	public void addAllCardControllers(ArrayList<CardController> cardControllers) {
		for(int i = 0; i < cardControllers.size(); i++) {
			addCardController(cardControllers.get(i));
		}
	}
	
	/*
	 * Deletes a card from the project.
	 */
	public void deleteCardController(CardController cardController) {
		// Remove the controller from the list
		cardControllers.remove(cardControllers.indexOf(cardController));
		
		// Remove the template from the project panel
		projectController.getProjectTemplate().remove(cardController.getCardTemplate());
		
		// Force everything to show the changes
		projectController.getProjectTemplate().revalidate();
		frame.repaint();
	}
	
	/*
	 * Deletes all cards in the project.
	 */
	public void deleteAllCardControllers() {
		// Remove all the templates from the project panel
		for(int i = 0; i < cardControllers.size(); i++) {
			projectController.getProjectTemplate().remove(cardControllers.get(i).getCardTemplate());
		}
		
		// Remove all the controllers
		cardControllers = new ArrayList<CardController>();
		
		// Force everything to show the changes
		projectController.getProjectTemplate().revalidate();
		frame.repaint();
	}
	
	/*
	 * Deletes all the cards, and adds all the cards from the array list passed to the method.
	 */
	public void replaceAllCardControllers(ArrayList<CardController> cardControllers) {
		// Delete everything
		deleteAllCardControllers();
		
		// Now add the new cards
		addAllCardControllers(cardControllers);
	}
	
	/*
	 * Duplicates a card and adds it in place of the original.
	 */
	public void duplicateCardController(CardController cardController) {
		// Add the duplicated CardController
		Card duplicate = new Card(cardController.getCard().getFront(), cardController.getCard().getBack());
		CardController duplicateController = new CardController(this, duplicate);
		cardControllers.add(cardControllers.indexOf(cardController), duplicateController);
		
		// Replace and redraw everything
		replaceAllCardControllers(cardControllers);
	}
	
	/*
	 * Find the index of the CardController which overlaps the given X and Y coordinates.
	 */
	public CardController getCardControllerInCoordinates(int x, int y) {
		Point adjustedLocation = new Point(x, y);
		Point frameLocation = frame.getLocation();
		Point panelLocation = panel.getLocation();
		Point projectLocation = projectController.getProjectTemplate().getLocation();
		adjustedLocation.translate((int)frameLocation.getX(), (int)frameLocation.getY());
		adjustedLocation.translate((int)panelLocation.getX(), (int)panelLocation.getY());
		adjustedLocation.translate((int)projectLocation.getX(), (int)projectLocation.getY());
		
		for(int i = 0; i < cardControllers.size(); i++) {
			CardController cc = cardControllers.get(i);
			Point templateLocation = cc.getCardTemplate().getLocation();
			templateLocation.translate((int)frameLocation.getX(), (int)frameLocation.getY());
			templateLocation.translate((int)panelLocation.getX(), (int)panelLocation.getY());
			templateLocation.translate((int)projectLocation.getX(), (int)projectLocation.getY());
			
			int cardX = (int)templateLocation.getX();
			int cardY = (int)templateLocation.getY();
			int cardW = cc.getCardTemplate().getWidth();
			int cardH = cc.getCardTemplate().getHeight();
			
			boolean overlapX = (int)adjustedLocation.getX() >= cardX
					&& (int)adjustedLocation.getX() <= (cardX + cardW);
			
			boolean overlapY = (int)adjustedLocation.getY() >= cardY
					&& (int)adjustedLocation.getY() <= (cardY + cardH);
			
			if(overlapX && overlapY) {
				return cc; 
			}
		}
		
		return null;
	}
	
	/*
	 * Displays a filechooser window to allow the user to save the current project.
	 */
	private void saveProject() {
		int fileChooserReturnValue = fileChooser.showSaveDialog(frame);
		
		if(fileChooserReturnValue == JFileChooser.APPROVE_OPTION) {
			// Get the file that the user selected
			File file = fileChooser.getSelectedFile();
			
			try {
				
				// Open the output streams
				FileOutputStream fileOutput = new FileOutputStream(file.getAbsolutePath());
				ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			
				// Write the objects
				for(int i = 0; i < cardControllers.size(); i++) {
					objectOutput.writeObject(cardControllers.get(i).getCard());
				}
				
				// Close the output streams
				objectOutput.close();
				fileOutput.close();
				
				// Update the title of the frame to reflect the file name
				frame.setTitle(file.getName());
				
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(frame, "Could not save the file.");
			}
		}
	}
	
	/*
	 * Displays a filechooser window to allow the user to load a project from a file.
	 */
	private void loadProject() {
		int fileChooserReturnValue = fileChooser.showOpenDialog(frame);
		
		if(fileChooserReturnValue == JFileChooser.APPROVE_OPTION) {
			// Delete the current cards
			deleteAllCardControllers();
			
			// Get the file that the user selected
			File file = fileChooser.getSelectedFile();
			
			try {
				
				// Open the input streams
				FileInputStream fileInput = new FileInputStream(file.getAbsolutePath());
				ObjectInputStream objectInput = new ObjectInputStream(fileInput);
				
				// Get the objects
				try {
					Object inputCard = objectInput.readObject();
					
					while(inputCard instanceof Card) {
						addCardController(new CardController(this, (Card) inputCard));
						inputCard = objectInput.readObject();
					}
				} catch(EOFException e2) {}
				
				// Close the input streams
				objectInput.close();
				fileInput.close();
				
				// Update the title of the frame to reflect the current project
				frame.setTitle(file.getName());
				
			} catch (ClassNotFoundException | IOException e1) {
				JOptionPane.showMessageDialog(frame, "Could not open the file.");
			}
		}
	}
	
	/*
	 * Clears the current cards and makes a new project.
	 */
	private void newProject() {
		int confirmNew = JOptionPane.showConfirmDialog(frame, "Are you sure you want to discard this project?");
		
		if(confirmNew == JOptionPane.OK_OPTION) {
			deleteAllCardControllers();
			frame.setTitle("New Project");
		}
	}

	/*
	 * ActionListener methods for handling menu bar clicks.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// New project clicked
		if(e.getSource() == newProjectMenuItem) {
			newProject();
		}
		
		// Save project clicked
		if(e.getSource() == saveProjectMenuItem) {
			saveProject();
		}
		
		// Load project clicked
		if(e.getSource() == loadProjectMenuItem) {
			loadProject();
		}
		
		// About clicked
		if(e.getSource() == aboutMenuItem) {
			JOptionPane.showMessageDialog(frame, "Easynotes v1.0\nCreated by: Steven Raines\nCreated on: 8/28/2019");
		}
	}
	
	/*
	 * KeyListener events for saving, loading, and new project.
	 */
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
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
	 * Setters and getters.
	 */
	public ProjectController getProjectController() {
		return projectController;
	}

	public void setProjectController(ProjectController projectController) {
		this.projectController = projectController;
	}

	public AddCardController getAddCardController() {
		return addCardController;
	}

	public void setAddCardController(AddCardController addCardController) {
		this.addCardController = addCardController;
	}

	public ArrayList<CardController> getCardControllers() {
		return cardControllers;
	}

	public void setCardControllers(ArrayList<CardController> cardControllers) {
		this.cardControllers = cardControllers;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
