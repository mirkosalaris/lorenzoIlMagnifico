package it.polimi.ingsw.GC_36.controller;

import it.polimi.ingsw.GC_36.exception.PlayingException;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.server.Participant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoundController {

	private final ActionExecutor executor;

	public RoundController() {
		executor = new ActionExecutor();
	}

	public void execute(Player player) throws PlayingException {

		Participant p = player.getParticipant();
		List<LeaderCard> cards = new ArrayList<>();
		Action action = null;

		try {
			if (GameMode.ADVANCED.equals(Game.getInstance().GAME_MODE)) {
				// leader card
				cards = chooseLeaderCards(player);
			}

			boolean executed = false;

			do {
				action = new Action();
				action.setLeaderCards(cards);
				p.play(action);
				executed = executor.execute(action);
				// TODO @mirko send 'error' to player
				// (s)he has to know if (s)he had done something wrong
			} while (!executed);

		} catch (IOException | ClassNotFoundException e) {
			throw new PlayingException(
					"An error occurred during turn of player " + player, e);
		}

		// TODO delete
		System.out.println(action);
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
	 * @param <E>
	 * @return a List containing elements of 'list' but not in 'toRemove'
	 */
	private <E> List<E> copyWithout(List<E> list, List<E> toRemove) {
		List<E> result = new ArrayList<E>(list);
		result.removeAll(toRemove);

		return result;
	}

}
