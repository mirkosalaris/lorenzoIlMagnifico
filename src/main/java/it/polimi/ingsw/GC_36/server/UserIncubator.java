package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.client.UserInterface;
import it.polimi.ingsw.GC_36.model.GameMode;
import it.polimi.ingsw.GC_36.utils.ExceptionLogger;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class UserIncubator extends UnicastRemoteObject
		implements UserIncubatorInterface {

	private transient Map<GameMode, BlockingQueue<Pair<ObjectInputStream,
			ObjectOutputStream>>> sockets;
	private transient Map<GameMode, BlockingQueue<UserInterface>> users;

	// AtomicInteger and not Integer
	private Map<GameMode, AtomicInteger> totalPlayers;
	private TimerTask timerTask;

	public UserIncubator() throws RemoteException {
		sockets = new HashMap<>();
		sockets.put(GameMode.STANDARD, new LinkedBlockingQueue<>());
		sockets.put(GameMode.ADVANCED, new LinkedBlockingQueue<>());

		users = new HashMap<>();
		users.put(GameMode.STANDARD, new LinkedBlockingQueue<>());
		users.put(GameMode.ADVANCED, new LinkedBlockingQueue<>());

		totalPlayers = new HashMap<>();
		totalPlayers.put(GameMode.STANDARD, new AtomicInteger(0));
		totalPlayers.put(GameMode.ADVANCED, new AtomicInteger(0));
	}


	public void addUser(Socket socket)
			throws IOException, ClassNotFoundException {
		new Thread(new Runnable() {
			public void run() {
				try {
					ObjectOutputStream objOut = new ObjectOutputStream(
							socket.getOutputStream());
					ObjectInputStream objIn = new ObjectInputStream(
							socket.getInputStream());

					objOut.writeObject(new SimpleEntry<>("chooseMode", null));
					objOut.flush();
					GameMode mode = (GameMode) objIn.readObject();

					Pair<ObjectInputStream, ObjectOutputStream> socketInOut =
							new Pair<>(objIn, objOut);
					sockets.get(mode).put(socketInOut);

					System.out.println("Client accepted: " + socket);

					boolean started = incrementTotal(mode);
					if (!started) {

						SimpleEntry<String, Object> obj =
								new SimpleEntry<>("show",
										"waiting for other players. Currently "
												+ totalPlayers.get(mode)
												+ " players have joined");
						objOut.writeObject(obj);

						if (timerTask != null) {
							long s = (timerTask.scheduledExecutionTime() -
									System.currentTimeMillis()) / 1000;
							obj = new SimpleEntry<>("show",
									s + " seconds to auto play");
							objOut.writeObject(obj);
						}
					}
				} catch (InterruptedException | IOException |
						ClassNotFoundException e) {
					ExceptionLogger.log(e);
					System.out.println("Cannot add user");
				}
			}
		}).start();


	}

	@Override
	public void addUser(UserInterface user)
			throws InterruptedException, RemoteException {
		// no need to use a thread, this is called by RMI

		GameMode mode = user.chooseMode();
		users.get(mode).put(user);
		System.out.println("Client accepted: " + user);

		boolean started = incrementTotal(mode);
		if (!started) {
			user.show("Waiting for other players. Currently "
					+ totalPlayers.get(mode) + " players have joined");
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
	private synchronized boolean incrementTotal(GameMode mode) {
		int value = totalPlayers.get(mode).incrementAndGet();

		if (value == Commons.MAX_PLAYERS) {
			if (timerTask != null) {
				timerTask.cancel();
			}
			startMatch(mode);
			return true;
		} else if (value == Commons.MIN_PLAYERS) {
			timerTask = new TimerTask() {
				@Override
				public void run() {
					startMatch(mode);
				}
			};

			new Timer().schedule(timerTask, Commons.getStartingMatchTimer());
		}

		return false;
	}

	private synchronized void startMatch(GameMode mode) {
		timerTask = null;

		System.out.println("Starting match\n");

		new Thread(
				new GameInitializer(
						new LinkedBlockingQueue<>(sockets.get(mode)),
						new LinkedBlockingQueue<>(users.get(mode)),
						mode)
		).start();

		// TODO @mirko these 'clear' maybe eliminate users not yet considered
		sockets.get(mode).clear();
		users.get(mode).clear();
		totalPlayers.get(mode).set(0);
	}
}
