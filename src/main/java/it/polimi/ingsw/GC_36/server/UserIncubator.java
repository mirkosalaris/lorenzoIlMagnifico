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
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class UserIncubator extends UnicastRemoteObject
		implements UserIncubatorInterface {
	private transient BlockingQueue<Pair<ObjectInputStream,
			ObjectOutputStream>> socketsPair
			= new LinkedBlockingQueue<>();
	private transient BlockingQueue<UserInterface> users = new
			LinkedBlockingQueue<>();

	private int total = 0;
	private TimerTask timerTask;

	public UserIncubator() throws RemoteException { }


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

				if (timerTask != null) {
					long s = (timerTask.scheduledExecutionTime() - System
							.currentTimeMillis()) / 1000;
					obj = new SimpleEntry<>(
							"show", s + " seconds to auto play");
					objOut.writeObject(obj);
				}
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
			if (timerTask != null) {
				long s = (timerTask.scheduledExecutionTime() - System
						.currentTimeMillis()) / 1000;
				user.show(s + " seconds to auto play");
			}
		}
	}


	/**
	 * @return true if a new game has started, false otherwise
	 */
	private synchronized boolean incrementTotal() {
		// TODO: introduce a 'timer' to let MIN_PLAYERS to play
		total += 1;
		if (total == Commons.MAX_PLAYERS) {
			if (timerTask != null) {
				timerTask.cancel();
			}
			startMatch();
			return true;
		} else if (total == Commons.MIN_PLAYERS) {
			timerTask = new TimerTask() {
				@Override
				public void run() {
					startMatch();
				}
			};

			new Timer().schedule(timerTask, Commons.STARTING_MATCH_TIMER);
		}

		return false;
	}

	private synchronized void startMatch() {
		timerTask = null;

		System.out.println("Starting match\n");

		new Thread(
				new GameInitializer(
						new LinkedBlockingQueue<>(socketsPair),
						new LinkedBlockingQueue<>(users)
				)
		).start();

		socketsPair.clear();
		users.clear();
		total = 0;
	}
}
