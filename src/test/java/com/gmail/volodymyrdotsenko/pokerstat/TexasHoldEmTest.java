package com.gmail.volodymyrdotsenko.pokerstat;

import org.junit.Test;

public class TexasHoldEmTest {

	private static TexasHoldEm the = new TexasHoldEm();
	private Hand preFlopHand = new Hand("tcac");
	private Hand flopHand = new Hand("tcacjc8c7c");
	private Hand turnHand = new Hand("tcacjc8c7ckd");

	@Test
	public void preflopOuts() {
		System.out.println("-----------preFlop------------");
		System.out.println("Straight Flush outs: "
				+ String.valueOf(the.straightFlushOuts(preFlopHand)));
		System.out.println("Four of a kind outs: "
				+ String.valueOf(the.quadsOuts(preFlopHand)));
		System.out.println("Straight outs: "
				+ String.valueOf(the.straightOuts(preFlopHand)));
		System.out.println("-------------------------------");
	}

	@Test
	public void flopOuts() {
		System.out.println("-----------flop------------");
		System.out.println("Straight Flush outs: "
				+ String.valueOf(the.straightFlushOuts(flopHand, 2)));
		System.out.println("Four of a kind outs: "
				+ String.valueOf(the.quadsOuts(flopHand, 2)));
		System.out.println("Straight outs: "
				+ String.valueOf(the.straightOuts(flopHand, 2)));
		System.out.println("----------------------------");
	}

	@Test
	public void turnOuts() {
		System.out.println("-----------turn------------");
		System.out.println("Straight Flush outs: "
				+ String.valueOf(the.straightFlushOuts(turnHand, 1)));
		System.out.println("Four of a kind outs: "
				+ String.valueOf(the.quadsOuts(turnHand, 1)));
		System.out.println("Straight outs: "
				+ String.valueOf(the.straightOuts(turnHand, 1)));
		System.out.println("----------------------------");
	}
}