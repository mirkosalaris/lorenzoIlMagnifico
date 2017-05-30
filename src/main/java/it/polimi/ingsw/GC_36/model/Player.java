package it.polimi.ingsw.GC_36.model;

import java.util.HashSet;
import java.util.Set;

public class Player {
	private PlayerColor color;
	private PersonalBoard personalBoard;
	private FamilyMember[] familyMembers;
	private PlayerState currentState;
	private Set<PlayerObserver> observers = new HashSet<>();

	public Player(PlayerColor color, PersonalBoard personalBoard) {
		// TODO

		this.color = color;

		this.personalBoard = personalBoard;
		setCurrentState(PlayerState.UNINITIALIZED);
	}

	public void init() {
		// TODO initialize familyMembers getting dice
		setCurrentState(PlayerState.WAITING);
	}

	public PersonalBoard getPersonalBoard() {
		return personalBoard;
	}

	public FamilyMember[] getAvailableFamilyMembers() {
		//TODO
		return null;
	}

	public void subscribe(PlayerObserver o) {
		observers.add(o);
	}

	private void newStateNotify() {
		for (PlayerObserver o : observers) {
			o.update(currentState);
		}
	}

	private void setCurrentState(PlayerState newState) {
		currentState = newState;
		newStateNotify();
	}
}
