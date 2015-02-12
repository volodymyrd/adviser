package com.gmail.volodymyrdotsenko.adviser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import com.gmail.volodymyrdotsenko.pokerstat.Hand;
import com.gmail.volodymyrdotsenko.pokerstat.TexasHoldEm;

public class ComparisonTest {

	private static TexasHoldEm the;

	static {
		the = new TexasHoldEm();
	}

	@Test
	public void StraightFlushComparator() {
		// List<Hand> hands = new ArrayList<>(the.getStraightFlushHands());
		//
		// Collections.sort(hands, new TexasHoldEm.StraightFlushComparator());
		//
		// for (Hand h : hands) {
		// System.out.println(h);
		// }
	}

	@Test
	public void QuadsHandsComparator() {
		// List<Hand> hands = new ArrayList<>(the.getQuadsHands());
		//
		// Collections.sort(hands, new TexasHoldEm.QuadsHandsComparator());
		//
		// for (Hand h : hands) {
		// System.out.println(h);
		// }
	}

	@Test
	public void StraightFlushMap() {
		Map<Hand, Integer> map = the.getStraightFlushHandsMap();
		Map<Hand, Set<Hand>> strong = the.getStraightFlushStrongHands();

		for (Entry<Hand, Integer> e : map.entrySet()) {
			System.out.println("--------------start-------------");
			System.out.println(e.getKey() + ": " + e.getValue());
			System.out.println("--------------strong-------------");
			System.out.println(strong.get(e.getKey()).size());
			for (Hand h : strong.get(e.getKey())) {
				System.out.println(h);
			}
			
			if((e.getValue() - strong.get(e.getKey()).size()) != 0)
				System.out.println(e.getKey() + ": error");
			
			System.out.println("--------------end-------------");
		}
	}

	@Test
	public void QuadsHandsMap() {
		// Map<Hand, Integer> map = the.getQuadsHandsMap();
		//
		// for (Entry<Hand, Integer> e : map.entrySet()) {
		// System.out.println(e.getKey() + ": " + e.getValue());
		// }
	}

}