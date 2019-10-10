package easynotes.templates;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpTemplate
{

	private JDialog dialog;
	private JFrame frame;
	private JPanel panel;
	private JLabel label;
	
	public HelpTemplate()
	{
		
		// Initialize properties
		frame = new JFrame("Help");
		dialog = new JDialog(frame, true);
		panel = new JPanel();
		label = new JLabel("Help");
		
		// Add components
		frame.add(panel);
		panel.add(label);
		
		// Prepare for display
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
