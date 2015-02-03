package com.gmail.volodymyrdotsenko.shell.commands.poker;

import java.io.IOException;

import com.gmail.volodymyrdotsenko.pokerstat.TexasHoldEm;
import com.gmail.volodymyrdotsenko.shell.Command;
import com.gmail.volodymyrdotsenko.shell.ICommand;
import com.gmail.volodymyrdotsenko.shell.IShell;

public final class Holdem extends Command {

	public final static String NUM_PLAYERS_KEY = "numPlayers";
	public final static String HOLDEM = "holdem";

	public Holdem(IShell shell) {
		super(shell);

		code = "holdem";
		shortCode = "h";
		buildHelpMessage("Start Texas Holdem Adviser", code + "(" + shortCode
				+ ") numPlayers");
		enable = true;
	}

	public static ICommand instance(IShell shell) {
		ICommand c = new Holdem(shell);

		return c;
	}

	@Override
	public void cmd(String... params) throws IOException {

		if (params.length == 1) {
			try {
				int numPlayers = Integer.parseInt(params[0]);

				IShell.sharedMemory.put(NUM_PLAYERS_KEY, numPlayers);
			} catch (NumberFormatException ex) {
				shell.errorCommandFormat(code);
				shell.info(help);

				return;
			}
		}

		TexasHoldEm holdem = new TexasHoldEm();

		IShell.sharedMemory.put(HOLDEM, holdem);

		shell.info("Now Adviser in Texas Holdem Mode");

		enable = false;

		shell.getCommandByCode("preflop").enable();

		shell.setPrompt(IShell.GREEN + "holdem" + IShell.RED + "> "
				+ IShell.RESET);

		shell.setMode(IShell.Mode.HOLDEM);
	}
}