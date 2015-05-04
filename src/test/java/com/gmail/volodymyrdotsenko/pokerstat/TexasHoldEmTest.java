package com.gmail.volodymyrdotsenko.pokerstat;

import org.junit.Test;

public class TexasHoldEmTest {
	
	private static TexasHoldEm the = new TexasHoldEm();
	private Hand preFlopHand = new Hand("tcac");
	private Hand flopHand = new Hand("tcacjc8c7c");
	
	@Test
	public void preflopOuts(){
		System.out.println("Straight Flush outs: " + 
				String.valueOf(the.straightFlushOuts(preFlopHand)));		
	}
	
	@Test
	public void flopOuts(){
		System.out.println("Straight Flush outs: " + 
				String.valueOf(the.straightFlushOuts(flopHand, 2)));		
	}
}