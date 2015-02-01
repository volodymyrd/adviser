package com.gmail.volodymyrdotsenko.pokerstat;

public enum CardValue {
	TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE(
			"9"), TEN("T"), JACK("J"), QUEEN("Q"), KING("K"), ACE("A");

	private final String value;

	private CardValue(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public static CardValue fromString(String value) {
		if (value != null && !value.isEmpty()) {
			for (CardValue v : CardValue.values()) {
				if (value.equalsIgnoreCase(v.value)) {				
					return v;
				}
			}
		}

		return null;
	}
}