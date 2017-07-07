package it.polimi.ingsw.GC_36.controller;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.exception.SetupException;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.server.Participant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class GameSetupper {
	private List<Player> players;
	private GameMode mode;
	private List<LeaderCard> leaderCards = Commons.getLeaderCard();

	private List<LeaderCard> chosenCard = new ArrayList<>();

	GameSetupper(List<Player> players, GameMode mode) {
		this.players = players;
		this.mode = mode;
	}

	void setup() throws SetupException {
		try {
			setupPersonalBoard(players);
			setupBonusTiles(players);
			setupLeaderCards(players);
			subscribePlayersToGame(players);
		} catch (Exception e) {
			throw new SetupException("Cannot complete setup of game", e);
		}
	}

	private void setupPersonalBoard(List<Player> players) throws IOException {
		for (int i = 0; i < players.size(); i++) {
			PersonalBoard playerBoard = new PersonalBoard(i + 1);
			players.get(i).init(playerBoard);
		}
	}

	private void setupBonusTiles(List<Player> players) throws Exception {
		if (GameMode.STANDARD.equals(mode)) {
			for (Player p : players) {
				p.setBonusTile(BonusTileId.DEFAULT);
			}
		} else {
			chooseBonusTiles(players);
		}
	}

	private void chooseBonusTiles(List<Player> players) throws Exception {
		ExceptionListener exceptionListener = new ExceptionListener();
		List<Thread> threads = new ArrayList<>();

		for (Player player : players) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					Participant p = player.getParticipant();
					try {
						int choice = p.chooseBonusTile();
						BonusTileId bonusTileId = BonusTileId.values()[choice];
						player.setBonusTile(bonusTileId);
					} catch (IOException | ClassNotFoundException e) {
						exceptionListener.set(e);
					}
				}
			});
			t.start();
			threads.add(t);
		}

		// wait for all users to choose
		for (int j = 0; j <= players.size() - 1; j++) {
			threads.get(j).join();
		}

		Exception e = exceptionListener.get();
		if (e != null) {
			throw e;
		}
	}

	private void setupLeaderCards(List<Player> players)
			throws Exception {
		if (GameMode.STANDARD.equals(mode)) {
			// exit. This function has to be executed only in advanced mode
			return;
		}

		for (int i = 0; i < Commons.LEADERS_FOR_PLAYER; i++) {
			startChoosingTurn(i);
			leaderCards.removeAll(chosenCard);
			chosenCard.clear();
		}
	}

	/**
	 * Let users choose a leader card for this single choosing turn. The
	 * choices
	 * will be parallel for all users
	 *
	 * @param iteration
	 * 		the choosing turn number
	 */
	private void startChoosingTurn(int iteration) throws Exception {
		List<Thread> threads = new ArrayList<>();
		ExceptionListener exceptionListener = new ExceptionListener();
		for (int j = 0; j <= players.size() - 1; j++) {
			final Integer playerNumber = j;
			final Integer it = iteration;
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					LeaderCard card = null;

					try {
						card = choose(
								players.get(playerNumber), playerNumber, it);
					} catch (IOException | ClassNotFoundException e) {
						exceptionListener.set(e);
					}

					players.get(playerNumber).addLeaderCard(card);
				}
			});
			t.start();
			threads.add(t);
		}

		// wait for all users to choose
		for (int j = 0; j <= players.size() - 1; j++) {
			threads.get(j).join();
		}

		Exception e = exceptionListener.get();
		if (e != null) {
			throw e;
		}
	}

	private void subscribePlayersToGame(List<Player> players) {
		Game game = Game.getInstance();
		for (Player p : players) {
			game.subscribe(p.getParticipant());
		}
	}

	/**
	 * Let the player choose a leader card
	 *
	 * @param player
	 * 		the Player who have to choose
	 * @param playerNumber
	 * 		the player number in the order of player
	 * @param iteration
	 * 		the iteration of the leader cards setup process
	 */
	private LeaderCard choose(Player player, int playerNumber, int iteration)
			throws IOException, ClassNotFoundException {
		Participant p = player.getParticipant();
		int choice;
		// exit only when the choice is correct
		do {
			choice = p.chooseLeaderCard(
					new ArrayList<>(getChunk(playerNumber, iteration)));
		} while (choice < 0
				|| choice > Commons.LEADERS_FOR_PLAYER - iteration);

		LeaderCard card = getChunk(playerNumber, iteration).get(choice);
		// add to the list of chosen card for this iteration
		chosenCard.add(card);

		return card;
	}


	private List<LeaderCard> getChunk(int playerNumber, int iteration) {
		// TODO: @mirko this has to depend on the number of players

		int N = Commons.LEADERS_FOR_PLAYER;
		int i = iteration;

		/* Table of 'start' values
		 *
		 * Horizontal: iteration
		 * Vertical: playerNumber
		 *
		 *   |  0   |   1   |   2   |   3
		 * --|------|-------|-------|----
		 * 0 |  0   |   9   |   4   |   1
		 * 1 |  4   |   0   |   6   |   2
		 * 2 |  8   |   3   |   0   |   3
		 * 3 |  12  |   6   |   2   |   0
		 */
		int start = (playerNumber * (N - i) - i * (N - i)) % (4 * (N - i));
		// '%' in java is only the reminder. Be sure to not cancel next line
		if (start < 0) start += 4 * (N - i);

		int end = start + N - i;
		List<LeaderCard> chunk = leaderCards.subList(start, end);
		// new arrayList because 'subList' returns a not serializable list
		return new ArrayList<>(chunk);
	}

	private class ExceptionListener {
		Exception exception;

		private void set(Exception e) {
			exception = e;
		}

		private Exception get() {
			return exception;
		}
	}
}
