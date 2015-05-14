package com.gmail.volodymyrdotsenko.pokerstat;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.gmail.volodymyrdotsenko.pokerstat.TexasHoldEm.HandCategory;
import com.gmail.volodymyrdotsenko.pokerstat.TexasHoldEm.Statistic;

public class TexasHoldEmTest {

	private static TexasHoldEm the = new TexasHoldEm();
	private Hand preFlopHand = new Hand("tcac");
	private Hand flopHand = new Hand("tcacjc8c7c");
	private Hand turnHand = new Hand("tcacjc8c7ckd");
	private Set<Hand> outs = new HashSet<>();
	private Set<Hand> likely = new HashSet<>();

	@Test
	public void preflopStat() {
		the.buildStraightFlushStat(preFlopHand);

		Map<HandCategory, Statistic> stat = the.getStat();

		System.out.println(stat.get(HandCategory.STRAIGHTFLUSH).outs);
		System.out.println(stat.get(HandCategory.STRAIGHTFLUSH).contrOuts);
	}

	// @Test
	public void preflopOuts() {
		System.out.println("-----------preFlop------------");
		System.out.println("Straight Flush outs: "
				+ String.valueOf(the.straightFlushOuts(preFlopHand, outs,
						likely)));
		// System.out.println("Four of a kind outs: "
		// + String.valueOf(the.quadsOuts(preFlopHand, outs)));
		// System.out.println("Straight outs: "
		// + String.valueOf(the.straightOuts(preFlopHand, outs)));

		System.out.println("---outs:");
		for (Hand h : outs)
			System.out.println(h);

		System.out.println("Total outs: " + outs.size());

		System.out.println("---likely:");
		for (Hand h : likely)
			System.out.println(h);

		System.out.println("Total likely: " + likely.size());

		Set<Hand> loseOuts = new HashSet<>();
		// for(Hand h : likely)
		// the.strongStraightFlushOuts(h, base, loseOuts);

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