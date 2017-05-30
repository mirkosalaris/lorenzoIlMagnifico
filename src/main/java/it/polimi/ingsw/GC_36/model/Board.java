package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.observers.BoardState;

import java.util.*;

public class Board {
	private TurnOrder turnOrder;
	private Map<PlayerColor, Player> players = null;
	private EnumMap<DieColor, Die> dice;
	private DeckSet deckSet;

	private BoardState currentState;

	private Set<BoardObserver> observers = new HashSet<>();
	private Map<Integer, ActionSpace> actionSpaces;

	public Board() {
		// Board is being constructed, but it WON'T be initialized after that
		setCurrentState(BoardState.UNINITIALIZED);

		dice = Commons.diceInitializer();
		actionSpaces = Commons.actionSpacesInitializer();
	}

	public void initPlayers(Map<PlayerColor, Player> players) {
		// initialize players and store them

		// initialize players
		List<Player> p = new ArrayList<>(players.values());
		for (int i = 0; i < p.size(); i++) {
			PersonalBoard playerBoard = new PersonalBoard(i + 1);
			p.get(i).init(playerBoard);
		}

		// store the list of players and create a turnOrder instance
		if (this.players == null) {
			this.players = players;
			turnOrder = new TurnOrder(players);
		} else {
			throw new IllegalStateException(
					"The list of players can be set just once");
		}

	}

	// called before every Round
	public void initialize(DeckSet deckSet) {
		if (currentState != BoardState.UNINITIALIZED) {
			throw new IllegalStateException(
					"to initialize Board it has to be uninitialized");
		}

		setCurrentState(BoardState.INITIALIZING);
		this.deckSet = deckSet;
		initTowers();
		setCurrentState(BoardState.READY);
	}

	// TODO: test
	public Map<DieColor, Die> getDice() {
		return dice;
	}

	public void clean() {
		// reset deckSet
		deckSet = null;

		// reset all actionSpaces
		for (ActionSpace as : actionSpaces.values()) {
			// TODO: uncomment when Parser implemented
			// as.reset();
		}

		setCurrentState(BoardState.UNINITIALIZED);
	}

	public DeckSet getDeckSet() {
		return deckSet;
	}

	public TurnOrder getTurnOrder() {
		return turnOrder;
	}

	private void setCurrentState(BoardState newState) {
		this.currentState = newState;
		newStateNotify();
	}

	private void initTowers() {
		// distribute cards in towers

		// iterate on towers
		for (Tower tower : Tower.values()) {

			// iterate on floors of tower
			for (int i = 0; i < Commons.NUMBER_OF_FLOORS; i++) {
				DevelopmentCard card =
						deckSet.getDeck(tower.getCardType()).popCard();

				tower.getFloor(i).setDevelopmentCard(card);
			}
		}
	}

	public void subscribe(BoardObserver o) {
		observers.add(o);
	}

	private void newStateNotify() {
		for (BoardObserver o : observers) {
			o.update(currentState);
		}
	}
}
