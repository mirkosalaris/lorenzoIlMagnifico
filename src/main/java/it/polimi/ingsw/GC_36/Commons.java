package it.polimi.ingsw.GC_36;

import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.parsers.Parser;

import java.io.File;
import java.util.EnumMap;
import java.util.Map;

public final class Commons {
	private static final String COMMONS_FILE = "commons.json";
	public static final String HOST = "localhost";
	public static final int PORT = 7777;
	public static final int RMI_PORT = 7070;

	public static final int MAX_PLAYERS = 4;
	public static final int MIN_PLAYERS = 2;
	public static final int ROUNDS_IN_PERIOD = 2;
	public static final int NUMBER_OF_PERIODS = 3;
	public static final int NUMBER_OF_FLOORS = 4;

	// TODO, increase the timer.
	public static final long STARTING_MATCH_TIMER = 3000;

	//TODO:compilare councilPrivilegeMap tramite parser
	public static Map<Integer, ResourcesList> councilPrivilegeMap;

	private static final Parser parser = new Parser(new File(COMMONS_FILE));

	public static ResourcesList getTax() {
		return (ResourcesList) parser.get("tax");
	}

	public ResourcesList getResourcesList(Integer integer) {
		return councilPrivilegeMap.get(integer);
	}

	private Commons() {}

	public static Map<DieColor, Die> diceInitializer() {
		Map<DieColor, Die> dice = new EnumMap<>(DieColor.class);
		dice.put(DieColor.BLACK, new Die(DieColor.BLACK));
		dice.put(DieColor.ORANGE, new Die(DieColor.ORANGE));
		dice.put(DieColor.WHITE, new Die(DieColor.WHITE));
		return dice;
	}

	public static ResourcesList getInitialResources(int ordinal) {
		return (ResourcesList) parser.get("personalBoard" + ordinal);
	}


	public static Map<CardType, Deck> getDeckSet(int period) {
		@SuppressWarnings("unchecked")
		Map<CardType, Deck> map = (Map<CardType, Deck>) parser.get(
				"deckSet" + period);
		return map;
	}

	public static ActionSpaceIds getAssociatedActionSpaceIds(CardType cardType,
	                                                         int floorNumber) {
		if (floorNumber < 1 || floorNumber > 4) {
			throw new IllegalStateException(
					"floorNumber has to be between 1 and 4");
		}

		int offset;
		switch (cardType) {
			case TERRITORY:
				offset = 0;
				break;
			case BUILDING:
				offset = 4;
				break;
			case CHARACTER:
				offset = 8;
				break;
			case VENTURE:
				offset = 12;
				break;
			default:
				throw new IllegalArgumentException("unknown Tower type");
		}

		return ActionSpaceIds.values()[offset + floorNumber];
	}

	public static boolean isFloor(ActionSpaceIds actionSpaceId) {
		return actionSpaceId.value() >= 1 && actionSpaceId.value() <= 16;
	}

	public static int getASRequiredActionValue(ActionSpaceIds actionSpaceId) {
		int value = (int) parser.get("actionSpace" + actionSpaceId.value(),
				"requiredActionValue");
		return value;
	}

	public static ResourcesList getASBonus(ActionSpaceIds actionSpaceId) {
		return (ResourcesList) parser.get("actionSpace" + actionSpaceId
				.value(), "bonus");
	}

	public static DieColor memberColorToDieColor(MemberColor color) {
		Map<MemberColor, DieColor> map = new EnumMap<>(MemberColor.class);
		map.put(MemberColor.ORANGE, DieColor.ORANGE);
		map.put(MemberColor.BLACK, DieColor.BLACK);
		map.put(MemberColor.WHITE, DieColor.WHITE);

		return map.get(color);
	}

	public static boolean getASIsSingle(ActionSpaceIds actionSpaceIds) {
		return (boolean) parser.get("actionSpace" + actionSpaceIds.value(),
				"isSingle");
	}

	public static ResourcesList getPrivilege(int id) {
		return (ResourcesList) parser.get("councilPrivilege" + id);
	}

	public static BonusTile getBonusTile(int ordinal) {
		return (BonusTile) parser.get("bonusTile" + ordinal);
	}
}

