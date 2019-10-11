package easynotes.templates;

import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HelpTemplate
{

	private JDialog dialog;
	private JFrame frame;
	private JPanel panel;
	private JLabel titleLabel;
	private JLabel bodyLabel;
	
	public HelpTemplate()
	{
		
		// Initialize properties
		frame = new JFrame("Help");
		dialog = new JDialog(frame, true);
		panel = new JPanel();
		titleLabel = new JLabel("Basic Usage");
		
		String bodyText =
			"<html>Right click on the application background to show the program context menu.<br />"
			+ "<br />Right click on a card to show a context menu with options to edit, delete, etc.<br />"
			+ "<br />CTRL + Left Click a card to flip it.<br />"
			+ "<br />Double click a card to edit it.</html>";
		
		bodyLabel = new JLabel(bodyText);
		
		// Set panel layout manager
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		// Give panel contents some space
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		// Add components
		frame.add(panel);
		panel.add(titleLabel);
		panel.add(Box.createVerticalStrut(15));
		panel.add(bodyLabel);
		
		// Enlarge JLabel font sizes
		titleLabel.setFont(
			new Font(
				titleLabel.getFont().getName(),
				titleLabel.getFont().getStyle(),
				titleLabel.getFont().getSize() + 8
			)
		);
		
		bodyLabel.setFont(
			new Font(
				bodyLabel.getFont().getName(),
				bodyLabel.getFont().getStyle(),
				bodyLabel.getFont().getSize() + 3
			)
		);
		
		// Prepare for display
		// TODO figure out how to center label text
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.pack();
		frame.setVisible(false);
		
	}

	/*
	 * Setters and getters
	 */
	public JDialog getDialog()
	{
		return dialog;
	}

	public void setDialog(JDialog dialog)
	{
		this.dialog = dialog;
	}

	public JFrame getFrame()
	{
		return frame;
	}

	public void setFrame(JFrame frame)
	{
		this.frame = frame;
	}

	public JPanel getPanel()
	{
		return panel;
	}

	public void setPanel(JPanel panel)
	{
		this.panel = panel;
	}
	
}
