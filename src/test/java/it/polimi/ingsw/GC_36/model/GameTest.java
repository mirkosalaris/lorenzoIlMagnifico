package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.observers.GameObserver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {
	Game game;

	@Before
	public void setUp() throws Exception {
		List<Player> players = new ArrayList<>();

		players.add(new Player());
		players.add(new Player());

		game = new Game(players);
	}

	@After
	public void tearDown() throws Exception {
		Field field = game.getClass().getDeclaredField("threadInstance");
		field.setAccessible(true);
		field.set(game, null);
	}

	@Test
	public void getInstance() throws Exception {
		assertEquals(game, Game.getInstance());
	}

	@Test
	public void getBoard() throws Exception {
		final Field field = game.getClass().getDeclaredField("board");
		field.setAccessible(true);
		assertEquals("board retrieved in wrong way", field.get(game),
				game.getBoard());
	}

	@Test
	public void start() throws Exception {
		// the only thing you can test is that after start the field state
		// should be set to GameState.PLAYING
		game.start();
		final Field field = game.getClass().getDeclaredField("currentState");
		field.setAccessible(true);
		assertEquals("Game state is wrong after finishing running",
				field.get(game), GameState.PLAYING);
	}

	@Test
	public void subscribe() throws Exception {
		GameObserver o1 = new GameObserver() {
			@Override
			public void update(GameState newState) {}

			@Override
			public void update(Period newPeriod) {}
		};
		GameObserver o2 = new GameObserver() {
			@Override
			public void update(GameState newState) {}

			@Override
			public void update(Period period) {}
		};

		game.subscribe(o1);
		game.subscribe(o2);

		final Field fieldList = game.getClass().getDeclaredField("observers");
		fieldList.setAccessible(true);

		Set<GameObserver> set = (Set<GameObserver>) fieldList.get(game);
		assertTrue(set.contains(o1) && set.contains(o2));
	}

	@Test
	public void newStateNotifyCalled() throws Exception {
		// check if newStateNotify is being called correctly

		// at the end both cells have to be true
		Boolean obValue[] = new Boolean[2];

		GameObserver o1 = new GameObserver() {
			@Override
			public void update(GameState newState) {obValue[0] = true;}

			@Override
			public void update(Period newPeriod) {}
		};
		GameObserver o2 = new GameObserver() {
			@Override
			public void update(GameState newState) {obValue[1] = true;}

			@Override
			public void update(Period newPeriod) {}
		};

		game.subscribe(o1);
		game.subscribe(o2);

		Method method = game.getClass().getDeclaredMethod("setCurrentState",
				GameState.class);
		method.setAccessible(true);

		method.invoke(game, GameState.FINISHED);

		assertTrue(obValue[0]);
		assertTrue(obValue[1]);
	}

	@Test
	public void newStateNotifyCalledJustOnce() throws Exception {
		// check if the notify is called just once per each observer

		// can't use a simple counter because it must be final. So our counter
		// is a List and his size is the counter value
		List<Integer> updatedCounter = new ArrayList<>();

		GameObserver o1 = new GameObserver() {
			@Override
			public void update(GameState newState) {
				updatedCounter.add(0);
			}

			@Override
			public void update(Period newPeriod) {}

		};

		game.subscribe(o1);
		game.subscribe(o1);
		game.subscribe(o1);

		Method method = game.getClass().getDeclaredMethod("setCurrentState",
				GameState.class);
		method.setAccessible(true);

		method.invoke(game, GameState.FINISHED);

		assertTrue(updatedCounter.size() == 1);
	}

	@Test
	public void newPeriodNotifyCalled() throws Exception {
		// check if newPeriodNotify is being called correctly

		// at the end both cells have to be true
		Boolean obValue[] = new Boolean[2];

		GameObserver o1 = new GameObserver() {
			@Override
			public void update(GameState newState) {}

			@Override
			public void update(Period newPeriod) {obValue[0] = true;}
		};
		GameObserver o2 = new GameObserver() {
			@Override
			public void update(GameState newState) {}

			@Override
			public void update(Period newPeriod) {obValue[1] = true;}
		};

		game.subscribe(o1);
		game.subscribe(o2);

		game.newPeriod(1);

		assertTrue(obValue[0]);
		assertTrue(obValue[1]);
	}

	@Test
	public void newPeriodNotifyCalledJustOnce() throws Exception {
		// check if the notify is called just once per each observer

		// can't use a simple counter because it must be final. So our counter
		// is a List and his size is the counter value
		List<Integer> updatedCounter = new ArrayList<>();

		GameObserver o1 = new GameObserver() {
			@Override
			public void update(GameState newState) {}

			@Override
			public void update(Period newPeriod) {
				updatedCounter.add(0);
			}

		};

		game.subscribe(o1);
		game.subscribe(o1);
		game.subscribe(o1);

		game.newPeriod(1);

		assertTrue(updatedCounter.size() == 1);
	}

}