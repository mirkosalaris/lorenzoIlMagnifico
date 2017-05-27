package it.polimi.ingsw.GC_36.model;

public class Period {
	private int periodNumber;
	final DeckSet deckSet;
	private Round currentRound;

	public Period(int periodNumber, DeckSet deckSet) {
		this.periodNumber = periodNumber;
		this.deckSet = deckSet;
	}

	public void start() {
		new Round(deckSet).startRound();
		new Round(deckSet).startRound();
		// TODO: check if this is all
	}

	public Round getCurrentRound() {
		return currentRound;
	}
}
