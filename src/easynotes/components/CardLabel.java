package easynotes.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import easynotes.controllers.CorkboardController;
import easynotes.models.Card;

public class CardLabel extends JLabel implements Transferable
{
	
	private static final long serialVersionUID = -5481487587265558470L;
	
	// Register model
	private Card card;
	
	// Maintain custom DataFlavor for drag and drop
	private static DataFlavor cardLabelFlavor =
			new DataFlavor(CardLabel.class, "A CardLabel object");
	
	public CardLabel(Card card, CorkboardController corkboardController)
	{
		
		super();
		
		// Initialize properties
		this.setCard(card);
		
		// Set transfer handler for drag and drop
		this.setTransferHandler(corkboardController);
		
		// Prepare for display
		LineBorder line = new LineBorder(Color.lightGray);
		EmptyBorder empty = new EmptyBorder(5, 5, 5, 5);
		this.setBorder(new CompoundBorder(line, empty));
		this.setPreferredSize(new Dimension(250, 150));
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
	
	/*
	 * Transferable methods
	 */
	@Override
	public DataFlavor[] getTransferDataFlavors()
	{
		DataFlavor[] flavors = {cardLabelFlavor};
		return flavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor)
	{
		return flavor.equals(cardLabelFlavor);
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException
	{
		
		if(flavor.equals(cardLabelFlavor)) {
			
			return card;
			
		}
		
		throw new UnsupportedFlavorException(flavor);
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

	public static DataFlavor getCardLabelFlavor()
	{
		return cardLabelFlavor;
	}

	public static void setCardLabelFlavor(DataFlavor cardLabelFlavor)
	{
		CardLabel.cardLabelFlavor = cardLabelFlavor;
	}
	
}
