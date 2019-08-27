package easynotes;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class IndexCard extends JPanel {
	private JLabel title;
	private JLabel body;
	private GridBagConstraints gbc;
	
	public IndexCard() {
		this.title = new JLabel();
		this.body = new JLabel();
		init();
	}
	
	public IndexCard(String body) {
		this.title = new JLabel();
		this.body = new JLabel(body);
		init();
	}
	
	public IndexCard(String title, String body) {
		this.title = new JLabel(title);
		this.body = new JLabel(body);
		init();
	}
	
	public void init() {
		// Set border and grid layout
		this.setBorder(new LineBorder(Color.black));
		this.setLayout(new GridBagLayout());
		
		// Init the grid bag constraints
		gbc = new GridBagConstraints();
		
		// Add title and body
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(title, gbc);
		gbc.gridy = 1;
		this.add(body, gbc);
	}
	
	public String getTitle() {
		return title.getText();
	}
	
	public String getBody() {
		return body.getText();
	}
	
	public void setTitle(String title) {
		this.title.setText(title);
	}
	
	public void setBody(String body) {
		this.body.setText(body);
	}
}
