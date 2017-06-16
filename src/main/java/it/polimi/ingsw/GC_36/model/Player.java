package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.observers.ModelObserver;
import it.polimi.ingsw.GC_36.observers.PlayerObserver;
import it.polimi.ingsw.GC_36.server.Participant;

import java.io.IOException;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Player {
	private PlayerColor playerColor;
	private PlayerIdentifier identifier;
	private final Participant participant;
	private PersonalBoard personalBoard;
	private Map<MemberColor, FamilyMember> familyMembers =
			new EnumMap<>(MemberColor.class);
	private PlayerState currentState;
	private Set<PlayerObserver> observers = new HashSet<>();

	public Player(PlayerColor playerColor, Participant participant)
			throws IOException {

		this.playerColor = playerColor;
		this.participant = participant;

		this.identifier = new PlayerIdentifier(playerColor.toString());

		participant.setIdentifier(this.identifier);

		setCurrentState(PlayerState.UNINITIALIZED);
	}

	public void init(PersonalBoard personalBoard)
			throws IllegalStateException, IOException {
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

	public Map<MemberColor, FamilyMember> getFamilyMembers() {

		return familyMembers;
	}

	public Participant getParticipant() {
		return participant;
	}

	@Override
	public String toString() {
		return identifier.get();
	}

	public void subscribe(PlayerObserver o) {
		observers.add(o);
	}

	public void subscribe(ModelObserver o) {
		observers.add(o);
		personalBoard.subscribe(o);
	}

	private void newStateNotify() throws IOException {
		for (PlayerObserver o : observers) {
			o.update(currentState);
		}
	}

	private void setCurrentState(PlayerState newState) throws IOException {
		currentState = newState;
		newStateNotify();
	}

	public PlayerIdentifier getIdentifier() {
		return identifier;
	}

	public int getFamilyMemberValue(MemberColor memberColor) {
		int value = familyMembers.get(memberColor).getValue();
		return value;
	}

	public boolean canEnter(Tower tower) {
		for (FamilyMember member : familyMembers.values()) {
			if (member.getColor() != MemberColor.UNCOLORED) {
				ActionSpace as = Game.getInstance().getBoard().getActionSpace(
						member.getLocation());
				if (as.getAssociatedFloor().getAssociatedTower().equals(
						tower)) {
					return false;
				}
			}
		}

		return true;
	}
}
