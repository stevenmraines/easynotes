package easynotes.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainController implements ActionListener {
	// Register controllers
	private ProjectController projectController;
	private CardController cardController;
	private AddCardController addCardController;
	private EditCardController editCardController;
	
	// Register GUI components
	private JFrame frame;
	private JPanel panel;
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
		cardController = new CardController(this);
		addCardController = new AddCardController(this);
		editCardController = new EditCardController(this);
		frame = new JFrame("New Project");
		panel = new JPanel();
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		infoMenu = new JMenu("Info");
		newProjectMenuItem = new JMenuItem("New Project");
		saveProjectMenuItem = new JMenuItem("Save Project");
		loadProjectMenuItem = new JMenuItem("Load Project");
		aboutMenuItem = new JMenuItem("About");
		
		// Add components
		frame.add(panel);
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

	public CardController getCardController() {
		return cardController;
	}

	public void setCardController(CardController cardController) {
		this.cardController = cardController;
	}

	public EditCardController getEditCardController() {
		return editCardController;
	}

	public void setEditCardController(EditCardController editCardController) {
		this.editCardController = editCardController;
	}
}