package it.polimi.ingsw.GC_36;

import java.io.File;

public final class Common {
	private static ThreadLocal<Common> threadInstance;

	public final int NUMBER_OF_PERIODS;

	// AS stands for ActionSpace
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

	public Common(File file) {
		Common instance = this;
		threadInstance = new ThreadLocal<Common> () {
			@Override
			protected Common initialValue() {
				return instance;
			}
		};

		Parser parser = new Parser(file);

		NUMBER_OF_PERIODS = parser.get("number_of_periods");

		AS_COUNCIL = parser.get("as_council");

		AS_MARKET_COINS = parser.get("as_market_coins");
		AS_MARKET_SERVANT = parser.get("as_market_servant");
		AS_MARKET_MILITARY_COINS = parser.get("as_market_military_coins");
		AS_MARKET_COUNCILS_FAVORS = parser.get("as_market_councils_favors");

		AS_HARVEST = parser.get("as_harvest");
		AS_PRODUCTION = parser.get("as_production");

		AS_TERRITORIES_1 = parser.get("as_territories_1");
		AS_TERRITORIES_2 = parser.get("as_territories_2");
		AS_TERRITORIES_3 = parser.get("as_territories_3");
		AS_TERRITORIES_4 = parser.get("as_territories_4");

		AS_BUILDINGS_1 = parser.get("as_buildings_1");
		AS_BUILDINGS_2 = parser.get("as_buildings_2");
		AS_BUILDINGS_3 = parser.get("as_buildings_3");
		AS_BUILDINGS_4 = parser.get("as_buildings_4");

		AS_CHARACTERS_1 = parser.get("as_characters_1");
		AS_CHARACTERS_2 = parser.get("as_characters_2");
		AS_CHARACTERS_3 = parser.get("as_characters_3");
		AS_CHARACTERS_4 = parser.get("as_characters_4");

		AS_VENTURES_1 = parser.get("as_ventures_1");
		AS_VENTURES_2 = parser.get("as_ventures_2");
		AS_VENTURES_3 = parser.get("as_ventures_3");
		AS_VENTURES_4 = parser.get("as_ventures_4");
	}

	public static Common getInstance() {
		if (threadInstance == null) {
			throw new IllegalStateException(
					"There has to be an instance of Common");
		} else {
			return threadInstance.get();
		}
	}
}

