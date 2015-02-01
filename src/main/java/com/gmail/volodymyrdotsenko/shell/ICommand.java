package com.gmail.volodymyrdotsenko.shell;

import java.io.IOException;

public interface ICommand {

	void cmd(String... params) throws IOException;

	boolean isEnable();

	void disable();

	void enable();

	String code();

	String shortCode();

	String help();
}