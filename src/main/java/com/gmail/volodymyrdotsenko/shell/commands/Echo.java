package com.gmail.volodymyrdotsenko.shell.commands;

import java.io.IOException;

import com.gmail.volodymyrdotsenko.shell.Command;
import com.gmail.volodymyrdotsenko.shell.ICommand;
import com.gmail.volodymyrdotsenko.shell.IShell;

public final class Echo extends Command {

	public Echo(IShell shell) {
		super(shell);
		code = "echo";
		shortCode = "";
		buildHelpMessage("Echo", "echo message");
		enable = true;
	}

	public static ICommand instance(IShell shell) {
		ICommand c = new Echo(shell);

		return c;
	}

	@Override
	public void cmd(String... params) throws IOException {
		if (params.length != 1) {
			shell.errorCommandFormat(code);
			shell.info(help);
			
			return;
		}

		shell.info(params[0]);
	}
}