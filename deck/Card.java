package deck;

public class Card implements Comparable<Card> {
	private int rank;
	private int suit;
	private String rankSuitStr;
	/**
	 * 2 to "2", 14 to "A"
	 */
	private final String RANKSTR = "  23456789TJQKA";
	/**
	 * 1 to "c", 4 to "s"
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

	public void parseRankSuitStr() {
		for (int i = 2; i < RANKSTR.length(); i++) {
			if (RANKSTR.charAt(i) == rankSuitStr.charAt(0)) {
				rank = i;
				break;
			}
		}
		for (int j = 1; j < SUITSTR.length(); j++) {
			if (SUITSTR.charAt(j) == rankSuitStr.charAt(1)) {
				suit = j;
				break;
			}
		}

	}

	@Override
	public String toString() {
		return rankSuitStr;
	}

	@Override
	public int compareTo(Card other) {
		int result=this.rank-other.getRank();
		if(result==0){
			result=this.suit-other.getSuit();
		}
		return result;
		
	}

}
