package it.polimi.ingsw.GC_36.controller;

import it.polimi.ingsw.GC_36.exception.PlayingException;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.observers.NewPeriodObserver;
import it.polimi.ingsw.GC_36.observers.NewRoundObserver;
import it.polimi.ingsw.GC_36.server.Participant;
import it.polimi.ingsw.GC_36.utils.ExceptionLogger;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class GameExecutor
		implements Runnable, NewPeriodObserver, NewRoundObserver {
	private Game game;
	private Map<PlayerColor, Player> players;
	private Set<Participant> users;
	private RoundController roundController = new RoundController();

	public GameExecutor(Set<Participant> usersSet) throws IOException {
		this.users = usersSet;

		Participant[] usersArray = new Participant[usersSet.size() - 1];
		usersArray = usersSet.toArray(usersArray);

		players = new EnumMap<>(PlayerColor.class);
		for (int i = 0; i < usersArray.length; i++) {
			Participant u = usersArray[i];

			PlayerColor color = PlayerColor.values()[i];
			Player p = new Player(color, u);
			players.put(color, p);
		}
	}

	@Override
	public void run() {
		try {
			game = Game.getInstance();

			game.setPlayers(players);
			for (Participant u : users) {
				game.subscribe(u);
			}
			game.subscribe(this);

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
				ClassNotFoundException e) {
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
		for (Participant user : users) {
			newRound.subscribe(user);
		}
		newRound.setController(roundController);
	}

	@Override
	public void update(Period newPeriod) {
		for (Participant user : users) {
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
