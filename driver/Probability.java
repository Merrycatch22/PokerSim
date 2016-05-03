package driver;

import deck.*;
import java.util.*;

public class Probability {
	public static float all = 0.0f;
	public static float event = 0.0f;
	public static long startTime, stopTime;
	public static double elapsedTime;
	public static Scanner in = new Scanner(System.in);

	public static void single() {

		System.out.println("Hand:");
		String inHand = in.next();

		System.out.println("Cards discarded:");
		String inDiscard = in.next();

		System.out.println("Greater than or equal to:");
		String low = in.next();

		System.out.println("Less than:");
		String high = in.next();

		startTime = System.nanoTime();
		
		Deck deck = new Deck();
		Hand hand = new Hand();
		Card[] cards = new Card[5];
		
		int heldCards = inHand.length() / 2;
		for (int i = 0; i < heldCards && i < cards.length; i++) {
			cards[i] = deck.remove(inHand.substring(2 * i, 2 * (i + 1)));
		}
		
		int discards = inDiscard.length() / 2;
		for (int i = 0; i < discards; i++) {
			deck.remove(inDiscard.substring(2 * i, 2 * (i + 1)));
		}
		
		//System.out.println(deck.toString());
		
		all = 0.0f;
		event = 0.0f;
		
		//System.out.println(cards.length - heldCards + " " + deck.size());
		//System.out.println(Arrays.toString(cards));
		
		combination(cards.length - heldCards, deck, cards, hand, 0, low, high);
		/*
		 * for (int a = 0; a < size; a++) { for (int b = a + 1; b < size; b++) {
		 * for (int c = b + 1; c < size; c++) { for (int d = c + 1; d < size;
		 * d++) { for (int e = d + 1; e < size; e++) { hand = new
		 * Hand(deck.get(a), deck.get(b), deck.get(c), deck.get(d),
		 * deck.get(e)); hand.rankHand(); if (hand.getStrength() == 1) {
		 * event++; } all++; } } } }
		 * 
		 * }
		 */
		System.out.println(event + " / " + all + " = " + 100.0f * event / all + "% or 1.0 / " + all / event);

		stopTime = System.nanoTime(); // stop timing ---------------------
		elapsedTime = (double) (stopTime - startTime) / 1000000.0;
		System.out.println("Process took " + elapsedTime + " milliseconds.");
	}

	public static void combination(int len, Deck deck, Card[] cards, Hand hand, int start, String low, String high) {
		if (len == 0) {
			hand = new Hand(cards[0], cards[1], cards[2], cards[3], cards[4]);
			hand.rankHand();

			if (hand.compareTo(low) >= 0 && hand.compareTo(high) < 0) {
				// System.out.println(hand.toString());
				event++;
			}
			all++;
			return;
		}
		for (int i = start; i < deck.size(); i++) {
			cards[5 - len] = deck.get(i);
			combination(len - 1, deck, cards, hand, i + 1, low, high);
		}

	}

	public static void combination(int r, Deck deck, int start, String[] hole) {
		if (r == 0) {
			System.out.println(hole[0] + hole[1]);
			all++;
			return;
		}
		for (int i = start; i < deck.size() - r; i++) {
			hole[hole.length - r] = deck.get(i).getRankSuitStr();
			combination(r - 1, deck, i + 1, hole);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// single();
		/*
		 * Deck deck=new Deck(); combination(2, deck, 0, new String[2]);
		 * System.out.println(all);
		 */
		single();

	}

}
