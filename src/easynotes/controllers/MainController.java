package easynotes.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class MainController implements ActionListener {
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
	public void addNewCardController(CardController cardController) {
		// Add the new CardController to the list
		cardControllers.add(cardController);
		
		// Add the template of the new CardController to the project template
		projectController.getProjectTemplate().add(cardController.getCardTemplate());
		
		// Force the project panel to show the changes
		projectController.getProjectTemplate().revalidate();
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
	 * ActionListener methods for handling menu bar clicks.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// New project clicked
		if(e.getSource() == newProjectMenuItem) {
			int confirmNew = JOptionPane.showConfirmDialog(frame, "Are you sure you want to discard this project?");
			
			if(confirmNew == JOptionPane.OK_OPTION) {
				deleteAllCardControllers();
			}
		}
		
		// Save project clicked
		if(e.getSource() == saveProjectMenuItem) {
			int fileChooserReturnValue = fileChooser.showSaveDialog(frame);
			
			if(fileChooserReturnValue == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				
				try {
					
					FileOutputStream fileOutput = new FileOutputStream(file.getAbsolutePath());
					ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
				
					for(int i = 0; i < cardControllers.size(); i++) {
						objectOutput.writeObject(cardControllers.get(i).getCard());
					}
					
					objectOutput.close();
					fileOutput.close();
					
					frame.setTitle(file.getName());
					
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(frame, "Could not save the file.");
				}
			}
		}
		
		// Load project clicked
		if(e.getSource() == loadProjectMenuItem) {
			int fileChooserReturnValue = fileChooser.showOpenDialog(frame);
			
			if(fileChooserReturnValue == JFileChooser.APPROVE_OPTION) {
				// Delete the current cards
				deleteAllCardControllers();
				
				File file = fileChooser.getSelectedFile();
				
				try {
					
					FileInputStream fileInput = new FileInputStream(file.getAbsolutePath());
					ObjectInputStream objectInput = new ObjectInputStream(fileInput);
					
					while(objectInput.available() > 0) {
						Card inputCard = (Card) objectInput.readObject();
						addNewCardController(new CardController(this, inputCard));
					}
					
					objectInput.close();
					fileInput.close();
					
					frame.setTitle(file.getName());
					
				} catch (ClassNotFoundException | IOException e1) {
					JOptionPane.showMessageDialog(frame, "Could not open the file.");
				}
			}
		}
		
		// About clicked
		if(e.getSource() == aboutMenuItem) {
			JOptionPane.showMessageDialog(frame, "Easynotes v1.0\nCreated by: Steven Raines\nCreated on: 8/28/2019");
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
}
