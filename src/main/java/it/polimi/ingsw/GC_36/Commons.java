package it.polimi.ingsw.GC_36;

import it.polimi.ingsw.GC_36.model.ActionSpace;
import it.polimi.ingsw.GC_36.model.Color;
import it.polimi.ingsw.GC_36.model.Die;
import it.polimi.ingsw.GC_36.parsers.Parser;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class Commons {
	private static ThreadLocal<Commons> threadInstance;

	public static final int NUMBER_OF_PERIODS = 1;

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


		Parser parser = new Parser(file);

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

	public static Map<Integer, Die> diceInitializer() {
		Map<Integer, Die> dice = new HashMap<>();
		dice.put(BLACK_DIE, new Die(Color.BLACK));
		dice.put(ORANGE_DIE, new Die(Color.ORANGE));
		dice.put(WHITE_DIE, new Die(Color.WHITE));

		return dice;
	}

	public static Map<Integer, ActionSpace> actionSpacesInitializer() {
		Map<Integer, ActionSpace> map = new HashMap<>();

		map.put(AS_COUNCIL, Parser.getActionSpace(AS_COUNCIL));
		map.put(AS_TERRITORIES_1, Parser.getActionSpace(AS_TERRITORIES_1));
		map.put(AS_TERRITORIES_2, Parser.getActionSpace(AS_TERRITORIES_2));
		map.put(AS_TERRITORIES_3, Parser.getActionSpace(AS_TERRITORIES_3));
		map.put(AS_TERRITORIES_4, Parser.getActionSpace(AS_TERRITORIES_4));
		map.put(AS_BUILDINGS_1, Parser.getActionSpace(AS_BUILDINGS_1));
		map.put(AS_BUILDINGS_2, Parser.getActionSpace(AS_BUILDINGS_2));
		map.put(AS_BUILDINGS_3, Parser.getActionSpace(AS_BUILDINGS_3));
		map.put(AS_BUILDINGS_4, Parser.getActionSpace(AS_BUILDINGS_4));
		map.put(AS_CHARACTERS_1, Parser.getActionSpace(AS_CHARACTERS_1));
		map.put(AS_CHARACTERS_2, Parser.getActionSpace(AS_CHARACTERS_2));
		map.put(AS_CHARACTERS_3, Parser.getActionSpace(AS_CHARACTERS_3));
		map.put(AS_CHARACTERS_4, Parser.getActionSpace(AS_CHARACTERS_4));
		map.put(AS_VENTURES_1, Parser.getActionSpace(AS_VENTURES_1));
		map.put(AS_VENTURES_2, Parser.getActionSpace(AS_VENTURES_2));
		map.put(AS_VENTURES_3, Parser.getActionSpace(AS_VENTURES_3));
		map.put(AS_VENTURES_4, Parser.getActionSpace(AS_VENTURES_4));
		map.put(AS_HARVEST, Parser.getActionSpace(AS_HARVEST));
		map.put(AS_HARVEST_BIG, Parser.getActionSpace(AS_HARVEST_BIG));
		map.put(AS_PRODUCTION, Parser.getActionSpace(AS_PRODUCTION));
		map.put(AS_PRODUCTION_BIG, Parser.getActionSpace(AS_PRODUCTION_BIG));
		map.put(AS_MARKET_COINS, Parser.getActionSpace(AS_MARKET_COINS));
		map.put(AS_MARKET_SERVANT, Parser.getActionSpace(AS_MARKET_SERVANT));
		map.put(AS_MARKET_MILITARY_COINS,
				Parser.getActionSpace(AS_MARKET_MILITARY_COINS));
		map.put(AS_MARKET_COUNCILS_FAVORS,
				Parser.getActionSpace(AS_MARKET_COUNCILS_FAVORS));


		return map;
	}
}

