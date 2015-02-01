package com.gmail.volodymyrdotsenko.shell.commands;

import com.gmail.volodymyrdotsenko.shell.Command;
import com.gmail.volodymyrdotsenko.shell.ICommand;
import com.gmail.volodymyrdotsenko.shell.IShell;

public final class Quit extends Command {

	public Quit(IShell shell) {
		super(shell);

		code = "quit";
		shortCode = "q";
		buildHelpMessage("Exit from console", code + "(" + shortCode + ")");
		enable = true;
	}

	public static ICommand instance(IShell shell) {
		ICommand c = new Quit(shell);

		return c;
	}

	@Override
	public void cmd(String... params) {
		shell.setWorking(false);
	}
}