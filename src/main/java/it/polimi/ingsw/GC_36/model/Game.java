package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.Observable;
import it.polimi.ingsw.GC_36.Observer;
import it.polimi.ingsw.GC_36.controller.Scorer;
import it.polimi.ingsw.GC_36.parsers.DeckSetsParser;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game implements Observable {
	public static final String COMMONS_FILE = "commons.json";
	public static final String DECKSETS_FILE = "deckSets.json";

	private DeckSetsParser deckSetsParser;

	private static ThreadLocal<Game> threadInstance = null;
	private Board board;
	private Commons commons;
	private GameState currentState;
	private Period currentPeriod;

	private Set<Observer> observers = new HashSet<>();


	public Game(List<Player> players) {
		if (threadInstance != null)
			throw new IllegalStateException("A game instance already exists");

		setCurrentState(GameState.STARTING);
		board = new Board(players);

		commons = new Commons(new File(COMMONS_FILE));

		deckSetsParser = new DeckSetsParser(new File(DECKSETS_FILE));

		// save this instance to be referable from static context
		// -> Game is a multithread singleton (one instance for each thread)
		Game instance = this;
		threadInstance = new ThreadLocal<Game>() {
			@Override
			protected Game initialValue() {
				return instance;
			}
		};
	}

	// Game is a Thread-Singleton (a singleton for each thread)
	// this method implements it.
	public static Game getInstance() throws IllegalStateException {
		if (threadInstance == null) {
			throw new IllegalStateException("No game instance");
		}
		return threadInstance.get();
	}

	public Board getBoard() {
		return board;
	}

	public Period getCurrentPeriod() {
		return currentPeriod;
	}

	public GameState getState() {
		return currentState;
	}

	private void setCurrentState(GameState currentState) {
		this.currentState = currentState;
		changeNotify();
	}

	public void run() {
		play();
		finalScoring();
		setCurrentState(GameState.FINISHED);
	}

	private void play() {
		setCurrentState(GameState.PLAYING);
		for (int periodNumber = 1;
		     periodNumber < commons.NUMBER_OF_PERIODS;
		     periodNumber++) {

			DeckSet deckSet = deckSetsParser.get(periodNumber);
			currentPeriod = new Period(periodNumber, deckSet);
			currentPeriod.start();
		}
		// TODO: check if this is all
	}

	private void finalScoring() {
		setCurrentState(GameState.SCORING);

		// TODO: impl the action to communicate the winner
		Scorer.calculate();
	}

	@Override
	public void subscribe(Observer o) {
		observers.add(o);
	}

	@Override
	public void changeNotify() {
		for (Observer o : observers) {
			o.update();
		}
	}
}
