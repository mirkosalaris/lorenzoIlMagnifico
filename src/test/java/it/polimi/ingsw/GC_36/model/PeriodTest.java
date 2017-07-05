package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.view.ViewCLI;
import it.polimi.ingsw.GC_36.controller.RoundController;
import it.polimi.ingsw.GC_36.observers.NewRoundObserver;
import it.polimi.ingsw.GC_36.observers.PeriodObserver;
import it.polimi.ingsw.GC_36.server.ParticipantRMI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class PeriodTest {
	Period period;
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

		DeckSet deckSet = new DeckSet(1);
		period = new Period(1, deckSet);


		RoundController controller = new RoundController() {
			@Override
			public void execute(Player player) {
				// do nothing
			}
		};
		NewRoundObserver nro = new NewRoundObserver() {
			@Override
			public void update(Round newRound) {
				newRound.setController(controller);
			}
		};

		period.subscribe(nro);
	}

	@After
	public void tearDown() throws Exception {

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
			public void terminatedRound() {}
		};
		PeriodObserver o2 = new PeriodObserver() {
			@Override
			public void terminatedRound() {}
		};

		period.subscribe(o1);
		period.subscribe(o2);

		Field fieldList = period.getClass().getDeclaredField("observers");
		fieldList.setAccessible(true);

		@SuppressWarnings("unchecked")
		Set<PeriodObserver> set = (Set<PeriodObserver>) fieldList.get(period);
		assertTrue(set.contains(o1) && set.contains(o2));
	}
}