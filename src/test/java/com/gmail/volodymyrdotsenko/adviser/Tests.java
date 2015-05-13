package com.gmail.volodymyrdotsenko.adviser;

import org.junit.Test;

import com.gmail.volodymyrdotsenko.pokerstat.Card;
import com.gmail.volodymyrdotsenko.pokerstat.Hand;
import com.gmail.volodymyrdotsenko.pokerstat.TexasHoldEm;

public class Tests {

	private static TexasHoldEm the = new TexasHoldEm();
	private static Hand flopHand = new Hand(5);

	static {
		flopHand.add(new Card('5', 's')).add(new Card('j', 'd'))
				.add(new Card('k', 's')).add(new Card('A', 's'))
				.add(new Card('q', 'c'));
	}

	@Test
	public void allFlopComb() {
		for (Hand h : the.allFlopComb(flopHand))
			System.out.println(h);
	}

	@Test
	public void preflopCalcOut() {
		long s = System.currentTimeMillis();

		Hand h = new Hand(2);

		h.add(new Card('4', 'c'));
		h.add(new Card('4', 'c'));

		System.out.println("preflop: " + the.twoPairOuts(h));

		System.out.println("preflop: " + (System.currentTimeMillis() - s)
				/ 1000.0 + "sec.");
	}

	@Test
	public void flopCalcOut() {
		long s = System.currentTimeMillis();

		for (Hand hand : the.allFlopComb(flopHand)){
			System.out.println("flop for hand " + hand + ": ");
			
			System.out.println("straightFlushOuts: " + the.straightFlushOuts(hand, null, null));
			System.out.println("straightOuts: " + the.straightOuts(hand, null));
			System.out.println("twoPairOuts: " + the.twoPairOuts(hand));			
		}

		System.out.println("flop: " + (System.currentTimeMillis() - s) / 1000.0
				+ "sec.");
	}
	
	@Test
	public void handsCompare(){
		Hand h1 = new Hand(5);
	}
}