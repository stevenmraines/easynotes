package easynotes.templates;

/**
 * 
 * The EditCardTemplate class contains the Swing components
 * needed for the "Edit a card" window.
 * 
 * @author sraines
 *
 */
public class EditCardTemplate extends CardTextTemplate
{
	
	public EditCardTemplate()
	{
		
		super();
		actionButton.setText("Save");
		frame.setTitle("Edit a card");
		frame.pack();
		
	}

}
