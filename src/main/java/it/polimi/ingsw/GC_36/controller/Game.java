package it.polimi.ingsw.GC_36.controller;

import it.polimi.ingsw.GC_36.Common;
import it.polimi.ingsw.GC_36.model.Board;
import it.polimi.ingsw.GC_36.model.DeckSet;

import java.io.File;

public class Game {
	private static ThreadLocal<Game> instance = null;
	private Board board;
	private Common common;

	private Game() {}

	// Game is a Thread-Singleton (a singleton for each thread)
	// this method implements it.
	public static Game getInstance() {
		if (instance == null) {
			instance = new ThreadLocal<Game>() {
				@Override
				protected Game initialValue() {
					return new Game();
				}
			};
		}
		return instance.get();
	}

	public Board getBoard() {
		return board;
	}

	public void run() {
		initialize();
		play();
		finalScoring();
	}

	private void initialize() {
		File file = new File("common.json");
		common = new Common(file);
		board = new Board();
		board.initialize();
		// TODO: implement ModelCommunicator as in UML or change to observer
		// pattern
	}

	private void play() {
		for (int i = 1; i < common.NUMBER_OF_PERIODS; i++) {
			DeckSet deckSet = board.getDeckSet(i);
			new Period(i, deckSet).start();
		}
		// TODO: check if this is all
	}

	private void finalScoring() {
		// TODO: impl the action to communicate the winner

		Scorer.calculate();
	}
}
