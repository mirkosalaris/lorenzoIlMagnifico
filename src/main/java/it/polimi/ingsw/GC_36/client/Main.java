package it.polimi.ingsw.GC_36.client;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.Scanner;

public class Main {
	static Communicator communicator;

	private Main() {}

	public static void main(String[] args) {
		// TODO impl

		ViewInterface view = chooseView();

		User user = new User(view);

		communicator = chooseCommunicator(user);

		try {
			communicator.connect();
		} catch (IOException | InterruptedException | NotBoundException e) {
			System.out.println("Cannot connect to server. Exiting...");
			e.printStackTrace();
			System.exit(1);
		}

		view.start();


		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					communicator.start();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	private static Communicator chooseCommunicator(User user) {
		Communicator communicator = null;

		Scanner sc = new Scanner(System.in);
		System.out.print("Type r to choose RMI, s to choose socket: ");
		char choice = sc.next().charAt(0);
		do {
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
		char choice = sc.next().charAt(0);

		do {
			if (choice == 'c') {
				view = new ViewCLI();
			} else if (choice == 'g') {
				view = new ViewGUI();
			}
		} while (view == null);

		return view;
	}
}
