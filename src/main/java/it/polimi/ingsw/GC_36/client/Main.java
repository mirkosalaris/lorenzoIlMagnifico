package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.ExceptionLogger;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Main {
	static Communicator communicator;

	private Main() {}

	public static void main(String[] args) throws RemoteException {
		// TODO impl

		ExceptionLogger.setDebug();

		ViewInterface view = chooseView();

		User user = new User(view);

		communicator = chooseCommunicator(user);

		try {
			communicator.connect();
		} catch (IOException | InterruptedException | NotBoundException e) {
			ExceptionLogger.log(e);
			System.out.println("Cannot connect to server. Exiting...");
			System.exit(1);
		}

		view.start();


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

	private static Communicator chooseCommunicator(User user) {
		Communicator communicator = null;

		Scanner sc = new Scanner(System.in);
		System.out.print("Type r to choose RMI, s to choose socket: ");
		char choice;
		do {
			choice = sc.next().charAt(0);

			if (choice == 's') {
				communicator = new CommunicatorSocket(user);
			} else if (choice == 'r') {
				communicator = new CommunicatorRMI(user);
			}
		} while (communicator == null);

		return communicator;
	}

	private static ViewInterface chooseView() {
		ViewInterface view = null;

		Scanner sc = new Scanner(System.in);
		System.out.print("Type g to choose GUI, c to choose CLI: ");
		char choice;

		do {
			choice = sc.next().charAt(0);

			if (choice == 'c') {
				view = new ViewCLI();
			} else if (choice == 'g') {
				view = new ViewGUI();
			}
		} while (view == null);

		return view;
	}
}
