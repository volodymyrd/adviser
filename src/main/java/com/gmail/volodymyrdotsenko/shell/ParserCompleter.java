package com.gmail.volodymyrdotsenko.shell;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jline.console.completer.Completer;

public class ParserCompleter implements Completer {

	@Override
	public int complete(String buffer, int cursor, List<CharSequence> candidates) {
		int result = -1;

		try {

			for (ICommand c : IShell.commands) {
				if (c.isEnable()) {
					Pattern p = Pattern.compile(buffer + "\\w+");
					Matcher m = p.matcher(c.code());
					if (m.matches())
						candidates.add(c.code());
				}
			}
		} finally {

		}

		if (candidates.size() > 0)
			result = 0;

		return result;
	}
}