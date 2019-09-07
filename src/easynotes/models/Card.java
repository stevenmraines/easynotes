package easynotes.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Card {
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
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
		changes.firePropertyChange("front", this.front, front);
		this.front = front;
	}

	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		changes.firePropertyChange("back", this.back, back);
		this.back = back;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }
}
