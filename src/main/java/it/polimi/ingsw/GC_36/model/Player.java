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
	private List<LeaderCard> usedLeaderCards = new ArrayList<>();

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

		// subscribe participant to personalBoard
		personalBoard.subscribe(participant);

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
		// reset family members
		for (FamilyMember member : familyMembers.values()) {
			member.reset();
		}

		// reset leader cards
		usedLeaderCards.clear();
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

	public void subscribe(ModelObserver o) throws IOException {
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
				if (as != null) {
					if (as.getAssociatedFloor().getAssociatedTower()
							.equals(tower)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	public void addLeaderCard(LeaderCard card) {
		leaderCards.add(card);
	}

	public List<LeaderCard> getLeaderCards() {
		return getLeaderCards(false);
	}

	public List<LeaderCard> getLeaderCards(boolean onlyAvailable) {
		List<LeaderCard> result = new ArrayList<>(leaderCards);

		if (onlyAvailable) {
			result.removeAll(usedLeaderCards);
		}

		return result;
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

	public boolean canPlay() {
		int count = 0;
		for (FamilyMember member : familyMembers.values()) {
			count += member.isAvailable() ? 1 : 0;
		}
		if (count <= 0) {
			return false;
		} else {
			int servants = personalBoard.getResourcesList().get(
					ResourceType.SERVANT).getValue();
			boolean uncoloredAvailable = familyMembers.get(
					MemberColor.UNCOLORED).isAvailable();
			if (count == 1 && uncoloredAvailable && servants == 0) {
				return false;
			}
		}
		return true;
	}

	public PlayerColor getPlayerColor() {
		return playerColor;
	}

	public void setUsed(List<LeaderCard> cardsList) {
		if (!leaderCards.containsAll(cardsList)) {
			throw new IllegalArgumentException(
					"the players does not own all of those cards");
		}
		usedLeaderCards.addAll(cardsList);
	}
}
