package it.polimi.ingsw.GC_36.client.view;

import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ViewCLI implements ViewInterface {

	@Override
	public MemberColor chooseMemberColor() {
		MemberColor memberColor = null;
		Scanner in = new Scanner(System.in);
		do {
			System.out.println(
					"Please select the family member specifying the color: " +
							"-ORANGE, BLACK, WHITE, UNCOLORED- and press " +
							"Enter");
			String color;
			color = in.nextLine();
			if (color.equals("ORANGE")) {
				memberColor = MemberColor.ORANGE;
			}
			if (color.equals("BLACK")) {
				memberColor = MemberColor.BLACK;
			}
			if (color.equals("WHITE")) {
				memberColor = MemberColor.WHITE;
			}
			if (color.equals("UNCOLORED")) {
				memberColor = MemberColor.UNCOLORED;
			}
		} while (memberColor == null);
		return memberColor;

	}

	@Override
	public int setActionValueIncrement() {
		Scanner in = new Scanner(System.in);
		String answer;
		boolean wrong = true;
		int servants = 0;
		do {
			System.out.println(
					"Do you want to use servants to increment your " +
							"actionValue? yes or no");
			answer = new String(in.nextLine());
			if (answer.equals("no"))
				wrong = false;
			if (answer.equals("yes")) {
				System.out.println("How much servants do you want to use?");
				servants = in.nextInt();
				if (servants >= 0)
					wrong = false;

			}
		} while (wrong);
		return servants;
	}

	@Override
	public int chooseActionSpaceId(Set<ActionSpaceIds> actionSpaceIds) {
		Scanner in = new Scanner(System.in);
		System.out.println("Please select an ActionSpace specifying the ID:");
		if (actionSpaceIds != null) {
			for (ActionSpaceIds id : actionSpaceIds) {
				System.out.print(id.value() + ", ");
			}
		}
		return in.nextInt();
	}

	@Override
	public int choosePrivilege(int n) {
		Scanner in = new Scanner(System.in);
		System.out.println("choice number " + n);
		System.out.println("Please specify how to convert your privilege:\n" +
				"0: one wood & one stone\n" +
				"1: two servants\n" +
				"2: two coins\n" +
				"3: two military points\n" +
				"4: one faith point\n");
		return in.nextInt();
	}

	@Override
	public int chooseConvertingMethod(
			Map<Integer, Pair<ResourcesList, ResourcesList>> options) {
		// show the various options

		for (Integer i : options.keySet()) {
			System.out.println("Option number" + i);
			System.out.println("pay:");
			System.out.println(options.get(i).getFirst());
			System.out.println("get:");
			System.out.println(options.get(i).getSecond());
		}
		Scanner in = new Scanner(System.in);
		System.out.println("Please specify the options");
		return in.nextInt();
	}

	@Override
	public int chooseCardPaymentOptions(DevelopmentCard card) {
		Scanner in = new Scanner(System.in);
		Pair<ResourcesList, ResourcesList> resourcesList;
		System.out.println("Please select the payment option of the card");
		for (int i = 1; i <= card.getRequirements().size(); i++) {
			System.out.println("option " + i + ": ");
			resourcesList = card.getRequirements().get(i - 1);
			System.out.println(
					"\tRequired: " + resourcesList.getFirst() + "\n\tTo Pay: "
							+ resourcesList.getSecond());
		}
		int choice = in.nextInt() - 1;
		return choice;
	}


	@Override
	public void show(String message) {
		System.out.println(message);
	}

	@Override
	public void fatalError(String s) {
		System.out.println("fatal error: " + s);

	}

	@Override
	public void play(ActionInterface action) {
		System.out.println("\n\nNow it's your turn");
	}

	@Override
	public void exit(String message) {
		System.out.println(message);
	}

	@Override
	public void setIdentifier(PlayerIdentifier identifier) {
		System.out.println("Your identifier is " + identifier.get());
	}

	@Override
	public void update() {
		System.out.println("A new Round has started");
	}

	@Override
	public void update(BoardState currentState) {
		System.out.println("new Board State: " + currentState);
	}

	@Override
	public void update(DieColor dieColor, int value) {
		System.out.println("new Die value:\n"
				+ "\tdie color: " + dieColor + ", new value: " + value);
	}

	@Override
	public void update(PlayerState newState) {
		System.out.println("new Player State: " + newState);
	}

	@Override
	public void update(ActionSpaceIds id, boolean free) {
		System.out.print(id.name());
		if (free) {
			System.out.print("is free\n");
		} else {
			System.out.print("is not free\n");
		}
	}

	@Override
	public void update(ActionSpaceIds id, PlayerColor playerColor) {
		System.out.println(
				"Player " + playerColor + " has entered " + id.name());
	}

	@Override
	public void update(GameState newState) {
		System.out.println("new Game State: " + newState);

	}

	@Override
	public void update(int periodNumber) {
		System.out.println("A new Period has started");

	}

	@Override
	public void update(List<Pair<PlayerIdentifier, Integer>>
			                   winningOrderList) {
		System.out.println("The winner is "
				+ winningOrderList.get(0).getFirst() + " with "
				+ winningOrderList.get(0).getSecond() + " points");
		winningOrderList.remove(0);

		for (Pair<PlayerIdentifier, Integer> pair : winningOrderList) {
			System.out.println("Player " + pair.getFirst()
					+ " got " + pair.getSecond() + " victory points");
		}
	}

	@Override
	public void update(RoundState newState) {
		System.out.println("new Round State: " + newState);

	}

	@Override
	public void update(PlayerIdentifier newPlayer) {
		System.out.println("Current player: " + newPlayer.get());
	}

	@Override
	public void update(int floorNumber, DevelopmentCard developmentCard) {
		System.out.println(
				"updateFloor:\n floorNumber: " + floorNumber
						+ "\n tower: " + developmentCard.getType()
						+ "\n Development Card: " + developmentCard);

	}

	@Override
	public void update(DevelopmentCard card) {
		System.out.println("You have a new card:\n" + card);
	}

	@Override
	public void update(ResourcesList resourcesList) {
		System.out.println("Your current resources are: " + resourcesList);
	}
}
