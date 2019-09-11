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

	@Override
	public void actionPerformed(ActionEvent e) {
		mainController.hideAllContextMenus();
		
		if(e.getSource() == projectTemplate.getAddCardMenuItem()) {
			mainController.getAddCardController().getAddCardTemplate().showModal();
		}
		
		if(e.getSource() == projectTemplate.getFlipAllCardsMenuItem()) {
			mainController.flipAllCards();
		}
	}

	public ProjectTemplate getProjectTemplate() {
		return projectTemplate;
	}

	public void setProjectTemplate(ProjectTemplate projectTemplate) {
		this.projectTemplate = projectTemplate;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Hide all visible context menus
		mainController.hideAllContextMenus();
		
		if(SwingUtilities.isRightMouseButton(e)) {
			Point menuLocation = mainController.getFrame().getLocation();
			menuLocation.translate(e.getX(), e.getY());
			projectTemplate.getContextMenu().setLocation(menuLocation);
			projectTemplate.getContextMenu().setVisible(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
