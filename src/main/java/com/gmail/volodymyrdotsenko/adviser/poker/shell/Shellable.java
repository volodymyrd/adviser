package com.gmail.volodymyrdotsenko.adviser.poker.shell;

import java.io.IOException;
import java.io.Writer;

public interface Shellable extends Runnable {

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
}