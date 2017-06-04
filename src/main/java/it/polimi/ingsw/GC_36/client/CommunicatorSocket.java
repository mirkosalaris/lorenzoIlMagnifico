package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.Commons;

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

		objIn = new ObjectInputStream(
				new BufferedInputStream(socket.getInputStream()));
		objOut = new ObjectOutputStream(
				new BufferedOutputStream(socket.getOutputStream()));
	}

	@Override
	public void start() {
		System.out.println("listening...");

		while (!matchEnded) {
			// TODO manage exception: what to do in case of exception???
			try {
				SimpleEntry<String, Object> entry = (SimpleEntry<String,
						Object>) objIn
						.readObject();

				handleEntry(entry);

			} catch (IOException e) {
				System.out.println("Cannot read object from socket");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println("Cannot deserialize object from socket");
				e.printStackTrace();
			}
		}

		try {
			socket.close();
		} catch (IOException e) {
			System.out.println("Cannot close the socket");
			e.printStackTrace();
		}
	}

	private void handleEntry(SimpleEntry<String, Object> entry) {
		switch (entry.getKey()) {
			case "fatal_error":
				//user.fatalError(entry.getValue());
				matchEnded = true;

			case "updateBoardState":
				//user.update();

				// TODO etc.
			default:
				System.out.println(
						"Cannot retrieve information correctly from network:" +
								" " +
								"Object with wrong key");
		}
	}
}