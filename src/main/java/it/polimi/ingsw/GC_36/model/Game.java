package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.controller.Scorer;
import it.polimi.ingsw.GC_36.observers.GameObserver;
import it.polimi.ingsw.GC_36.observers.ModelObserver;
import it.polimi.ingsw.GC_36.parsers.DeckSetsParser;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Game {
	public static final String COMMONS_FILE = "commons.json";
	public static final String DECKSETS_FILE = "deckSets.json";

	private DeckSetsParser deckSetsParser;

	private static ThreadLocal<Game> threadInstance = null;
	private Board board;
	private GameState currentState;
	private Period currentPeriod;

	private Set<GameObserver> observers = new HashSet<>();

	public Game() {
		// instantiate Commons, for later use of other classes
		Commons common = Commons.getInstance(new File(COMMONS_FILE));

		setCurrentState(GameState.STARTING);

		board = new Board();
		deckSetsParser = new DeckSetsParser(new File(DECKSETS_FILE));
	}

	public static Game getInstance() {
		// Game is a Thread-Singleton (a singleton for each thread).
		// This method implements it.

		if (threadInstance == null) {
			threadInstance = new ThreadLocal<Game>() {
				@Override
				protected Game initialValue() {
					return new Game();
				}
			};
			//throw new IllegalStateException("No game instance exists");
		}
		return threadInstance.get();
	}

	// TODO: test
	public void setPlayers(Map<PlayerColor, Player> players) {
		board.initPlayers(players);
	}

	public Board getBoard() {
		return board;
	}

	public Period getCurrentPeriod() {
		return currentPeriod;
	}

	public void newPeriod(int periodNumber) {
		// TODO how do we check that this method will be called just three
		// times?

		DeckSet deckSet = deckSetsParser.get(periodNumber);
		currentPeriod = new Period(periodNumber, deckSet);

		newPeriodNotify();
	}

	public GameState getState() {
		return currentState;
	}

	public void start() {
		play();
	}

	public void subscribe(GameObserver o) {
		observers.add(o);
	}

	public void subscribe(ModelObserver o) {
		observers.add(o);
		board.subscribe(o);
	}

	private void play() {
		setCurrentState(GameState.PLAYING);

		/*
		// TODO: delete
		 for (int periodNumber = 1;
		     periodNumber < commons.NUMBER_OF_PERIODS;
		     periodNumber++) {


			currentPeriod.start();
		}*/
	}

	private void setCurrentState(GameState currentState) {
		this.currentState = currentState;
		newStateNotify();
	}

	private void finalScoring() {
		setCurrentState(GameState.SCORING);

		// TODO: impl the action to communicate the winner
		Scorer.calculate();

		setCurrentState(GameState.FINISHED);
	}

	private void newStateNotify() {
		for (GameObserver o : observers) {
			o.update(currentState);
		}
	}

	private void newPeriodNotify() {
		for (GameObserver o : observers) {
			o.update(currentPeriod);
		}
	}
}
