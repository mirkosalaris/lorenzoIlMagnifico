package it.polimi.ingsw.GC_36.controller;

import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.exception.NotCorrectlyCheckedException;
import it.polimi.ingsw.GC_36.exception.ScoringException;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.util.*;

public class Scorer {
	private static List<Integer> territoriesToPoints =
			Arrays.asList(0, 0, 1, 4, 10, 20);
	private static List<Integer> charactersToPoints =
			Arrays.asList(0, 0, 1, 4, 10, 20);

	public static List<Pair<PlayerIdentifier, Integer>> calculate(
			List<Player> players) throws ScoringException {
		Map<Integer, PlayerIdentifier> pointsMap = new HashMap<>();
		try {
			for (Player p : players) {
				pointsMap.put(calculatePoints(p), p.getIdentifier());
			}
			return winningOrderList(pointsMap);
		} catch (NotCorrectlyCheckedException
				| EffectApplyingException e) {
			throw new ScoringException(e);
		}
	}

	private static int calculatePoints(Player p)
			throws NotCorrectlyCheckedException, EffectApplyingException {
		int points = 0;
		PersonalBoard personalBoard = p.getPersonalBoard();


		// Territory's cards: 3/4/5/6 -> 1/4/10/20
		int nTerritories = personalBoard.getCards(CardType.TERRITORY).size();
		points += intToPoints(nTerritories, territoriesToPoints);

		// Character's cards -> 1,3,6,10,15,21
		int nCharacters = personalBoard.getCards(CardType.CHARACTER).size();
		points += intToPoints(nCharacters, charactersToPoints);

		// permanent effects (Venture's cards)
		List<DevelopmentCard> ventureCards =
				personalBoard.getCards(CardType.VENTURE);
		for (DevelopmentCard card : ventureCards) {
			card.getPermanentEffect().applyEffect(null, p);
		}

		// military points -> 5 for the first, 2 for the second
		points += pointsFromMilitaryPoints(p);

		points += personalBoard.getResourcesList()
				.get(ResourceType.VICTORY_POINTS).getValue();

		// convert resources (resourcesTotal/5)
		points += resourcesToPoints(personalBoard);

		return points;
	}

	private static int pointsFromMilitaryPoints(Player p) {
		Comparator<Player> comparator = new Comparator<Player>() {
			@Override
			public int compare(Player p1, Player p2) {
				int p1Points = p1.getPersonalBoard().getResourcesList()
						.get(ResourceType.MILITARY_POINTS).getValue();
				int p2Points = p2.getPersonalBoard().getResourcesList()
						.get(ResourceType.MILITARY_POINTS).getValue();

				return (p1Points < p2Points) ? -1 :
						(p1Points == p2Points) ? 0 : 1;
			}
		};

		List<Player> players = new ArrayList<>(Game.getInstance().getBoard()
				.getPlayers().values());

		players.sort(comparator);
		if (players.get(0).equals(p)) {
			return 5;
		} else if (players.get(1).equals(p)) {
			return 2;
		} else {
			return 0;
		}
	}

	private static int resourcesToPoints(PersonalBoard personalBoard) {
		int nResources = 0;

		for (ResourceType type : ResourceType.values()) {

			if (!(type.equals(ResourceType.FAITH_POINTS) || type.equals(
					ResourceType.MILITARY_POINTS) || type.equals(
					ResourceType.VICTORY_POINTS))) {
				nResources += personalBoard.getResourcesList().get(type)
						.getValue();
			}
		}

		return nResources / 5;
	}

	/**
	 * Create the list of players in order of points given a pointsMap
	 *
	 * @param pointsMap
	 * 		the Map of points, integer, and Players
	 * @return the ordered list of Pairs of players and points (ordered
	 * according to points)
	 */
	private static List<Pair<PlayerIdentifier, Integer>> winningOrderList(
			Map<Integer, PlayerIdentifier> pointsMap) {

		List<Pair<PlayerIdentifier, Integer>> winningOrderList = new
				ArrayList<>();
		int maxPoints;
		Pair<PlayerIdentifier, Integer> pair;
		while (!pointsMap.isEmpty()) {
			maxPoints = Collections.max(pointsMap.keySet());
			pair = new Pair<>(pointsMap.get(maxPoints), maxPoints);
			pointsMap.remove(maxPoints);

			winningOrderList.add(pair);
		}

		return winningOrderList;
	}

	private static int intToPoints(int fromInt, List<Integer> points) {
		return points.get(fromInt);
	}
}
