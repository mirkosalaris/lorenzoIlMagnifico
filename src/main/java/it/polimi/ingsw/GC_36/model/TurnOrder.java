package it.polimi.ingsw.GC_36.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TurnOrder {
	private List<Player> players;

	public TurnOrder(Map<PlayerColor, Player> players) {
		this.players = new ArrayList<>(players.values());
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

		// board.getActionSpaces().get(Commons.COUNCIL_ACTION_SPACE);
		// calculate new turn order
	}
}
