package easynotes.components;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import easynotes.models.Card;

/**
 * 
 * The CardLabel class is a Swing component for
 * drawing a card on the corkboard view.
 * 
 * @author sraines
 *
 */
public class CardLabel extends JLabel
{
	
	private static final long serialVersionUID = -5481487587265558470L;
	
	// Register model
	private Card card;
	
	public CardLabel(Card card)
	{
		
		super();
		
		/*
		 * Initialize properties
		 * 
		 * Call setCard so that text, background color,
		 * and font color are also set.
		 */
		this.setCard(card);
		
		// Prepare for display
		LineBorder line = new LineBorder(Color.lightGray);
		EmptyBorder empty = new EmptyBorder(5, 5, 5, 5);
		this.setBorder(new CompoundBorder(line, empty));
		this.setPreferredSize(new Dimension(250, 150));
		this.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		this.setAlignmentY(JLabel.CENTER_ALIGNMENT);
		this.setOpaque(true);
		
	}
	
	/**
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
