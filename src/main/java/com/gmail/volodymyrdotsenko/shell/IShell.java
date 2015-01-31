package com.gmail.volodymyrdotsenko.shell;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

public interface IShell extends Runnable {

	Set<ICommand> commands = new HashSet<>();

	String LINE_SEPARATOR = System.getProperty("line.separator");

	String RESET = "\u001B[0m";
	String BLACK = "\u001B[30m";
	String RED = "\u001B[31m";
	String GREEN = "\u001B[32m";
	String YELLOW = "\u001B[33m";
	String BLUE = "\u001B[34m";
	String PURPLE = "\u001B[35m";
	String CYAN = "\u001B[36m";
	String WHITE = "\u001B[37m";

	void start();

	void banner(Writer writer) throws IOException;

	void regCommand(ICommand command) throws Exception;

	void doCommand(String command) throws Exception;

	public void setWorking(boolean working);

	public boolean isWorking();
}