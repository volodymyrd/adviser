package com.gmail.volodymyrdotsenko.pokerstat;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.math3.util.CombinatoricsUtils;

public class TexasHoldEm {

	public final short handLength = 5;

	private Set<Hand> straightFlushHands = new HashSet<>();

	// Four of a kind
	private Set<Hand> quadsHands = new HashSet<>();

	private Set<Hand> fullHouseHands = new HashSet<>();

	private Set<Hand> flushHands = new HashSet<>();

	private Set<Hand> straightHands = new HashSet<>();

	// Three of a kind
	private Set<Hand> tripsHands = new HashSet<>();

	private Set<Hand> twoPairHands = new HashSet<>();

	public Set<Hand> getStraightFlushHands() {
		return straightFlushHands;
	}

	public Set<Hand> getQuadsHands() {
		return quadsHands;
	}

	public Set<Hand> getFullHouseHands() {
		return fullHouseHands;
	}

	public Set<Hand> getFlushHands() {
		return flushHands;
	}

	public Set<Hand> getStraightHands() {
		return straightHands;
	}

	public Set<Hand> getTripsHands() {
		return tripsHands;
	}

	public double preFlopStraightFlushHandProbability(Hand preFlopHand) {
		int n = 0;

		for (Hand h : straightFlushHands) {
			if (h.containsAll(preFlopHand))
				n++;
		}

		return n;
	}

	private void buildStraightFlushHands() {
		Hand h = new Hand(handLength);

		CardValue[] value = new CardValue[14];
		value[0] = CardValue.ACE;
		for (int i = 0; i < CardValue.values().length; i++)
			value[i + 1] = CardValue.values()[i];

		for (CardSuit suit : CardSuit.values()) {
			for (int i = 0; i < 10; i++) {
				for (int j = i; j < i + 5; j++) {
					h.add(new Card(value[j], suit));
				}

				straightFlushHands.add(h);

				h = new Hand(handLength);
			}
		}

		System.out.println("straightFlushHands: " + straightFlushHands.size());
		// for(Hand h1 : straightFlushHands)
		// System.out.println(h1);
	}

	private void buildQuadsHands() {
		Hand h = new Hand(handLength);

		for (CardValue v : CardValue.values()) {
			for (CardSuit suit : CardSuit.values()) {
				h.add(new Card(v, suit));
			}

			Hand t = h.copy();

			for (CardValue v1 : CardValue.values()) {
				if (v1.equals(v))
					continue;
				for (CardSuit s : CardSuit.values()) {
					t.add(new Card(v1, s));

					quadsHands.add(t);

					t = h.copy();
				}

				t = h.copy();
			}

			h.clear();
		}
		
		System.out.println("quadsHands: " + quadsHands.size());
	}

	private void buildFullHouseHands() {
		Iterator<int[]> it3 = CombinatoricsUtils.combinationsIterator(4, 3);
		Iterator<int[]> it2 = CombinatoricsUtils.combinationsIterator(4, 2);

		for (CardValue v3 : CardValue.values()) {
			while (it3.hasNext()) {
				Hand h3 = new Hand(3);

				for (int i3 : it3.next()) {
					h3.add(new Card(v3, CardSuit.values()[i3]));
				}

				Hand h = h3.copy();

				for (CardValue v2 : CardValue.values()) {
					if (v2.equals(v3))
						continue;

					while (it2.hasNext()) {
						for (int i2 : it2.next()) {
							h.add(new Card(v2, CardSuit.values()[i2]));
						}

						fullHouseHands.add(h);

						h = h3.copy();
					}

					it2 = CombinatoricsUtils.combinationsIterator(4, 2);
				}
			}

			it3 = CombinatoricsUtils.combinationsIterator(4, 3);
		}

		System.out.println("fullHouseHands: " + fullHouseHands.size());
		// for (Hand h1 : fullHouseHands)
		// System.out.println(h1);
	}

	private void buildFlushHands() {
		Iterator<int[]> it = CombinatoricsUtils.combinationsIterator(13, 5);

		Hand h = new Hand(handLength);

		for (CardSuit s : CardSuit.values()) {
			while (it.hasNext()) {
				for (int i : it.next()) {
					h.add(new Card(CardValue.values()[i], s));
				}

				flushHands.add(h);

				h = new Hand(handLength);
			}

			it = CombinatoricsUtils.combinationsIterator(13, 5);
		}

		Iterator<Hand> itH = flushHands.iterator();
		while (itH.hasNext()) {
			if (straightFlushHands.contains(itH.next())) {
				itH.remove();
			}
		}

		System.out.println("flushHands: " + flushHands.size());
		// for(Hand h1 : flushHands){
		// System.out.println(h1);
		// }
	}

	private void buildStraightHands() {
		Hand h = new Hand(handLength);

		int[][] p = Utils.permutations(4, 5);

		CardValue[] value = new CardValue[14];
		value[0] = CardValue.ACE;
		for (int i = 0; i < CardValue.values().length; i++)
			value[i + 1] = CardValue.values()[i];

		for (int[] p1 : p) {
			for (int k = 0; k < 10; k++) {
				int pi = 0;
				for (int j = k; j < k + 5; j++) {
					h.add(new Card(value[j], CardSuit.values()[p1[pi++]]));
				}

				straightHands.add(h);

				h = new Hand(handLength);
			}
		}

		Iterator<Hand> itH = straightHands.iterator();
		while (itH.hasNext()) {
			if (straightFlushHands.contains(itH.next())) {
				itH.remove();
			}
		}

		System.out.println("straightHands: " + straightHands.size());
		// for (Hand h1 : straightHands) {
		// System.out.println(h1);
		// }
	}

	private void buildTripsHands() {
		Iterator<int[]> it3 = CombinatoricsUtils.combinationsIterator(4, 3);
		Iterator<int[]> it1 = CombinatoricsUtils.combinationsIterator(4, 1);
		Iterator<int[]> it0 = CombinatoricsUtils.combinationsIterator(4, 1);

		for (CardValue v3 : CardValue.values()) {
			while (it3.hasNext()) {
				Hand h3 = new Hand(3);

				for (int i3 : it3.next()) {
					h3.add(new Card(v3, CardSuit.values()[i3]));
				}

				Hand h4 = h3.copy();

				for (CardValue v2 : CardValue.values()) {
					if (v2.equals(v3))
						continue;

					while (it1.hasNext()) {
						for (int i1 : it1.next()) {
							h4.add(new Card(v2, CardSuit.values()[i1]));
						}

						Hand h5 = h4.copy();

						for (CardValue v1 : CardValue.values()) {
							if (v1.equals(v3) || v1.equals(v2))
								continue;

							while (it0.hasNext()) {
								for (int i0 : it0.next()) {
									h5.add(new Card(v1, CardSuit.values()[i0]));
								}

								tripsHands.add(h5);

								h5 = h4.copy();
							}

							it0 = CombinatoricsUtils.combinationsIterator(4, 1);
						}

						h4 = h3.copy();
					}

					it1 = CombinatoricsUtils.combinationsIterator(4, 1);
				}
			}

			it3 = CombinatoricsUtils.combinationsIterator(4, 3);
		}

		System.out.println("tripsHands: " + tripsHands.size());
		// for (Hand h1 : tripsHands) {
		// System.out.println(h1);
		// }

	}

	private void buildTwoPairHands() {

		Set<Hand> p1 = buildPair();
		Set<Hand> p2 = buildPair();

		for (Hand h1 : p1) {
			for (Hand h2 : p2) {
				Hand h = h1.concat(h2);

				if (h != null && !h.containsQuads()) {

					Hand h4 = h.copy();

					for (CardValue v5 : CardValue.values()) {
						for (CardSuit s5 : CardSuit.values()) {
							h4.add(new Card(v5, s5));

							if (h4.size() == 5 && !h4.containsTrips()) {
								twoPairHands.add(h4);
							}

							h4 = h.copy();
						}
					}
				}
			}
		}

		System.out.println("twoPairHands: " + twoPairHands.size());
//		for (Hand hp : twoPairHands) {
//			System.out.println(hp);
//		}
	}

	private Set<Hand> buildPair() {
		Set<Hand> pair = new HashSet<>();

		Hand h1 = new Hand(1);
		for (CardValue v1 : CardValue.values()) {
			for (CardSuit s1 : CardSuit.values()) {
				h1.add(new Card(v1, s1));

				Hand h2 = h1.copy();
				for (CardSuit s2 : CardSuit.values()) {
					if (s1.equals(s2))
						continue;

					h2.add(new Card(v1, s2));

					pair.add(h2);

					h2 = h1.copy();
				}

				h1 = new Hand(1);
			}
		}

		return pair;
	}

	public TexasHoldEm() {
		buildStraightFlushHands();
		buildQuadsHands();
		buildFullHouseHands();
		buildFlushHands();
		buildStraightHands();
		buildTripsHands();
		buildTwoPairHands();
	}
}