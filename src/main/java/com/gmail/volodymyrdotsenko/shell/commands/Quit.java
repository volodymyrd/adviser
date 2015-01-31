package com.gmail.volodymyrdotsenko.shell.commands;

import com.gmail.volodymyrdotsenko.shell.Command;
import com.gmail.volodymyrdotsenko.shell.ICommand;
import com.gmail.volodymyrdotsenko.shell.IShell;

public final class Quit extends Command {

	public static ICommand instance() {
		ICommand c = new Quit();

		c.registration("quit", "q", "Exit from console");

		return c;
	}

	@Override
	public void cmd(IShell shell) {
		shell.setWorking(false);
	}
}