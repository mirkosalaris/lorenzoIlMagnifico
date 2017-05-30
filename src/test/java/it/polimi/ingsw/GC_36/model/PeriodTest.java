package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.observers.PeriodObserver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.Assert.*;

public class PeriodTest {
	Period period;
	Game game;

	@Before
	public void setUp() throws Exception {
		game = new Game();

		Map<PlayerColor, Player> players = new HashMap<>();

		players.put(PlayerColor.BLUE, new Player(PlayerColor.BLUE));
		players.put(PlayerColor.BLUE, new Player(PlayerColor.BLUE));

		game.setPlayers(players);

		DeckSet deckSet = new DeckSet(1);
		period = new Period(1, deckSet);
	}

	@After
	public void tearDown() throws Exception {
		Field field = game.getClass().getDeclaredField("threadInstance");
		field.setAccessible(true);
		field.set(game, null);
	}

	@Test
	public void advance() throws Exception {
		// check if the currentRound change for real
		Field field = period.getClass().getDeclaredField("currentRound");
		field.setAccessible(true);

		Round oldRound = (Round) field.get(period);
		period.advance();
		Round newRound = (Round) field.get(period);
		assertNotEquals(oldRound, newRound);
	}

	@Test
	public void getCurrentRound() throws Exception {
		Field field = period.getClass().getDeclaredField("currentRound");
		field.setAccessible(true);
		assertEquals(field.get(period), period.getCurrentRound());
	}

	@Test
	public void subscribe() throws Exception {
		PeriodObserver o1 = new PeriodObserver() {
			@Override
			public void update(Round newRound) {}
		};
		PeriodObserver o2 = new PeriodObserver() {
			@Override
			public void update(Round newRound) {}
		};

		period.subscribe(o1);
		period.subscribe(o2);

		Field fieldList = period.getClass().getDeclaredField("observers");
		fieldList.setAccessible(true);

		Set<PeriodObserver> set = (Set<PeriodObserver>) fieldList.get(period);
		assertTrue(set.contains(o1) && set.contains(o2));
	}

	@Test
	public void newRoundNotifyCalled() throws Exception {
		// check if newRoundNotify is being called correctly

		// at the end both cells have to be true
		Boolean obValue[] = new Boolean[2];

		PeriodObserver o1 = new PeriodObserver() {
			@Override
			public void update(Round newRound) {obValue[0] = true;}
		};
		PeriodObserver o2 = new PeriodObserver() {
			@Override
			public void update(Round newRound) {obValue[1] = true;}
		};

		period.subscribe(o1);
		period.subscribe(o2);

		Method method = period.getClass().getDeclaredMethod("newRound");
		method.setAccessible(true);
		method.invoke(period);

		assertTrue(obValue[0]);
		assertTrue(obValue[1]);
	}

	@Test
	public void newRoundNotifyCalledJustOnce() throws Exception {
		// check if the notify is called just once per each observer

		// can't use a simple counter because it must be final. So our counter
		// is a List and his size is the counter value
		List<Integer> updatedCounter = new ArrayList<>();

		PeriodObserver o1 = new PeriodObserver() {
			@Override
			public void update(Round newRound) {updatedCounter.add(0);}
		};

		period.subscribe(o1);
		period.subscribe(o1);
		period.subscribe(o1);

		Method method = period.getClass().getDeclaredMethod("newRound");
		method.setAccessible(true);

		method.invoke(period);

		assertTrue(updatedCounter.size() == 1);
	}


}