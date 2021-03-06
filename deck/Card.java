package deck;
/**
 * 
 * @author Merrycatch22
 *
 */
public class Card implements Comparable<Card> {
	/**
	 * The rank of the card, from 2 to 14.
	 */
	private int rank;
	/**
	 * The suit of the card, from 1 to 4.
	 */
	private int suit;
	/**
	 * String version of the card.
	 */
	private String rankSuitStr;
	/**
	 * Holds the string to convert 2 to "2", 14 to "A"
	 */
	private final String RANKSTR = "  23456789TJQKA";
	/**
	 * Hold the string to convert 1 to "c", 4 to "s"
	 */
	private final String SUITSTR = " cdhs";

	public Card() {
		this(0, 0);
	}

	public Card(int rank, int suit) {
		this.rank = rank;
		this.suit = suit;
		rankSuitStr = "" + rankChar() + suitChar();
	}

	public Card(String rankSuitStr) {
		this();
		this.rankSuitStr = rankSuitStr;
		parseRankSuitStr();

	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getSuit() {
		return suit;
	}

	public String getRankSuitStr() {
		return rankSuitStr;
	}

	public void setRankSuitStr(String rankSuitStr) {
		this.rankSuitStr = rankSuitStr;
	}

	public String getRANKSTR() {
		return RANKSTR;
	}

	public String getSUITSTR() {
		return SUITSTR;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}
	
	public char rankChar() {
		return RANKSTR.charAt(rank);
	}

	public char suitChar() {
		return SUITSTR.charAt(suit);
	}
	/**
	 * breaks a constructed rankSuitStr into component rank and suit.
	 */
	public void parseRankSuitStr() {
		if (rankSuitStr.length() >= 1) {
			for (int i = 2; i < RANKSTR.length(); i++) {
				if (RANKSTR.charAt(i) == rankSuitStr.charAt(0)) {
					rank = i;
					break;
				}
			}
		}
		if (rankSuitStr.length() >= 2) {
			for (int j = 1; j < SUITSTR.length(); j++) {
				if (SUITSTR.charAt(j) == rankSuitStr.charAt(1)) {
					suit = j;
					break;
				}
			}
		}

	}
	
	@Override
	/**
	 * @return rankSuitStr
	 */
	public String toString() {
		return rankSuitStr;
	}

	@Override
	/**
	 * @return a + int if the card has higher rank then suit - int otherwise, 0 if the same card.
	 */
	public int compareTo(Card other) {
		if (other != null) {
			int result = this.rank - other.getRank();
			if (result == 0) {
				result = this.suit - other.getSuit();
			}
			return result;
		} else {
			return 0;
		}

	}

}
