package easynotes;

import javax.swing.SwingUtilities;

import easynotes.controllers.MainController;
import easynotes.views.ProjectTemplate;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainController();
			}
		});
	}

}
