package com.gmail.volodymyrdotsenko.adviser;

import org.junit.Before;
import org.junit.Test;

import com.gmail.volodymyrdotsenko.pokerstat.Card;
import com.gmail.volodymyrdotsenko.pokerstat.Hand;
import com.gmail.volodymyrdotsenko.pokerstat.TexasHoldEm;

public class Tests {

	private static TexasHoldEm the = new TexasHoldEm();

	@Test
	public void preflopCalcOut() {
		long s = System.currentTimeMillis();

		Hand h = new Hand(2);

		h.add(new Card('4', 'c'));
		h.add(new Card('A', 's'));

		System.out.println("preflop: " + the.twoPairOuts(h));

		System.out.println("preflop: " + (System.currentTimeMillis() - s)
				/ 1000.0 + "sec.");
	}

	@Test
	public void flopCalcOut() {
		long s = System.currentTimeMillis();

		Hand h = new Hand(5);

		h.add(new Card('4', 'c'));
		h.add(new Card('2', 's'));
		h.add(new Card('t', 'h'));
		h.add(new Card('k', 'd'));
		h.add(new Card('A', 's'));

		System.out.println("flop: " + the.twoPairOuts(h));

		System.out.println("flop: " + (System.currentTimeMillis() - s) / 1000.0
				+ "sec.");
	}
}