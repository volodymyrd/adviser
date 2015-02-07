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
	public void StraightFlushMap() {
		Map<Hand, Integer> map = the.getStraightFlushHandsMap();

		for (Entry<Hand, Integer> e : map.entrySet()) {
			System.out.println(e.getKey() + ": " + e.getValue());
		}
	}
}