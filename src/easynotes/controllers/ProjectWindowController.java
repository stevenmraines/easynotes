package easynotes.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import easynotes.models.Project;
import easynotes.views.ProjectWindowTemplate;

public class ProjectWindowController implements ActionListener, MouseListener {
	private Project project;
	private ProjectWindowTemplate projectWindow;
	
	public ProjectWindowController(ProjectWindowTemplate projectWindow) {
		project = new Project();
		this.projectWindow = projectWindow;
		initialize();
	}
	
	private void initialize() {
		projectWindow.getAboutMenuItem().addActionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == projectWindow.getAboutMenuItem()) {
			JOptionPane.showMessageDialog(null, "Created by: Steven Raines\nVersion: 1.0\nCreated on: 8/28/2019");
		}
	}
}
