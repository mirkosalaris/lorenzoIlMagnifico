package it.polimi.ingsw.GC_36.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TurnOrder {
	private final Map<PlayerColor, Player> fullMap;
	private List<Player> players = new ArrayList<>();

	public TurnOrder(Map<PlayerColor, Player> players) {
		fullMap = players;
		// TODO from 0 to number of family members...
		for (int i = 0; i < 4; i++) {
			this.players.addAll(fullMap.values());
		}
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

		players = new ArrayList<>(fullMap.values());
	}
}
