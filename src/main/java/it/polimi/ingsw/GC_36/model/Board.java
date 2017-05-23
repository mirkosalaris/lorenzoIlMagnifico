package it.polimi.ingsw.GC_36.model;

public class Board {

	Die[] dice;
	TurnOrder turnOrder;
	Player[] players;
	DeckSet[] deckSets;


	public Board() {
		//TODO
	}

	public void initialize() {
		//TODO
	}

	public void clean() {
		//TODO
	}

	public DeckSet getDeckSet(int periodNumber) {
		return deckSets[periodNumber - 1];
	}

	public TurnOrder getTurnOrder() {
		//TODO
		return null;
	}
}
