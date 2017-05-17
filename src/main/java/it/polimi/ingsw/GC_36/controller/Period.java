package it.polimi.ingsw.GC_36.controller;

public class Period {
	final DeckSet deckSet;

	public Period(DeckSet deckSet) {
		this.deckSet = deckSet;
	}

	public void start() {
		new Round(deckSet).startRound();
		new Round(deckSet).startRound();
		// TODO: check if this is all
	}
}
