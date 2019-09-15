package easynotes.views;

import java.awt.event.WindowEvent;

import javax.swing.JDialog;

public class AddCardTemplate extends CardTextTemplate {
	public AddCardTemplate() {
		super();
		dialog = new JDialog(frame, true);
		frame.setTitle("Add a card");
		actionButton.setText("Create");
		frame.pack();
	}
	
	@Override
	public void showModal() {
		this.redText.setText("255");
		this.greenText.setText("255");
		this.blueText.setText("255");
		super.showModal();
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		hideModal();
	}
}
