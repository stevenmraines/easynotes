package easynotes.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	
	public CardLabel(Card card)
	{
		
		super();
		
		init(card, 50);
		
	}
	
	public CardLabel(Card card, int scale)
	{
		
		super();
		
		init(card, scale);
		
	}
	
	private void init(Card card, int scale)
	{
		
		// Initialize properties
		this.setCard(card);
		
		// Prepare for display
		LineBorder line = new LineBorder(Color.lightGray);
		EmptyBorder empty = new EmptyBorder(5, 5, 5, 5);
		this.setBorder(new CompoundBorder(line, empty));
		this.setPreferredSize(getScaledDimensions(getNormalizedScale(scale)));
		this.setFont(getScaledFont(getNormalizedScale(scale)));
		// TODO Center CardLabel text
		this.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		this.setAlignmentY(JLabel.CENTER_ALIGNMENT);
		this.setOpaque(true);
		
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
	
	private Dimension getScaledDimensions(float scale)
	{
		
		return new Dimension(
			Math.round(CardLabel.defaultDimensions.width * scale),
			Math.round(CardLabel.defaultDimensions.height * scale)
		);
		
	}
	
	private Font getScaledFont(float scale)
	{
		
		return new Font(
			this.getFont().getName(),
			this.getFont().getStyle(),
			Math.round(CardLabel.defaultFontSize * scale)
		);
		
	}
	
	private float getNormalizedScale(int scale)
	{
		return scale * 0.01f;
	}
	
	public void resize(int scale)
	{
		
		this.setPreferredSize(getScaledDimensions(getNormalizedScale(scale)));
		this.setFont(getScaledFont(getNormalizedScale(scale)));
		this.revalidate();
		this.repaint();
		
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
	
}
