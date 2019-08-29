package easynotes.views;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AddCardTemplate {
	private JFrame frame;
	private JPanel panel;
	private JTextArea cardFrontTextArea;
	private JTextArea cardBackTextArea;
	private JButton createButton;
	
	public AddCardTemplate() {
		// Initialize components
		frame = new JFrame("Add a card");
		panel = new JPanel(new GridLayout(3, 1));
		cardFrontTextArea = new JTextArea();
		cardBackTextArea = new JTextArea();
		createButton = new JButton("Create");
		
		// Add components
		frame.add(panel);
		panel.add(cardFrontTextArea);
		panel.add(cardBackTextArea);
		panel.add(createButton);
		
		// Prepare and display the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
