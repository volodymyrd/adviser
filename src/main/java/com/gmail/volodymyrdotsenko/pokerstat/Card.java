package com.gmail.volodymyrdotsenko.pokerstat;

public class Card implements Comparable<Card> {

	private CardValue value;
	private CardSuit suit;

	public CardValue getValue() {
		return value;
	}

	public CardSuit getSuit() {
		return suit;
	}

	public Card(String value, String suit) {
		this(CardValue.fromString(value), CardSuit.fromString(suit));
	}

	public Card(char value, char suit) {
		this(String.valueOf(value), String.valueOf(suit));
	}

	public Card(CardValue value, CardSuit suit) {
		if (value == null || suit == null)
			throw new IllegalArgumentException("value and suit must be set");

		this.value = value;
		this.suit = suit;
	}

	// public Card(CardSuit suit) {
	// this.suit = suit;
	// }
	//
	// public Card(CardValue value) {
	// this.value = value;
	// }

	@Override
	public String toString() {
		return value.toString() + suit.toString();
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		return toString().equals(obj.toString());
	}

	@Override
	public int compareTo(Card c) {
		return value.compareTo(c.value);
	}
}