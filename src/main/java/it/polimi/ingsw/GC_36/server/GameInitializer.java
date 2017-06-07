package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.ExceptionLogger;
import it.polimi.ingsw.GC_36.client.UserInterface;
import it.polimi.ingsw.GC_36.controller.GameExecutor;

import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

public class GameInitializer implements Runnable {

	private final BlockingQueue<Socket> sockets;
	private final BlockingQueue<UserInterface> users;

	public GameInitializer(BlockingQueue<Socket> sockets,
	                       BlockingQueue<UserInterface> users) {
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
					participants.add(new ParticipantRMI(users.take()));

					if (participants.size() >= Commons.MAX_PLAYERS) {
						full = true;
					}
				} catch (InterruptedException e) {
					ExceptionLogger.log(e);
					System.err.println(
							"Cannot take an element from users set");
					Thread.currentThread().interrupt();
				}
			}
		}

		GameExecutor executor = null;
		try {
			executor = new GameExecutor(participants);
			new Thread(executor).start();
		} catch (RemoteException e) {
			System.out.println("Cannot start a new Game");
			ExceptionLogger.log(e);
		}
	}
}