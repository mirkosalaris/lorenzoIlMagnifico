package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.ExceptionLogger;
import it.polimi.ingsw.GC_36.controller.Scorer;
import it.polimi.ingsw.GC_36.observers.GameObserver;
import it.polimi.ingsw.GC_36.observers.ModelObserver;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Game {
	private static ThreadLocal<Game> threadInstance = null;
	private Board board;
	private GameState currentState;
	private Period currentPeriod;

	private Set<GameObserver> observers = new HashSet<>();

	public Game() throws RemoteException {
		setCurrentState(GameState.STARTING);

		board = new Board();
	}

	public static Game getInstance() throws IllegalStateException {
		// Game is a Thread-Singleton (a singleton for each thread).
		// This method implements it.

		if (threadInstance == null) {
			threadInstance = new ThreadLocal<Game>() {
				@Override
				protected Game initialValue() {

					try {
						return new Game();
					} catch (RemoteException e) {
						ExceptionLogger.log(e);
						return null;
					}

				}
			};

			if (threadInstance.get() == null) {
				throw new IllegalStateException("cannot instantiate Game");
			}
			//throw new IllegalStateException("No game instance exists");
		}
		return threadInstance.get();
	}

	// TODO: test
	public void setPlayers(Map<PlayerColor, Player> players)
			throws IllegalStateException, RemoteException {
		board.setPlayers(players);
	}

	public Board getBoard() {
		return board;
	}

	public Period getCurrentPeriod() {
		return currentPeriod;
	}

	public void newPeriod(int periodNumber) throws RemoteException {
		// TODO how do we check that this method will be called just three
		// times?

		DeckSet deckSet = new DeckSet(periodNumber);
		currentPeriod = new Period(periodNumber, deckSet);

		newPeriodNotify();
	}

	public GameState getState() {
		return currentState;
	}

	public void start() throws RemoteException {
		play();
	}

	public void subscribe(GameObserver o) {
		observers.add(o);
	}

	public void subscribe(ModelObserver o) {
		observers.add(o);
		board.subscribe(o);
	}

	private void play() throws RemoteException {
		setCurrentState(GameState.PLAYING);
	}

	private void setCurrentState(GameState currentState)
			throws RemoteException {
		this.currentState = currentState;
		newStateNotify();
	}

	private void finalScoring() throws RemoteException {
		setCurrentState(GameState.SCORING);

		// TODO: impl the action to communicate the winner
		Scorer.calculate();

		setCurrentState(GameState.FINISHED);
	}

	private void newStateNotify() throws RemoteException {
		for (GameObserver o : observers) {
			o.update(currentState);
		}
	}

	private void newPeriodNotify() throws RemoteException {
		for (GameObserver o : observers) {
			o.update(currentPeriod.getPeriodNumber());
		}
	}
}
