package it.polimi.ingsw.GC_36.controller;

import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.Player;

import java.io.IOException;

public class RoundController {

	private Player player;

	public void execute(Player player) throws PlayingException {
		this.player = player;
		Action action = new Action();

		try {
			player.getParticipant().play(action);
		} catch (IOException | ClassNotFoundException e) {
			throw new PlayingException(
					"An error occurred during turn of player " + player, e);
		}

		// TODO check action
	}

	/**
	 * Called when an exception is raised during a turn
	 */
	public class PlayingException extends Exception {
		public PlayingException() {
			super();
		}

		public PlayingException(String message) {
			super(message);
		}

		public PlayingException(String message, Throwable throwable) {
			super(message, throwable);
		}
	}

}
