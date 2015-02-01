package com.gmail.volodymyrdotsenko.shell.commands;

import com.gmail.volodymyrdotsenko.shell.Command;
import com.gmail.volodymyrdotsenko.shell.ICommand;
import com.gmail.volodymyrdotsenko.shell.IShell;

public final class Exit extends Command {

	public Exit(IShell shell) {
		super(shell);

		code = "exit";
		shortCode = "ex";
		buildHelpMessage("Exit from console", code + "(" + shortCode + ")");
		enable = true;
	}

	public static ICommand instance(IShell shell) {
		ICommand c = new Exit(shell);

		return c;
	}

	@Override
	public void cmd(String... params) {
		shell.setWorking(false);
	}
}