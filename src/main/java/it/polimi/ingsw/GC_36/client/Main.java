package it.polimi.ingsw.GC_36.client;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	static Communicator communicator;

	public static void main(String[] args) {
		// TODO impl

		communicator = chooseCommunicator();

		try {
			communicator.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ViewInterface view = chooseView();
		view.start();

		// wait for other players

		// launch actual application
	}

	private static Communicator chooseCommunicator() {
		Communicator communicator = null;

		Scanner sc = new Scanner(System.in);
		System.out.println("Type r to choose RMI, s to choose socket");
		char choice = (char) sc.nextByte();
		do {
			if (choice == 's') {
				communicator = new CommunicatorSocket();
			} else if (choice == 'r') {
				communicator = new CommunicatorRMI();
			}
		} while (communicator == null);

		return communicator;
	}

	private static ViewInterface chooseView() {
		ViewInterface view = null;

		Scanner sc = new Scanner(System.in);
		System.out.println("Type g to choose GUI, c to choose CLI");
		char choice = (char) sc.nextByte();

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
