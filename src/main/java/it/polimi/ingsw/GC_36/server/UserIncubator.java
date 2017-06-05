package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.controller.GameExecutor;

import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class UserIncubator extends UnicastRemoteObject {
	private BlockingQueue<Socket> sockets = new LinkedBlockingDeque<>();
	private BlockingQueue<User> users = new LinkedBlockingDeque<>();

	UserIncubator() throws RemoteException {}


	void addUser(Socket socket) {

		try {
			sockets.put(socket);

			System.out.println("Client accepted " + socket);

			if (sockets.size() + users.size() == Commons.MIN_PLAYERS) {
				new Thread(new Initializer(sockets, users)).start();
			}
		} catch (InterruptedException e) {
			System.out.println("Cannot add user");
			e.printStackTrace();
		}


	}

	public void addUser(User user) throws InterruptedException {
		users.put(user);
		System.out.println("Client accepted " + user);

		if (sockets.size() + users.size() == Commons.MIN_PLAYERS) {
			new Thread(new Initializer(sockets, users)).start();
		}
	}
}

class Initializer implements Runnable {

	private final BlockingQueue<Socket> sockets;
	private final BlockingQueue<User> users;

	public Initializer(BlockingQueue<Socket> sockets,
	                   BlockingQueue<User> users) {
		this.sockets = sockets;
		this.users = users;
	}

	@Override
	public void run() {
		// TODO: introduce a 'timer' to let MIN_PLAYERS to play

		Set<Participant> participants = new HashSet<>();
		boolean full = false;
		while (!full) {
			// TODO: bug. If we enter this loop with sockets and users empty..?

			if (!sockets.isEmpty()) {
				try {
					participants.add(new ParticipantSOC(sockets.take()));

					if (participants.size() >= Commons.MAX_PLAYERS) {
						full = true;
					}

				} catch (IOException e) {
					System.err.println("Cannot instantiate new Participant");
					e.printStackTrace();
				} catch (InterruptedException e) {
					System.err.println(
							"Cannot take an element from sockets set");
					e.printStackTrace();
				}
			}

			if (!users.isEmpty() && !full) {
				try {
					participants.add(new ParticipantRMI(users.take()));

					if (participants.size() >= Commons.MAX_PLAYERS) {
						full = true;
					}
				} catch (InterruptedException e) {
					System.err.println(
							"Cannot take an element from users set");
					e.printStackTrace();
				}
			}
		}

		GameExecutor executor = new GameExecutor(participants);
		new Thread(executor).start();
	}
}
