package it.polimi.ingsw.GC_36.controller;

import it.polimi.ingsw.GC_36.model.*;

public class Round {
	DeckSet deckSet;
	Game game;

	public Round(DeckSet deckSet) {
		this.deckSet = deckSet;
		game = Game.getInstance();
	}

	public void startRound() {
		// Game is a Thread-Singleton
		Board board = Game.getInstance().getBoard();
		rollDice(board);
		executeRound(board);
		adjustTurnOrder(board);
		cleanBoard(board);
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
}
