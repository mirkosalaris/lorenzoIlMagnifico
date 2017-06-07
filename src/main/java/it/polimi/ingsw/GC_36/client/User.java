package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.model.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class User extends UnicastRemoteObject implements UserInterface {
	private transient Player player;
	private transient ViewInterface view;
	private transient HashMap<ActionSpaceIds, DevelopmentCard> cards;

	public User(ViewInterface view) throws RemoteException {
		this.view = view;
	}

	private void chooseMemberColor(Action action) {
		MemberColor memberColor = view.chooseMemberColor();
		action.setMemberColor(memberColor);
	}

	private void chooseActionSpace(Action action) {
		int id;
		do {
			id = view.chooseActionSpaceId();
		} while (!ActionSpaceIds.checkId(id));
		ActionSpaceIds[] actionSpaceIds = ActionSpaceIds.values();
		ActionSpaceIds actionSpaceId = actionSpaceIds[id];
		action.setActionSpaceIds(actionSpaceId);
		DevelopmentCard card = cards.get(actionSpaceId);
		if (!(card == null)) {
			card.getImmediateEffect().chooseOptions(view, action);
		}
		if (actionSpaceId == ActionSpaceIds.AS_COUNCIL) {
			int choice;
			//check the choice
			choice = view.choosePrivilege(1);
			//check the choice
			action.putPrivilegeChoice(choice);

		}

	}

	private void compilePaymentResourcesList(Action action) {
		// TODO auto-compilation for simple cases?

		// TODO do we really need to check here for resources of player?
		// we don't have a reference to player!!!

		ResourcesList paymentList = new ResourcesList();
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
	}

	@Override
	public void update() throws RemoteException {
		// TODO think and impl
		view.update();
	}

	@Override
	public void update(BoardState currentState) throws RemoteException {
		// TODO think and impl
		view.update(currentState);
	}

	@Override
	public void update(PlayerState newState) throws RemoteException {
		// TODO think and impl
		view.update(newState);
	}

	@Override
	public void update(ActionSpaceIds id, boolean free) throws
			RemoteException {
		// TODO think and impl
		view.update(id, free);
	}

	@Override
	public void fatalError(String s) throws RemoteException {
		// TODO think and impl
		view.fatalError(s);
	}

	@Override
	public void exit() throws RemoteException {

	}

	@Override
	public void play(Action action)
			throws IOException, ClassNotFoundException {

		// this change action through setter. It's to be this way for safety:
		// there can't be a way to construct a new Action. The user has to use
		// the one passed

		chooseMemberColor(action);
		chooseActionSpace(action);
		if (Commons.isFloor(action.getActionSpaceId())) {
			compilePaymentResourcesList(action);
		}
	}

	@Override
	public void update(GameState newState) throws RemoteException {
		// TODO think and impl
		view.update(newState);
	}

	@Override
	public void update(int periodNumber) throws RemoteException {
		// TODO think and impl
		view.update(periodNumber);
	}

	@Override
	public void update(RoundState newState) throws RemoteException {
		// TODO think and impl
		view.update(newState);
	}

	@Override
	public void update(Player newPlayer) throws RemoteException {
		// DO NOT inform user HERE of his turn
		if (!player.equals(newPlayer)) {
			view.update(newPlayer);
		}
	}

	@Override
	public void update(int floorNumber, Tower tower,
	                   DevelopmentCard developmentCard) throws
			RemoteException {

		// TODO save developmentCard in cards
		view.update(floorNumber, tower, developmentCard);
	}
}
