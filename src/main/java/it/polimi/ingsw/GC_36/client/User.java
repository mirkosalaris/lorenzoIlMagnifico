package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.client.view.ViewInterface;
import it.polimi.ingsw.GC_36.exception.NotAvailableException;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.utils.ExceptionLogger;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class User extends UnicastRemoteObject implements UserInterface {
	private transient ViewInterface view;
	private transient Map<ActionSpaceIds, DevelopmentCard> cards;
	private transient Map<ActionSpaceIds, List<PlayerColor>> actionSpaces;
	private transient Map<CardType, List<DevelopmentCard>> ownedCards;

	private PlayerIdentifier identifier;
	private ResourcesList ownedResources;
	private Set<ActionSpaceIds> availableActionSpaces;

	// actionSpaces used in the current action (and extra actions)
	private List<ActionSpaceIds> currentActionSpaces = new ArrayList<>();

	public User(ViewInterface view) throws RemoteException {
		this.view = view;
		cards = new EnumMap<>(ActionSpaceIds.class);
		actionSpaces = new EnumMap<>(ActionSpaceIds.class);
		availableActionSpaces =
				new HashSet<>(Arrays.asList(ActionSpaceIds.values()));
		ownedCards = new EnumMap<>(CardType.class);

		for (CardType type : CardType.values()) {
			ownedCards.put(type, new ArrayList<>());
		}
	}

	@Override
	public void fatalError(String s) throws IOException {
		view.fatalError(s);
	}

	@Override
	public void play(ActionInterface action)
			throws IOException, ClassNotFoundException {
		// this change action through setter. It's to be this way for safety:
		// there can't be a way to construct a new Action. The user has to use
		// the one passed

		view.play(action);

		chooseLeaderOptions(action);

		chooseMemberColor(action);
		chooseActionSpace(action);
		setActionValueIncrement(action);
		if (action.getActionSpaceId().isInFloor()) {
			chooseCardPaymentOptions(action);
		}
		actionSpaceHandler(action);

		// clear the list of currently used action spaces
		currentActionSpaces.clear();
	}

	@Override
	public void outOfTime() throws IOException {
		view.outOfTime();
	}

	@Override
	public void actionResult(boolean result) throws IOException {
		view.actionResult(result);
	}

	public void play(ExtraAction action)
			throws IOException, ClassNotFoundException {

		chooseActionSpace(action, action.getActionSpacesIds());
		setActionValueIncrement(action);
		if (action.getActionSpaceId().isInFloor()) {
			chooseCardPaymentOptions(action);
		}
		actionSpaceHandler(action);
	}

	@Override
	public void exit(String message) throws IOException {
		view.exit(message);
	}

	@Override
	public void setIdentifier(PlayerIdentifier identifier) throws IOException {
		this.identifier = identifier;
		view.setIdentifier(identifier);
	}

	@Override
	public int chooseLeaderCard(List<LeaderCard> leaderCards)
			throws IOException, ClassNotFoundException {
		return view.chooseLeaderCard(leaderCards);
	}

	@Override
	public LeaderCard useCard(List<LeaderCard> cardsAvailable)
			throws IOException, ClassNotFoundException {
		return view.useCard(cardsAvailable);
	}

	@Override
	public int chooseBonusTile()
			throws IOException, ClassNotFoundException {
		int choice;

		do {
			choice = view.chooseBonusTile();
		} while (BonusTileId.DEFAULT.value() == choice);

		return choice;
	}

	@Override
	public void terminatedRound() throws IOException {
		// a new round has started
		roundReset();
		view.terminatedRound();
	}

	@Override
	public void update(BoardState currentState) throws IOException {
		view.update(currentState);
	}

	public void update(DieColor dieColor, int value) throws IOException {
		view.update(dieColor, value);
	}

	@Override
	public void update(PlayerState newState) throws IOException {
		view.update(newState);
	}

	@Override
	public void update(ActionSpaceIds id, boolean free) throws IOException {
		if (free) {
			availableActionSpaces.add(id);
		} else {
			availableActionSpaces.remove(id);
		}
		view.update(id, free);
	}

	@Override
	public void update(ActionSpaceIds id, PlayerColor playerColor,
	                   MemberColor memberColor)
			throws IOException {
		view.update(id, playerColor, memberColor);
	}

	@Override
	public void update(GameState newState) throws IOException {
		if (GameState.FINISHED.equals(newState)) {
			System.exit(0);
		}
		view.update(newState);

	}

	@Override
	public void update(int periodNumber) throws IOException {
		view.update(periodNumber);
	}

	@Override
	public void update(List<Pair<PlayerIdentifier, Integer>> winningOrderList)
			throws IOException {
		view.update(winningOrderList);
	}

	@Override
	public void update(RoundState newState) throws IOException {
		view.update(newState);
	}

	@Override
	public void update(PlayerIdentifier newPlayer) throws IOException {
		// DO NOT inform user HERE of his turn. That's the job of 'play'
		//if (!identifier.equals(newPlayer)) {
		if (!newPlayer.equals(identifier)) {
			view.update(newPlayer);
		}
	}

	@Override
	public void update(CardType cardType, int floorNumber,
	                   DevelopmentCard developmentCard)
			throws IOException {

		ActionSpaceIds id = Commons.getAssociatedActionSpaceIds(
				cardType, floorNumber);
		cards.put(id, developmentCard);

		view.update(cardType, floorNumber, developmentCard);
	}

	@Override
	public void update(DevelopmentCard card) throws IOException {
		ownedCards.get(card.getType()).add(card);
		view.update(card);
	}

	@Override
	public void update(ResourcesList resourcesList) throws IOException {
		ownedResources = resourcesList;
		view.update(ownedResources);
	}

	@Override
	public void show(String message) {
		view.show(message);
	}

	@Override
	public List<DevelopmentCard> getCards(CardType type) {
		// return a COPY of the list
		return new ArrayList<>(ownedCards.get(type));
	}

	@Override
	public GameMode chooseMode() throws RemoteException {
		return view.chooseMode();
	}

	@Override
	public void askRejoin() throws RemoteException {
		// wait until user wants to rejoin
		view.askToRejoin();
	}

	private void chooseLeaderOptions(ActionInterface action)
			throws IOException, ClassNotFoundException {
		List<LeaderCard> cards = action.getLeaderCards();
		for (LeaderCard card : cards) {
			card.getEffect().chooseOption(view, action, this);
		}
	}

	private void chooseMemberColor(ActionInterface actionInterface)
			throws RemoteException {
		MemberColor memberColor = view.chooseMemberColor();
		actionInterface.setMemberColor(memberColor);
	}

	private void chooseActionSpace(ActionInterface action)
			throws RemoteException {

		Set<ActionSpaceIds> fullSet = availableActionSpaces;
		chooseActionSpace(action, fullSet);
	}

	private void chooseActionSpace(ActionInterface action,
	                               Set<ActionSpaceIds> actionSpaces)
			throws RemoteException {
		int id;
		boolean wrong = true;
		ActionSpaceIds actionSpaceId;
		// calculate intersection with available ActionSpaces
		actionSpaces.retainAll(availableActionSpaces);
		actionSpaces.removeAll(currentActionSpaces);
		do {
			id = view.chooseActionSpaceId(actionSpaces);

			if (ActionSpaceIds.checkId(id)) {
				actionSpaceId = ActionSpaceIds.values()[id];

				if (action.isAvailable(actionSpaceId)) {
					wrong = false;
					try {
						action.setActionSpaceId(actionSpaceId);
						currentActionSpaces.add(actionSpaceId);
					} catch (NotAvailableException e) {
						ExceptionLogger.log(e);
						wrong = true;
					}
				}
			}

		} while (wrong);
	}

	private void actionSpaceHandler(ActionInterface action)
			throws IOException, ClassNotFoundException {
		if (Commons.isFloor(action.getActionSpaceId())) {
			DevelopmentCard card = cards.get(action.getActionSpaceId());
			if (card.getImmediateEffect() != null) {
				card.getImmediateEffect().chooseOptions(view, action, this);
			}
		}

		ActionSpaceIds asId = action.getActionSpaceId();

		if (ActionSpaceIds.AS_COUNCIL.equals(asId)) {
			List<Integer> choices = chooseCouncilPrivileges(1);
			action.putPrivilegeChoice(choices.get(0));
		} else if (ActionSpaceIds.AS_MARKET_COUNCILS_FAVORS.equals(asId)) {
			List<Integer> choices;
			boolean error = false;
			do {
				error = false;
				choices = chooseCouncilPrivileges(2);
				// check duplicates
				if (new HashSet<>(choices).size() != 2) {
					error = true;
					view.show("Privileges must differ");
				}
			} while (error);

			for (Integer choice : choices) {
				action.putPrivilegeChoice(choice);
			}
		}

		List<DevelopmentCard> cards = null;
		if ((action.getActionSpaceId() == ActionSpaceIds.AS_HARVEST) ||
				(action.getActionSpaceId() == ActionSpaceIds.AS_HARVEST_BIG)) {
			cards = ownedCards.get(CardType.TERRITORY);
		} else if (
				(action.getActionSpaceId() == ActionSpaceIds.AS_PRODUCTION) ||
						(action.getActionSpaceId() == ActionSpaceIds
								.AS_PRODUCTION_BIG)) {
			cards = ownedCards.get(CardType.BUILDING);
		}
		if (cards != null) {
			//invokes the chooseoption method for each card of the player
			//to save the choice
			for (DevelopmentCard card : cards) {
				if (card.getPermanentEffect() != null) {
					card.getPermanentEffect().chooseOption(view, action, this);
				}
			}
		}
	}

	private List<Integer> chooseCouncilPrivileges(int n) {
		List<Integer> choices = new ArrayList<>();
		int choice;
		for (int i = 0; i < n; i++) {
			do {
				choice = view.choosePrivilege(i + 1);
			} while ((choice < 0)
					|| (choice > (CouncilPrivilege.values().length - 1)));

			choices.add(choice);
		}
		return choices;
	}


	private void roundReset() {
		cards.clear();
		actionSpaces.clear();
	}

	private void setActionValueIncrement(ActionInterface action)
			throws RemoteException {
		int increment;
		increment = view.setActionValueIncrement();
		action.setActionValueIncrement(increment);
	}

	/**
	 * Access the card chosen by the user and ask him/her to make a payment
	 * choice, if there's a choice to make
	 *
	 * @param action
	 * 		from where to take info and where to store new info
	 * @throws RemoteException
	 */
	private void chooseCardPaymentOptions(ActionInterface action)
			throws RemoteException {
		// take the card from the local Map<ActionSpaceIds, Cards>
		DevelopmentCard card = cards.get(action.getActionSpaceId());
		int choice;
		choice = card.getRequirements().size() > 1
				? view.chooseCardPaymentOptions(card) : 0;

		// store in action the choice
		action.setCardPaymentOptions(choice);

	}
}