package it.polimi.ingsw.GC_36.controller;


import it.polimi.ingsw.GC_36.model.Game;
import it.polimi.ingsw.GC_36.model.Player;
import it.polimi.ingsw.GC_36.model.PlayerColor;
import it.polimi.ingsw.GC_36.server.User;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class GameExecutor implements Runnable {
	private Game game;

	public GameExecutor(List<User> users) {
		game = new Game();

		Map<PlayerColor, Player> players = new EnumMap<>(PlayerColor.class);
		for (int i = 0; i < users.size(); i++) {
			User u = users.get(i);
			PlayerColor color = PlayerColor.values()[i];
			Player p = new Player(color);
			players.put(color, p);
			u.setPlayer(p);
		}

		game.setPlayers(players);

		for (User u : users) {
			game.subscribe(u);
		}


	}

	@Override
	public void run() {

		game.start();

		// TODO input output
	}
}
