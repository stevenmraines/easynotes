package easynotes.views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public abstract class CardTextTemplate implements WindowListener {
	protected JDialog dialog;
	protected JFrame frame;
	protected JPanel panel;
	protected JPanel colorPanel;
	protected JLabel frontTextLabel;
	protected JLabel backTextLabel;
	protected JLabel redTextLabel;
	protected JLabel greenTextLabel;
	protected JLabel blueTextLabel;
	protected JTextArea frontText;
	protected JTextArea backText;
	protected JTextArea redText;
	protected JTextArea greenText;
	protected JTextArea blueText;
	protected JScrollPane frontScrollPane;
	protected JScrollPane backScrollPane;
	protected JButton actionButton;
	protected GridBagConstraints gbc;
	
	public CardTextTemplate() {
		// Initialize properties
		frame = new JFrame();
		panel = new JPanel();
		colorPanel = new JPanel(new GridBagLayout());
		frontTextLabel = new JLabel("Front text");
		backTextLabel = new JLabel("Back text");
		redTextLabel = new JLabel("Red");
		greenTextLabel = new JLabel("Green");
		blueTextLabel = new JLabel("Blue");
		frontText = new JTextArea();
		backText = new JTextArea();
		redText = new JTextArea();
		greenText = new JTextArea();
		blueText = new JTextArea();
		frontScrollPane = new JScrollPane(frontText);
		backScrollPane = new JScrollPane(backText);
		actionButton = new JButton();
		gbc = new GridBagConstraints();
		
		// Add components
		frame.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(frontTextLabel);
		panel.add(Box.createVerticalStrut(5));
		panel.add(frontScrollPane);
		panel.add(Box.createVerticalStrut(5));
		panel.add(backTextLabel);
		panel.add(Box.createVerticalStrut(5));
		panel.add(backScrollPane);
		panel.add(Box.createVerticalStrut(5));
		panel.add(colorPanel);
		panel.add(Box.createVerticalStrut(10));
		panel.add(actionButton);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		colorPanel.add(redTextLabel, gbc);
		gbc.gridx = 1;
		colorPanel.add(greenTextLabel, gbc);
		gbc.gridx = 2;
		colorPanel.add(blueTextLabel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		colorPanel.add(redText, gbc);
		gbc.gridx = 1;
		colorPanel.add(greenText, gbc);
		gbc.gridx = 2;
		colorPanel.add(blueText, gbc);
		
		// Prepare frame and JDialog
		panel.setBorder(new EmptyBorder(5,5,5,5));
		frame.setMinimumSize(new Dimension(250, 150));
		frame.addWindowListener(this);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(false);
	}
	
	public void showModal() {
		frame.setVisible(true);
		frontText.requestFocus();
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

	public JPanel getColorPanel() {
		return colorPanel;
	}

	public void setColorPanel(JPanel colorPanel) {
		this.colorPanel = colorPanel;
	}

	public JLabel getRedTextLabel() {
		return redTextLabel;
	}

	public void setRedTextLabel(JLabel redTextLabel) {
		this.redTextLabel = redTextLabel;
	}

	public JLabel getGreenTextLabel() {
		return greenTextLabel;
	}

	public void setGreenTextLabel(JLabel greenTextLabel) {
		this.greenTextLabel = greenTextLabel;
	}

	public JLabel getBlueTextLabel() {
		return blueTextLabel;
	}

	public void setBlueTextLabel(JLabel blueTextLabel) {
		this.blueTextLabel = blueTextLabel;
	}

	public JTextArea getRedText() {
		return redText;
	}

	public void setRedText(JTextArea redText) {
		this.redText = redText;
	}

	public JTextArea getGreenText() {
		return greenText;
	}

	public void setGreenText(JTextArea greenText) {
		this.greenText = greenText;
	}

	public JTextArea getBlueText() {
		return blueText;
	}

	public void setBlueText(JTextArea blueText) {
		this.blueText = blueText;
	}

	public GridBagConstraints getGbc() {
		return gbc;
	}

	public void setGbc(GridBagConstraints gbc) {
		this.gbc = gbc;
	}
}
