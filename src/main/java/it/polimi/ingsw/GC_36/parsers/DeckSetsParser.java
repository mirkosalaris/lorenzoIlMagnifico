package it.polimi.ingsw.GC_36.parsers;

import it.polimi.ingsw.GC_36.model.DeckSet;

import java.io.File;

public class DeckSetsParser extends Parser {

	public DeckSetsParser(File file) {
		super(file);
	}

	public DeckSet get(int periodNumber) {
		return (DeckSet) super.get(String.valueOf(periodNumber));
	}
}
