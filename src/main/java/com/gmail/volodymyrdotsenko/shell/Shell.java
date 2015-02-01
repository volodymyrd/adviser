package com.gmail.volodymyrdotsenko.shell;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.gmail.volodymyrdotsenko.shell.commands.Echo;
import com.gmail.volodymyrdotsenko.shell.commands.Exit;
import com.gmail.volodymyrdotsenko.shell.commands.Quit;
import com.gmail.volodymyrdotsenko.shell.commands.poker.Holdem;

import jline.console.ConsoleReader;

public final class Shell implements IShell {

	private ConsoleReader reader;
	private boolean working;
	private Set<String> code = new HashSet<>();
	private Set<String> shortCode = new HashSet<>();
	private Mode mode;

	public void setWorking(boolean working) {
		this.working = working;
	}

	public boolean isWorking() {
		return working;
	}

	public Shell() {
		try {
			regCommand(Exit.instance(this));
			regCommand(Quit.instance(this));
			regCommand(Echo.instance(this));
			regCommand(Holdem.instance(this));
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

			mode = Mode.COMMON;

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

		String[] params = command.split("\\s+");

		for (ICommand c : commands) {
			if (c.shortCode().equals(params[0]) || c.code().equals(params[0])) {
				if (c.isEnable()) {
					int l = params.length;
					if (l > 1)
						c.cmd(Arrays.copyOfRange(params, 1, l));
					else
						c.cmd();
				} else
					warn("Command {0} was found but not available in this time",
							command);

				return;
			}
		}

		error("Command {0} not found", command);

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

	@Override
	public void error(String message, String... params) throws IOException {

		reader.print(RED + buildParamMessage(message, params));

		reader.println(RESET);
	}

	@Override
	public void warn(String message, String... params) throws IOException {
		reader.print(YELLOW + buildParamMessage(message, params));

		reader.println(RESET);
	}

	@Override
	public void info(String message, String... params) throws IOException {
		reader.print(GREEN + buildParamMessage(message, params));

		reader.println(RESET);
	}

	private String buildParamMessage(String message, String... params) {

		if (params == null || params.length == 0)
			return message;

		for (int i = 0; i < params.length; i++) {
			message = message.replaceFirst("\\{" + i + "\\}", params[i]);
		}

		return message;
	}

	@Override
	public void errorCommandFormat(String command) throws IOException {
		error("Format command {0} is not correct", command);
	}

	@Override
	public void setMode(Mode mode) {
		this.mode = mode;
	}

	@Override
	public Mode getMode() {
		return this.mode;
	}
}