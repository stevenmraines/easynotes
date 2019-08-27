package easynotes;

import java.util.ArrayList;

public class IndexCardController {
	private ArrayList<IndexCard> cards;
	
	public IndexCardController() {
		cards = new ArrayList<IndexCard>();
	}
	
	public IndexCardController(ArrayList<IndexCard> cards) {
		this.cards = cards;
	}
	
	public int getSize() {
		return cards.size();
	}
	
	public ArrayList<IndexCard> getCards() {
		return cards;
	}
	
	public void addCard(IndexCard card) {
		cards.add(card);
	}
	
	public void removeCard(int index) {
		cards.remove(index);
	}
	
	public void moveCard(IndexCard card, int newIndex) {
		cards.remove(cards.indexOf(card));
		cards.add(newIndex, card);
		cards.trimToSize();
	}
}
