package com.gmail.volodymyrdotsenko.shell;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

import com.gmail.volodymyrdotsenko.shell.commands.Exit;
import com.gmail.volodymyrdotsenko.shell.commands.Quit;

import jline.console.ConsoleReader;

public final class Shell implements IShell {

	private ConsoleReader reader;
	private boolean working;
	private Set<String> code = new HashSet<>();
	private Set<String> shortCode = new HashSet<>();

	public void setWorking(boolean working) {
		this.working = working;
	}

	public boolean isWorking() {
		return working;
	}

	public Shell() {
		try {
			regCommand(Exit.instance());
			regCommand(Quit.instance());
		} catch (Exception ex) {
			System.exit(-1);
		}
	}

	@Override
	public void run() {
		try {
			working = true;

			reader = new ConsoleReader();

			reader.addCompleter(new ParserCompleter());

			banner(reader.getOutput());

			reader.setPrompt(GREEN + "adviser" + RED + "> " + RESET);

			String s = "";

			while ((s = reader.readLine()) != null) {

				doCommand(s);

				if (!working)
					break;
			}

			reader.shutdown();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void start() {
		new Thread(this).start();
	}

	@Override
	public void banner(Writer writer) throws IOException {
		StringBuffer buf = new StringBuffer();
		buf.append(BLUE + "=============================================="
				+ LINE_SEPARATOR);
		buf.append("*                                            *"
				+ LINE_SEPARATOR);
		buf.append("*" + RED + "                  Poker-Adviser             "
				+ BLUE + "*" + LINE_SEPARATOR);
		buf.append("*                                            *"
				+ LINE_SEPARATOR);
		buf.append("=============================================="
				+ LINE_SEPARATOR);
		// buf.append("Version:" + this.getVersion());
		writer.write(buf.toString() + RESET);
	}

	@Override
	public void doCommand(String command) throws Exception {
		if (command.isEmpty())
			return;

		for (ICommand c : commands) {
			if (c.shortCode().equals(command) || c.code().equals(command)) {
				if (c.isEnable())
					c.cmd(this);
				else
					reader.getOutput()
							.write(YELLOW
									+ "Command "
									+ command
									+ " was found but not available in this time"
									+ RESET);

				return;
			}
		}

		reader.getOutput().write(
				RED + "Command " + command + " not found" + RESET);

		reader.println();
	}

	@Override
	public void regCommand(ICommand command) throws Exception {
		if (code.contains(command.code()))
			throw new Exception(
					"Try to registrate more than one commands with code "
							+ command.code());

		if (shortCode.contains(command.shortCode()))
			throw new Exception(
					"Try to registrate more than one commands with short code "
							+ command.shortCode());

		commands.add(command);
	}
}