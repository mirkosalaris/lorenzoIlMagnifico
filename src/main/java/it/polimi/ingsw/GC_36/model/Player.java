package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.observers.PlayerObserver;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Player {
	private PlayerColor playerColor;
	private PersonalBoard personalBoard;
	private Map<DieColor, FamilyMember> familyMembers =
			new EnumMap<>(DieColor.class);
	private PlayerState currentState;
	private Set<PlayerObserver> observers = new HashSet<>();

	public Player(PlayerColor playerColor) {
		// TODO

		this.playerColor = playerColor;

		setCurrentState(PlayerState.UNINITIALIZED);
	}

	public void init(PersonalBoard personalBoard) {
		if (currentState != PlayerState.UNINITIALIZED) {
			throw new IllegalStateException(
					"Player already initialized");
		}

		// save associated personalBoard
		this.personalBoard = personalBoard;

		// initialize familyMembers
		Map<DieColor, Die> dice = Game.getInstance().getBoard().getDice();
		for (Map.Entry<DieColor, Die> dieEntry : dice.entrySet()) {
			DieColor dieColor = dieEntry.getKey();
			FamilyMember member =
					new FamilyMember(this.playerColor, dieColor);

			familyMembers.put(dieColor, member);
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
