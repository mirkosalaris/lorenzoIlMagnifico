package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.model.MemberColor;

import java.util.Scanner;

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
	public int chooseActionSpaceId() {
		Scanner in = new Scanner(System.in);
		System.out.println("Please select the ActionSpace specifying the ID");
		return in.nextInt();
	}

	@Override
	public int choosePrivilege(int n) {
		Scanner in = new Scanner(System.in);
		System.out.println("choose number:" + n);
		System.out.println("Please specify how to convert your privilege:" +
				"0: one wood & one stone" +
				"1: two servants" +
				"3: two coins" +
				"4: two military points" +
				"5: one faith point");
		return in.nextInt();

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
					"how many faith points do you want to use? Keep attention" +
							" " +
							"to specify not more faith points than you got or" +
							" " +
							"less than 0");

			faithPoints = in.nextInt();
		} while (faithPoints >= 0);
		return faithPoints;
	}

	@Override
	public void start() {
		// this has to "start" the view
		// TODO impl
	}
}
