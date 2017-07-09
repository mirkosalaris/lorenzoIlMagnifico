package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.observers.BoardObserver;
import it.polimi.ingsw.GC_36.observers.ModelObserver;
import it.polimi.ingsw.GC_36.server.Participant;

import java.io.IOException;
import java.util.*;

public class Board {
	private TurnOrder turnOrder;
	private Map<PlayerColor, Player> players = null;
	private Map<DieColor, Die> dice;
	private DeckSet deckSet;

	private BoardState currentState;

	private Set<BoardObserver> boardObservers;
	private Map<ActionSpaceIds, ActionSpace> actionSpaces;
	private Map<CardType, Tower> towers;
	private Map<Participant, Player> outOfGame = new HashMap<>();

	public Board() throws IOException {
		// Board is being constructed, but it WON'T be initialized after that
		boardObservers = new HashSet<>();
		dice = Commons.diceInitializer();
		towers = initTowers();
		actionSpaces = setActionSpaces();

		setCurrentState(BoardState.UNINITIALIZED);
	}

	private Map<ActionSpaceIds, ActionSpace> setActionSpaces() {
		Map<ActionSpaceIds, ActionSpace> map = new EnumMap<>(
				ActionSpaceIds.class);

		for (ActionSpaceIds id : ActionSpaceIds.values()) {
			map.put(id, new ActionSpace(id, this));
		}

		return map;
	}

	public void setPlayers(Map<PlayerColor, Player> players)
			throws IllegalStateException, IOException {
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
	public void prepare(DeckSet deckSet) throws IOException {
		if (currentState != BoardState.UNINITIALIZED) {
			throw new IllegalStateException(
					"to prepare Board it has to be uninitialized");
		}

		setCurrentState(BoardState.PREPARING);

		this.deckSet = deckSet;
		prepareFloors();
		rollDice();

		setCurrentState(BoardState.READY);
	}

	public Map<DieColor, Die> getDice() {
		return dice;
	}

	public ActionSpace getActionSpace(ActionSpaceIds id) {
		return actionSpaces.get(id);
	}

	// called at the end of every round
	public void clean() throws IOException {
		// reset deckSet
		deckSet = null;

		// reset all actionSpaces
		for (ActionSpace as : actionSpaces.values()) {
			as.reset();
		}

		// reset players
		for (Player p : players.values()) {
			p.roundReset();
		}

		setCurrentState(BoardState.UNINITIALIZED);
	}

	public DeckSet getDeckSet() {
		return deckSet;
	}

	public TurnOrder getTurnOrder() {
		return turnOrder;
	}

	public Tower getTower(CardType cardType) {
		return towers.get(cardType);
	}

	public void rollDice() throws IOException {
		for (Die die : dice.values()) {
			die.roll();
		}

		changeDieNotify();
	}

	public void setOutOfGame(Player player) {
		turnOrder.setOutOfGame(player);
		outOfGame.put(player.getParticipant(), player);
	}

	public void rejoin(Participant participant) {
		turnOrder.rejoin(outOfGame.get(participant));
		outOfGame.remove(participant);
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
		for (Tower tower : towers.values()) {
			for (int i = 0; i < Commons.NUMBER_OF_FLOORS; i++) {
				tower.getFloor(i + 1).subscribe(o);
			}
		}
	}

	private Map<CardType, Tower> initTowers() {
		Map<CardType, Tower> map = new EnumMap<>(CardType.class);
		for (CardType type : CardType.values()) {
			Tower tower = new Tower(type);
			map.put(type, tower);
		}
		return map;
	}

	private void prepareFloors() throws IOException {
		// distribute cards in towers

		// iterate on towers

		DevelopmentCard card;
		for (CardType type : CardType.values()) {
			Tower tower = towers.get(type);

			// iterate on floors of tower
			for (int i = 0; i < Commons.NUMBER_OF_FLOORS; i++) {
				card = deckSet.getDeck(type).popCard();

				tower.getFloor(i + 1).setDevelopmentCard(card);
			}
		}
	}

	private void setCurrentState(BoardState newState) throws IOException {
		this.currentState = newState;
		newStateNotify();
	}

	private void newStateNotify() throws IOException {
		for (BoardObserver o : boardObservers) {
			o.update(currentState);
		}
	}

	private void changeDieNotify() throws IOException {
		for (BoardObserver o : boardObservers) {
			for (Die die : dice.values()) {
				o.update(die.getDieColor(), die.getValue());
			}
		}
	}

	public Map<PlayerColor, Player> getPlayers() {
		return players;
	}
}
