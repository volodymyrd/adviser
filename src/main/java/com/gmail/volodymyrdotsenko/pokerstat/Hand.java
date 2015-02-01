package com.gmail.volodymyrdotsenko.pokerstat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.util.CombinatoricsUtils;

public class Hand {

	private Set<Card> cards;

	public Hand(int length) {
		cards = new HashSet<>(length);
	}

	public Hand(Card c, int length) {
		cards = new HashSet<>(length);

		cards.add(c);
	}

	public Hand(Card c) {
		cards = new HashSet<>();

		cards.add(c);
	}

	public Hand(Set<Card> cards) {
		if (cards == null || cards.size() == 0)
			throw new IllegalArgumentException(
					"cards must contains at least one card");

		this.cards = cards;
	}

	public int size() {
		return cards.size();
	}

	public Hand add(Card card) {
		if (cards.add(card))
			return this;
		else
			return null;
	}

	public boolean contains(Hand h) {
		return cards.contains(h.getCards());
	}

	public boolean containsAll(Hand h) {
		return cards.containsAll(h.getCards());
	}

	public Set<Card> getCards() {
		return cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

	public void clear() {
		this.cards.clear();
	}

	public Hand copy() {
		Hand h = new Hand(this.cards.size());

		for (Card c : this.cards) {
			h.add(c);
		}

		return h;
	}

	public Hand concat(Hand h) {
		if (this.equals(h))
			return null;
		else {
			Hand newH = new Hand(this.size() + h.size());

			for (Card c : this.cards) {
				newH.add(c);
			}

			for (Card c : h.cards) {
				if (!newH.cards.add(c))
					return null;
			}

			return newH;
		}
	}

	private int numWithTheSameValue() {

		int i = 0;

		Iterator<int[]> it = CombinatoricsUtils.combinationsIterator(size(), 2);

		List<Card> l = getSorted();

		while (it.hasNext()) {
			int[] inds = it.next();

			if (l.get(inds[0]).equals(l.get(inds[1])))
				continue;

			if (l.get(inds[0]).getValue().equals(l.get(inds[1]).getValue()))
				i++;
		}

		return i;
	}

	public boolean containsTrips() {

		if (size() < 3)
			return false;

		if (numWithTheSameValue() >= 3)
			return true;
		else
			return false;
	}

	public boolean containsQuads() {

		if (size() < 4)
			return false;

		if (numWithTheSameValue() >= 6)
			return true;
		else
			return false;
	}

	@Override
	public int hashCode() {
		int result = 1;

		for (Card c : cards)
			result += c.hashCode();

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Hand other = (Hand) obj;

		return cards.size() == other.getCards().size()
				&& cards.containsAll(other.getCards());
	}

	public List<Card> getSorted() {
		List<Card> l = new ArrayList<Card>(cards);
		Collections.sort(l);

		return l;
	}

	@Override
	public String toString() {
		return getSorted().toString();
	}

	public static void main(String[] args) {
		Hand h = new Hand(5);

		// Kh, Qd, Qh, Ks
		//[3h, 3d, 3s, 9c, 9h]
		h.add(new Card("9", "s")).add(new Card("9", "d"))
				.add(new Card("9", "h")).add(new Card("7", "c"))
				.add(new Card("7", "h"));

		System.out.println(h.containsQuads());
		System.out.println(h.containsTrips());
	}
}