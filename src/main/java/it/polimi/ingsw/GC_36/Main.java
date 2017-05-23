package it.polimi.ingsw.GC_36;

import it.polimi.ingsw.GC_36.controller.Game;

public class Main {

	private Main() {}

	public static void main(String[] args) {
		Game game = Game.getInstance();
		game.run();
	}
}