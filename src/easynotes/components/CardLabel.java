package easynotes.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import easynotes.models.Card;

public class CardLabel extends JLabel
{
	
	private static final long serialVersionUID = -5481487587265558470L;
	
	// Register model
	private Card card;
	
	// Register default size constant
	public static final Dimension defaultDimensions = new Dimension(500, 300);
	
	// Register default font size constant
	// TODO make this more dynamic like this example: https://stackoverflow.com/questions/2715118/how-to-change-the-size-of-the-font-of-a-jlabel-to-take-the-maximum-size
	public static final int defaultFontSize = 24;
	
	// Register size scale value
	// TODO this should be responsibility of CorkboardController
	private float scale;
	
	public CardLabel(Card card)
	{
		
		super();
		
		// Initialize properties
		this.setCard(card);
		scale = 0.5f;
		
		// Prepare for display
		LineBorder line = new LineBorder(Color.lightGray);
		EmptyBorder empty = new EmptyBorder(5, 5, 5, 5);
		this.setBorder(new CompoundBorder(line, empty));
		this.setPreferredSize(getScaledDimensions());
		this.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		this.setAlignmentY(JLabel.CENTER_ALIGNMENT);
		this.setOpaque(true);
		this.setFont(getScaledFont());
		
	}
	
	/*
	 * The setText method is overridden to add HTML to the given text,
	 * this allows line wrapping on the label.
	 * 
	 * Newline characters are replaced with HTML line breaks to
	 * preserve user-added line breaks.
	 */
	@Override
	public void setText(String text)
	{
		
		text = text.replaceAll("\n", "<br />");
		super.setText("<html>" + text + "</html>");
		
	}
	
	private Dimension getScaledDimensions()
	{
		
		int scaledWidth = Math.round(
			CardLabel.defaultDimensions.width * this.scale
		);
		
		int scaledHeight = Math.round(
			CardLabel.defaultDimensions.height * this.scale
		);
		
		return new Dimension(scaledWidth, scaledHeight);
		
	}
	
	private Font getScaledFont()
	{
		
		Font currentFont = this.getFont();
		
		int scaledFontSize = Math.round(CardLabel.defaultFontSize * this.scale);
		
		return new Font(
			currentFont.getName(),
			currentFont.getStyle(),
			scaledFontSize
		);
		
	}

	/*
	 * Setters and getters
	 */
	public Card getCard()
	{
		return card;
	}

	public void setCard(Card card)
	{
		
		this.card = card;
		this.setBackground(card.getBackgroundColor());
		this.setForeground(card.getFontColor());
		this.setText(card.getFrontText());
		
		if(card.isFlipped()) {
			this.setText(card.getBackText());
		}
		
	}

	public float getScale()
	{
		return scale;
	}
	
	public void setScale(float scale)
	{
		this.scale = scale;
	}

	// TODO make sure newly added cards use current slider value and don't reset to default
	public void setScale(int scale)
	{
		
		// Value will be between 0 and 100 coming from JSlider
		this.scale = scale * 0.01f;
		this.setPreferredSize(getScaledDimensions());
		this.setFont(getScaledFont());
		this.revalidate();
		this.repaint();
		
	}
	
}
