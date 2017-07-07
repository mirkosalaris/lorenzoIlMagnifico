package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.client.view.ViewInterface;
import it.polimi.ingsw.GC_36.utils.ExceptionLogger;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.Scanner;

public class Main {
	static Communicator communicator;

	private Main() {}

	public static void main(String[] args)
			throws Exception {
		ExceptionLogger.setDebug();

		ViewStarter starter = chooseView();
		ViewInterface view = starter.start();
		User user = new User(view);

		communicator = view.chooseCommunicator(user);

		try {
			communicator.connect();
		} catch (IOException | InterruptedException | NotBoundException e) {
			ExceptionLogger.log(e);
			System.out.println("Cannot connect to server. Exiting...");
			System.exit(1);
		}

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					communicator.start();
				} catch (IOException | ClassNotFoundException e) {
					ExceptionLogger.log(e);
				}
			}
		}).start();
	}

	private static ViewStarter chooseView() {
		ViewStarter starter = null;

		Scanner sc = new Scanner(System.in);
		char choice;

		boolean setted = false;
		do {
			System.out.print("Type g to choose GUI, c to choose CLI: ");
			choice = sc.next().charAt(0);

			if (choice == 'c' || choice == 'C') {
				starter = new ViewStarter(ViewStarter.CLI);
				setted = true;
			} else if (choice == 'g' || choice == 'G') {
				starter = new ViewStarter(ViewStarter.GUI);
				setted = true;
			}
		} while (!setted);

		return starter;
	}
}
