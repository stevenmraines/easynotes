package easynotes.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import easynotes.models.Project;
import easynotes.views.ProjectTemplate;

/*
 * This class seems to be only controlling the context menu of the
 * project window. Perhaps this functionality should be handled by
 * the maincontroller class?
 */
public class ProjectController implements ActionListener, MouseListener {
	// Register models
	private Project project;
	
	// Register views
	private ProjectTemplate projectTemplate;
	
	// Register parent controller
	private MainController mainController;
	
	public ProjectController(MainController mainController) {
		// Initialize properties
		this.mainController = mainController;
		project = new Project();
		projectTemplate = new ProjectTemplate();
		
		// Add action listeners
		projectTemplate.addMouseListener(this);
		projectTemplate.getAddCardMenuItem().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == projectTemplate.getAddCardMenuItem()) {
			projectTemplate.getContextMenu().setVisible(false);
			mainController.getAddCardController().getAddCardTemplate().showModal();
		}
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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
		if(SwingUtilities.isRightMouseButton(e)) {
			projectTemplate.getContextMenu().setLocation(e.getX(), e.getY());
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
