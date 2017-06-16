package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.client.UserInterface;
import it.polimi.ingsw.GC_36.controller.GameExecutor;
import it.polimi.ingsw.GC_36.utils.ExceptionLogger;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

public class GameInitializer implements Runnable {

	private final BlockingQueue<Pair<ObjectInputStream, ObjectOutputStream>>
			sockets;
	private final BlockingQueue<UserInterface> users;

	public GameInitializer(
			BlockingQueue<Pair<ObjectInputStream, ObjectOutputStream>> sockets,
			BlockingQueue<UserInterface> users) {
		this.sockets = sockets;
		this.users = users;
	}

	@Override
	public void run() {
		Set<Participant> participants = new HashSet<>();
		boolean full = false;

		if (sockets.size() + users.size() < Commons.MIN_PLAYERS) {
			throw new IllegalStateException("too few users to start a game");
		}

		while (!full) {
			if (!sockets.isEmpty()) {
				try {
					ObjectOutputStream objOut;
					Pair<ObjectInputStream, ObjectOutputStream> pair =
							sockets.take();
					participants.add(new ParticipantSOC(pair));

					objOut = pair.getSecond();
					SimpleEntry<String, Object> obj = new SimpleEntry<>("show",
							"Welcome, enjoy the game");
					objOut.writeObject(obj);

					if (participants.size() >= Commons.MAX_PLAYERS) {
						full = true;
					}

				} catch (IOException e) {
					ExceptionLogger.log(e);
					System.err.println("Cannot instantiate new Participant");
				} catch (InterruptedException e) {
					ExceptionLogger.log(e);
					System.err.println(
							"Cannot take an element from sockets set");
					Thread.currentThread().interrupt();
				}
			}

			if (!users.isEmpty() && !full) {
				try {
					UserInterface user = users.take();
					participants.add(new ParticipantRMI(user));

					user.show("Welcome, enjoy the game");

					if (participants.size() >= Commons.MAX_PLAYERS) {
						full = true;
					}
				} catch (InterruptedException | RemoteException e) {
					ExceptionLogger.log(e);
					System.err.println(
							"Cannot take an element from users set");
					Thread.currentThread().interrupt();
				}
			}

			if (users.isEmpty() && sockets.isEmpty()) {
				full = true;
			}
		}

		GameExecutor executor = null;
		try {
			executor = new GameExecutor(participants);
			new Thread(executor).start();
		} catch (IOException e) {
			System.out.println("Cannot start a new Game");
			ExceptionLogger.log(e);
		}
	}
}