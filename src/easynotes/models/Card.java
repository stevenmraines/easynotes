package easynotes.models;

import java.io.Serializable;

public class Card implements Serializable {
	private static final long serialVersionUID = -6450209576226923325L;
	private String front;
	private String back;
	
	public Card() {
		front = "";
		back = "";
	}
	
	public Card(String front) {
		this.front = front;
		back = "";
	}
	
	public Card(String front, String back) {
		this.front = front;
		this.back = back;
	}

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
	
}
