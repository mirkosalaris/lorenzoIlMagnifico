package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.controller.TurnExecutor;

public class Round {
	private DeckSet deckSet;
	private Game game;
	private Player currentPlayer;


	public Round(DeckSet deckSet) {
		this.deckSet = deckSet;
		game = Game.getInstance();
	}

	public void startRound() {
		// Game is a Thread-Singleton
		Board board = Game.getInstance().getBoard();
		initialize(board);
		executeRound(board);
		adjustTurnOrder(board);
		cleanBoard(board);
	}

	private void initialize(Board board) {
		board.initialize(deckSet);
		rollDice(board);
	}

	private void rollDice(Board board) {
		// TODO impl
	}

	private void executeRound(Board board) {
		TurnOrder turnOrder = board.getTurnOrder();
		while (turnOrder.hasNext()) {
			Player player = turnOrder.getNextPlayer();
			TurnExecutor.play(player);
		}
		// TODO check if this is the whole implementation
	}

	private void adjustTurnOrder(Board board) {
		board.getTurnOrder().adjust();
	}

	private void cleanBoard(Board board) {
		board.clean();
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

}
