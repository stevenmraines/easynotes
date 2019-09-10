package easynotes.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/*
 * This class mainly acts as a hook for the child
 * controllers to communicate with each other.
 * 
 * It also creates the main JFrame of the application.
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
		
		// Prepare and display the frame
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void flipAllCards() {
		for(int i = 0; i < cardControllers.size(); i++) {
			cardControllers.get(i).flip();
		}
	}
	
	public void hideAllContextMenus() {
		projectController.getProjectTemplate().getContextMenu().setVisible(false);
		
		for(int i = 0; i < cardControllers.size(); i++) {
			cardControllers.get(i).getCardTemplate().getContextMenu().setVisible(false);
		}
	}
	
	public void addNewCardController(CardController cardController) {
		// Add the new CardController to the list
		cardControllers.add(cardController);
		
		// Add the template of the new CardController to the project template
		projectController.getProjectTemplate().add(cardController.getCardTemplate());
	}
	
	public void deleteCardController(CardController cardController) {
		cardControllers.remove(cardControllers.indexOf(cardController));
		projectController.getProjectTemplate().remove(cardController.getCardTemplate());
		projectController.getProjectTemplate().revalidate();
		frame.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == newProjectMenuItem) {
			
		}
		
		if(e.getSource() == saveProjectMenuItem) {
			
		}
		
		if(e.getSource() == loadProjectMenuItem) {
			
		}
		
		if(e.getSource() == aboutMenuItem) {
			JOptionPane.showMessageDialog(null, "Created by: Steven Raines\nCreated on: 8/28/2019\nVersion: 1.0");
		}
	}

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
