package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.observers.RoundObserver;

import java.util.HashSet;
import java.util.Set;

public class Round {
	private DeckSet deckSet;
	private RoundState currentState;
	private Player currentPlayer;
	private Set<RoundObserver> observers = new HashSet<>();

	public Round(DeckSet deckSet) {
		this.deckSet = deckSet;

		// Game is a Thread-Singleton
		Board board = Game.getInstance().getBoard();
		initialize(board);
	}

	public void advance() {
		// every time this get called, it checks if there's a new player and
		// if there's one set it to currentPlayer. When there is no more
		// player the turn is finished so it perform final operations

		if (currentState == RoundState.STARTING) {
			setCurrentState(RoundState.PLAYING);
		} else if (currentState != RoundState.PLAYING) {
			throw new IllegalStateException("Can't advance a finished round");
		}

		Board board = Game.getInstance().getBoard();
		TurnOrder turnOrder = board.getTurnOrder();
		if (turnOrder.hasNext()) {
			Player player = turnOrder.getNextPlayer();
			setCurrentPlayer(player);
		} else {
			setCurrentState(RoundState.FINALIZING);
			adjustTurnOrder(board);
			cleanBoard(board);
			setCurrentState(RoundState.FINISHED);
		}
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void subscribe(RoundObserver observer) {
		observers.add(observer);
	}

	private void initialize(Board board) {
		setCurrentState(RoundState.STARTING);

		board.initialize(deckSet);
		rollDice(board);
	}

	private void rollDice(Board board) {
		// TODO impl
	}

	private void adjustTurnOrder(Board board) {
		board.getTurnOrder().adjust();
	}

	private void cleanBoard(Board board) {
		board.clean();
	}

	private void setCurrentState(RoundState currentState) {
		this.currentState = currentState;
		newStateNotify();
	}

	private void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
		newPlayerNotify();
	}

	private void newStateNotify() {
		for (RoundObserver o : observers) {
			o.update(currentState);
		}
	}

	private void newPlayerNotify() {
		for (RoundObserver o : observers) {
			o.update(currentPlayer);
		}
	}
}
