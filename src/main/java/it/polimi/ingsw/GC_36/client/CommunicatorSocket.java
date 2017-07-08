package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.utils.ExceptionLogger;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.io.*;
import java.net.Socket;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class CommunicatorSocket implements Communicator {

	private final User user;
	private ObjectOutputStream objOut;
	private ObjectInputStream objIn;
	private Socket socket;
	private boolean matchEnded = false;

	public CommunicatorSocket(User user) {
		this.user = user;
	}

	@Override
	public void connect() throws IOException {

		socket = new Socket(Commons.HOST, Commons.PORT);
		user.show("Connection established\n");

		user.show("listening...");

		objOut = new ObjectOutputStream(
				new BufferedOutputStream(socket.getOutputStream()));
		objOut.flush(); // send the header (to avoid input hanging on server)
		objIn = new ObjectInputStream(
				new BufferedInputStream(socket.getInputStream()));
	}

	@Override
	public void start() throws ClassNotFoundException, IOException {

		while (!matchEnded) {
			try {
				@SuppressWarnings("unchecked")
				SimpleEntry<String, Object> entry = (SimpleEntry<String,
						Object>) objIn.readObject();

				handleEntry(entry);

			} catch (IOException e) {
				throw new IOException("Cannot read object from socket", e);
			} catch (ClassNotFoundException e) {
				throw new ClassNotFoundException(
						"Cannot deserialize object from socket", e);
			}
		}

		try {
			socket.close();
		} catch (IOException e) {
			ExceptionLogger.log(e);
			System.out.println("Cannot close the socket");
		}
	}

	@SuppressWarnings("unchecked")
	private void handleEntry(SimpleEntry<String, Object> entry)
			throws IOException, ClassNotFoundException {

		// used in some "case", not all
		List<Object> params;

		switch (entry.getKey()) {
			case "chooseMode":
				sendBack(user.chooseMode());
				break;

			case "show":
				user.show((String) entry.getValue());
				break;

			case "fatal_error":
				user.fatalError((String) entry.getValue());
				matchEnded = true;
				break;

			case "play":
				ActionInterface action = (ActionInterface) entry.getValue();
				user.play(action);

				sendBack(action);
				break;

			case "exit":
				user.exit((String) entry.getValue());
				matchEnded = true;
				break;

			case "identifier":
				user.setIdentifier((PlayerIdentifier) entry.getValue());
				break;

			case "chooseLeaderCard":
				sendBack(user.chooseLeaderCard(
						(List<LeaderCard>) entry.getValue()));
				break;

			case "useCard":
				sendBack(user.useCard(
						(List<LeaderCard>) entry.getValue()));
				break;

			case "chooseBonusTile":
				sendBack(user.chooseBonusTile());
				break;

			case "updateBoardState":
				user.update((BoardState) entry.getValue());
				break;

			case "changeDie":
				params = (List<Object>) entry.getValue();
				DieColor color = (DieColor) params.get(0);
				int value = (int) params.get(1);
				user.update(color, value);
				break;

			case "updatePlayerState":
				user.update((PlayerState) entry.getValue());
				break;

			case "updateActionSpaceFree":
				params = (List<Object>) entry.getValue();
				ActionSpaceIds id = (ActionSpaceIds) params.get(0);
				boolean free = (boolean) params.get(1);
				user.update(id, free);
				break;

			case "updateActionSpacePlayer":
				params = (List<Object>) entry.getValue();
				ActionSpaceIds asId = (ActionSpaceIds) params.get(0);
				PlayerColor playerColor = (PlayerColor) params.get(1);
				MemberColor memberColor = (MemberColor) params.get(2);
				user.update(asId, playerColor, memberColor);
				break;

			case "updateFloor":
				params = (List<Object>) entry.getValue();
				CardType cardType = (CardType) params.get(0);
				int floorNumber = (int) params.get(1);
				DevelopmentCard card = (DevelopmentCard) params.get(2);
				user.update(cardType, floorNumber, card);
				break;

			case "terminatedRound":
				user.terminatedRound();
				break;

			case "updateGameState":
				user.update((GameState) entry.getValue());
				break;

			case "updateNewPeriod":
				user.update((int) entry.getValue());
				break;

			case "finalScore":
				user.update(
						(List<Pair<PlayerIdentifier, Integer>>) entry
								.getValue());
				break;

			case "updateRoundState":
				user.update((RoundState) entry.getValue());
				break;

			case "updateCurrentPlayer":
				user.update((PlayerIdentifier) entry.getValue());
				break;

			case "updateOwnedCards":
				user.update((DevelopmentCard) entry.getValue());
				break;

			case "updateOwnedResources":
				user.update((ResourcesList) entry.getValue());
				break;

			// TODO check if this is all
			default:
				System.out.println(
						"Cannot retrieve information correctly from network:" +
								" " +
								"Object with wrong key: " + entry.getKey());
		}
	}

	private void sendBack(Object obj) throws IOException {
		objOut.writeObject(obj);
		objOut.flush();
	}
}