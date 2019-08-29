package easynotes;

import easynotes.controllers.ProjectWindowController;
import easynotes.views.ProjectWindowTemplate;

public class Main {

	public static void main(String[] args) {
		new ProjectWindowController(new ProjectWindowTemplate());
	}

}
