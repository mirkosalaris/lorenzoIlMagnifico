package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.model.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class User extends UnicastRemoteObject implements UserInterface {
	private transient ViewInterface view;
	private transient Map<ActionSpaceIds, DevelopmentCard> cards;
	private transient Map<ActionSpaceIds, List<PlayerColor>> actionSpaces;
	private transient Map<CardType, List<DevelopmentCard>> ownedCards;

	private PlayerIdentifier identifier;
	private ResourcesList ownedResources;

	public User(ViewInterface view) throws RemoteException {
		this.view = view;
		cards = new EnumMap<>(ActionSpaceIds.class);
		actionSpaces = new EnumMap<>(ActionSpaceIds.class);
		ownedCards = new EnumMap<>(CardType.class);
	}

	@Override
	public void fatalError(String s) throws IOException {
		// TODO think and impl
		view.fatalError(s);
	}

	@Override
	public void play(ActionInterface action)
			throws IOException, ClassNotFoundException {
		// this change action through setter. It's to be this way for safety:
		// there can't be a way to construct a new Action. The user has to use
		// the one passed

		view.play(action);

		chooseMemberColor(action);
		chooseActionSpace(action);
		compilePaymentResourcesList(action);
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
	public void update() throws IOException {
		// a new round has started
		roundReset();
		view.update();
	}

	@Override
	public void update(BoardState currentState) throws IOException {
		// TODO think and impl
		view.update(currentState);
	}

	@Override
	public void update(PlayerState newState) throws IOException {
		// TODO think and impl
		view.update(newState);
	}

	@Override
	public void update(ActionSpaceIds id, boolean free) throws
			IOException {
		// TODO think and impl
		view.update(id, free);
	}

	@Override
	public void update(ActionSpaceIds id, PlayerColor playerColor)
			throws IOException {
		view.update(id, playerColor);
	}

	@Override
	public void update(GameState newState) throws IOException {
		// TODO think and impl
		view.update(newState);
	}

	@Override
	public void update(int periodNumber) throws IOException {
		// TODO think and impl
		view.update(periodNumber);
	}

	@Override
	public void update(RoundState newState) throws IOException {
		// TODO think and impl
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
	public void update(int floorNumber, DevelopmentCard developmentCard)
			throws IOException {

		ActionSpaceIds id = Commons.getAssociatedActionSpaceIds(
				developmentCard.getType(), floorNumber);
		cards.put(id, developmentCard);

		view.update(floorNumber, developmentCard);
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

	private void chooseMemberColor(ActionInterface actionInterface)
			throws RemoteException {
		MemberColor memberColor = view.chooseMemberColor();
		actionInterface.setMemberColor(memberColor);
	}

	private void chooseActionSpace(ActionInterface action)
			throws RemoteException {
		int id;
		boolean wrong = true;
		ActionSpaceIds actionSpaceId;
		do {
			id = view.chooseActionSpaceId();
			if (ActionSpaceIds.checkId(id)) {
				actionSpaceId = ActionSpaceIds.values()[id];
				if (action.isAvailable(actionSpaceId)) {
					wrong = false;
					action.setActionSpaceIds(actionSpaceId);
				}
			}

		} while (wrong);
	}

	private void actionSpaceHandler(ActionInterface action)
			throws IOException, ClassNotFoundException {
		if (Commons.isFloor(action.getActionSpaceId())) {
			compilePaymentResourcesList(action);
			DevelopmentCard card = cards.get(action.getActionSpaceId());
			card.getImmediateEffect().chooseOptions(view, action, this);
		}

		if ((action.getActionSpaceId()) == ActionSpaceIds.AS_COUNCIL)

		{
			int choice;
			// TODO check the choice
			choice = view.choosePrivilege(1);
			// TODO check the choice
			action.putPrivilegeChoice(choice);

		}

		if ((action.getActionSpaceId() == ActionSpaceIds.AS_HARVEST) ||
				(action.getActionSpaceId() == ActionSpaceIds.AS_HARVEST_BIG)) {
			//TODO: harvest
		}

		if ((action.getActionSpaceId() == ActionSpaceIds.AS_PRODUCTION) ||
				(action.getActionSpaceId() == ActionSpaceIds
						.AS_PRODUCTION_BIG)) {
			//TODO: production
			//scorre la lista di carte territorio del player e chiama la
			// chooseoption che si occupa di salvare la scelta nella action
			List<DevelopmentCard> terrytoriCards = ownedCards.get(
					CardType.BUILDING);
			for (int i = 0; i < terrytoriCards.size(); i++) {
				terrytoriCards.get(i).getPermanentEffect().chooseOption(view,
						action, this);
			}
		}

	}

	private void compilePaymentResourcesList(ActionInterface actionInterface) {
		// TODO auto-compilation for simple cases?

		// TODO do we really need to check here for resources of player?
		// we don't have a reference to player!!!

		/*ResourcesList paymentList = new ResourcesList();
		ResourcesList actualResources = player.getPersonalBoard()
				.getResourcesList();

		int woods, stones, coins, servants, victoryPoints, militaryPoints,
				faithPoints;
		do {
			woods = view.selectNumberOfWoods();
		} while (woods > actualResources.get(ResourceType.WOOD).getValue());

		do {
			stones = view.selectNumberOfStones();
		} while (stones > actualResources.get(ResourceType.STONE).getValue());
		do {
			servants = view.selectNumberOfServants();
		} while (servants >
				actualResources.get(ResourceType.SERVANT).getValue());
		do {
			coins = view.selectNumberOfCoins();
		} while (coins > actualResources.get(ResourceType.COINS).getValue());
		do {
			victoryPoints = view.selectNumberOfVictoryPoints();
		} while (victoryPoints >
				actualResources.get(ResourceType.VICTORY_POINTS).getValue());
		do {
			militaryPoints = view.selectNumberOfMilitaryPoints();
		} while (militaryPoints >
				actualResources.get(ResourceType.MILITARY_POINTS).getValue());
		do {
			faithPoints = view.selectNumberOfFaithPoints();
		} while (faithPoints >
				actualResources.get(ResourceType.FAITH_POINTS).getValue());

		paymentList.set(ResourceType.WOOD, woods);
		paymentList.set(ResourceType.STONE, stones);
		paymentList.set(ResourceType.SERVANT, servants);
		paymentList.set(ResourceType.COINS, coins);
		paymentList.set(ResourceType.VICTORY_POINTS, victoryPoints);
		paymentList.set(ResourceType.MILITARY_POINTS, militaryPoints);
		paymentList.set(ResourceType.FAITH_POINTS, faithPoints);

		action.setPaymentList(paymentList);
		*/
	}

	private void roundReset() {
		cards.clear();
		actionSpaces.clear();
	}
}
