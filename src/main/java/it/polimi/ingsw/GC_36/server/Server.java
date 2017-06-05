package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.Commons;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Server extends UnicastRemoteObject {
	private static ServerSocket ss;
	private static boolean exit = false;
	UserIncubator incubator;

	private Server() throws RemoteException {
		incubator = new UserIncubator();
	}

	public static void main(String[] args) throws IOException {
		Server server = new Server();

		server.startRMI();
		server.startSOC();
	}

	private void startRMI() throws RemoteException {
		LocateRegistry.createRegistry(Commons.RMI_PORT);
		Registry reg = LocateRegistry.getRegistry(Commons.RMI_PORT);
		reg.rebind("lorenzoServer", incubator);

		System.out.println("Server RMI up and running");
	}

	private void startSOC() throws IOException {
		ss = new ServerSocket(Commons.PORT);
		System.out.println("ServerSocket waiting for connections...");

		// accept "exit" as input and close the server
		new Thread(new Runnable() {
			@Override
			public void run() {
				String line;
				Scanner sc = new Scanner(System.in);
				do {
					line = sc.nextLine();
					if ("exit".equalsIgnoreCase(line)) {
						exit = true;
						exit();
					}
				} while (!exit);
			}
		});

		while (!exit) {

			/*
			Socket[] sockets = new Socket[Commons.MAX_PLAYERS];
			boolean full = false;

			// TODO add a "timer" to enter a match with less than MAX_PLAYERS
			int i = 0;
			while (!full) {
				sockets[i] = ss.accept();
				System.out.println("Client accepted " + sockets[i]);
				i++;
				if (i == Commons.MAX_PLAYERS) {
					full = true;
				}
			}
			threadPool.execute(new GameInitializer(sockets));
			*/

			Socket socket = ss.accept();
			incubator.addUser(socket);
		}
	}

	private void exit() {
		try {
			ss.close();
		} catch (IOException e) {
			System.out.println("Cannot properly close ServerSocket");
			e.printStackTrace();
		}

		System.exit(0);
	}
}