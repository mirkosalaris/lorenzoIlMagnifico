package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.observers.ModelObserver;
import it.polimi.ingsw.GC_36.observers.PlayerObserver;
import it.polimi.ingsw.GC_36.server.Participant;

import java.io.IOException;
import java.util.*;

public class Player {
	private PlayerColor playerColor;
	private PlayerIdentifier identifier;
	private final Participant participant;
	private PersonalBoard personalBoard;
	private Map<MemberColor, FamilyMember> familyMembers =
			new EnumMap<>(MemberColor.class);
	private PlayerState currentState;
	private Set<PlayerObserver> observers = new HashSet<>();
	private List<LeaderCard> leaderCards = new ArrayList<>();

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

	public void setBonusTile(BonusTileId id) {
		personalBoard.setBonusTile(id);
	}

	public PersonalBoard getPersonalBoard() {
		return personalBoard;
	}

	public FamilyMember getFamilyMember(MemberColor color) {
		// return a copy
		return familyMembers.get(color);
	}

	public void roundReset() {
		// TODO @mirko how it is that this is never invoked?
		for (FamilyMember member : familyMembers.values()) {
			member.reset();
		}
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

	public PlayerIdentifier getIdentifier() {
		return identifier;
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

	public void addLeaderCard(LeaderCard card) {
		leaderCards.add(card);
	}

	public ArrayList<LeaderCard> getLeaderCards() {
		return new ArrayList<>(leaderCards);
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
}
