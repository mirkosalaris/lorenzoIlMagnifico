package it.polimi.ingsw.GC_36;

import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.parsers.Parser;

import java.io.File;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public final class Commons {
	private final Parser parser;
	private static ThreadLocal<Commons> threadInstance;

	public static final int NUMBER_OF_PERIODS = 1;
	public static final int NUMBER_OF_FLOORS = 4;

	// Dice
	public static final int BLACK_DIE = 1;
	public static final int WHITE_DIE = 2;
	public static final int ORANGE_DIE = 3;


	// ActionSpaces
	public static final int AS_COUNCIL = 0;

	public static final int AS_TERRITORIES_1 = 1;
	public static final int AS_TERRITORIES_2 = 2;
	public static final int AS_TERRITORIES_3 = 3;
	public static final int AS_TERRITORIES_4 = 4;

	public static final int AS_BUILDINGS_1 = 5;
	public static final int AS_BUILDINGS_2 = 6;
	public static final int AS_BUILDINGS_3 = 7;
	public static final int AS_BUILDINGS_4 = 8;

	public static final int AS_CHARACTERS_1 = 9;
	public static final int AS_CHARACTERS_2 = 10;
	public static final int AS_CHARACTERS_3 = 11;
	public static final int AS_CHARACTERS_4 = 12;

	public static final int AS_VENTURES_1 = 13;
	public static final int AS_VENTURES_2 = 14;
	public static final int AS_VENTURES_3 = 15;
	public static final int AS_VENTURES_4 = 16;

	public static final int AS_HARVEST = 17;
	public static final int AS_HARVEST_BIG = 18;

	public static final int AS_PRODUCTION = 19;
	public static final int AS_PRODUCTION_BIG = 20;

	public static final int AS_MARKET_COINS = 21;
	public static final int AS_MARKET_SERVANT = 22;
	public static final int AS_MARKET_MILITARY_COINS = 23;
	public static final int AS_MARKET_COUNCILS_FAVORS = 24;


	public Commons(File file) {
		// save this instance to be referable from static context
		// -> Commons is a multithread singleton (one instance for each thread)
		Commons instance = this;
		threadInstance = new ThreadLocal<Commons>() {
			@Override
			protected Commons initialValue() {
				return instance;
			}
		};


		parser = new Parser(file);

		// TODO
	}

	public static Commons getInstance() {
		if (threadInstance == null) {
			throw new IllegalStateException(
					"There has to be an instance of Commons");
		} else {
			return threadInstance.get();
		}
	}

	public static Map<DieColor, Die> diceInitializer() {
		Map<DieColor, Die> dice = new EnumMap<>(DieColor.class);
		dice.put(DieColor.BLACK, new Die(DieColor.BLACK));
		dice.put(DieColor.ORANGE, new Die(DieColor.ORANGE));
		dice.put(DieColor.WHITE, new Die(DieColor.WHITE));

		return dice;
	}

	public static Map<Integer, ActionSpace> actionSpacesInitializer() {
		Map<Integer, ActionSpace> map = new HashMap<>();

		map.put(AS_COUNCIL, new ActionSpace(AS_COUNCIL));
		map.put(AS_TERRITORIES_1, new ActionSpace(AS_TERRITORIES_1));
		map.put(AS_TERRITORIES_2, new ActionSpace(AS_TERRITORIES_2));
		map.put(AS_TERRITORIES_3, new ActionSpace(AS_TERRITORIES_3));
		map.put(AS_TERRITORIES_4, new ActionSpace(AS_TERRITORIES_4));
		map.put(AS_BUILDINGS_1, new ActionSpace(AS_BUILDINGS_1));
		map.put(AS_BUILDINGS_2, new ActionSpace(AS_BUILDINGS_2));
		map.put(AS_BUILDINGS_3, new ActionSpace(AS_BUILDINGS_3));
		map.put(AS_BUILDINGS_4, new ActionSpace(AS_BUILDINGS_4));
		map.put(AS_CHARACTERS_1, new ActionSpace(AS_CHARACTERS_1));
		map.put(AS_CHARACTERS_2, new ActionSpace(AS_CHARACTERS_2));
		map.put(AS_CHARACTERS_3, new ActionSpace(AS_CHARACTERS_3));
		map.put(AS_CHARACTERS_4, new ActionSpace(AS_CHARACTERS_4));
		map.put(AS_VENTURES_1, new ActionSpace(AS_VENTURES_1));
		map.put(AS_VENTURES_2, new ActionSpace(AS_VENTURES_2));
		map.put(AS_VENTURES_3, new ActionSpace(AS_VENTURES_3));
		map.put(AS_VENTURES_4, new ActionSpace(AS_VENTURES_4));
		map.put(AS_HARVEST, new ActionSpace(AS_HARVEST));
		map.put(AS_HARVEST_BIG, new ActionSpace(AS_HARVEST_BIG));
		map.put(AS_PRODUCTION, new ActionSpace(AS_PRODUCTION));
		map.put(AS_PRODUCTION_BIG, new ActionSpace(AS_PRODUCTION_BIG));
		map.put(AS_MARKET_COINS, new ActionSpace(AS_MARKET_COINS));
		map.put(AS_MARKET_SERVANT, new ActionSpace(AS_MARKET_SERVANT));
		map.put(AS_MARKET_MILITARY_COINS,
				new ActionSpace(AS_MARKET_MILITARY_COINS));
		map.put(AS_MARKET_COUNCILS_FAVORS,
				new ActionSpace(AS_MARKET_COUNCILS_FAVORS));


		return map;
	}

	public ResourcesList getInitialResources(int ordinal) {
		return (ResourcesList) parser.get("personalBoard" + ordinal);
	}

	public Map<CardType, Deck> getDeckSet(int period) {
		return (Map<CardType, Deck>) parser.get("deckSet" + period);
	}

	public Floor getAssociatedFloor(int actionSpaceId) {

		// goes fine or throws IllegalArgumentException
		if (!checkId(actionSpaceId, i -> (i > 0) && (i <= 16))) {
			return null;
		}

		if (actionSpaceId <= 4) {
			return Tower.TERRITORIES.getFloor(actionSpaceId);
		} else if (actionSpaceId <= 8) {
			return Tower.BUILDINGS.getFloor(actionSpaceId - 4);
		} else if (actionSpaceId <= 12) {
			return Tower.CHARACTERS.getFloor(actionSpaceId - 8);
		} else {
			return Tower.VENTURERS.getFloor(actionSpaceId - 12);
		}
	}

	public int getRequiredActionValue(int actionSpaceId) {

		if (!checkId(actionSpaceId,
				i -> (i >= 0) && (i <= AS_MARKET_COUNCILS_FAVORS))) {

			throw new IllegalArgumentException(
					"actionSpaceId can't be " + actionSpaceId);
		}

		// TODO delete next line and uncomment next line when parser is impl
		return 0;

		// return (int) parser.get("actionSpace" + actionSpaceId,
		// "requiredActionValue");
	}

	public ResourcesList getRequirements(int actionSpaceId) {

		if (!checkId(actionSpaceId,
				i -> (i >= 0) && (i <= AS_MARKET_COUNCILS_FAVORS))) {

			throw new IllegalArgumentException(
					"actionSpaceId can't be " + actionSpaceId);
		}

		return (ResourcesList) parser.get("actionSpace" + actionSpaceId,
				"requirements");
	}

	private boolean checkId(int id, Predicate<Integer> p) {
		return p.test(id);
	}
}

