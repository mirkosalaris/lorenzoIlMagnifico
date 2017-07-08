package it.polimi.ingsw.GC_36.client.view;

import it.polimi.ingsw.GC_36.client.Communicator;
import it.polimi.ingsw.GC_36.client.CommunicatorRMI;
import it.polimi.ingsw.GC_36.client.CommunicatorSocket;
import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.io.IOException;
import java.util.*;

public class ViewCLI implements ViewInterface {
	private Scanner in;

	public ViewCLI() {
		in = new Scanner(System.in);
	}

	@Override
	public MemberColor chooseMemberColor() {
		MemberColor memberColor = null;
		do {
			System.out.println(
					"Please select the family member specifying the color: " +
							"-ORANGE, BLACK, WHITE, UNCOLORED- and press " +
							"Enter");
			String color;
			color = in.nextLine();
			if (color.equalsIgnoreCase("ORANGE")) {
				memberColor = MemberColor.ORANGE;
			}
			if (color.equalsIgnoreCase("BLACK")) {
				memberColor = MemberColor.BLACK;
			}
			if (color.equalsIgnoreCase("WHITE")) {
				memberColor = MemberColor.WHITE;
			}
			if (color.equalsIgnoreCase("UNCOLORED")) {
				memberColor = MemberColor.UNCOLORED;
			}
		} while (memberColor == null);
		return memberColor;

	}

	@Override
	public int setActionValueIncrement() {
		String answer;
		boolean wrong = true;
		int servants = 0;
		do {
			System.out.println(
					"Do you want to use servants to increment your " +
							"actionValue? yes or no");
			answer = in.nextLine();
			if (answer.equalsIgnoreCase("no"))
				wrong = false;
			if (answer.equalsIgnoreCase("yes")) {
				System.out.println("How much servants do you want to use?");
				servants = in.nextInt();
				in.nextLine(); // consume line
				if (servants >= 0)
					wrong = false;

			}
		} while (wrong);
		return servants;
	}

	@Override
	public int chooseActionSpaceId(Set<ActionSpaceIds> actionSpaceIds) {
		boolean wrong = true;
		int value;
		do {
			System.out.println(
					"Please select an ActionSpace specifying the ID:");
			if (actionSpaceIds.size() != ActionSpaceIds.values().length) {
				for (ActionSpaceIds id : actionSpaceIds) {
					System.out.print(id.value() + ", ");
				}
			}
			value = in.nextInt();
			if (!ActionSpaceIds.checkId(value)) {
				wrong = true;
			} else {
				wrong = !actionSpaceIds.contains(
						ActionSpaceIds.values()[value]);
			}
		} while (wrong);
		in.nextLine(); // consume line
		return value;
	}

	@Override
	public int choosePrivilege(int n) {
		System.out.println("choice number " + n);
		System.out.println("Please specify how to convert your privilege:\n" +
				"0: one wood & one stone\n" +
				"1: two servants\n" +
				"2: two coins\n" +
				"3: two military points\n" +
				"4: one faith point\n");
		int value = in.nextInt();
		in.nextLine(); // consume line
		return value;
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
		System.out.println("Please specify the options");
		int value = in.nextInt();
		in.nextLine(); // consume line
		return value;
	}

	@Override
	public int chooseCardPaymentOptions(DevelopmentCard card) {
		Pair<ResourcesList, ResourcesList> resourcesList;
		if (card.getRequirements().size() == 1)
			return 0;
		System.out.println("Please select the payment option of the card");
		for (int i = 1; i <= card.getRequirements().size(); i++) {
			System.out.println("option " + i + ": ");
			resourcesList = card.getRequirements().get(i - 1);
			System.out.println(
					"\tRequired: " + resourcesList.getFirst() + "\n\tTo Pay: "
							+ resourcesList.getSecond());
		}
		int choice = in.nextInt() - 1;
		in.nextLine(); // consume line
		return choice;
	}


	@Override
	public void show(String message) {
		System.out.println(message);
	}

	@Override
	public Communicator chooseCommunicator(User user) {
		Communicator communicator = null;

		char choice;
		do {
			System.out.print("Type r to choose RMI, s to choose socket: ");
			choice = in.nextLine().charAt(0);

			if (choice == 's' || choice == 'S') {
				communicator = new CommunicatorSocket(user);
			} else if (choice == 'r' || choice == 'R') {
				communicator = new CommunicatorRMI(user);
			}
		} while (communicator == null);

		return communicator;
	}

	@Override
	public GameMode chooseMode() {
		System.out.print("Do you want to play advanced mode (Yes/No)? ");
		System.out.flush();

		if (in.nextLine().equalsIgnoreCase("yes")) {
			return GameMode.ADVANCED;
		} else {
			return GameMode.STANDARD;
		}
	}

	@Override
	public void fatalError(String s) {
		System.out.println("fatal error: " + s);

	}

	@Override
	public void play(ActionInterface action) {
		// don't need to do anything
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
	public int chooseLeaderCard(List<LeaderCard> leaderCards)
			throws IOException, ClassNotFoundException {
		System.out.println("Choose a leader card from the following list:\n");

		return chooseFromList(leaderCards);
	}

	private <E> int chooseFromList(List<E> list) {
		// it never returns null if 'cancel' is set to null
		return chooseFromList(list, null);
	}

	/**
	 * Let the user choose between elements of a list typing an integer or a
	 * string 'cancel'
	 *
	 * @param list
	 * 		the list of element
	 * @param cancel
	 * 		the string to type to not choose.
	 * @param <E>
	 * @return the choice of the user. Null if no choice is made. Never returns
	 * null if 'cancel' is null
	 */
	private <E> Integer chooseFromList(List<E> list, String cancel) {
		boolean wrong = true;
		boolean canceled = false;
		int choice = 0;

		do {
			// print the alternatives
			for (int i = 0; i <= list.size() - 1; i++) {
				System.out.println(i + ") " + list.get(i));
			}

			String typed = in.nextLine();

			if (typed.matches("^-?\\d+$")) {
				// is it an integer?

				choice = Integer.parseInt(typed);
				if (choice >= 0 && choice <= list.size() - 1) {
					wrong = false;
				}
			} else if (cancel != null) {
				// maybe is a 'cancel' String

				if (typed.equalsIgnoreCase(cancel)) {
					wrong = false;
					canceled = true;
				}
			}
		} while (wrong);

		return (canceled) ? null : choice;
	}

	@Override
	public LeaderCard useCard(List<LeaderCard> cardsAvailable)
			throws IOException, ClassNotFoundException {
		System.out.println(
				"If you want to use a card choose one of the following, " +
						"otherwise type 'no':");
		Integer choice = chooseFromList(cardsAvailable, "no");
		if (choice == null) {
			return null;
		} else {
			// don't check if choice is in bound because it is guaranteed to be
			// right by 'chooseFromList'
			return cardsAvailable.get(choice);
		}
	}

	@Override
	public int chooseBonusTile()
			throws IOException, ClassNotFoundException {
		System.out.println("Choose a bonus tile from the following list:\n");
		List<BonusTileId> tiles =
				new ArrayList<>(Arrays.asList(BonusTileId.values()));
		tiles.remove(BonusTileId.DEFAULT);

		return chooseFromList(tiles);
	}

	@Override
	public void terminatedRound() {
		System.out.println("The round is terminated");
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
		if (free) {
			System.out.println(id.name() + " is free again");
		}
	}

	@Override
	public void update(ActionSpaceIds id, PlayerColor playerColor,
	                   MemberColor memberColor) {
		System.out.println(
				"Player " + playerColor + " has entered " + id.name() + " with" +
						" " + memberColor + " color");
	}

	@Override
	public void update(GameState newState) {
		System.out.println("new Game State: " + newState);

	}

	@Override
	public void update(int periodNumber) {
		System.out.println(
				"A new Period has started, current period: " + periodNumber);

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
	public void update(CardType cardType, int floorNumber,
	                   DevelopmentCard developmentCard) {
		if (developmentCard == null) {
			System.out.println(
					"The card from floor " + floorNumber + " of tower " +
							cardType + " has been taken");
		} else {
			System.out.println(
					"updateFloor:\n floorNumber: " + floorNumber
							+ "\n tower: " + cardType
							+ "\n Development Card: " + developmentCard);
		}
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
