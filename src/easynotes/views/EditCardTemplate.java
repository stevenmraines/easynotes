package easynotes.views;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class EditCardTemplate extends CardTextTemplate {
	
	public EditCardTemplate() {
		super();
		actionButton.setText("Save");
		dialog = new JDialog(frame, true);
		frame.setTitle("Edit a card");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
	}

}
