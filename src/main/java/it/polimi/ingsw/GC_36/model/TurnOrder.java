package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Observable;
import it.polimi.ingsw.GC_36.Observer;

import java.util.*;

public class TurnOrder implements Observable {
	List<Player> players;
	Set<Observer> observers = new HashSet<>();

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

		changeNotify();
	}

	@Override
	public void subscribe(Observer o) {
		observers.add(o);
	}

	@Override
	public void changeNotify() {
		for (Observer o : observers) {
			o.update();
		}
	}
}
