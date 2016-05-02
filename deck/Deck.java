package deck;

import java.util.ArrayList;
import java.util.Iterator;

public class Deck {
	private ArrayList<Card> deck = new ArrayList<Card>();

	public Deck() {
		for (int i = 14; i >= 2; i--) {
			for (int j = 4; j >= 1; j--) {
				deck.add(new Card(i, j));
			}
		}
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < deck.size(); i++) {
			str = str + deck.get(i).toString() + " ";
			if (i % 13 == 12) {
				str = str + "\n";
			}
		}
		return str;
	}

	public void add(Card card) {
		deck.add(card);

	}

	public void add(String string) {
		deck.add(new Card(string));
	}

	public Card get(int index) {
		return deck.get(index);
	}

	public Card remove(String string) {
		Iterator<Card> iter = deck.iterator();
		Card card;
		while (iter.hasNext()) {
			card = iter.next();
			deck.remove(card);
			if (card.toString().equals(string)) {
				return card;
			}
		}
		return null;
	}

	public Card remove(int index) {
		Card card = deck.get(index);

		deck.remove(card);
		return card;

	}

	public Hand dealHand() {
		Card[] array = { remove((int) (deck.size() * Math.random())),
				remove((int) (deck.size() * Math.random())), remove((int) (deck.size() * Math.random())),
				remove((int) (deck.size() * Math.random())), remove((int) (deck.size() * Math.random())) };
		return new Hand(array);
	}
	public int size(){
		return deck.size();
	}
}
