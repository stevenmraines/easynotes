package easynotes.models;

import java.awt.Color;
import java.io.Serializable;

/*
 * This is the main model used by the application.
 * It simply contains the card front and back text.
 */
// TODO trigger events when these props are changed to auto update template
public class Card implements Serializable {
	private static final long serialVersionUID = -6450209576226923325L;
	private String front;
	private String back;
	private Color color;
	
	public Card() {
		front = "";
		back = "";
		color = Color.white;
	}
	
	public Card(String front) {
		this.front = front;
		back = "";
		color = Color.white;
	}
	
	public Card(String front, String back) {
		this.front = front;
		this.back = back;
		color = Color.white;
	}
	
	public Card(String front, String back, Color color) {
		this.front = front;
		this.back = back;
		this.color = color;
	}

	/*
	 * Setters and getters.
	 */
	public String getFront() {
		return front;
	}

	public void setFront(String front) {
		this.front = front;
	}

	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		this.back = back;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
