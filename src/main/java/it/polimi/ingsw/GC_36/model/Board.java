package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Common;

public class Board {

	Die[] dice;
	TurnOrder turnOrder;
	Player[] players;
	DeckSet[] deckSets;


	public Board() {
		//TODO
	}

	public void initialize() {
		// TODO
		deckSets = new DeckSet[Common.getInstance().NUMBER_OF_PERIODS];
		turnOrder = new TurnOrder(null);
	}

	public void clean() {
		//TODO
	}

	public DeckSet getDeckSet(int periodNumber) {
		// TODO: un-comment after actual initialization of data
		//return deckSets[periodNumber - 1];
		return null;
	}

	public TurnOrder getTurnOrder() {
		//TODO
		return turnOrder;
	}
}
