package it.polimi.ingsw.GC_36.controller;

import it.polimi.ingsw.GC_36.exception.PlayingException;
import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.ActionInterface;
import it.polimi.ingsw.GC_36.model.Player;

import java.io.IOException;

public class RoundController {

	public void execute(Player player) throws PlayingException {

		ActionInterface action = null;
		try {
			action = new Action();
			player.getParticipant().play(action);
		} catch (IOException | ClassNotFoundException e) {
			throw new PlayingException(
					"An error occurred during turn of player " + player, e);
		}

		// TODO check action
		System.out.println("finito");
		System.out.println(action);
	}

}
