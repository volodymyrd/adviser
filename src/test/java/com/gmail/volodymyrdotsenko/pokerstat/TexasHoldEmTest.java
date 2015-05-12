package com.gmail.volodymyrdotsenko.pokerstat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class TexasHoldEmTest {

	private static TexasHoldEm the = new TexasHoldEm();
	private Hand preFlopHand = new Hand("tcac");
	private Hand flopHand = new Hand("tcacjc8c7c");
	private Hand turnHand = new Hand("tcacjc8c7ckd");
	private Set<Hand> outs = new HashSet<>();

	@Test
	public void preflopOuts() {
		System.out.println("-----------preFlop------------");
		System.out.println("Straight Flush outs: "
				+ String.valueOf(the.straightFlushOuts(preFlopHand, outs)));
		System.out.println("Four of a kind outs: "
				+ String.valueOf(the.quadsOuts(preFlopHand, outs)));
		System.out.println("Straight outs: "
				+ String.valueOf(the.straightOuts(preFlopHand, outs)));

		for (Hand h : outs)
			System.out.println(h);

		System.out.println("Total outs: " + outs.size());

		System.out.println("-------------------------------");
	}

	/*
	 * @Test public void flopOuts() {
	 * System.out.println("-----------flop------------");
	 * System.out.println("Straight Flush outs: " +
	 * String.valueOf(the.straightFlushOuts(flopHand, 2, outs)));
	 * System.out.println("Four of a kind outs: " +
	 * String.valueOf(the.quadsOuts(flopHand, 2)));
	 * System.out.println("Straight outs: " +
	 * String.valueOf(the.straightOuts(flopHand, 2)));
	 * 
	 * for (Hand h : outs) System.out.println(h);
	 * 
	 * System.out.println("----------------------------"); }
	 * 
	 * @Test public void turnOuts() {
	 * System.out.println("-----------turn------------");
	 * System.out.println("Straight Flush outs: " +
	 * String.valueOf(the.straightFlushOuts(turnHand, 1, outs)));
	 * System.out.println("Four of a kind outs: " +
	 * String.valueOf(the.quadsOuts(turnHand, 1)));
	 * System.out.println("Straight outs: " +
	 * String.valueOf(the.straightOuts(turnHand, 1)));
	 * 
	 * for (Hand h : outs) System.out.println(h);
	 * 
	 * System.out.println("----------------------------"); }
	 */
}