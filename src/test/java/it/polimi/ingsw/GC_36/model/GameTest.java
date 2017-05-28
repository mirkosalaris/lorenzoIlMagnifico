package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Observer;
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
	public void run() throws Exception {
		// the only thing you can test is that after run the field state
		// should be set to GameState.FINISHED
		game.run();
		final Field field = game.getClass().getDeclaredField("currentState");
		field.setAccessible(true);
		assertEquals("Game state is wrong after finishing running",
				field.get(game), GameState.FINISHED);
	}

	@Test
	public void subscribe() throws Exception {
		Observer o1 = () -> {
		};
		Observer o2 = () -> {
		};

		game.subscribe(o1);
		game.subscribe(o2);

		final Field fieldList = game.getClass().getDeclaredField("observers");
		fieldList.setAccessible(true);

		Set<Observer> set = (Set<Observer>) fieldList.get(game);
		assertTrue(set.contains(o1) && set.contains(o2));
	}

	@Test
	public void changeNotifyCalled() throws Exception {
		Boolean obValue[] = new Boolean[2];

		Observer o1 = new Observer() {
			@Override
			public void update() {
				obValue[0] = true;
			}
		};
		Observer o2 = new Observer() {
			@Override
			public void update() {
				obValue[1] = true;
			}
		};

		game.subscribe(o1);
		game.subscribe(o2);

		Method method = game.getClass().getDeclaredMethod("setCurrentState",
				GameState.class);
		method.setAccessible(true);

		method.invoke(game, GameState.FINISHED);

		assertTrue(obValue[0] && obValue[1]);
	}

	@Test
	public void changeNotifyCalledJustOnce() throws Exception {
		// can't use a simple counter because it must be final. So our counter
		// is a List and his size is the counter value
		List<Integer> updatedCounter = new ArrayList<>();

		Observer o1 = new Observer() {
			@Override
			public void update() {
				updatedCounter.add(0);
			}
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

}