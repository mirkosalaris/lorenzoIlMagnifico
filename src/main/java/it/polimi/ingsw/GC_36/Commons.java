package it.polimi.ingsw.GC_36;

import it.polimi.ingsw.GC_36.parsers.Parser;

import java.io.File;

public final class Commons {
	private static ThreadLocal<Commons> threadInstance;

	public final int NUMBER_OF_PERIODS;

	// ActionSpaces
	public final int AS_COUNCIL;

	public final int AS_MARKET_COINS;
	public final int AS_MARKET_SERVANT;
	public final int AS_MARKET_MILITARY_COINS;
	public final int AS_MARKET_COUNCILS_FAVORS;

	public final int AS_HARVEST;
	public final int AS_PRODUCTION;

	public final int AS_TERRITORIES_1;
	public final int AS_TERRITORIES_2;
	public final int AS_TERRITORIES_3;
	public final int AS_TERRITORIES_4;

	public final int AS_BUILDINGS_1;
	public final int AS_BUILDINGS_2;
	public final int AS_BUILDINGS_3;
	public final int AS_BUILDINGS_4;

	public final int AS_CHARACTERS_1;
	public final int AS_CHARACTERS_2;
	public final int AS_CHARACTERS_3;
	public final int AS_CHARACTERS_4;

	public final int AS_VENTURES_1;
	public final int AS_VENTURES_2;
	public final int AS_VENTURES_3;
	public final int AS_VENTURES_4;

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

		NUMBER_OF_PERIODS = (int) parser.get("number_of_periods");

		AS_COUNCIL = (int) parser.get("as_council");

		AS_MARKET_COINS = (int) parser.get("as_market_coins");
		AS_MARKET_SERVANT = (int) parser.get("as_market_servant");
		AS_MARKET_MILITARY_COINS = (int) parser.get
				("as_market_military_coins");
		AS_MARKET_COUNCILS_FAVORS = (int) parser.get(
				"as_market_councils_favors");

		AS_HARVEST = (int) parser.get("as_harvest");
		AS_PRODUCTION = (int) parser.get("as_production");

		AS_TERRITORIES_1 = (int) parser.get("as_territories_1");
		AS_TERRITORIES_2 = (int) parser.get("as_territories_2");
		AS_TERRITORIES_3 = (int) parser.get("as_territories_3");
		AS_TERRITORIES_4 = (int) parser.get("as_territories_4");

		AS_BUILDINGS_1 = (int) parser.get("as_buildings_1");
		AS_BUILDINGS_2 = (int) parser.get("as_buildings_2");
		AS_BUILDINGS_3 = (int) parser.get("as_buildings_3");
		AS_BUILDINGS_4 = (int) parser.get("as_buildings_4");

		AS_CHARACTERS_1 = (int) parser.get("as_characters_1");
		AS_CHARACTERS_2 = (int) parser.get("as_characters_2");
		AS_CHARACTERS_3 = (int) parser.get("as_characters_3");
		AS_CHARACTERS_4 = (int) parser.get("as_characters_4");

		AS_VENTURES_1 = (int) parser.get("as_ventures_1");
		AS_VENTURES_2 = (int) parser.get("as_ventures_2");
		AS_VENTURES_3 = (int) parser.get("as_ventures_3");
		AS_VENTURES_4 = (int) parser.get("as_ventures_4");
	}

	public static Commons getInstance() {
		if (threadInstance == null) {
			throw new IllegalStateException(
					"There has to be an instance of Commons");
		} else {
			return threadInstance.get();
		}
	}
}

