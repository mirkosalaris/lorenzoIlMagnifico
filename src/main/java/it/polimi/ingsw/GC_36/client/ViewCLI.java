package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.io.IOException;
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
	public int chooseActionSpaceId() {
		Scanner in = new Scanner(System.in);
		System.out.println("Please select the ActionSpace specifying the ID");
		return in.nextInt();
	}

	@Override
	public int choosePrivilege(int n) {
		Scanner in = new Scanner(System.in);
		System.out.println("choice number " + n);
		System.out.println("Please specify how to convert your privilege:\n" +
				"0: one wood & one stone\n" +
				"1: two servants\n" +
				"3: two coins\n" +
				"4: two military points\n" +
				"5: one faith point\n");
		return in.nextInt();
	}

	@Override
	public int chooseExtraActionSpaceId(Set<ActionSpaceIds> actionSpaceIds,
	                                    int actionValue) {
		Scanner in = new Scanner(System.in);
		System.out.println(
				"You have the chance to choose another card, the choice " +
						"doesn't include all the possible cards on the board");
		System.out.println(
				"Please select the associated ActionSpace from the list " +
						"below");
		for (ActionSpaceIds id : actionSpaceIds) {
			System.out.println(id.value());
		}
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
		ResourcesList resourcesList;
		System.out.println("Please select the payment option of the card");
		for (int i = 1; i <= card.getRequirements().size(); i++) {
			System.out.println("option " + i + ": ");
			resourcesList = card.getRequirements().get(i - 1);
			System.out.println(resourcesList.toString());
		}
		int choice = in.nextInt() - 1;
		return choice;
	}

	@Override
	public int selectNumberOfWoods() {
		int woods;
		Scanner in = new Scanner(System.in);
		do {
			System.out.println(
					"how many woods do you want to use? Keep attention to " +
							"specify not more woods than you got or less " +
							"than" +
							" " +
							"0");

			woods = in.nextInt();
		} while (woods >= 0);
		return woods;
	}

	@Override
	public int selectNumberOfStones() {
		int stones;
		Scanner in = new Scanner(System.in);
		do {
			System.out.println(
					"how many stones do you want to use? Keep attention to " +
							"specify not more stones than you got or less " +
							"than" +
							" 0");

			stones = in.nextInt();
		} while (stones >= 0);
		return stones;
	}

	@Override
	public int selectNumberOfServants() {
		int servants;
		Scanner in = new Scanner(System.in);
		do {
			System.out.println(
					"how many servants do you want to use? Keep attention to" +
							" " +
							"specify not more servants than you got or less " +
							"than 0");

			servants = in.nextInt();
		} while (servants >= 0);
		return servants;
	}

	@Override
	public int selectNumberOfCoins() {
		int coins;
		Scanner in = new Scanner(System.in);
		do {
			System.out.println(
					"how many coins do you want to use? Keep attention to " +
							"specify not more coins than you got or less " +
							"than" +
							" " +
							"0");

			coins = in.nextInt();
		} while (coins >= 0);
		return coins;
	}

	@Override
	public int selectNumberOfVictoryPoints() {
		int victoryPoints;
		Scanner in = new Scanner(System.in);
		do {
			System.out.println(
					"how many victory points do you want to use? Keep " +
							"attention to specify not more victory points " +
							"than" +
							" you got or less than 0");

			victoryPoints = in.nextInt();
		} while (victoryPoints >= 0);
		return victoryPoints;
	}

	@Override
	public int selectNumberOfMilitaryPoints() {
		int militaryPoints;
		Scanner in = new Scanner(System.in);
		do {
			System.out.println(
					"how many military points do you want to use? Keep " +
							"attention to specify not more military points " +
							"than you got or less than " +
							"0");

			militaryPoints = in.nextInt();
		} while (militaryPoints >= 0);
		return militaryPoints;
	}

	@Override
	public int selectNumberOfFaithPoints() {
		int faithPoints;
		Scanner in = new Scanner(System.in);
		do {
			System.out.println(
					"how many faith points do you want to use? Keep " +
							"attention" +
							" " +
							"to specify not more faith points than you got " +
							"or" +
							" " +
							"less than 0");

			faithPoints = in.nextInt();
		} while (faithPoints >= 0);
		return faithPoints;
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
	public void setIdentifier(PlayerIdentifier identifier) throws IOException {
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
	public void update(DieColor dieColor, int value) throws IOException {
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
	public void update(ActionSpaceIds id, PlayerColor playerColor)
			throws IOException {
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
	public void update(DevelopmentCard card) throws IOException {
		System.out.println("You have a new card:\n" + card);
	}

	@Override
	public void update(ResourcesList resourcesList) throws IOException {
		System.out.println("Your current resources are: " + resourcesList);
	}
}
