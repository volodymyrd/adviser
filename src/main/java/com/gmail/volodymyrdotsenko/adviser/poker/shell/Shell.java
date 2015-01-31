package com.gmail.volodymyrdotsenko.adviser.poker.shell;

import java.io.IOException;
import java.io.Writer;

import jline.console.ConsoleReader;

public class Shell implements Shellable {

	@Override
	public void run() {
		try {

			ConsoleReader reader = new ConsoleReader();

			banner(reader.getOutput());

			reader.setPrompt(GREEN + "adviser" + RED + "> " + RESET);

			String s = "";
			while (!(s = reader.readLine()).equals("q")) {
				// int k = reader.readCharacter();
				// System.out.println(k);
			}
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

}