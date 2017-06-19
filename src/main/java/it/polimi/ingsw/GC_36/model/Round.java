package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.controller.RoundController;
import it.polimi.ingsw.GC_36.exception.PlayingException;
import it.polimi.ingsw.GC_36.observers.RoundObserver;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Round {
	private DeckSet deckSet;
	private RoundState currentState;
	private Player currentPlayer;
	private Set<RoundObserver> observers = new HashSet<>();
	private RoundController controller;

	public Round(DeckSet deckSet)
			throws IllegalStateException, IOException {
		this.deckSet = deckSet;

		// Game is a Thread-Singleton
		Board board = Game.getInstance().getBoard();
		initialize(board);
	}

	public void advance()
			throws IOException, RoundTerminatedException, PlayingException,
			ClassNotFoundException {
		// every time this get called, it checks if there's a new player and
		// if there's one set it to currentPlayer. When there is no more
		// player the turn is finished so it perform final operations

		if (currentState == RoundState.STARTING) {
			setCurrentState(RoundState.PLAYING);
		} else if (currentState != RoundState.PLAYING) {
			throw new RoundTerminatedException(
					"Can't advance a finished Round");
		}

		Board board = Game.getInstance().getBoard();
		TurnOrder turnOrder = board.getTurnOrder();
		if (turnOrder.hasNext()) {
			Player player = turnOrder.getNextPlayer();
			setCurrentPlayer(player);
			controller.execute(player);
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

	public void setController(RoundController controller) {
		this.controller = controller;
	}

	public void subscribe(RoundObserver observer) {
		observers.add(observer);
	}

	private void initialize(Board board) throws IOException {
		setCurrentState(RoundState.STARTING);

		board.prepare(deckSet);
	}

	private void adjustTurnOrder(Board board) {
		board.getTurnOrder().adjust();
	}

	private void cleanBoard(Board board) throws IOException {
		board.clean();
	}

	private void setCurrentState(RoundState currentState)
			throws IOException {
		this.currentState = currentState;
		newStateNotify();
	}

	private void setCurrentPlayer(Player currentPlayer) throws
			IOException {
		this.currentPlayer = currentPlayer;
		newPlayerNotify();
	}

	private void newStateNotify() throws IOException {
		for (RoundObserver o : observers) {
			o.update(currentState);
		}
	}

	private void newPlayerNotify() throws IOException {
		for (RoundObserver o : observers) {
			o.update(currentPlayer.getIdentifier());
		}
	}

	/**
	 * Called when trying to advance a Round already terminated
	 */
	public class RoundTerminatedException extends Exception {
		public RoundTerminatedException() {
			super();
		}

		public RoundTerminatedException(String message) {
			super(message);
		}
	}
}
