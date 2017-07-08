package it.polimi.ingsw.GC_36.model;

import java.util.*;

public class TurnOrder {
	/* Pay attention:
	 * playersFullOrder contains the full list of turn, for the whole round.
	 * Suppose we have 2 players: Y and G (yellow and green).
	 * The list would be: (Y,G,Y,G,Y,G,Y,G)
	 */
	private List<Player> playersFullOrder = new ArrayList<>();

	// populated with "priority" players
	private List<Player> nextPlayersOrder = new ArrayList<>();

	// lastOrder keeps a (fixed) copy of players order (not full).
	// It's needed to calculate the playersFullOrder of the next turn
	private List<Player> lastOrder = new ArrayList<>();

	// players who have been set out of game
	private Set<Player> outOfGame = new HashSet<>();

	public TurnOrder(Map<PlayerColor, Player> players) {
		generateFullOrder(new ArrayList<>(players.values()));
	}

	private void generateFullOrder(List<Player> players) {
		playersFullOrder.clear();
		lastOrder.clear();

		// for the number of turns of each players in a round
		for (int i = 0; i < MemberColor.values().length; i++) {
			playersFullOrder.addAll(players);
		}

		// last order is not 'full'
		lastOrder.addAll(players);
	}

	public synchronized boolean hasNext() {
		// synchronized to avoid being called together with rejoin()

		while (!playersFullOrder.isEmpty()
				&& outOfGame.contains(playersFullOrder.get(0))) {
			playersFullOrder.remove(0);
		}
		return !playersFullOrder.isEmpty();
	}

	public Player getNextPlayer() {
		if (!hasNext()) return null;

		Player player = playersFullOrder.get(0);
		playersFullOrder.remove(0);
		return player;
	}

	/**
	 * set the playersOrder for the next turn
	 */
	public void adjust() {
		for (Player player : lastOrder) {
			if (!nextPlayersOrder.contains(player))
				nextPlayersOrder.add(player);
		}
		// nextPlayersOrder now is the new (NOT FULL) players order

		generateFullOrder(nextPlayersOrder);
		nextPlayersOrder.clear();
	}

	public void addCouncilPlayer(Player player) {
		nextPlayersOrder.add(player);
	}

	public void setOutOfGame(Player player) {
		outOfGame.add(player);
	}

	public synchronized void rejoin(Player player) {
		// synchronized to avoid being called together with hasNext()

		outOfGame.remove(player);
	}
}
