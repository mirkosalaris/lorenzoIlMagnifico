package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.observers.PlayerObserver;
import it.polimi.ingsw.GC_36.server.Participant;

import java.rmi.RemoteException;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Player {
	private PlayerColor playerColor;
	private final Participant user;
	private PersonalBoard personalBoard;
	private Map<MemberColor, FamilyMember> familyMembers =
			new EnumMap<>(MemberColor.class);
	private PlayerState currentState;
	private Set<PlayerObserver> observers = new HashSet<>();

	public Player(PlayerColor playerColor, Participant user)
			throws RemoteException {
		// TODO

		this.playerColor = playerColor;
		this.user = user;

		setCurrentState(PlayerState.UNINITIALIZED);
	}

	public void init(PersonalBoard personalBoard)
			throws IllegalStateException, RemoteException {
		if (currentState != PlayerState.UNINITIALIZED) {
			throw new IllegalStateException(
					"Player already initialized");
		}

		// save associated personalBoard
		this.personalBoard = personalBoard;

		// initialize familyMembers
		for (MemberColor memberColor : MemberColor.values()) {

			FamilyMember member =
					new FamilyMember(this.playerColor, memberColor);

			familyMembers.put(memberColor, member);
		}

		// change state
		setCurrentState(PlayerState.WAITING);
	}


	public PersonalBoard getPersonalBoard() {
		return personalBoard;
	}

	public FamilyMember[] getAvailableFamilyMembers() {
		//TODO
		return null;
	}

	public Participant getUser() {
		return user;
	}

	public void subscribe(PlayerObserver o) {
		observers.add(o);
	}

	private void newStateNotify() throws RemoteException {
		for (PlayerObserver o : observers) {
			o.update(currentState);
		}
	}

	private void setCurrentState(PlayerState newState) throws RemoteException {
		currentState = newState;
		newStateNotify();
	}
}
