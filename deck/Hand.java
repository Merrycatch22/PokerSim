package deck;

import java.util.ArrayList;
/**
 * A five card hand. Also holds ranking capabilities.
 * @author Merrycatch22
 *
 */
public class Hand implements Comparable<Hand> {
	/**
	 * holds the 5 cards in array.
	 */
	private Card[] hand=new Card[5];
	/**
	 * Strength of the hand rank, 8 (Straight Flush) to 0 (High Card)
	 */
	private int strength = -1;
	/**
	 * Rank of the hand in words. Call apply handRank to run.
	 */
	private String handRank = "";
	/**
	 * Stores the sub-ranks. cardRank[0] is usually the <Hand> to "rank" part.
	 */
	private int[] cardRank = { 0, 0, 0, 0, 0 };
	/**
	 * 2 to "2", 14 to "A"
	 */
	private final String RANKSTR = "  23456789TJQKA";
	/**
	 * 1 to "c", 4 to "s"
	 */
	private final String SUITSTR = " cdhs";

	public Hand() {
		
	}

	public Hand(Card[] hand) {
		this.hand = hand;
	}

	public Hand(Card c0, Card c1, Card c2, Card c3, Card c4) {
		this();
		hand[0] = c0;
		hand[1] = c1;
		hand[2] = c2;
		hand[3] = c3;
		hand[4] = c4;
	}

	public Hand(String string) {
		this();
		for (int i = 0; i < string.length() / 2; i++) {
			hand[i] = new Card(string.substring(2 * i, 2 * (i + 1)));
		}
	}

	public Card[] getHand() {
		return hand;
	}

	public void setHand(Card[] hand) {
		this.hand = hand;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public String getHandRank() {
		return handRank;
	}

	public void setHandRank(String handRank) {
		this.handRank = handRank;
	}

	public int[] getCardRank() {
		return cardRank;
	}

	public void setCardRank(int[] cardRank) {
		this.cardRank = cardRank;
	}

	public String getRANKSTR() {
		return RANKSTR;
	}

	public String getSUITSTR() {
		return SUITSTR;
	}

	@Override
	/**
	 * @return The five cards, spaced.
	 */
	public String toString() {
		String handString = "";
		for (int i = 0; i < hand.length; i++) {
			handString = handString + " " + hand[i].toString();
		}
		return handString;
	}
	@Override
	/**
	 * Compares two hands.
	 * @return positive if this is bigger, negative otherwise, 0 if the same hand.
	 */
	
	public int compareTo(Hand other) {
		int result = strength - other.getStrength();
		for (int i = 0; i < cardRank.length && result == 0; i++) {
			result = cardRank[i] - other.getCardRank()[i];
		}
		return result;
	}
	/**
	 * The first character designates the strength, subsequent characters subranks.
	 * @param tokens
	 * 
	 * @return return positive if this is bigger, negative otherwise, 0 if the same hand.
	 */
	public int compareTo(String tokens){
		int result=strength-Character.getNumericValue(tokens.charAt(0));
		for (int i = 0; i < cardRank.length && i < tokens.length()-1 && result == 0; i++) {
			for (int j = 2; j < RANKSTR.length(); j++) {
				if (RANKSTR.charAt(j) == tokens.charAt(i+1)) {
					result=cardRank[i]-j;
					break;
				}
			}
		}
		return result;
	}
	/**
	 * Arranges the hand, largest hard in [0].
	 */
	public void arrangeHand() {
		Card temp;
		for (int i = 0; i < hand.length - 1; i++) {
			int minLoc = i;
			for (int j = i + 1; j < hand.length; j++) {
				if (hand[j].compareTo(hand[minLoc]) > 0) {
					minLoc = j;
				}
			}
			if (minLoc != i) {
				temp = hand[i];
				hand[i] = hand[minLoc];
				hand[minLoc] = temp;
			}
		}
	}

	public void rankHand() {
		// arranges hand before ranking
		arrangeHand();
		// flush
		boolean isFlush = true;
		// straight w/o wheel
		boolean isStraightAndNotWheel = true;

		for (int i = 1; i < hand.length; i++) {
			if (hand[0].getSuit() != hand[i].getSuit()) {
				isFlush = false;
			}
			if (hand[0].getRank() != hand[i].getRank() + i) {
				isStraightAndNotWheel = false;
			}
		}
		// wheel
		boolean isWheel = !isStraightAndNotWheel;
		if (!(hand[0].getRank() == 14 && hand[4].getRank() == 2)) {
			isWheel = false;
		}

		for (int i = 2; i < hand.length && isWheel; i++) {
			if (hand[1].getRank() != hand[i].getRank() + i - 1) {
				isWheel = false;
			}
		}
		// boolean isStraight=isWheel||isStraightAndNotWheel;
		if (isFlush && isStraightAndNotWheel) { // straight flush no wheel
			strength = 8;
			cardRank[0] = hand[0].getRank();
		} else if (isFlush && isWheel) {
			strength = 8;
			cardRank[0] = hand[1].getRank();// to get the 5
		} else if (isFlush) {
			strength = 5;
			for (int i = 0; i < cardRank.length; i++) {
				cardRank[i] = hand[i].getRank();
			}

		} else if (isStraightAndNotWheel) {
			strength = 4;
			cardRank[0] = hand[0].getRank();

		} else if (isWheel) {
			strength = 4;
			cardRank[0] = hand[1].getRank(); // force 5

		} else {
			// quads testing
			boolean isQuads = false;
			int quadCombo = 1;
			for (int i = 0; i < hand.length - 3 && !isQuads; i++) {
				quadCombo = 1;
				for (int j = i + 1; j < i + 4; j++) {
					if (hand[i].getRank() == hand[j].getRank()) {
						quadCombo++;
					}
					if (quadCombo >= 4) {
						isQuads = true;
						cardRank[0] = hand[i].getRank();
					}
				}
			}
			if (isQuads) {
				strength = 7;
			} else {
				boolean hasTrips = false;
				boolean isHouse = false;
				int tripCombo = 1;
				for (int i = 0; i < hand.length - 2 && !hasTrips; i++) {
					tripCombo = 1;
					for (int j = i + 1; j < i + 3; j++) {
						if (hand[i].getRank() == hand[j].getRank()) {
							tripCombo++;
						}
						if (tripCombo >= 3) {
							hasTrips = true;
							cardRank[0] = hand[i].getRank();
							// house checking
							if (i == 0 && hand[3].getRank() == hand[4].getRank()) {
								isHouse = true;
								cardRank[1] = hand[3].getRank();
							} else if (i == 2 && hand[0].getRank() == hand[1].getRank()) {
								isHouse = true;
								cardRank[1] = hand[0].getRank();
							} else {
								switch (i) {
								case 0:
									cardRank[1] = hand[3].getRank();
									cardRank[2] = hand[4].getRank();
									break;
								case 1:
									cardRank[1] = hand[0].getRank();
									cardRank[2] = hand[4].getRank();
									break;
								case 2:
									cardRank[1] = hand[0].getRank();
									cardRank[2] = hand[1].getRank();
									break;
								default:
								}
							}
						}
					}
				}
				if (isHouse) {
					strength = 6;
				} else if (hasTrips) {
					strength = 3;
				} else {
					boolean hasPair = false;
					boolean isTwoPair = false;
					for (int i = 0; i < hand.length - 1 && !hasPair; i++) {

						if (hand[i].getRank() == hand[i + 1].getRank()) {

							hasPair = true;
							cardRank[0] = hand[i].getRank();
							// check two pair
							if (i == 0 && hand[2].getRank() == hand[3].getRank()) {
								isTwoPair = true;
								cardRank[1] = hand[2].getRank();
								cardRank[2] = hand[4].getRank();
							} else if (i == 0 && hand[3].getRank() == hand[4].getRank()) {
								isTwoPair = true;
								cardRank[1] = hand[3].getRank();
								cardRank[2] = hand[2].getRank();
							} else if (i == 1 && hand[3].getRank() == hand[4].getRank()) {
								isTwoPair = true;
								cardRank[1] = hand[3].getRank();
								cardRank[2] = hand[0].getRank();
							} else {
								if (i <= 0) {
									cardRank[1] = hand[2].getRank();
								} else {
									cardRank[1] = hand[0].getRank();
								}
								if (i <= 1) {
									cardRank[2] = hand[3].getRank();
								} else {
									cardRank[2] = hand[1].getRank();
								}
								if (i <= 2) {
									cardRank[3] = hand[4].getRank();
								} else {
									cardRank[3] = hand[2].getRank();
								}
							}

						}
					}
					if (isTwoPair) {
						strength = 2;
					} else if (hasPair) {
						strength = 1;
					} else {
						strength = 0;
						for (int i = 0; i < cardRank.length; i++) {
							cardRank[i] = hand[i].getRank();
						}
					}

				}

			}
		}

	}

	public void applyHandRank() {
		switch (strength) {
		case 8:
			handRank = "Straight Flush to " + RANKSTR.charAt(cardRank[0]);
			break;
		case 7:
			handRank = "Quad " + RANKSTR.charAt(cardRank[0]) + "'s";
			break;
		case 6:
			handRank = "Full House " + RANKSTR.charAt(cardRank[0]) + "'s over " + RANKSTR.charAt(cardRank[1]) + "'s";
			break;
		case 5:
			handRank = "" + RANKSTR.charAt(cardRank[0]) + " high Flush +";
			for (int i = 1; i < cardRank.length; i++) {
				handRank = handRank + RANKSTR.charAt(cardRank[i]);
			}
			break;
		case 4:
			handRank = "Straight to " + RANKSTR.charAt(cardRank[0]);
			break;
		case 3:
			handRank = "Trip " + RANKSTR.charAt(cardRank[0]) + "'s +" + RANKSTR.charAt(cardRank[1])
					+ RANKSTR.charAt(cardRank[2]);
			break;
		case 2:
			handRank = "Two Pair " + RANKSTR.charAt(cardRank[0]) + "'s and " + RANKSTR.charAt(cardRank[1]) + "'s +"
					+ RANKSTR.charAt(cardRank[2]);
			break;
		case 1:
			handRank = "A Pair of " + RANKSTR.charAt(cardRank[0]) + "'s +";
			for (int i = 1; i <= 3; i++) {
				handRank = handRank + RANKSTR.charAt(cardRank[i]);
			}
			break;
		case 0:
			handRank = "High Card " + RANKSTR.charAt(cardRank[0]) + " +";
			for (int i = 1; i < cardRank.length; i++) {
				handRank = handRank + RANKSTR.charAt(cardRank[i]);
			}
			break;
		default:
			handRank = "Unknown";

		}
	}

}
