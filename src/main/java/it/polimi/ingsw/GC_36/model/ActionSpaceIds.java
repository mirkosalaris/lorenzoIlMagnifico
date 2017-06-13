package it.polimi.ingsw.GC_36.model;

public enum ActionSpaceIds {
	AS_COUNCIL(0),

	AS_TERRITORIES_1(1, CardType.TERRITORY, 1),
	AS_TERRITORIES_2(2, CardType.TERRITORY, 2),
	AS_TERRITORIES_3(3, CardType.TERRITORY, 3),
	AS_TERRITORIES_4(4, CardType.TERRITORY, 4),

	AS_BUILDINGS_1(5, CardType.BUILDING, 1),
	AS_BUILDINGS_2(6, CardType.BUILDING, 2),
	AS_BUILDINGS_3(7, CardType.BUILDING, 3),
	AS_BUILDINGS_4(8, CardType.BUILDING, 4),

	AS_CHARACTERS_1(9, CardType.CHARACTER, 1),
	AS_CHARACTERS_2(10, CardType.CHARACTER, 2),
	AS_CHARACTERS_3(11, CardType.CHARACTER, 3),
	AS_CHARACTERS_4(12, CardType.CHARACTER, 4),

	AS_VENTURES_1(13, CardType.VENTURE, 1),
	AS_VENTURES_2(14, CardType.VENTURE, 2),
	AS_VENTURES_3(15, CardType.VENTURE, 3),
	AS_VENTURES_4(16, CardType.VENTURE, 4),

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
	static int minValue;
	private static boolean first = true;
	private CardType type;
	private int floorNumber;

	ActionSpaceIds(int value) {
		this.value = value;

		setMinAndMax(value);
	}

	ActionSpaceIds(int value, CardType type, int floorNumber) {
		this.value = value;
		this.type = type;
		this.floorNumber = floorNumber;

		setMinAndMax(value);
	}

	public int value() {
		return value;
	}

	public static boolean checkId(int id) {
		return (id <= maxValue && id >= minValue);
	}

	public boolean isInFloor() {
		return type != null;
	}

	public CardType getCardType() {
		return type;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	private static void setMin(int value) {
		if (first || value < minValue) {
			minValue = value;
		}
	}

	private static void setMax(int value) {
		if (first || value > maxValue) {
			maxValue = value;
		}
	}

	private static void setMinAndMax(int value) {
		// method needed to assign 'first': it is illegal to access static
		// member directly from constructor

		setMin(value);
		setMax(value);
		first = false;
	}
}
