package it.polimi.ingsw.GC_36.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class ExceptionLogger {
	private static Level level = Level.DEPLOYMENT;

	private ExceptionLogger() {}

	public static void log(Exception e) {
		if (Level.DEBUG.equals(level)) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			System.err.println(sw);
		} else {
			System.err.println(e.getMessage());
		}
	}

	public static void setDebug() {
		level = Level.DEBUG;
	}

	public static void setDeployment() {
		level = Level.DEPLOYMENT;
	}
}

enum Level {
	DEPLOYMENT, DEBUG
}
