package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Observable;
import it.polimi.ingsw.GC_36.Observer;

import java.util.List;

public class Board implements Observable {
	Die[] dice;
	TurnOrder turnOrder;
	Player[] players;

	List<Observer> observerList;

	public Board(List<Player> players) {
		//TODO
	}

	public void initialize(DeckSet deckSet) {
		// TODO

		turnOrder = new TurnOrder(null);
	}

	public void clean() {
		//TODO
	}

	public DeckSet getDeckSet() {
		// TODO: un-comment after actual initialization of data
		//return deckSets[periodNumber - 1];
		return null;
	}

	public TurnOrder getTurnOrder() {
		//TODO
		return turnOrder;
	}

	@Override
	public void subscribe(Observer o) {

	}

	@Override
	public void changeNotify() {

	}
}
