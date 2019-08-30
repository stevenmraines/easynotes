package easynotes.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public abstract class CardTextTemplate implements WindowListener {
	protected JDialog dialog;
	protected JFrame frame;
	protected JPanel panel;
	protected GridBagConstraints gbc;
	protected JLabel frontTextLabel;
	protected JLabel backTextLabel;
	protected JTextArea frontText;
	protected JTextArea backText;
	protected JButton actionButton;
	
	public CardTextTemplate() {
		// Initialize properties
		frame = new JFrame();
		panel = new JPanel(new GridBagLayout());
		gbc = new GridBagConstraints();
		frontTextLabel = new JLabel("Front text");
		backTextLabel = new JLabel("Back text");
		frontText = new JTextArea();
		backText = new JTextArea();
		actionButton = new JButton();
		
		// Add components
		frame.add(panel);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(frontTextLabel, gbc);
		
		gbc.gridy = 1;
		gbc.gridwidth = 5;
		panel.add(frontText, gbc);
		
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		panel.add(backTextLabel, gbc);
		
		gbc.gridy = 3;
		gbc.gridwidth = 5;
		panel.add(backText, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		panel.add(actionButton, gbc);
		
		// Prepare frame and JDialog
		frame.addWindowListener(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(false);
	}
	
	public void showModal() {
		frame.setVisible(true);
	}
	
	public void hideModal() {
		frontText.setText("");
		backText.setText("");
		frame.setVisible(false);
	}

	public JDialog getDialog() {
		return dialog;
	}

	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public GridBagConstraints getGbc() {
		return gbc;
	}

	public void setGbc(GridBagConstraints gbc) {
		this.gbc = gbc;
	}

	public JLabel getFrontTextLabel() {
		return frontTextLabel;
	}

	public void setFrontTextLabel(JLabel frontTextLabel) {
		this.frontTextLabel = frontTextLabel;
	}

	public JLabel getBackTextLabel() {
		return backTextLabel;
	}

	public void setBackTextLabel(JLabel backTextLabel) {
		this.backTextLabel = backTextLabel;
	}

	public JTextArea getFrontText() {
		return frontText;
	}

	public void setFrontText(JTextArea frontText) {
		this.frontText = frontText;
	}

	public JTextArea getBackText() {
		return backText;
	}

	public void setBackText(JTextArea backText) {
		this.backText = backText;
	}

	public JButton getActionButton() {
		return actionButton;
	}

	public void setActionButton(JButton actionButton) {
		this.actionButton = actionButton;
	}
}
