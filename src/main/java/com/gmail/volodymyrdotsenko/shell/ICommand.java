package com.gmail.volodymyrdotsenko.shell;

public interface ICommand {

	void registration(String code, String shortCode, String help);

	void cmd(IShell shell);

	boolean isEnable();

	void disable();

	void enable();

	String code();

	String shortCode();

	String help();
}