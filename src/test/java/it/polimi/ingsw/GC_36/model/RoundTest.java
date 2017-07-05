package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.view.ViewCLI;
import it.polimi.ingsw.GC_36.controller.RoundController;
import it.polimi.ingsw.GC_36.observers.RoundObserver;
import it.polimi.ingsw.GC_36.server.ParticipantRMI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class RoundTest {
	Round round;
	Game game;
	Player player;

	@Before
	public void setUp() throws Exception {
		game = new Game();

		Map<PlayerColor, Player> players = new HashMap<>();

		// DO NOT CHANGE, let 'players' have only one player for the right
		// execution of the test "advanceHasPlayer"
		player = new Player(PlayerColor.BLUE,
				new ParticipantRMI(new User(new ViewCLI())));
		players.put(PlayerColor.BLUE, player);

		game.setPlayers(players);
		player.init(new PersonalBoard(1));
		game.subscribe(player.getParticipant());

		int currentPeriod = 1;
		DeckSet deckSet = new DeckSet(currentPeriod);
		round = new Round(deckSet);

		RoundController controller = new RoundController() {
			@Override
			public void execute(Player player) {
				// do nothing
			}
		};
		round.setController(controller);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void advanceStToPl() throws Exception {
		//check the transition from STARTING to PLAYING
		round.advance();
		final Field field = round.getClass().getDeclaredField("currentState");
		field.setAccessible(true);
		assertEquals(
				"advance doesn't change currentState from STARTING to " +
						"PLAYING",
				RoundState.PLAYING,
				field.get(round));
	}

	//check if advance sets properly the next player and the transition from
	// PLAYING to FINISHED of the currentState
	@Test
	public void advance() throws Exception {
		round.advance();
		final Field field = round.getClass().getDeclaredField("currentPlayer");
		field.setAccessible(true);
		assertEquals(
				"advance doesn't set properly current player to the next " +
						"player",
				player,
				field.get(round));
		round.advance();
		/*final Field field2 = round.getClass().getDeclaredField
		("currentState");
		field2.setAccessible(true);
		assertEquals(
				"advance doesn't change currentState from PLAYING to " +
						"FINISHED",
				RoundState.FINISHED,
				field2.get(round));*/
	}

	@Test(expected = Round.RoundTerminatedException.class)
	public void advanceException() throws Exception {
		Field field = round.getClass().getDeclaredField("currentState");
		field.setAccessible(true);
		field.set(round, RoundState.FINALIZING);
		round.advance();
	}


	@Test
	public void getCurrentPlayer() throws Exception {
		final Field field = round.getClass().getDeclaredField("currentPlayer");
		field.setAccessible(true);
		assertEquals("player retrieved in wrong way", field.get(round),
				round.getCurrentPlayer());
	}

	@Test
	public void subscribe() throws Exception {
		RoundObserver o1 = new RoundObserver() {
			@Override
			public void update(RoundState newState) {

			}

			@Override
			public void update(PlayerIdentifier newPlayer) {

			}
		};
		RoundObserver o2 = new RoundObserver() {
			@Override
			public void update(RoundState newState) {

			}

			@Override
			public void update(PlayerIdentifier newPlayer) {

			}
		};
		round.subscribe(o1);
		round.subscribe(o2);

		final Field fieldList = round.getClass().getDeclaredField("observers");
		fieldList.setAccessible(true);

		@SuppressWarnings("unchecked")
		Set<RoundObserver> set = (Set<RoundObserver>) fieldList.get(round);
		assertTrue(set.contains(o1) && set.contains(o2));
	}


}