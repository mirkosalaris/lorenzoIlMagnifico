package it.polimi.ingsw.GC_36.parsers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Parser {
	Map<String, Object> map;

	private Parser() {}

	public Parser(File file) {
		/*if (!file.exists()) {
			throw new IOException();
		}*/
		map = new HashMap<>();
	}

	public Object get(String s) {
		Integer.parseInt(s.replaceAll("[\\D]", ""));
		if (s.contains("deckSet")) {
			return null;
		} else if (s.contains("personalBoard")) {
			return null;
		} else {
			throw new IllegalArgumentException("Parameter not valid");
		}
	}

	public Object get(String s1, String s2) {
		Integer.parseInt(s1.replaceAll("[\\D]", ""));
		if (s1.contains("actionSpace")) {
			return null;
		} else {
			throw new IllegalArgumentException("Parameter not valid");
		}
	}
}
