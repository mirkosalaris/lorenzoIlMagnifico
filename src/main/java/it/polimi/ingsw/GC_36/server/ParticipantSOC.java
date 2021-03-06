package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.utils.ExceptionLogger;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

public class ParticipantSOC implements Participant {
	private ObjectOutputStream objOut;
	private ObjectInputStream objIn;

	public ParticipantSOC(Pair<ObjectInputStream, ObjectOutputStream> pair)
			throws IOException {

		objOut = pair.getSecond();
		// to be sure to send the header (to avoid input hanging on client)
		objOut.flush();

		objIn = pair.getFirst();
	}

	@Override
	public void fatalError(String s) throws IOException {
		sendMessage("fatal_error", s, "Cannot send error to user");
	}

	@Override
	public void play(ActionInterface action)
			throws IOException, ClassNotFoundException {

		sendMessage("play", action, "Error in making user play");

		ActionInterface retrievedAction = (ActionInterface) readFromStream();
		action.copyFrom(retrievedAction);
	}


	@Override
	public void exit(String message) throws IOException {
		sendMessage("exit", message, "Cannot send exit message to user");
	}

	@Override
	public void setIdentifier(PlayerIdentifier identifier) throws IOException {
		sendMessage("identifier", identifier, "Error in sending identifier");
	}

	@Override
	public int chooseLeaderCard(List<LeaderCard> leaderCards)
			throws IOException, ClassNotFoundException {
		sendMessage("chooseLeaderCard", leaderCards,
				"cannot choose leaderCards");

		// return the choice of the player
		return (int) readFromStream();
	}

	@Override
	public LeaderCard useCard(List<LeaderCard> cardsAvailable)
			throws IOException, ClassNotFoundException {
		sendMessage("useCard", cardsAvailable,
				"cannot ask to use a LeaderCard");

		// return the choice of the player
		return (LeaderCard) readFromStream();
	}

	@Override
	public int chooseBonusTile()
			throws IOException, ClassNotFoundException {
		sendMessage("chooseBonusTile", "cannot choose bonus tile");

		// return the choice of the player
		return (int) readFromStream();
	}

	@Override
	public void outOfTime() throws IOException {
		sendMessage("outOfTime", "cannot inform use of time out");
		final Board board = Game.getInstance().getBoard();
		Participant thisParticipant = this;

		// ignore every input unless it is a rejoin message
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					boolean rejoined = false;
					do {
						Object obj = readFromStream();

						if (obj instanceof String) {
							if (obj.equals("rejoin")) {
								board.rejoin(thisParticipant);
								rejoined = true;
							}
						}
					} while (!rejoined);
				} catch (IOException | ClassNotFoundException e) {
					ExceptionLogger.log(e);
				}
			}
		}).start();
	}

	@Override
	public void actionResult(boolean result) throws IOException {
		sendMessage("actionResult", result,
				"cannot send action result to user");
	}

	@Override
	public void update(BoardState currentState) throws IOException {
		sendMessage("updateBoardState", currentState,
				"cannot update Board State");
	}

	public void update(DieColor dieColor, int value) throws IOException {
		List<Object> obj = new ArrayList<>();
		obj.add(dieColor);
		obj.add(value);
		sendMessage("changeDie", obj, "cannot update Die");
	}

	@Override
	public void update(PlayerState newState) throws IOException {
		sendMessage("updatePlayerState", newState,
				"cannot update Player State");
	}

	@Override
	public void update(ActionSpaceIds id, boolean free) throws IOException {
		List<Object> obj = new ArrayList<>();
		obj.add(id);
		obj.add(free);
		sendMessage("updateActionSpaceFree", obj, "cannot update ActionSpace");
	}

	@Override
	public void update(ActionSpaceIds id, PlayerColor playerColor,
	                   MemberColor memberColor)
			throws IOException {
		List<Object> obj = new ArrayList<>();
		obj.add(id);
		obj.add(playerColor);
		obj.add(memberColor);
		sendMessage("updateActionSpacePlayer", obj,
				"cannot update ActionSpace");
	}

	@Override
	public void update(CardType cardType, int floorNumber,
	                   DevelopmentCard developmentCard)
			throws IOException {
		List<Object> obj = new ArrayList<>();
		obj.add(cardType);
		obj.add(floorNumber);
		obj.add(developmentCard);
		sendMessage("updateFloor", obj, "cannot update Floor");
	}

	@Override
	public void terminatedRound() throws IOException {
		sendMessage("terminatedRound", "cannot update new Round");
	}

	@Override
	public void update(GameState newState) throws IOException {
		sendMessage("updateGameState", newState, "cannot update GameState");
	}

	@Override
	public void update(int periodNumber) throws IOException {
		sendMessage("updateNewPeriod", periodNumber,
				"cannot update new Period");
	}

	@Override
	public void update(List<Pair<PlayerIdentifier, Integer>> winningOrderList)
			throws IOException {
		sendMessage("finalScore", winningOrderList, "cannot send final score");
	}

	@Override
	public void update(RoundState newState) throws IOException {
		sendMessage("updateRoundState", newState, "cannot update RoundState");
	}

	@Override
	public void update(PlayerIdentifier identifier) throws IOException {
		sendMessage("updateCurrentPlayer", identifier,
				"cannot update new Player");
	}

	@Override
	public void update(DevelopmentCard card) throws IOException {
		sendMessage("updateOwnedCards", card, "cannot update new Card");
	}

	@Override
	public void update(ResourcesList resourcesList) throws IOException {
		sendMessage("updateOwnedResources", resourcesList,
				"cannot update owned resources");
	}

	private void sendMessage(String type, String error) throws IOException {
		sendMessage(type, null, error);
	}

	private void sendMessage(String type, Object obj, String error)
			throws IOException {
		SimpleEntry<String, Object> entry;
		entry = new SimpleEntry<>(type, obj);
		try {
			objOut.writeObject(entry);
			objOut.flush();
			objOut.reset(); // needed to avoid optimization by reference
		} catch (IOException e) {
			System.err.println(error);
			ExceptionLogger.log(e);
			throw e;
		}
	}


	private synchronized Object readFromStream()
			throws IOException, ClassNotFoundException {
		return objIn.readObject();
	}
}
