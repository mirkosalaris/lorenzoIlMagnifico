package it.polimi.ingsw.GC_36.controller;

import it.polimi.ingsw.GC_36.exception.PlayingException;
import it.polimi.ingsw.GC_36.exception.ScoringException;
import it.polimi.ingsw.GC_36.exception.SetupException;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.observers.NewPeriodObserver;
import it.polimi.ingsw.GC_36.observers.NewRoundObserver;
import it.polimi.ingsw.GC_36.server.Participant;
import it.polimi.ingsw.GC_36.utils.ExceptionLogger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class GameExecutor
		implements Runnable, NewPeriodObserver, NewRoundObserver {
	private Game game;
	private GameMode mode;
	private Map<PlayerColor, Player> players;
	private Set<Participant> participants;
	private RoundController roundController = new RoundController();

	public GameExecutor(Set<Participant> usersSet) throws IOException {
		this(usersSet, GameMode.STANDARD);
	}

	public GameExecutor(Set<Participant> participants, GameMode mode)
			throws IOException {
		this.participants = participants;
		this.mode = mode;

		Participant[] participantsArray =
				new Participant[participants.size() - 1];
		participantsArray = participants.toArray(participantsArray);

		players = new EnumMap<>(PlayerColor.class);
		for (int i = 0; i < participantsArray.length; i++) {
			Participant p = participantsArray[i];

			PlayerColor color = PlayerColor.values()[i];
			Player player = new Player(color, p);
			players.put(color, player);
		}
	}

	@Override
	public void run() {
		try {
			game = new Game(mode);

			game.setPlayers(players);
			game.subscribe(this);

			GameSetupper setupper = new GameSetupper(
					new ArrayList<>(players.values()), mode);
			setupper.setup();

			game.start();

			while (game.getState().equals(GameState.PLAYING)) {
				game.advance();
			}

			if (game.getState().equals(GameState.SCORING)) {
				game.finalScoring();
			} else {
				throw new IllegalStateException(
						"Game should be scoring right now");
			}

		} catch (IllegalStateException | IOException | PlayingException |
				ClassNotFoundException | SetupException | ScoringException e) {
			ExceptionLogger.log(e);
			try {
				closeAll(e.getMessage());
			} catch (IOException e1) {
				ExceptionLogger.log(e1);
			}

			// kill thread
			return;
		}
	}


	@Override
	public void update(Round newRound) {
		for (Participant user : participants) {
			newRound.subscribe(user);
		}
		newRound.setController(roundController);
	}

	@Override
	public void update(Period newPeriod) {
		for (Participant user : participants) {
			newPeriod.subscribe(user);
			newPeriod.subscribe(this);
		}
	}

	private void closeAll() throws IOException {
		closeAll(null);
	}

	private void closeAll(String message) throws IOException {
		for (Player p : players.values()) {
			p.getParticipant().exit(message);
		}
	}
}
