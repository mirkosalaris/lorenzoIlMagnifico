package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.ExceptionLogger;
import it.polimi.ingsw.GC_36.client.UserInterface;

import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class UserIncubator extends UnicastRemoteObject
		implements UserIncubatorInterface {
	private transient BlockingQueue<Socket> sockets = new
			LinkedBlockingDeque<>();
	private transient BlockingQueue<UserInterface> users = new
			LinkedBlockingDeque<>();

	public UserIncubator() throws RemoteException {}


	public void addUser(Socket socket) throws InterruptedException {

		try {
			sockets.put(socket);

			System.out.println("Client accepted: " + socket);

			if (sockets.size() + users.size() == Commons.MIN_PLAYERS) {
				new Thread(new GameInitializer(sockets, users)).start();
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

		if (sockets.size() + users.size() == Commons.MIN_PLAYERS) {
			new Thread(new GameInitializer(sockets, users)).start();
		}
	}

}
