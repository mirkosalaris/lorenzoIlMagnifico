package it.polimi.ingsw.GC_36;

import it.polimi.ingsw.GC_36.model.Game;
import it.polimi.ingsw.GC_36.model.Player;
import it.polimi.ingsw.GC_36.model.PlayerColor;

import java.util.HashMap;
import java.util.Map;

public class Main {

	private Main() {}

	public static void main(String[] args) {
		Game game = new Game();

		Map<PlayerColor, Player> players = new HashMap<>();

		// TODO just for the moment
		players.put(PlayerColor.BLUE, new Player(PlayerColor.BLUE));
		players.put(PlayerColor.BLUE, new Player(PlayerColor.BLUE));

		game.setPlayers(players);

		game.start();
	}
}