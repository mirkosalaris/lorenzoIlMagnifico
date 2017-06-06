package it.polimi.ingsw.GC_36;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class ExceptionHandler {
	private static String level = "deployment";

	private ExceptionHandler() {}

	public void setLevel(String s) {
		if ("debug".equalsIgnoreCase(s)) {
			level = "debug";
		}
	}

	public static void log(Exception e) {
		if ("debug".equals(level)) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			System.err.println("sw");
		} else if ("deployment".equalsIgnoreCase(level)) {
			System.err.println(e.getMessage());
		}
	}
}
