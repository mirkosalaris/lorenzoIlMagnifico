package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.exception.PlayingException;
import it.polimi.ingsw.GC_36.model.Round.RoundTerminatedException;
import it.polimi.ingsw.GC_36.observers.NewRoundObserver;
import it.polimi.ingsw.GC_36.observers.PeriodObserver;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Period {
	private int periodNumber;
	final DeckSet deckSet;
	private Round currentRound;
	private int startedRounds = 0;

	private Set<PeriodObserver> observers = new HashSet<>();
	private Set<NewRoundObserver> newRoundObservers = new HashSet<>();

	public Period(int periodNumber, DeckSet deckSet) {
		if (periodNumber <= 0 || periodNumber > Commons.NUMBER_OF_PERIODS) {
			throw new IllegalStateException(
					"periodNumber not acceptable: " + periodNumber);
		}
		this.periodNumber = periodNumber;
		this.deckSet = deckSet;
	}

	public void advance()
			throws IOException, PeriodTerminatedException,
			PlayingException, ClassNotFoundException {
		try {
			if (currentRound == null) {
				newRound();
			}
			currentRound.advance();
		} catch (RoundTerminatedException e) {
			if (startedRounds < Commons.ROUNDS_IN_PERIOD) {
				newRound();

				// TODO impl
				startedRounds += 1;
			} else {
				throw new PeriodTerminatedException(
						"The period can't advance because it's finished");
			}
		}
	}

	public Round getCurrentRound() {
		return currentRound;
	}

	private void newRound() throws IOException {
		currentRound = new Round(deckSet);
		newRoundNotify();
	}

	public void subscribe(PeriodObserver observer) {
		observers.add(observer);
	}

	public void subscribe(NewRoundObserver o) {
		newRoundObservers.add(o);
	}

	private void newRoundNotify() throws IOException {
		for (PeriodObserver o : observers) {
			o.update();
		}
		for (NewRoundObserver o : newRoundObservers) {
			o.update(currentRound);
		}
	}


	public int getPeriodNumber() {
		return periodNumber;
	}

	public class PeriodTerminatedException extends Exception {

		public PeriodTerminatedException() {
			super();
		}

		public PeriodTerminatedException(String message) {
			super(message);
		}
	}
}
