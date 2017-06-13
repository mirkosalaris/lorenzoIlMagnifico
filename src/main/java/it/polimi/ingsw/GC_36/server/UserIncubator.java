package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.client.UserInterface;
import it.polimi.ingsw.GC_36.utils.ExceptionLogger;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.AbstractMap.SimpleEntry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class UserIncubator extends UnicastRemoteObject
		implements UserIncubatorInterface {
	private transient BlockingQueue<Pair<ObjectInputStream,
			ObjectOutputStream>> socketsPair
			= new LinkedBlockingDeque<>();
	private transient BlockingQueue<UserInterface> users = new
			LinkedBlockingDeque<>();

	private int total = 0;

	public UserIncubator() throws RemoteException {}


	public void addUser(Socket socket)
			throws InterruptedException, IOException {

		try {
			ObjectOutputStream objOut = new ObjectOutputStream(
					socket.getOutputStream());
			ObjectInputStream objIn = new ObjectInputStream(
					socket.getInputStream());

			Pair<ObjectInputStream, ObjectOutputStream> socketInOut = new
					Pair<>(objIn, objOut);
			socketsPair.put(socketInOut);

			System.out.println("Client accepted: " + socket);

			boolean started = incrementTotal();
			if (!started) {

				SimpleEntry<String, Object> obj = new SimpleEntry<>("show",
						"waiting for other players. Currently "
								+ total + " players have joined");
				objOut.writeObject(obj);
			}
		} catch (InterruptedException e) {
			ExceptionLogger.log(e);
			System.out.println("Cannot add user");
			throw e;
		}


	}

	@Override
	public void addUser(UserInterface user)
			throws InterruptedException, RemoteException {
		users.put(user);
		System.out.println("Client accepted: " + user);

		boolean started = incrementTotal();
		if (!started) {
			user.show("Waiting for other players. Currently "
					+ total + " players have joined");
		}
	}


	private synchronized boolean incrementTotal() {
		// TODO: introduce a 'timer' to let MIN_PLAYERS to play
		total += 1;
		if (total == Commons.MIN_PLAYERS) {
			System.out.println("Starting match\n");
			new Thread(new GameInitializer(socketsPair, users)).start();
			total = 0;
			return true;
		} else {
			return false;
		}
	}
}
