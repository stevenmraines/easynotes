package easynotes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartMenu implements ActionListener {
	private JFrame window;
	private JPanel mainPanel;
	private JButton newButton;
	private JButton loadButton;
	private GridBagConstraints gbc;
	
	public StartMenu() {
		// Init components
		window = new JFrame("Easy Notes v1.0");
		mainPanel = new JPanel(new GridBagLayout());
		newButton = new JButton("New Project");
		loadButton = new JButton("Load Project");
		gbc = new GridBagConstraints();
		
		// Add components
		window.add(mainPanel);
		gbc.gridx = 0;
		gbc.gridy = 0;
		mainPanel.add(newButton, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		mainPanel.add(loadButton, gbc);
		
		// Add event listeners
		newButton.addActionListener(this);
		loadButton.addActionListener(this);
		
		// Prepare and display main window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == newButton) {
			System.out.println("new");
		}
		
		if(e.getSource() == loadButton) {
			System.out.println("load");
		}
	}
}
