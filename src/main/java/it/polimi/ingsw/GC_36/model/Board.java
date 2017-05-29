package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.observers.BoardState;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Board {
	private TurnOrder turnOrder;
	private List<Player> players;
	private Map<Integer, Die> dice;
	private DeckSet deckSet;

	private BoardState currentState;

	private Set<BoardObserver> observers = new HashSet<>();
	private Map<Integer, ActionSpace> actionSpaces;

	public Board(List<Player> players) {
		// Board is being constructed, but it WON'T be initialized after that
		currentState = BoardState.UNINITIALIZED;

		this.players = players;
		turnOrder = new TurnOrder(players);
		dice = Commons.diceInitializer();
		actionSpaces = Commons.actionSpacesInitializer();
	}

	public void initialize(DeckSet deckSet) {
		if (currentState != BoardState.UNINITIALIZED) {
			throw new IllegalStateException(
					"to initialize Board it has to be uninitialized");
		}

		setCurrentState(BoardState.INITIALIZING);
		this.deckSet = deckSet;
		setCurrentState(BoardState.READY);
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

	public void setCurrentState(BoardState newState) {
		this.currentState = newState;
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
