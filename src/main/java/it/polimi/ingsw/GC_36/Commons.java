package it.polimi.ingsw.GC_36;

import it.polimi.ingsw.GC_36.parsers.Parser;

import java.io.File;

public final class Commons {
	private static ThreadLocal<Commons> threadInstance;

	public static final int NUMBER_OF_PERIODS = 1;

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
}

