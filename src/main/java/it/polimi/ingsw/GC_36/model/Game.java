package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.controller.Scorer;
import it.polimi.ingsw.GC_36.exception.PlayingException;
import it.polimi.ingsw.GC_36.exception.ScoringException;
import it.polimi.ingsw.GC_36.model.Period.PeriodTerminatedException;
import it.polimi.ingsw.GC_36.observers.GameObserver;
import it.polimi.ingsw.GC_36.observers.ModelObserver;
import it.polimi.ingsw.GC_36.observers.NewPeriodObserver;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.io.IOException;
import java.util.*;

public class Game {
	public final GameMode GAME_MODE;

	private static final ThreadLocal<Game> threadInstance = new
			ThreadLocal<>();
	private Board board;
	private GameState currentState;
	private Period currentPeriod;

	private Set<GameObserver> observers = new HashSet<>();
	private Set<NewPeriodObserver> newPeriodObservers = new HashSet<>();

	private int currentPeriodNumber = 0;

	public Game() throws IOException {
		this(GameMode.STANDARD);
	}

	public Game(GameMode mode) throws IOException {
		setCurrentState(GameState.STARTING);

		GAME_MODE = mode;
		board = new Board();

		threadInstance.set(this);
	}

	public static Game getInstance() throws IllegalStateException {
		// Game is a Thread-Singleton (a singleton for each thread).
		// This method implements it.

		Game instance = threadInstance.get();
		if (instance == null) {
			throw new IllegalStateException("cannot instantiate Game");
		}

		return instance;
	}

	public void setPlayers(Map<PlayerColor, Player> players)
			throws IllegalStateException, IOException {

		board.setPlayers(players);
	}

	public Board getBoard() {
		return board;
	}

	public Period getCurrentPeriod() {
		return currentPeriod;
	}

	// TODO test
	public void advance()
			throws IOException, PlayingException, ClassNotFoundException {
		try {
			if (currentPeriod == null) {
				throw new IllegalStateException("There is no period");
			}
			currentPeriod.advance();
		} catch (PeriodTerminatedException e) {
			if (currentPeriodNumber < Commons.NUMBER_OF_PERIODS) {
				currentPeriodNumber += 1;
				newPeriod(currentPeriodNumber);
			} else { // Game is finished, so now...
				setCurrentState(GameState.SCORING);
			}
		}
	}

	private void newPeriod(int periodNumber) throws IOException {
		DeckSet deckSet = new DeckSet(periodNumber);
		currentPeriod = new Period(periodNumber, deckSet);

		newPeriodNotify();
	}

	public GameState getState() {
		return currentState;
	}

	public void start() throws IOException {
		play();
	}

	public void subscribe(GameObserver o) {
		observers.add(o);
	}

	public void subscribe(ModelObserver o) {
		observers.add(o);
		board.subscribe(o);
	}

	public void subscribe(NewPeriodObserver o) {
		newPeriodObservers.add(o);
	}

	private void play() throws IOException {
		setCurrentState(GameState.PLAYING);
		newPeriod(++currentPeriodNumber);
	}

	private void setCurrentState(GameState currentState)
			throws IOException {
		this.currentState = currentState;
		newStateNotify();
	}

	public void finalScoring() throws IOException, ScoringException {
		List<Player> playerList = new ArrayList<>(board.getPlayers().values());
		List<Pair<PlayerIdentifier, Integer>> playersWinningList =
				Scorer.calculate(playerList);

		finalScoreNotify(playersWinningList);

		setCurrentState(GameState.FINISHED);
	}

	private void newStateNotify() throws IOException {
		for (GameObserver o : observers) {
			o.update(currentState);
		}
	}

	private void newPeriodNotify() throws IOException {
		for (GameObserver o : observers) {
			o.update(currentPeriod.getPeriodNumber());
		}
		for (NewPeriodObserver o : newPeriodObservers) {
			o.update(currentPeriod);
		}
	}

	private void finalScoreNotify(
			List<Pair<PlayerIdentifier, Integer>> playersWinningList)
			throws IOException {
		for (GameObserver o : observers) {
			o.update(new ArrayList<>(playersWinningList));
		}
	}
}
