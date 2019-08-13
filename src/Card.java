/**
 * This class defines the card properties:
 * Width
 * Height
 * Value
 * Suit
 * Also defines two methods for card handling
 */

import java.applet.Applet;

public class Card extends Applet{
	/**
	 * Declare two class variables (common for all the cards)
	 */
	final static int WIDTH = 72;
	final static int HEIGHT = 96;
	/**
	 * Declare two instance variables unique for each card
	 */
	private int cardValue;
	private int cardSuit;	
	/**
	 *Card constructor which gives Value and Suit to each card
	 */
	Card(int cardValue, int cardSuit) {
		
		this.cardValue = cardValue;
		
		this.cardSuit = cardSuit;		
	}
	/**
	 *Rerurns card's value
	 */
	public int getCardValue() {
		
		return cardValue;
	}
	/**
	 *Returns card's suit
	 */
	public int getCardSuit() {
		
		return cardSuit;
	}
}