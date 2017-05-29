package it.polimi.ingsw.GC_36.model;

public class Player {
	private PersonalBoard personalBoard;
	private FamilyMember[] familyMembers;
	private PlayerState currentState;

	public Player() {
		// TODO

		currentState = PlayerState.WAITING;
	}

	public PersonalBoard getPersonalBoard() {
		return personalBoard;
	}

	public FamilyMember[] getAvailableFamilyMembers() {
		//TODO
		return null;
	}

}
