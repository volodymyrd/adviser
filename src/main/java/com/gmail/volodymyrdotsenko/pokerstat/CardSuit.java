package com.gmail.volodymyrdotsenko.pokerstat;

public enum CardSuit {
	SPADE("s"), CLUB("c"), DIAMOND("d"), HEART("h");

	private final String suit;

	private CardSuit(final String suit) {
		this.suit = suit;
	}

	@Override
	public String toString() {
		return suit;
	}

	public static CardSuit fromString(String suit) {
		if (suit != null && !suit.isEmpty()) {
			for (CardSuit s : CardSuit.values()) {
				if (suit.equalsIgnoreCase(s.suit)) {
					return s;
				}
			}
		}

		return null;
	}
}