package easynotes.templates;

public class AddCardTemplate extends CardTextTemplate
{
	
	public AddCardTemplate()
	{
		
		super();
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
		redSpinnerModel.setValue(255);
		greenSpinnerModel.setValue(255);
		blueSpinnerModel.setValue(255);
		
	}
	
}
