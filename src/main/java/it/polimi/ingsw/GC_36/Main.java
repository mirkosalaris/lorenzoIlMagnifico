package it.polimi.ingsw.GC_36;

import it.polimi.ingsw.GC_36.model.Game;
import it.polimi.ingsw.GC_36.model.Player;

import java.util.ArrayList;

public class Main {

	private Main() {}

	public static void main(String[] args) {
		ArrayList<Player> players = new ArrayList<>();

		// TODO just for the moment
		players.add(new Player());
		players.add(new Player());

		Game game = new Game(players);
		game.run();
	}
}