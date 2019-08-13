import java.util.ArrayList;

public class Deck {
	
	/**
	 * Declare a structure of time ArrayList
	 */
	ArrayList<Card> cards = new ArrayList<Card>();
	/**
	 * This constructor creates the cards of the deck and assigns (in ascending order) a value and suit to each card
	 */
	Deck() {

		for (int suit = 0; suit <= 3; suit++) { // 0-clubs 1-diamonds 2-hearts 3-spades

			for (int value = 1; value <= 13; value++) {// 1-Ace,...,11-Jack, 12-Queen, 13-King

				cards.add(new Card(value, suit));				
			}
		}		
	}
	/**
	 * Returns the total number of the cards in the deck
	 */
	public int getTotalCards() {

		return cards.size();
	}
}