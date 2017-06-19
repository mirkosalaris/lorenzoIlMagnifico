package it.polimi.ingsw.GC_36.controller;

import it.polimi.ingsw.GC_36.model.PersonalBoard;
import it.polimi.ingsw.GC_36.model.Player;
import it.polimi.ingsw.GC_36.model.ResourceType;

import java.util.*;

public class Scorer {
	public static List<Player> calculate(List<Player> players) {
		Map<Integer, Player> pointsMap = new HashMap<>();
		for (Player p : players) {
			pointsMap.put(calculatePoints(p), p);
		}

		return winningOrderList(pointsMap);
	}

	private static int calculatePoints(Player p) {
		int points = 0;
		PersonalBoard personalBoard = p.getPersonalBoard();

		points += personalBoard.getResourcesList()
				.get(ResourceType.VICTORY_POINTS).getValue();

		// TODO @mirko

		return points;
	}

	/**
	 * Create the list of players in order of points given a pointsMap
	 *
	 * @param pointsMap
	 * 		the Map of points, integer, and Players
	 * @return the ordered list of players according to their points
	 */
	private static List<Player> winningOrderList(
			Map<Integer, Player> pointsMap) {

		List<Player> winningOrderList = new ArrayList<>();
		int maxPoints;
		while (!pointsMap.isEmpty()) {
			maxPoints = Collections.max(pointsMap.keySet());
			winningOrderList.add(pointsMap.get(maxPoints));
		}

		return winningOrderList;
	}
}
