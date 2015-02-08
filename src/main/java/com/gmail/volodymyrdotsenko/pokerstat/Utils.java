package com.gmail.volodymyrdotsenko.pokerstat;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.math3.util.CombinatoricsUtils;

public class Utils {

	public static long allFlopCombination(int cardsNum, int playersNum){
		int i = 0;

		long p = 1;
		
		while (i++ < playersNum - 1) {
			int k = (cardsNum - 2) - 2 * (i - 1);

			p *= CombinatoricsUtils.binomialCoefficient(k, 2);
		}
		
		p *= CombinatoricsUtils.binomialCoefficient(cardsNum - 2 * playersNum, 3);

		return p / CombinatoricsUtils.factorial(playersNum);		
	}
	
	public static long allCombination(int cardsNum, int playersNum) {
		int i = 0;

		long p = 1;

		while (i++ < playersNum - 1) {
			int k = cardsNum - 2 * (i - 1);

			p *= CombinatoricsUtils.binomialCoefficient(k, 2);
		}

		return p / CombinatoricsUtils.factorial(playersNum);
	}

	public static int[][] permutations(int n, int k) {

		int[] ind = new int[n];

		for (int i = 0; i < n; i++) {
			ind[i] = i;
		}

		int p = (int) Math.pow(n, k);

		int[][] permutations = new int[p][k];

		for (int x = 0; x < k; x++) {
			int t2 = (int) Math.pow(n, x);
			for (int p1 = 0; p1 < p;) {
				for (int al = 0; al < n; al++) {
					for (int p2 = 0; p2 < t2; p2++) {
						permutations[p1][x] = ind[al];
						p1++;
					}
				}
			}
		}

		return permutations;
	}

	public static Queue<int[]> combination(int n, int k) {
		Queue<int[]> comb = new LinkedList<int[]>();

		Iterator<int[]> it = CombinatoricsUtils.combinationsIterator(n, k);
		while (it.hasNext()) {
			comb.add(it.next());
		}

		return comb;
	}

	public static int[][] Combinations(int n, int k) {
		long s = System.currentTimeMillis();
		int[][] comb = new int[(int) CombinatoricsUtils.binomialCoefficient(n,
				k)][k];
		int[] ind = new int[n];

		for (int i = 0; i < n; i++) {
			ind[i] = i;
		}

		temp = new int[k];
		r = 0;
		num = 0;

		combRecurse(comb, ind, 0, n, k);

		temp = null;

		System.out.println("combination: " + (System.currentTimeMillis() - s)
				/ 1000.0);

		return comb;
	}

	private static int r = 0;
	private static int num = 0;
	private static int[] temp = null;

	private static void combRecurse(int[][] comb, int[] ind, int s, int e,
			int lim) {
		if (r == lim - 1) {
			for (int i = s; i < e; i++) {
				temp[r] = ind[i];
				// System.out.println(Arrays.toString(temp));
				comb[num++] = Arrays.copyOf(temp, temp.length);
			}

			return;
		}

		for (int i = s; i < e; i++) {
			temp[r] = ind[i];
			++r;
			combRecurse(comb, ind, i + 1, e, lim);
			--r;
		}
	}

	public static void main(String[] args) {
		int[][] table = permutations(4, 5);
		System.out.println(table.length);
		System.out.println(Arrays.deepToString(table));

		// combination(25, 10);
		// Combinations(25, 10);
		// combination(4, 2);
		// System.out.println(Arrays.deepToString(Combinations(4, 3)));
	}
}