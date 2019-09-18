package easynotes.models;

import java.awt.Color;
import java.io.Serializable;

/**
 * 
 * The Card class is the model for an index card.
 * It has properties for front and back text,
 * font color, background color, and a boolean
 * indicating whether to show front or back text.
 * 
 * @author sraines
 *
 */
public class Card implements Serializable
{
	
	// TODO trigger events when these props are changed to auto update template
	private static final long serialVersionUID = -6450209576226923325L;
	private String frontText;
	private String backText;
	private Color fontColor;
	private Color backgroundColor;
	private boolean isFlipped;
	
	public Card()
	{
		
		frontText = "";
		backText = "";
		fontColor = Color.darkGray;
		backgroundColor = Color.white;
		isFlipped = false;
		
	}
	
	public Card(String frontText)
	{
		
		this.frontText = frontText;
		backText = "";
		fontColor = Color.darkGray;
		backgroundColor = Color.white;
		isFlipped = false;
		
	}
	
	public Card(String frontText, String backText)
	{
		
		this.frontText = frontText;
		this.backText = backText;
		fontColor = Color.darkGray;
		backgroundColor = Color.white;
		isFlipped = false;
		
	}
	
	public Card(String frontText, String backText, Color fontColor)
	{
		
		this.frontText = frontText;
		this.backText = backText;
		this.fontColor = fontColor;
		backgroundColor = Color.white;
		isFlipped = false;
		
	}
	
	public Card(
		String frontText,
		String backText,
		Color fontColor,
		Color backgroundColor
	) {
		
		this.frontText = frontText;
		this.backText = backText;
		this.fontColor = fontColor;
		this.backgroundColor = backgroundColor;
		isFlipped = false;
		
	}

	public Card(
		String frontText,
		String backText,
		Color fontColor,
		Color backgroundColor,
		boolean isFlipped
	) {
		
		this.frontText = frontText;
		this.backText = backText;
		this.fontColor = fontColor;
		this.backgroundColor = backgroundColor;
		this.isFlipped = isFlipped;
		
	}
	
	/**
	 * The flip function simply toggles the isFlipped property.
	 * The CorkboardController will handle updating the card display.
	 */
	public void flip()
	{
		isFlipped = !isFlipped;
	}

	/*
	 * Setters and getters
	 */
	public String getFrontText()
	{
		return frontText;
	}

	public void setFrontText(String frontText)
	{
		this.frontText = frontText;
	}

	public String getBackText()
	{
		return backText;
	}

	public void setBackText(String backText)
	{
		this.backText = backText;
	}

	public Color getFontColor()
	{
		return fontColor;
	}

	public void setFontColor(Color fontColor)
	{
		this.fontColor = fontColor;
	}

	public Color getBackgroundColor()
	{
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor)
	{
		this.backgroundColor = backgroundColor;
	}
	
	public boolean isFlipped()
	{
		return isFlipped;
	}
	
	public void setIsFlipped(boolean isFlipped)
	{
		this.isFlipped = isFlipped;
	}
	
}
