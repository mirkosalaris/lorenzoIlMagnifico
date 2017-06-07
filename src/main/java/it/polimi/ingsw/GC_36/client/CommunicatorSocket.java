package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.ExceptionLogger;
import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.BoardState;
import it.polimi.ingsw.GC_36.model.GameState;

import java.io.*;
import java.net.Socket;
import java.util.AbstractMap.SimpleEntry;

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
		System.out.println("Connection established\n");


		objOut = new ObjectOutputStream(
				new BufferedOutputStream(socket.getOutputStream()));
		objOut.flush(); // send the header (to avoid input hanging on server)
		objIn = new ObjectInputStream(
				new BufferedInputStream(socket.getInputStream()));
	}

	@Override
	public void start() throws ClassNotFoundException, IOException {
		System.out.println("listening...");

		while (!matchEnded) {
			// TODO manage exception: what to do in case of exception???
			try {
				SimpleEntry<String, Object> entry =
						(SimpleEntry<String, Object>) objIn.readObject();

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

	private void handleEntry(SimpleEntry<String, Object> entry)
			throws IOException, ClassNotFoundException {
		switch (entry.getKey()) {
			case "fatal_error":
				user.fatalError((String) entry.getValue());
				matchEnded = true;
				break;

			case "updateBoardState":
				user.update((BoardState) entry.getValue());
				break;

			case "updateGameState":
				user.update((GameState) entry.getValue());
				break;
			case "updateNewPeriod":
				user.update((int) entry.getValue());
				break;
			case "play":
				Action action = (Action) entry.getValue();
				user.play(action);

				sendBack(action);
				break;
			// TODO etc.
			default:
				System.out.println(
						"Cannot retrieve information correctly from network:" +
								" " +
								"Object with wrong key: " + entry.getKey());
		}
	}

	private void sendBack(Action action) throws IOException {
		objOut.writeObject(action);
	}
}