package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.ExceptionLogger;
import it.polimi.ingsw.GC_36.model.*;

import java.io.*;
import java.net.Socket;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

public class ParticipantSOC implements Participant {
	private ObjectOutputStream objOut;
	private ObjectInputStream objIn;

	public ParticipantSOC(Socket socket) throws IOException {

		objOut = new ObjectOutputStream(
				new BufferedOutputStream(socket.getOutputStream()));

		// send the header (to avoid input hanging on client)
		objOut.flush();

		objIn = new ObjectInputStream(
				new BufferedInputStream(socket.getInputStream()));
	}

	@Override
	public void exit() {
		sendMessage("exit", "Cannot send exit message to user");
	}


	@Override
	public void play(Action action)
			throws IOException, ClassNotFoundException {

		sendMessage("play", action, "Error in making user play");

		Action retrievedAction = (Action) objIn.readObject();
		action.copyFrom(retrievedAction);
	}

	@Override
	public void fatalError(String s) {
		sendMessage("fatal_error", s, "Cannot send error to user");
	}

	@Override
	public void update(BoardState currentState) {
		sendMessage("updateBoardState", currentState,
				"cannot update Board State");
	}

	@Override
	public void update(PlayerState newState) {
		sendMessage("updatePlayerState", newState,
				"cannot update Player State");
	}

	@Override
	public void update(ActionSpaceIds id, boolean free) {
		List<Object> obj = new ArrayList<>();
		obj.add(id);
		obj.add(free);
		sendMessage("updateActionSpace", obj, "cannot update ActionSpace");
	}

	@Override
	public void update(int floorNumber, DevelopmentCard developmentCard) {
		List<Object> obj = new ArrayList<>();
		obj.add(floorNumber);
		obj.add(developmentCard);
		sendMessage("updateFloor", obj, "cannot update Floor");
	}

	@Override
	public void update() {
		sendMessage("updateNewRound", "cannot update new Round");
	}

	@Override
	public void update(GameState newState) {
		sendMessage("updateGameState", newState, "cannot update GameState");
	}

	@Override
	public void update(int periodNumber) {
		sendMessage("updateNewPeriod", periodNumber,
				"cannot update new Period");
	}

	@Override
	public void update(RoundState newState) {
		sendMessage("updateRoundState", newState, "cannot update RoundState");
	}

	@Override
	public void update(PlayerIdentifier identifier) {
		sendMessage("updateCurrentPlayer", identifier,
				"cannot update new Player");
	}

	private void sendMessage(String type, String error) {
		sendMessage(type, null, error);
	}

	private void sendMessage(String type, Object obj, String error) {
		SimpleEntry<String, Object> entry;
		entry = new SimpleEntry<>(type, obj);
		try {
			objOut.writeObject(entry);
			objOut.flush();
		} catch (IOException e) {
			System.err.println(error);
			ExceptionLogger.log(e);
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
