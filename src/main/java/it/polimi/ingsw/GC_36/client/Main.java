package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.client.view.ViewCLI;
import it.polimi.ingsw.GC_36.client.view.ViewGUI;
import it.polimi.ingsw.GC_36.client.view.ViewInterface;
import it.polimi.ingsw.GC_36.utils.ExceptionLogger;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Main {
	static Communicator communicator;

	private Main() {}

	public static void main(String[] args) throws RemoteException {
		ExceptionLogger.setDebug();

		ViewInterface view = chooseView();

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

	private static ViewInterface chooseView() {
		ViewInterface view = null;

		Scanner sc = new Scanner(System.in);
		char choice;

		do {
			System.out.print("Type g to choose GUI, c to choose CLI: ");
			choice = sc.next().charAt(0);

			if (choice == 'c' || choice == 'C') {
				view = new ViewCLI();
			} else if (choice == 'g' || choice == 'G') {
				view = new ViewGUI();
			}
		} while (view == null);

		return view;
	}
}
