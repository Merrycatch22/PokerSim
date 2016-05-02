package driver;

import deck.*;

public class TestDriver {
	public static void main(String[] args){
		/*Card[][] deck=new Card[13][4];
		for (int i=0;i<13;i++){
			for (int j=0;j<4;j++){
				deck[i][j]=new Card(i+2,j+1);
				System.out.println(deck[i][j].toString());
			}
		}
		Card[][] cloneDeck=new Card[13][4];
		for (int i=0;i<13;i++){
			for (int j=0;j<4;j++){
				cloneDeck[i][j]=new Card(deck[i][j].toString());
				System.out.println(cloneDeck[i][j].getRank()+" "+cloneDeck[i][j].getSuit());
			}
		}*/
		//System.out.println(new Card("Kd").compareTo(new Card("Ad")));
		Hand hand=new Hand(new Card("3d"),new Card("3h"),new Card("3c"),new Card("As"),new Card("3s"));
		hand.arrangeHand();
		System.out.println(hand.toString());
		hand.rankHand();
		System.out.println(hand.getHandRank());
	}
}
