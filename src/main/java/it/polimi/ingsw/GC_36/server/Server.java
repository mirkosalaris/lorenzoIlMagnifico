package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.ExceptionLogger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Server extends UnicastRemoteObject {
	private ServerSocket ss;
	private boolean exit = false;
	private UserIncubator incubator;

	private Server() throws RemoteException {
		ExceptionLogger.setDebug();
		incubator = new UserIncubator();
	}

	public static void main(String[] args)
			throws IOException, InterruptedException {
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

	private void startSOC() throws IOException, InterruptedException {
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
		}).start();

		while (!exit) {
			Socket socket = ss.accept();
			incubator.addUser(socket);
		}

		ss.close();
	}

	private void exit() {
		try {
			ss.close();
		} catch (IOException e) {
			ExceptionLogger.log(e);
			System.out.println("Cannot properly close ServerSocket");
		}

		System.exit(0);
	}
}