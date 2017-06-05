package it.polimi.ingsw.GC_36.controller;


import it.polimi.ingsw.GC_36.model.Game;
import it.polimi.ingsw.GC_36.model.Player;
import it.polimi.ingsw.GC_36.model.PlayerColor;
import it.polimi.ingsw.GC_36.server.Participant;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class GameExecutor implements Runnable {
	private Game game;

	public GameExecutor(Set<Participant> users) {
		game = Game.getInstance();

		Map<PlayerColor, Player> players = new EnumMap<>(PlayerColor.class);
		for (int i = 0; i < users.size(); i++) {
			Participant u = users.iterator().next();

			PlayerColor color = PlayerColor.values()[i];
			Player p = new Player(color);
			players.put(color, p);
		}

		game.setPlayers(players);

		for (Participant u : users) {
			game.subscribe(u);
		}


	}

	@Override
	public void run() {

		game.start();

		// TODO input output
	}
}
