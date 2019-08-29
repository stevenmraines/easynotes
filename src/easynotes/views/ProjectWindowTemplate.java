package easynotes.views;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class ProjectWindowTemplate {
	private JFrame frame;
	private JPanel panel;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu infoMenu;
	private JMenuItem newProjectMenuItem;
	private JMenuItem saveProjectMenuItem;
	private JMenuItem loadProjectMenuItem;
	private JMenuItem aboutMenuItem;
	private JPopupMenu projectContextMenu;
	private JPopupMenu cardContextMenu;
	private JMenuItem addCardMenuItem;
	private JMenuItem deleteCardMenuItem;
	private JMenuItem editCardMenuItem;
	
	public ProjectWindowTemplate() {
		// Initialize components
		frame = new JFrame("New Project");
		panel = new JPanel();
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		infoMenu = new JMenu("Info");
		newProjectMenuItem = new JMenuItem("New Project");
		saveProjectMenuItem = new JMenuItem("Save Project");
		loadProjectMenuItem = new JMenuItem("Load Project");
		aboutMenuItem = new JMenuItem("About");
		projectContextMenu = new JPopupMenu();
		cardContextMenu = new JPopupMenu();
		addCardMenuItem = new JMenuItem("Add a card");
		editCardMenuItem = new JMenuItem("Edit this card");
		deleteCardMenuItem = new JMenuItem("Delete this card");
		
		// Add components
		frame.add(panel);
		frame.setJMenuBar(menuBar);
		panel.add(projectContextMenu);
		panel.add(cardContextMenu);
		menuBar.add(fileMenu);
		menuBar.add(infoMenu);
		fileMenu.add(newProjectMenuItem);
		fileMenu.add(saveProjectMenuItem);
		fileMenu.add(loadProjectMenuItem);
		infoMenu.add(aboutMenuItem);
		projectContextMenu.add(addCardMenuItem);
		cardContextMenu.add(editCardMenuItem);
		cardContextMenu.add(deleteCardMenuItem);
		
		// Prepare and display the window
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public JMenu getFileMenu() {
		return fileMenu;
	}

	public void setFileMenu(JMenu fileMenu) {
		this.fileMenu = fileMenu;
	}

	public JMenu getInfoMenu() {
		return infoMenu;
	}

	public void setInfoMenu(JMenu infoMenu) {
		this.infoMenu = infoMenu;
	}

	public JMenuItem getNewProjectMenuItem() {
		return newProjectMenuItem;
	}

	public void setNewProjectMenuItem(JMenuItem newProjectMenuItem) {
		this.newProjectMenuItem = newProjectMenuItem;
	}

	public JMenuItem getSaveProjectMenuItem() {
		return saveProjectMenuItem;
	}

	public void setSaveProjectMenuItem(JMenuItem saveProjectMenuItem) {
		this.saveProjectMenuItem = saveProjectMenuItem;
	}

	public JMenuItem getLoadProjectMenuItem() {
		return loadProjectMenuItem;
	}

	public void setLoadProjectMenuItem(JMenuItem loadProjectMenuItem) {
		this.loadProjectMenuItem = loadProjectMenuItem;
	}

	public JMenuItem getAboutMenuItem() {
		return aboutMenuItem;
	}

	public void setAboutMenuItem(JMenuItem aboutMenuItem) {
		this.aboutMenuItem = aboutMenuItem;
	}

	public JPopupMenu getProjectContextMenu() {
		return projectContextMenu;
	}

	public void setProjectContextMenu(JPopupMenu projectContextMenu) {
		this.projectContextMenu = projectContextMenu;
	}

	public JPopupMenu getCardContextMenu() {
		return cardContextMenu;
	}

	public void setCardContextMenu(JPopupMenu cardContextMenu) {
		this.cardContextMenu = cardContextMenu;
	}

	public JMenuItem getAddCardMenuItem() {
		return addCardMenuItem;
	}

	public void setAddCardMenuItem(JMenuItem addCardMenuItem) {
		this.addCardMenuItem = addCardMenuItem;
	}

	public JMenuItem getDeleteCardMenuItem() {
		return deleteCardMenuItem;
	}

	public void setDeleteCardMenuItem(JMenuItem deleteCardMenuItem) {
		this.deleteCardMenuItem = deleteCardMenuItem;
	}

	public JMenuItem getEditCardMenuItem() {
		return editCardMenuItem;
	}

	public void setEditCardMenuItem(JMenuItem editCardMenuItem) {
		this.editCardMenuItem = editCardMenuItem;
	}
}
