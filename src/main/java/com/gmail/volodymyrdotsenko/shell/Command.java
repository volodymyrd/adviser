package com.gmail.volodymyrdotsenko.shell;

import java.io.IOException;

public abstract class Command implements ICommand {

	protected String code;
	protected String shortCode;
	protected String help;
	protected boolean enable;
	protected IShell shell;

	public Command(IShell shell) {
		super();
		this.shell = shell;
	}

	@Override
	public abstract void cmd(String... params) throws IOException;

	@Override
	public boolean isEnable() {
		if (enable)
			return true;

		return false;
	}

	@Override
	public void disable() {
		this.enable = false;
	}

	@Override
	public void enable() {
		this.enable = true;
	}

	@Override
	public String code() {
		return code;
	}

	@Override
	public String shortCode() {
		return shortCode;
	}

	@Override
	public String help() {
		return help;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Command other = (Command) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	protected void buildHelpMessage(String info, String format) {
		help = IShell.BLUE + info + " command format: " + IShell.GREEN + format
				+ IShell.RESET;
	}
}