package it.polimi.ingsw.GC_36.model;

public enum ActionSpaceIds {
	AS_COUNCIL(0),

	AS_TERRITORIES_1(1),
	AS_TERRITORIES_2(2),
	AS_TERRITORIES_3(3),
	AS_TERRITORIES_4(4),

	AS_BUILDINGS_1(1),
	AS_BUILDINGS_2(2),
	AS_BUILDINGS_3(3),
	AS_BUILDINGS_4(4),

	AS_CHARACTERS_1(1),
	AS_CHARACTERS_2(2),
	AS_CHARACTERS_3(3),
	AS_CHARACTERS_4(4),

	AS_VENTURES_1(1),
	AS_VENTURES_2(2),
	AS_VENTURES_3(3),
	AS_VENTURES_4(4),

	AS_HARVEST(17),
	AS_HARVEST_BIG(18),

	AS_PRODUCTION(19),
	AS_PRODUCTION_BIG(20),

	AS_MARKET_COINS(21),
	AS_MARKET_SERVANT(22),
	AS_MARKET_MILITARY_COINS(23),
	AS_MARKET_COUNCILS_FAVORS(24);


	private int value;

	static int maxValue;
	static int minValue = 0;
	private boolean first = true;

	ActionSpaceIds(int value) {
		this.value = value;

		setMin(value);
		setMax(value);

		first = false;
	}

	public int value() {
		return value;
	}

	public static boolean checkId(ActionSpaceIds id) {
		return (id.value >= maxValue || id.value <= minValue);
	}

	private void setMin(int value) {
		if (first || value < minValue) {
			minValue = value;
		}
	}

	private void setMax(int value) {
		if (first || value < maxValue) {
			minValue = value;
		}
	}
}
