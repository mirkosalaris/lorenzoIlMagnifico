package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.controller.GameExecutor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {
	private static ServerSocket ss;

	private Server() {}

	public static void main(String[] args) throws IOException {
		Server server = new Server();
		Socket[] sockets = new Socket[Commons.MAX_PLAYERS];

		ss = new ServerSocket(Commons.PORT);
		System.out.println("ServerSocket waiting for connections...");

		// add a while(true) and thread management
		boolean full = false;
		int i = 0;
		// TODO add a "timer" to enter a match with less than MAX_PLAYERS
		while (!full) {
			sockets[i] = ss.accept();
			System.out.println("Client accepted " + sockets[i]);
			i++;
			if (i == Commons.MAX_PLAYERS) {
				full = true;
			}
		}


		List<User> users = new ArrayList<>();

		for (Socket s : sockets) {
			try {
				users.add(new UserSOC(s));
			} catch (IOException e) {
				System.out.println("Cannot connect to a user");
				e.printStackTrace();
				try {
					s.close();
				} catch (IOException e1) {
					System.out.println("Cannot close socket");
					e1.printStackTrace();
				}
			}
		}

		if (users.size() < Commons.MIN_PLAYERS) {
			for (User u : users) {
				u.fatalError(
						"Some players have abandoned the match. Too few " +
								"remaining to play");
			}

		} else {
			GameExecutor executor = new GameExecutor(users);
			new Thread(executor).start();
		}


		// accept "exit" as input and close the server
		new Thread(new Runnable() {
			private boolean running = true;

			@Override
			public void run() {
				String line;
				Scanner sc = new Scanner(System.in);
				do {
					line = sc.nextLine();
					if ("exit".equalsIgnoreCase(line)) {
						exit();
					}
				} while (running);
			}
		}).start();
	}

	private static void exit() {
		try {
			ss.close();
		} catch (IOException e) {
			System.out.println("Cannot properly close ServerSocket");
			e.printStackTrace();
		}

		System.exit(0);
	}
}
