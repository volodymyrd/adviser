package com.gmail.volodymyrdotsenko.adviser;

import org.junit.Test;

import com.gmail.volodymyrdotsenko.pokerstat.Utils;

public class UtilsTest {

	@Test
	public void allCombination() {
		System.out.println(Utils.allFlopCombination(52, 5));
	}
}