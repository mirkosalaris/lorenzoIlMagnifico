package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.observers.PeriodObserver;

import java.util.HashSet;
import java.util.Set;

public class Period {
	private int periodNumber;
	final DeckSet deckSet;
	private Round currentRound;

	private Set<PeriodObserver> observers = new HashSet<>();

	public Period(int periodNumber, DeckSet deckSet) {
		this.periodNumber = periodNumber;
		this.deckSet = deckSet;
	}

	public void advance() {
		// TODO: how do we check that this method will be called just 2 times?
		newRound();
	}

	public Round getCurrentRound() {
		return currentRound;
	}

	private void newRound() {
		currentRound = new Round(deckSet);
		newRoundNotify();
	}

	public void subscribe(PeriodObserver observer) {
		observers.add(observer);
	}

	private void newRoundNotify() {
		for (PeriodObserver o : observers) {
			o.update();
		}
	}


	public int getPeriodNumber() {
		return periodNumber;
	}
}
