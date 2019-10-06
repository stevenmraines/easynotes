package easynotes.models;

import java.awt.Color;
import java.io.Serializable;

public class Card implements Serializable
{
	
	private static final long serialVersionUID = -6450209576226923325L;
	private String frontText;
	private String backText;
	private Color fontColor;
	private Color backgroundColor;
	private boolean isFlipped;
	
	public Card()
	{
		init("", "", Color.darkGray, Color.white, false);
	}
	
	public Card(String frontText)
	{
		init(frontText, "", Color.darkGray, Color.white, false);
	}
	
	public Card(String frontText, String backText)
	{
		init(frontText, backText, Color.darkGray, Color.white, false);
	}
	
	public Card(String frontText, String backText, Color fontColor)
	{
		init(frontText, backText, fontColor, Color.white, false);
	}
	
	public Card(
		String frontText,
		String backText,
		Color fontColor,
		Color backgroundColor
	) {
		init(frontText, backText, fontColor, backgroundColor, false);
	}

	public Card(
		String frontText,
		String backText,
		Color fontColor,
		Color backgroundColor,
		boolean isFlipped
	) {
		init(frontText, backText, fontColor, backgroundColor, isFlipped);
	}
	
	private void init(
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
	
	public Card(Card duplicateCard)
	{
		init(
			duplicateCard.getFrontText(),
			duplicateCard.getBackText(),
			duplicateCard.getFontColor(),
			duplicateCard.getBackgroundColor(),
			duplicateCard.isFlipped()
		);
	}
	
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
