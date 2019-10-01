package easynotes.templates;

import java.awt.Color;

public class AddCardTemplate extends CardTextTemplate
{
	
	public AddCardTemplate()
	{
		
		super();
		fontColorDisplayPanel.setBackground(Color.darkGray);
		backgroundColorDisplayPanel.setBackground(Color.white);
		actionButton.setText("Create");
		frame.setTitle("Add a card");
		frame.pack();
		
	}
	
	/*
	 * Override setVisible method so that we can reset input fields
	 * each time the window is made visible again.
	 */
	@Override
	public void setVisible(boolean visible)
	{
		
		super.setVisible(visible);
		frontText.setText("");
		backText.setText("");
		fontColorDisplayPanel.setBackground(Color.darkGray);
		backgroundColorDisplayPanel.setBackground(Color.white);
		
	}
	
}
