package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.observers.BoardObserver;
import it.polimi.ingsw.GC_36.observers.ModelObserver;

import java.util.*;

public class Board {
	private TurnOrder turnOrder;
	private Map<PlayerColor, Player> players = null;
	private Map<DieColor, Die> dice;
	private DeckSet deckSet;

	private BoardState currentState;

	private Set<BoardObserver> boardObservers = new HashSet<>();
	private Map<ActionSpaceIds, ActionSpace> actionSpaces;

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

	// called at the end of every round
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

				tower.getFloor(i + 1).setDevelopmentCard(card);
			}
		}
	}

	public void subscribe(BoardObserver o) {
		boardObservers.add(o);
	}

	public void subscribe(ModelObserver o) {
		boardObservers.add(o);

		// subscribe to actionSpaces
		for (ActionSpace as : actionSpaces.values()) {
			as.subscribe(o);
		}

		// subscribe to Floors
		for (Tower tower : Tower.values()) {
			for (int i = 0; i < Commons.NUMBER_OF_FLOORS; i++) {
				tower.getFloor(i).subscribe(o);
			}
		}

		for (Player p : players.values()) {
			p.subscribe(o);
		}

	}

	private void newStateNotify() {
		for (BoardObserver o : boardObservers) {
			o.update(currentState);
		}
	}
}
