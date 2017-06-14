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

		ActionInterface retrievedAction = (ActionInterface) objIn.readObject();
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
	public void update(BoardState currentState) throws IOException {
		sendMessage("updateBoardState", currentState,
				"cannot update Board State");
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
	public void update(ActionSpaceIds id, PlayerColor playerColor)
			throws IOException {
		List<Object> obj = new ArrayList<>();
		obj.add(id);
		obj.add(playerColor);
		sendMessage("updateActionSpacePlayer", obj,
				"cannot update ActionSpace");
	}

	@Override
	public void update(int floorNumber, DevelopmentCard developmentCard)
			throws IOException {
		List<Object> obj = new ArrayList<>();
		obj.add(floorNumber);
		obj.add(developmentCard);
		sendMessage("updateFloor", obj, "cannot update Floor");
	}

	@Override
	public void update() throws IOException {
		sendMessage("updateNewRound", "cannot update new Round");
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
		} catch (IOException e) {
			System.err.println(error);
			ExceptionLogger.log(e);
			throw e;
		}
	}


	// TODO do we ever need to receive an "entry"? Can we delete the method?
	private void handleEntry(SimpleEntry<String, Object> entry) {
		switch (entry.getKey()) {
			case "example":
				break;

			case "second_example":
				//user.update();
				break;

			default:
				System.out.println(
						"Cannot retrieve information correctly from network:" +
								" " +
								"Object with wrong key: " + entry.getKey());
		}
	}
}
