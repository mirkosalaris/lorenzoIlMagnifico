package it.polimi.ingsw.GC_36.controller;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.util.List;

public class Checker {
	private Action action;

	public boolean check(Action action) {
		Player player = Game.getInstance().getCurrentPeriod()
				.getCurrentRound().getCurrentPlayer();
		ResourcesList playerResources = player.getPersonalBoard()
				.getResourcesList();

		return check(action, playerResources);
	}

	private boolean check(Action action, ResourcesList playerResources) {
		Player player = Game.getInstance().getCurrentPeriod()
				.getCurrentRound().getCurrentPlayer();
		ActionSpaceIds id = action.getActionSpaceId();
		Pair<ResourcesList, ResourcesList> paymentList = action
				.compilePaymentList();

		//check if familyMember is available
		if (!checkFamilyMember(action.getMemberColor(), player)) {
			return false;
		}

		//check if the user can enter in the action Space
		if (!checkActionSpace(action, player))
			return false;

		if (!playerResources.checkEnoughResources(paymentList.getFirst())) {
			return false;
		}
		playerResources.subtractResources(paymentList.getSecond());

		if (isProduction(id)) {
			if (!checkProduction(action, playerResources, player))
				return false;
		}

		Action extraAction = action.getExtraAction();
		if (extraAction != null) {
			if (!check(extraAction, playerResources)) {
				return false;
			}
		}

		return true;
	}


	private boolean checkFamilyMember(MemberColor memberColor, Player player) {
		return player.getFamilyMember(memberColor).isAvailable();
	}

	private boolean checkActionSpace(Action action, Player player) {
		ActionSpaceIds id = action.getActionSpaceId();

		if (Commons.isFloor(id)) {
			Tower tower = Game.getInstance().getBoard().getActionSpace(
					id).getAssociatedFloor().getAssociatedTower();

			// check if the player can enter in the tower
			if (!(action.getMemberColor() == MemberColor.UNCOLORED)) {
				if (!player.canEnter(tower)) {
					return false;
				}
			}
		}

		// check if the action space is available and if the action value is
		// enough
		ActionSpace actionSpace = Game.getInstance().getBoard().getActionSpace(
				id);
		boolean available = actionSpace.isAvailable();

		int requiredActionValue = actionSpace.getRequiredActionValue();
		boolean isSufficient =
				(action.getActionValue(player) >= requiredActionValue);

		return (available && isSufficient);
	}


	private boolean checkProduction(Action action,
	                                ResourcesList playerResources,
	                                Player player) {
		List<DevelopmentCard> buildingsCard = player.getPersonalBoard()
				.getCards(CardType.BUILDING);
		for (DevelopmentCard card : buildingsCard) {
			// TODO @antonino
			if (!card.getPermanentEffect().check(action, playerResources)) {
				//	return false;
			}

		}
		return true;
	}

	private boolean isProduction(ActionSpaceIds id) {
		return id.equals(ActionSpaceIds.AS_PRODUCTION)
				|| id.equals(ActionSpaceIds.AS_PRODUCTION_BIG);
	}

}
