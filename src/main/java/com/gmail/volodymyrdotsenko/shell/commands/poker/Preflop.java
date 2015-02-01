package com.gmail.volodymyrdotsenko.shell.commands.poker;

import java.io.IOException;

import com.gmail.volodymyrdotsenko.shell.Command;
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
		if (params.length < 1) {
			shell.errorCommandFormat(code);
			shell.info(help);

			return;
		} else if (params.length == 1) {
			if (params[0].length() != 4) {
				shell.errorCommandFormat(code);
				shell.info(help);

				return;
			}
		}
	}
}