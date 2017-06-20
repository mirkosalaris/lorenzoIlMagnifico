package it.polimi.ingsw.GC_36.controller;

import it.polimi.ingsw.GC_36.exception.PlayingException;
import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.Player;

import java.io.IOException;

public class RoundController {

	private final ActionExecutor executor;

	public RoundController() {
		executor = new ActionExecutor();
	}

	public void execute(Player player) throws PlayingException {

		Action action = null;
		try {
			boolean executed = false;

			do {
				action = new Action();
				player.getParticipant().play(action);
				executed = executor.execute(action);
				// TODO: send 'error' to player
				// (s)he has to know if (s)he had done something wrong
			} while (!executed);


		} catch (IOException | ClassNotFoundException e) {
			throw new PlayingException(
					"An error occurred during turn of player " + player, e);
		}

		// TODO delete
		System.out.println(action);
	}

}
