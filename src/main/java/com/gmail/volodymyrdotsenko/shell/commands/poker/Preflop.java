package com.gmail.volodymyrdotsenko.shell.commands.poker;

import java.io.IOException;

import com.gmail.volodymyrdotsenko.pokerstat.Hand;
import com.gmail.volodymyrdotsenko.pokerstat.TexasHoldEm;
import com.gmail.volodymyrdotsenko.shell.Command;
import com.gmail.volodymyrdotsenko.shell.FormatCommandException;
import com.gmail.volodymyrdotsenko.shell.ICommand;
import com.gmail.volodymyrdotsenko.shell.IShell;

public final class Preflop extends Command {

	public Preflop(IShell shell) {
		super(shell);

		code = "preflop";
		shortCode = "pf";
		buildHelpMessage("Pre-flop", code + "(" + shortCode
				+ ") hand numPlayers");
		enable = false;
	}

	public static ICommand instance(IShell shell) {
		ICommand c = new Preflop(shell);

		return c;
	}

	@Override
	public void cmd(String... params) throws IOException {

		try {
			preflop(params);
		} catch (FormatCommandException e) {
			shell.errorCommandFormat(code);
			shell.info(help);
		}
	}

	private void preflop(String... params) throws IOException,
			FormatCommandException {

		int numPlayers = 0;

		Hand hand = null;

		if (params.length == 1) {
			try {
				if (params[0].length() == 4) {
					hand = new Hand(params[0]);

					numPlayers = IShell.sharedMemory
							.get(Holdem.NUM_PLAYERS_KEY) == null ? 0
							: (int) IShell.sharedMemory
									.get(Holdem.NUM_PLAYERS_KEY);

					if (numPlayers == 0) {
						shell.error("Number of players must be set");

						throw new FormatCommandException();
					}
				} else
					throw new FormatCommandException();
			} catch (IllegalArgumentException ex) {
				throw new FormatCommandException();
			}
		} else if (params.length == 2) {
			try {
				if (params[0].length() == 4) {
					hand = new Hand(params[0]);

					numPlayers = Integer.parseInt(params[1]);
				} else if (params[1].length() == 4) {
					numPlayers = Integer.parseInt(params[0]);

					hand = new Hand(params[1]);
				} else {
					throw new FormatCommandException();
				}
			} catch (IllegalArgumentException ex) {
				throw new FormatCommandException();
			}
		} else {
			throw new FormatCommandException();
		}

		IShell.sharedMemory.put(Holdem.NUM_PLAYERS_KEY, numPlayers);

		shell.info("Your hand {0}", hand.toString());
		shell.info("Number of players {0}", String.valueOf(numPlayers));
		TexasHoldEm the = (TexasHoldEm) IShell.sharedMemory.get(Holdem.HOLDEM);

		shell.info("Straight Flush outs: {0}",
				String.valueOf(the.StraightFlushOuts(hand)));
	}
}