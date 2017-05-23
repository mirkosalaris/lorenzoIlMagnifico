package it.polimi.ingsw.GC_36.model;

import java.util.Collection;
import java.util.List;

public class TurnOrder {
	List<Player> players;

	public TurnOrder(List<Player> players) {
		this.players = players;
	}

	public boolean hasNext() {
		return !players.isEmpty();
	}

	public Player getNextPlayer() {
		Player player = players.get(0);
		players.remove(0);
		return player;
	}

	public void adjust() {
		// TODO

		Board board = new Board();
		// board.getActionSpaces().get(Commons.COUNCIL_ACTION_SPACE);
	}
}
