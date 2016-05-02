package deck;

import java.util.ArrayList;
import java.util.ListIterator;

public class Deck {
	private ArrayList<Card> deck = new ArrayList<Card>();

	public Deck() {
		for (int i = 4; i >= 1; i--) {
			for (int j = 14; j >= 2; j--) {
				deck.add(new Card(j,i));
			}
		}
	}
	public String toString(){
		String str="";
		for(int i=0;i<deck.size();i++){
			str=str+deck.get(i).toString()+" ";
			if(i!=deck.size()-1&&deck.get(i+1).getSuit()<deck.get(i).getSuit()){
				str=str+"\n";
			}
		}
		return str;
	}
}
