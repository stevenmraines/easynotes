package easynotes.models;

import java.util.ArrayList;

public class Project {
	private ArrayList<Card> cards;
	
	public Project() {
		cards = new ArrayList<Card>();
	}
	
	public Project(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	public int getSize() {
		return cards.size();
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public void addCard(int index, Card card) {
		cards.add(index, card);
	}
	
	public void removeCard(Card card) {
		cards.remove(card);
	}
	
	public void removeCard(int index) {
		cards.remove(index);
	}
	
	public Card get(int index) {
		return cards.get(index);
	}
	
	public int indexOf(Card card) {
		return cards.indexOf(card);
	}
}
