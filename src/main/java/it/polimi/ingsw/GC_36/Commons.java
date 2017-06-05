package it.polimi.ingsw.GC_36;

import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.parsers.Parser;

import java.io.File;
import java.util.EnumMap;
import java.util.Map;

public final class Commons {
	public static final String HOST = "localhost";
	public static final int PORT = 7777;
	public static final int RMI_PORT = 7070;

	// TODO: change value. Now both at 1 just for testing
	public static final int MAX_PLAYERS = 1;
	public static final int MIN_PLAYERS = 1;

	private final Parser parser;
	private static ThreadLocal<Commons> threadInstance;
	public static Map<Integer, ResourcesList> councilPrivilegeMap;

	//TODO:compilare councilPrivilegeMap tramite parser

	public ResourcesList getResourcesList(Integer integer) {
		return councilPrivilegeMap.get(integer);
	}

	public static final int NUMBER_OF_PERIODS = 3;
	public static final int NUMBER_OF_FLOORS = 4;

	private Commons(File file) {
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

	public static Commons getInstance(File file) {
		if (threadInstance == null) {
			threadInstance = new ThreadLocal<Commons>() {
				@Override
				protected Commons initialValue() {
					return new Commons(file);
				}
			};
		}
		return threadInstance.get();

	}

	public static Commons getInstance() {
		if (threadInstance == null) {
			throw new IllegalStateException(
					"There are no instances of Commons to use");
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

	public static Map<ActionSpaceIds, ActionSpace> actionSpacesInitializer() {
		Map<ActionSpaceIds, ActionSpace> map = new EnumMap<>(
				ActionSpaceIds.class);

		for (ActionSpaceIds id : ActionSpaceIds.values()) {
			map.put(id, new ActionSpace(id));
		}

		return map;
	}

	public ResourcesList getInitialResources(int ordinal) {
		return (ResourcesList) parser.get("personalBoard" + ordinal);
	}

	public Map<CardType, Deck> getDeckSet(int period) {
		return (Map<CardType, Deck>) parser.get("deckSet" + period);
	}

	public Floor getAssociatedFloor(ActionSpaceIds actionSpaceId) {

		if (actionSpaceId.value() == 0 || actionSpaceId.value() > 16) {
			return null;
		} else if (actionSpaceId.value() <= 4) {
			return Tower.TERRITORIES.getFloor(actionSpaceId.value());
		} else if (actionSpaceId.value() <= 8) {
			return Tower.BUILDINGS.getFloor(actionSpaceId.value() - 4);
		} else if (actionSpaceId.value() <= 12) {
			return Tower.CHARACTERS.getFloor(actionSpaceId.value() - 8);
		} else {
			return Tower.VENTURERS.getFloor(actionSpaceId.value() - 12);
		}
	}

	public int getRequiredActionValue(ActionSpaceIds actionSpaceId) {

		// TODO delete this line and uncomment next line when parser is impl
		return 0;

		// return (int) parser.get("actionSpace" + actionSpaceId,
		// "requiredActionValue");
	}

	public ResourcesList getResources(ActionSpaceIds actionSpaceId) {

		return (ResourcesList) parser.get("actionSpace" + actionSpaceId,
				"resources");
	}

	public static DieColor memberColorToDieColor(MemberColor color) {
		Map<MemberColor, DieColor> map = new EnumMap<>(MemberColor.class);
		map.put(MemberColor.ORANGE, DieColor.ORANGE);
		map.put(MemberColor.BLACK, DieColor.BLACK);
		map.put(MemberColor.WHITE, DieColor.WHITE);

		return map.get(color);
	}


}

