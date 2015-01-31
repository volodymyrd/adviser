package com.gmail.volodymyrdotsenko.shell.commands;

import com.gmail.volodymyrdotsenko.shell.Command;
import com.gmail.volodymyrdotsenko.shell.ICommand;
import com.gmail.volodymyrdotsenko.shell.IShell;

public final class Exit extends Command {

	public static ICommand instance() {
		ICommand c = new Exit();

		c.registration("exit", "ex", "Exit from console");

		return c;
	}

	@Override
	public void cmd(IShell shell) {
		shell.setWorking(false);
	}
}