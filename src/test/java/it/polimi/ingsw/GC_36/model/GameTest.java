package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.view.ViewCLI;
import it.polimi.ingsw.GC_36.observers.GameObserver;
import it.polimi.ingsw.GC_36.server.ParticipantRMI;
import it.polimi.ingsw.GC_36.utils.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {
	Game game;

	@Before
	public void setUp() throws Exception {
		game = new Game();

		Map<PlayerColor, Player> players = new HashMap<>();

		Player player1 = new Player(PlayerColor.BLUE,
				new ParticipantRMI(new User(new ViewCLI())));
		Player player2 = new Player(PlayerColor.BLUE,
				new ParticipantRMI(new User(new ViewCLI())));

		players.put(PlayerColor.BLUE, player1);
		players.put(PlayerColor.BLUE, player2);

		game.setPlayers(players);
		player1.init(new PersonalBoard(1));
		player2.init(new PersonalBoard(2));

		game.subscribe(player1.getParticipant());
		game.subscribe(player2.getParticipant());
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void getInstance() throws Exception {
		assertEquals("game retrieved in wrong way", game, Game.getInstance());
	}

	@Test
	public void getBoard() throws Exception {
		Field field = game.getClass().getDeclaredField("board");
		field.setAccessible(true);
		assertEquals("board retrieved in wrong way", field.get(game),
				game.getBoard());
	}

	@Test
	public void getCurrentPeriod() throws Exception {
		final Field field = game.getClass().getDeclaredField("currentPeriod");
		field.setAccessible(true);
		assertEquals("currentPeriod retrieved in wrong way", field.get(game),
				game.getCurrentPeriod());
	}

	@Test
	public void getState() throws Exception {
		final Field field = game.getClass().getDeclaredField("currentState");
		field.setAccessible(true);
		assertEquals("Game state is retrieved in the wrong way",
				field.get(game), game.getState());

		game.start();
		// now the gameState should have changed (but we don't have to test if
		// it has changed, it is just to do a second check)

		assertEquals("Game state is retrieved in the wrong way",
				field.get(game), game.getState());
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
		// check if subscribe actually add observers to the set of observers
		// The check is done with two observers, just to be sure

		GameObserver o1 = new GameObserver() {
			@Override
			public void update(GameState newState) {}

			@Override
			public void update(int periodNumber) {}

			@Override
			public void update(
					List<Pair<PlayerIdentifier, Integer>> winningOrderList) {

			}
		};
		GameObserver o2 = new GameObserver() {
			@Override
			public void update(GameState newState) {}

			@Override
			public void update(int periodNumber) {}

			@Override
			public void update(
					List<Pair<PlayerIdentifier, Integer>> winningOrderList) {

			}
		};

		game.subscribe(o1);
		game.subscribe(o2);

		final Field fieldList = game.getClass().getDeclaredField("observers");
		fieldList.setAccessible(true);

		@SuppressWarnings("unchecked")
		Set<GameObserver> set = (Set<GameObserver>) fieldList.get(game);
		assertTrue(set.contains(o1) && set.contains(o2));
	}
}