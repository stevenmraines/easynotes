package easynotes.controllers;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;

import easynotes.views.ProjectTemplate;

/*
 * Mainly controls the context menu of the project view panel.
 */
public class ProjectController implements ActionListener, MouseListener {
	// Register views
	private ProjectTemplate projectTemplate;
	
	// Register parent controller
	private MainController mainController;
	
	public ProjectController(MainController mainController) {
		// Initialize properties
		this.mainController = mainController;
		projectTemplate = new ProjectTemplate();
		
		// Add action listeners
		projectTemplate.addMouseListener(this);
		projectTemplate.getAddCardMenuItem().addActionListener(this);
		projectTemplate.getFlipAllCardsMenuItem().addActionListener(this);
	}
	
	/*
	 * ActionListener methods for handling context menu item clicks.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Hide any currently visible context menus
		mainController.hideAllContextMenus();
		
		// Add a card
		if(e.getSource() == projectTemplate.getAddCardMenuItem()) {
			mainController.getAddCardController().getAddCardTemplate().showModal();
		}
		
		// Flip all cards
		if(e.getSource() == projectTemplate.getFlipAllCardsMenuItem()) {
			mainController.flipAllCards();
		}
	}

	/*
	 * MouseListener methods for showing context menu on right click.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Hide all visible context menus
		mainController.hideAllContextMenus();
		
		// Right click
		if(SwingUtilities.isRightMouseButton(e)) {
			// Get location of click relative to the main JFrame of the application
			Point menuLocation = mainController.getFrame().getLocation();
			menuLocation.translate(e.getX(), e.getY());
			
			// Set location of context menu and make it visible
			projectTemplate.getContextMenu().setLocation(menuLocation);
			projectTemplate.getContextMenu().setVisible(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	/*
	 * Setters and getters.
	 */
	public ProjectTemplate getProjectTemplate() {
		return projectTemplate;
	}

	public void setProjectTemplate(ProjectTemplate projectTemplate) {
		this.projectTemplate = projectTemplate;
	}

}
