package it.polimi.ingsw.GC_36.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TurnOrder {
	private List<Player> playersOrder = new ArrayList<>();
	private List<Player> nextPlayersOrder = new ArrayList<>();
	//lastOrder in needed to calculate the nextplayersOrder
	private List<Player> lastOrder = new ArrayList<>();

	public TurnOrder(Map<PlayerColor, Player> players) {
		for (int i = 0; i < MemberColor.values().length; i++) {
			this.playersOrder.addAll(players.values());
			this.lastOrder.addAll(players.values());
		}
	}

	public boolean hasNext() {
		return !playersOrder.isEmpty();
	}

	public Player getNextPlayer() {
		Player player = playersOrder.get(0);
		playersOrder.remove(0);
		return player;
	}

	public void adjust() {

		//set the playersOrder for the next turn

		for (Player player : lastOrder) {
			if (!nextPlayersOrder.contains(player))
				nextPlayersOrder.add(player);
		}

		playersOrder = new ArrayList<>(nextPlayersOrder);
		lastOrder = new ArrayList<>(nextPlayersOrder);
		nextPlayersOrder.clear();
	}

	public void addPlayer(Player player) {
		nextPlayersOrder.add(player);
	}

}
