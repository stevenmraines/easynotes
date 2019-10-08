package easynotes.templates;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public abstract class CardTextTemplate implements MouseListener
{
	
	protected JDialog dialog;
	protected JFrame frame;
	protected JPanel panel;
	protected JPanel frontTextLabelPanel;
	protected JPanel backTextLabelPanel;
	protected JPanel colorPanel;
	protected JPanel fontColorPanel;
	protected JPanel backgroundColorPanel;
	protected JPanel fontColorDisplayPanel;
	protected JPanel backgroundColorDisplayPanel;
	protected JPanel actionButtonPanel;
	protected JLabel frontTextLabel;
	protected JLabel backTextLabel;
	protected JLabel fontColorLabel;
	protected JLabel backgroundColorLabel;
	protected JTextArea frontText;
	protected JTextArea backText;
	protected JScrollPane frontScrollPane;
	protected JScrollPane backScrollPane;
	protected JButton actionButton;
	
	public CardTextTemplate()
	{
		
		// Initialize properties
		frame = new JFrame();
		dialog = new JDialog(frame, true);
		panel = new JPanel();
		frontTextLabelPanel = new JPanel();
		backTextLabelPanel = new JPanel();
		colorPanel = new JPanel(new FlowLayout());
		fontColorPanel = new JPanel();
		backgroundColorPanel = new JPanel();
		fontColorDisplayPanel = new JPanel();
		backgroundColorDisplayPanel = new JPanel();
		actionButtonPanel = new JPanel();
		frontTextLabel = new JLabel("Front text");
		backTextLabel = new JLabel("Back text");
		fontColorLabel = new JLabel("Font Color:");
		backgroundColorLabel = new JLabel("Card Color:");
		frontText = new JTextArea(7, 40);
		backText = new JTextArea(7, 40);
		frontScrollPane = new JScrollPane(frontText);
		backScrollPane = new JScrollPane(backText);
		actionButton = new JButton();
		
		// Add components
		frame.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(frontTextLabelPanel);
		panel.add(Box.createVerticalStrut(5));
		panel.add(frontScrollPane);
		panel.add(Box.createVerticalStrut(5));
		panel.add(backTextLabelPanel);
		panel.add(Box.createVerticalStrut(5));
		panel.add(backScrollPane);
		panel.add(Box.createVerticalStrut(5));
		panel.add(colorPanel);
		panel.add(Box.createVerticalStrut(10));
		panel.add(actionButtonPanel);
		frontTextLabelPanel.add(frontTextLabel);
		backTextLabelPanel.add(backTextLabel);
		colorPanel.add(backgroundColorPanel);
		colorPanel.add(fontColorPanel);
		fontColorPanel.add(fontColorLabel);
		fontColorPanel.add(fontColorDisplayPanel);
		backgroundColorPanel.add(backgroundColorLabel);
		backgroundColorPanel.add(backgroundColorDisplayPanel);
		actionButtonPanel.add(actionButton);
		
		// Add action listeners
		fontColorDisplayPanel.addMouseListener(this);
		backgroundColorDisplayPanel.addMouseListener(this);
		
		// Prepare for display
		fontColorDisplayPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backgroundColorDisplayPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		fontColorDisplayPanel.setBorder(new LineBorder(Color.black));
		backgroundColorDisplayPanel.setBorder(new LineBorder(Color.black));
		panel.setBorder(new EmptyBorder(5,5,5,5));
		frame.setMinimumSize(new Dimension(250, 150));
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(false);
		
	}

	/**
	 * The setVisible method triggers the JFrame setVisible method,
	 * while also requesting focus on the first input.
	 * 
	 * @param visible
	 */
	public void setVisible(boolean visible)
	{
		
		frame.setVisible(visible);

		// If it's being made visible, make frontText field request focus
		if(visible) {
			frontText.requestFocus();
		}
		
	}
	
	/*
	 * MouseListener methods
	 */
	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	// TODO make example text in color chooser use chosen font color
	@Override
	public void mouseReleased(MouseEvent e)
	{
		
		if(e.getSource() == fontColorDisplayPanel) {
			
			Color fontColor = JColorChooser.showDialog(
				dialog,
				"Select a font color",
				fontColorDisplayPanel.getBackground()
			);
			
			fontColorDisplayPanel.setBackground(fontColor);
			
		}
		
		if(e.getSource() == backgroundColorDisplayPanel) {

			Color backgroundColor = JColorChooser.showDialog(
				dialog,
				"Select a card color",
				backgroundColorDisplayPanel.getBackground()
			);
			
			backgroundColorDisplayPanel.setBackground(backgroundColor);
			
		}
		
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

	public JPanel getFrontTextLabelPanel()
	{
		return frontTextLabelPanel;
	}

	public void setFrontTextLabelPanel(JPanel frontTextLabelPanel)
	{
		this.frontTextLabelPanel = frontTextLabelPanel;
	}

	public JPanel getBackTextLabelPanel()
	{
		return backTextLabelPanel;
	}

	public void setBackTextLabelPanel(JPanel backTextLabelPanel)
	{
		this.backTextLabelPanel = backTextLabelPanel;
	}

	public JPanel getFontColorPanel()
	{
		return fontColorPanel;
	}

	public void setFontColorPanel(JPanel fontColorPanel)
	{
		this.fontColorPanel = fontColorPanel;
	}

	public JPanel getBackgroundColorPanel()
	{
		return backgroundColorPanel;
	}

	public void setBackgroundColorPanel(JPanel backgroundColorPanel)
	{
		this.backgroundColorPanel = backgroundColorPanel;
	}

	public JPanel getFontColorDisplayPanel()
	{
		return fontColorDisplayPanel;
	}

	public void setFontColorDisplayPanel(JPanel fontColorDisplayPanel)
	{
		this.fontColorDisplayPanel = fontColorDisplayPanel;
	}

	public JPanel getBackgroundColorDisplayPanel()
	{
		return backgroundColorDisplayPanel;
	}

	public void setBackgroundColorDisplayPanel(JPanel backgroundColorDisplayPanel)
	{
		this.backgroundColorDisplayPanel = backgroundColorDisplayPanel;
	}

	public JPanel getActionButtonPanel()
	{
		return actionButtonPanel;
	}

	public void setActionButtonPanel(JPanel actionButtonPanel)
	{
		this.actionButtonPanel = actionButtonPanel;
	}

	public JLabel getFrontTextLabel()
	{
		return frontTextLabel;
	}

	public void setFrontTextLabel(JLabel frontTextLabel)
	{
		this.frontTextLabel = frontTextLabel;
	}

	public JLabel getBackTextLabel()
	{
		return backTextLabel;
	}

	public void setBackTextLabel(JLabel backTextLabel)
	{
		this.backTextLabel = backTextLabel;
	}

	public JLabel getFontColorLabel()
	{
		return fontColorLabel;
	}

	public void setFontColorLabel(JLabel fontColorLabel)
	{
		this.fontColorLabel = fontColorLabel;
	}

	public JLabel getBackgroundColorLabel()
	{
		return backgroundColorLabel;
	}

	public void setBackgroundColorLabel(JLabel backgroundColorLabel)
	{
		this.backgroundColorLabel = backgroundColorLabel;
	}

	public JTextArea getFrontText()
	{
		return frontText;
	}

	public void setFrontText(JTextArea frontText)
	{
		this.frontText = frontText;
	}

	public JTextArea getBackText()
	{
		return backText;
	}

	public void setBackText(JTextArea backText)
	{
		this.backText = backText;
	}

	public JScrollPane getFrontScrollPane()
	{
		return frontScrollPane;
	}

	public void setFrontScrollPane(JScrollPane frontScrollPane)
	{
		this.frontScrollPane = frontScrollPane;
	}

	public JScrollPane getBackScrollPane()
	{
		return backScrollPane;
	}

	public void setBackScrollPane(JScrollPane backScrollPane)
	{
		this.backScrollPane = backScrollPane;
	}

	public JButton getActionButton()
	{
		return actionButton;
	}

	public void setActionButton(JButton actionButton)
	{
		this.actionButton = actionButton;
	}
	
}
