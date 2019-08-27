package easynotes;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartMenu {
	private JFrame window;
	private JPanel mainPanel;
	
	public StartMenu() {
		window = new JFrame("Easy Notes v1.0");
		mainPanel = new JPanel();
		window.add(mainPanel);
		window.setVisible(true);
	}
}
