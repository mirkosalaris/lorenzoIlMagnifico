package it.polimi.ingsw.GC_36.controller;


import it.polimi.ingsw.GC_36.ExceptionLogger;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.server.Participant;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class GameExecutor implements Runnable {
	private Game game;
	private Board board;
	private Map<PlayerColor, Player> players;
	private Set<Participant> users;

	public GameExecutor(Set<Participant> users) throws RemoteException {
		this.users = users;

		players = new EnumMap<>(PlayerColor.class);
		for (int i = 0; i < users.size(); i++) {
			Participant u = users.iterator().next();

			PlayerColor color = PlayerColor.values()[i];
			Player p = new Player(color, u);
			players.put(color, p);
		}
	}

	@Override
	public void run() {
		try {
			game = Game.getInstance();

			game.setPlayers(players);

			for (Participant u : users) {
				game.subscribe(u);
			}


			game.start();


		/*
		while (game.advanceable()) {
			game.advance()
				-->
					if period.finished: newPeriod
					elseif roundfinished: currentPeriod.newRound
					elseif roundAdvancable: round.advance
					elseif ...
			game.wait()
		}

		 */


			board = game.getBoard();

			game.newPeriod(1);
			Period period = game.getCurrentPeriod();
			period.advance();
			Round round = period.getCurrentRound();
			round.advance();
			Player player = round.getCurrentPlayer();
			Action action = new Action();

			try {
				player.getUser().play(action);
				System.out.println(action);
			} catch (IOException | ClassNotFoundException e) {
				ExceptionLogger.log(e);
				System.out.println("Cannot let players play. Exiting");
				closeAll();
				// kill thread
				return;
			}

		} catch (IllegalStateException | RemoteException e) {
			ExceptionLogger.log(e);

			// kill thread
			return;
		}


		// TODO input output

	}

	private void closeAll() throws RemoteException {
		for (Participant u : users) {
			u.exit();
		}
	}


}
