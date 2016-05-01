package deck;

public class Card {
	private int rank;
	private int suit;

	public Card() {
		this(0, 0);
	}

	public Card(int rank, int suit) {
		this.rank = rank;
		this.suit = suit;
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

	public void setSuit(int suit) {
		this.suit = suit;
	}

	public String rankStr() {
		String[] rankStr={"T","J","Q","K","A"};
		if(rank>=10){
			return rankStr[rank-10];
		}
		else{
			return ""+rank;
		}
	}

	public String suitStr() {
		String[] suitStr={"c","d","h","s"};
		return suitStr[suit-1];
	}

	@Override
	public String toString() {
		return ""+rankStr()+suitStr();
	}

}
