package easynotes.templates;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * This class contains the main window and menu bar of the application.
 * 
 * @author sraines
 *
 */
public class WindowTemplate extends JFrame
{
	
	private static final long serialVersionUID = 3808626788892345411L;
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
	
	public WindowTemplate()
	{
		
		// Initialize properties
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
//		this.add(scrollPane);
		this.setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		menuBar.add(infoMenu);
		fileMenu.add(newProjectMenuItem);
		fileMenu.add(saveProjectMenuItem);
		fileMenu.add(loadProjectMenuItem);
		infoMenu.add(aboutMenuItem);
		
		// Prepare the JFileChooser
		fileChooser.addChoosableFileFilter(esnFilter);
		fileChooser.setFileFilter(esnFilter);
		
		// Prepare and display the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("New Project");
		this.setSize(500, 500);
		this.setVisible(true);
		
	}

	/*
	 * Setters and getters.
	 */
	public JScrollPane getScrollPane()
	{
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane)
	{
		this.scrollPane = scrollPane;
	}

	public JMenu getFileMenu()
	{
		return fileMenu;
	}

	public void setFileMenu(JMenu fileMenu)
	{
		this.fileMenu = fileMenu;
	}

	public JMenu getInfoMenu()
	{
		return infoMenu;
	}

	public void setInfoMenu(JMenu infoMenu)
	{
		this.infoMenu = infoMenu;
	}

	public JMenuItem getNewProjectMenuItem()
	{
		return newProjectMenuItem;
	}

	public void setNewProjectMenuItem(JMenuItem newProjectMenuItem)
	{
		this.newProjectMenuItem = newProjectMenuItem;
	}

	public JMenuItem getSaveProjectMenuItem()
	{
		return saveProjectMenuItem;
	}

	public void setSaveProjectMenuItem(JMenuItem saveProjectMenuItem)
	{
		this.saveProjectMenuItem = saveProjectMenuItem;
	}

	public JMenuItem getLoadProjectMenuItem()
	{
		return loadProjectMenuItem;
	}

	public void setLoadProjectMenuItem(JMenuItem loadProjectMenuItem)
	{
		this.loadProjectMenuItem = loadProjectMenuItem;
	}

	public JMenuItem getAboutMenuItem()
	{
		return aboutMenuItem;
	}

	public void setAboutMenuItem(JMenuItem aboutMenuItem)
	{
		this.aboutMenuItem = aboutMenuItem;
	}

	public JFileChooser getFileChooser()
	{
		return fileChooser;
	}

	public void setFileChooser(JFileChooser fileChooser)
	{
		this.fileChooser = fileChooser;
	}

}
