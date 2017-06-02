package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.model.*;

import java.util.HashMap;

public class User {
	private Player player;
	private ViewInterface view;
	private HashMap<ActionSpaceIds, DevelopmentCard> cards;

	public User(ViewInterface view) {
		this.view = view;
	}

	public void execute(Action action) {
		// this change action through setter. It's to be this way for safety:
		// there can't be a way to construct a new Action. The user has to use
		// the one passed

		chooseMemberColor(action);
		chooseActionSpace(action);

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
			int choose;
			//check the choice
			choose = view.choosePrivilege(1);
			//check the choice
			action.putPrivilegeChoose(choose);

		}

	}

	private void compilePaymentResourcesList(Action action) {
		ResourcesList paymentList = new ResourcesList();
		int woods, stones, coins, servant, victorypoints, militarypoints,
				faithpoints;
		do {
			woods = view.selectNumberOfWoods();
		} while (!(player.getPersonalBoard().getResourcesList().get(
				ResourceType.WOOD).getValue() >= woods));

		do {
			stones = view.selectNumberOfStones();
		} while (player.getPersonalBoard().getResourcesList().get(
				ResourceType.STONE).getValue() >= stones);
		do {
			servant = view.selectNumberOfServants();
		} while (!(player.getPersonalBoard().getResourcesList().get(
				ResourceType.SERVANT).getValue() >= servant));
		do {
			coins = view.selectNumberOfCoins();
		} while (!(player.getPersonalBoard().getResourcesList().get(
				ResourceType.COINS).getValue() >= coins));
		do {
			victorypoints = view.selectNumberOfVictoryPoints();
		} while (!(player.getPersonalBoard().getResourcesList().get(
				ResourceType.VICTORY_POINTS).getValue() >= victorypoints));
		do {
			militarypoints = view.selectNumberOfMilitaryPoints();
		} while (!(player.getPersonalBoard().getResourcesList().get(
				ResourceType.MILITARY_POINTS).getValue() >= militarypoints));
		do {
			faithpoints = view.selectNumberOfFaithPoints();
		} while (!(player.getPersonalBoard().getResourcesList().get(
				ResourceType.FAITH_POINTS).getValue() >= faithpoints));

		paymentList.set(ResourceType.WOOD, woods);
		paymentList.set(ResourceType.STONE, stones);
		paymentList.set(ResourceType.SERVANT, servant);
		paymentList.set(ResourceType.COINS, coins);
		paymentList.set(ResourceType.VICTORY_POINTS, victorypoints);
		paymentList.set(ResourceType.MILITARY_POINTS, militarypoints);
		paymentList.set(ResourceType.FAITH_POINTS, faithpoints);

		action.setPaymentList(paymentList);

	}

}
