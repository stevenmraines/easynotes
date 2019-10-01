package easynotes.templates;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

public abstract class CardTextTemplate
{
	
	protected JDialog dialog;
	protected JFrame frame;
	protected JPanel panel;
	protected JPanel frontTextLabelPanel;
	protected JPanel backTextLabelPanel;
	protected JPanel actionButtonPanel;
	protected JPanel colorPanel;
	protected JLabel frontTextLabel;
	protected JLabel backTextLabel;
	protected JLabel redTextLabel;
	protected JLabel greenTextLabel;
	protected JLabel blueTextLabel;
	protected JTextArea frontText;
	protected JTextArea backText;
	protected SpinnerNumberModel redSpinnerModel;
	protected SpinnerNumberModel greenSpinnerModel;
	protected SpinnerNumberModel blueSpinnerModel;
	protected JSpinner redSpinner;
	protected JSpinner greenSpinner;
	protected JSpinner blueSpinner;
	protected JScrollPane frontScrollPane;
	protected JScrollPane backScrollPane;
	protected JButton actionButton;
	protected GridBagConstraints gbc;
	
	public CardTextTemplate()
	{
		
		// Initialize properties
		frame = new JFrame();
		dialog = new JDialog(frame, true);
		panel = new JPanel();
		frontTextLabelPanel = new JPanel();
		backTextLabelPanel = new JPanel();
		actionButtonPanel = new JPanel();
		colorPanel = new JPanel(new GridBagLayout());
		frontTextLabel = new JLabel("Front text");
		backTextLabel = new JLabel("Back text");
		redTextLabel = new JLabel("Red");
		greenTextLabel = new JLabel("Green");
		blueTextLabel = new JLabel("Blue");
		frontText = new JTextArea(7, 40);
		backText = new JTextArea(7, 40);
		redSpinnerModel = new SpinnerNumberModel(255, 0, 255, 1);
		greenSpinnerModel = new SpinnerNumberModel(255, 0, 255, 1);
		blueSpinnerModel = new SpinnerNumberModel(255, 0, 255, 1);
		redSpinner = new JSpinner(redSpinnerModel);
		greenSpinner = new JSpinner(greenSpinnerModel);
		blueSpinner = new JSpinner(blueSpinnerModel);
		frontScrollPane = new JScrollPane(frontText);
		backScrollPane = new JScrollPane(backText);
		actionButton = new JButton();
		gbc = new GridBagConstraints();
		
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
		actionButtonPanel.add(actionButton);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		colorPanel.add(redTextLabel, gbc);
		gbc.gridx = 1;
		colorPanel.add(greenTextLabel, gbc);
		gbc.gridx = 2;
		colorPanel.add(blueTextLabel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		colorPanel.add(redSpinner, gbc);
		gbc.gridx = 1;
		colorPanel.add(greenSpinner, gbc);
		gbc.gridx = 2;
		colorPanel.add(blueSpinner, gbc);
		
		// Prepare frame and JDialog
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

	public JPanel getActionButtonPanel()
	{
		return actionButtonPanel;
	}

	public void setActionButtonPanel(JPanel actionButtonPanel)
	{
		this.actionButtonPanel = actionButtonPanel;
	}

	public JPanel getColorPanel()
	{
		return colorPanel;
	}

	public void setColorPanel(JPanel colorPanel)
	{
		this.colorPanel = colorPanel;
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

	public JLabel getRedTextLabel()
	{
		return redTextLabel;
	}

	public void setRedTextLabel(JLabel redTextLabel)
	{
		this.redTextLabel = redTextLabel;
	}

	public JLabel getGreenTextLabel()
	{
		return greenTextLabel;
	}

	public void setGreenTextLabel(JLabel greenTextLabel)
	{
		this.greenTextLabel = greenTextLabel;
	}

	public JLabel getBlueTextLabel()
	{
		return blueTextLabel;
	}

	public void setBlueTextLabel(JLabel blueTextLabel)
	{
		this.blueTextLabel = blueTextLabel;
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

	public SpinnerNumberModel getRedSpinnerModel()
	{
		return redSpinnerModel;
	}

	public void setRedSpinnerModel(SpinnerNumberModel redSpinnerModel)
	{
		this.redSpinnerModel = redSpinnerModel;
	}

	public SpinnerNumberModel getGreenSpinnerModel()
	{
		return greenSpinnerModel;
	}

	public void setGreenSpinnerModel(SpinnerNumberModel greenSpinnerModel)
	{
		this.greenSpinnerModel = greenSpinnerModel;
	}

	public SpinnerNumberModel getBlueSpinnerModel()
	{
		return blueSpinnerModel;
	}

	public void setBlueSpinnerModel(SpinnerNumberModel blueSpinnerModel)
	{
		this.blueSpinnerModel = blueSpinnerModel;
	}

	public JSpinner getRedSpinner()
	{
		return redSpinner;
	}

	public void setRedSpinner(JSpinner redSpinner)
	{
		this.redSpinner = redSpinner;
	}

	public JSpinner getGreenSpinner()
	{
		return greenSpinner;
	}

	public void setGreenSpinner(JSpinner greenSpinner)
	{
		this.greenSpinner = greenSpinner;
	}

	public JSpinner getBlueSpinner()
	{
		return blueSpinner;
	}

	public void setBlueSpinner(JSpinner blueSpinner)
	{
		this.blueSpinner = blueSpinner;
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

	public GridBagConstraints getGbc()
	{
		return gbc;
	}

	public void setGbc(GridBagConstraints gbc)
	{
		this.gbc = gbc;
	}
	
}
