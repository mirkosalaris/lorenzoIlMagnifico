package it.polimi.ingsw.GC_36.controller;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.exception.PlayingException;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.server.Participant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class RoundController {

	private final ActionExecutor executor;
	private final long maxActionTime;
	private Action action;
	private final Object playingLock = new Object();
	private final AtomicBoolean interrupted = new AtomicBoolean(false);
	private Exception error = null;
	private GameMode mode;

	public RoundController(GameMode mode) {
		executor = new ActionExecutor();
		this.mode = mode;
		this.maxActionTime = Commons.getActionMaxTime();
	}

	public void execute(Player player) throws PlayingException {
		Thread playingThread;
		TimerTask timerTask = getNewTimerTask();

		try {
			// activate the timer
			Timer timer = new Timer();
			timer.schedule(timerTask, maxActionTime);

			// start playing
			boolean executed = false;
			do {
				playingThread = new Thread(new TurnExecutor(player, mode));
				playingThread.start();

				// wait for playingThread or the timer
				synchronized (playingLock) {
					playingLock.wait();
				}

				// if the timer fired
				if (interrupted.get()) {
					System.out.println("turn timer has fired");
					playingThread.interrupt();

					setOutOfGame(player);
					executed = true;
				} else {
					timer.cancel();
					executed = executor.execute(action);

					// TODO delete
					System.out.println(action);
				}

				// TODO @mirko send 'error' to player
				// (s)he has to know if (s)he had done something wrong
			} while (!executed);
		} catch (InterruptedException | IOException e) {
			error = e;
		}

		Exception tempError = error;

		resetFields();

		// if there was an error
		if (tempError != null) {
			throw new PlayingException(
					"An error occurred during turn of player " + player,
					tempError);
		}


	}

	private void setOutOfGame(Player player) throws IOException {
		Participant p = player.getParticipant();
		Game.getInstance().getBoard().setOutOfGame(player);
		p.outOfTime();
	}

	private void resetFields() {
		action = null;
		interrupted.set(false);
		error = null;
	}

	private TimerTask getNewTimerTask() {
		return new TimerTask() {
			@Override
			public void run() {
				interrupted.set(true);
				synchronized (playingLock) {
					playingLock.notifyAll();
				}
			}
		};
	}


	/**
	 * @param player
	 * 		the player who has to choose
	 * @return the List of LeaderCards chosen by the player
	 */
	private List<LeaderCard> chooseLeaderCards(Player player)
			throws IOException, ClassNotFoundException {
		Participant p = player.getParticipant();

		// this will be the list of chosen cards
		List<LeaderCard> cardsList = new ArrayList<>();


		LeaderCard card;
		do {
			List<LeaderCard> cardsAvailable =
					copyWithout(player.getLeaderCards(), cardsList);
			card = p.useCard(cardsAvailable);

			// check if card is not null and is one of the available ones
			if (card != null && cardsAvailable.contains(card)) {
				cardsList.add(card);
			}
		} while (card != null);
		return cardsList;
	}


	/**
	 * Return a copy of the first list without items in the second list
	 *
	 * @param list
	 * 		original List, to modify
	 * @param toRemove
	 * 		List of item to remove
	 * @return a List containing elements of 'list' but not in 'toRemove'
	 */
	private <E> List<E> copyWithout(List<E> list, List<E> toRemove) {
		List<E> result = new ArrayList<E>(list);
		result.removeAll(toRemove);

		return result;
	}

	private class TurnExecutor implements Runnable {
		private Player player;
		private GameMode mode;

		public TurnExecutor(Player player, GameMode mode) {
			this.player = player;
			this.mode = mode;
		}

		@Override
		public void run() {
			Participant p = player.getParticipant();
			List<LeaderCard> cards = new ArrayList<>();
			try {
				// if mode is advanced
				if (GameMode.ADVANCED.equals(mode)) {
					// leader card
					cards = chooseLeaderCards(player);
				}

				action = new Action();
				action.setLeaderCards(cards);
				p.play(action);

				synchronized (playingLock) {
					playingLock.notifyAll();
				}
			} catch (IOException | ClassNotFoundException e) {
				error = e;
			}
		}
	}

}
