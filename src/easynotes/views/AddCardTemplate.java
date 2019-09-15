package easynotes.views;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class AddCardTemplate extends CardTextTemplate {
	
	public AddCardTemplate() {
		super();
		actionButton.setText("Create");
		dialog = new JDialog(frame, true);
		frame.setTitle("Add a card");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.pack();
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		frontText.setText("");
		backText.setText("");
		redSpinnerModel.setValue(255);
		greenSpinnerModel.setValue(255);
		blueSpinnerModel.setValue(255);
	}
	
}
