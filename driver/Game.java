package driver;

import deck.*;
import java.util.*;

public class Game {
	public static Scanner in = new Scanner(System.in);
	public static Deck deck;

	public static void play(int players) {
		deck = new Deck();
		Hand[] hands=new Hand[players];
		for(int i=0;i<hands.length;i++){
			hands[i]=dealHand(deck);
			System.out.println(hands[i].toString());
		}
	}
	public static Hand dealHand(Deck deck) {
		return new Hand(deck.remove((int) (deck.size() * Math.random())),deck.remove((int) (deck.size() * Math.random())),deck.remove((int) (deck.size() * Math.random())),deck.remove((int) (deck.size() * Math.random())),deck.remove((int) (deck.size() * Math.random())));
	}

	public static void main(String[] args) {
		System.out.println("How many players?");
		int players = in.nextInt();
		boolean quit = false;
		while (!quit) {
			play(players);
			System.out.println("Play Again?(y/n)");
			if (in.next().equals("n")) {
				quit = true;
			}

		}
	}
}
