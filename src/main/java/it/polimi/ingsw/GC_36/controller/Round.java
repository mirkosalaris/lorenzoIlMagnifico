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
		allocateCards(board);
		rollDice(board);
		executeRound(board);
		adjustTurnOrder(board);
		cleanBoard(board);
	}

	private void allocateCards(Board board) {
		allocateTerritories(board, deckSet.getTerritoriesDeck());
		allocateBuildings(board, deckSet.getBuildingsDeck());
		allocateCharacters(board, deckSet.getCharactersDeck());
		allocateVentures(board, deckSet.getVenturesDeck());
	}

	// TODO change this 4 methods to be just one and add modularity
	private void allocateTerritories(Board board, Deck deck) {
		// TODO impl
	}

	private void allocateBuildings(Board board, Deck deck) {
		// TODO impl
	}

	private void allocateCharacters(Board board, Deck deck) {
		// TODO impl
	}

	private void allocateVentures(Board board, Deck deck) {
		// TODO impl
	}

	private void rollDice(Board board) {
		// TODO impl
	}

	private void executeRound(Board board) {
		TurnOrder turnOrder = board.getTurnOrder();
		while(turnOrder.hasNext()) {
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
