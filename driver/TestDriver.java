package driver;

import deck.*;

public class TestDriver {
	public static void main(String[] args) {
		
		 /*Hand hand=new Hand("4d4cAh2s3d"); Hand other=new Hand ("5s5d2c3h4d");
		 hand.rankHand(); other.rankHand(); hand.applyHandRank();
		 other.applyHandRank(); System.out.println(hand.getHandRank());
		 System.out.println(other.getHandRank());
		 System.out.println(hand.compareTo(other));
		 
		long startTime, stopTime;
		double elapsedTime;
		startTime = System.nanoTime(); 
		Deck deck = new Deck();
		int size = deck.size();
		Hand hand;
		float all = 0.0f;
		float event = 0.0f;
		for (int a = 0; a < size; a++) {
			for (int b = a + 1; b < size; b++) {
				for (int c = b + 1; c < size; c++) {
					for (int d = c + 1; d < size; d++) {
						for (int e = d + 1; e < size; e++) {
							hand=new Hand(deck.get(a),deck.get(b),deck.get(c),deck.get(d),deck.get(e));
							hand.rankHand();
							if(hand.getStrength()==1){
								event++;
							}
							all++;
						}
					}
				}
			}

		}
		System.out.println(event+" / "+all+" = "+100.0f*event/all+"%");
		
		stopTime = System.nanoTime(); // stop timing ---------------------
		elapsedTime = (double) (stopTime - startTime) / 1000000.0;
		System.out.println(elapsedTime + " milliseconds.");*/
		Deck deck=new Deck();
		Hand hand=deck.dealHand();
		Hand other=deck.dealHand();
		 hand.rankHand(); 
		 other.rankHand(); 
		 hand.applyHandRank();
		 other.applyHandRank();
		 System.out.println(hand.toString());
		 System.out.println(other.toString());
		 System.out.println(hand.getHandRank());
		 System.out.println(other.getHandRank());
		 System.out.println(hand.compareTo(other));
		

	}
}
