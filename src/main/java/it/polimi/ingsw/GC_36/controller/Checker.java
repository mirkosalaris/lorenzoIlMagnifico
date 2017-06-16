package it.polimi.ingsw.GC_36.controller;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.model.*;

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
		ResourcesList paymentList = new ResourcesList();
		ActionSpaceIds id = action.getActionSpaceId();
		Tower tower = Game.getInstance().getBoard().getActionSpace(
				id).getAssociatedFloor().getAssociatedTower();

		//check if familyMember is available
		if (!checkFamilyMember(action.getMemberColor(), player)) {
			return false;
		}

		//check if the action is dooable
		if (!checkActionSpace(action, tower, player))
			return false;

		compilePaymentList(id, paymentList, tower);

		if (!playerResources.checkEnoughResources(paymentList)) {
			return false;
		}
		playerResources.subtractResources(paymentList);

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


	private void compilePaymentList(ActionSpaceIds id,
	                                ResourcesList paymentList, Tower tower) {
		//TODO
		//compila la lista complessiva da pagare considerando affitto, carta
		// se in una torre, valore azione

		if (Commons.isFloor(id)) {
			//considera eventuale affitto e le risorse da pagare della carta

			if (!(tower.isFree())) {
				//la torre e' occupata, si paga l'affitto
				paymentList.addResources(tower.getTax());
			}
			DevelopmentCard card = Game.getInstance().getBoard()
					.getActionSpace(
							id).getAssociatedFloor().readAssociatedCard();
			//vede in action con quale opzione si e' deciso di pagare la carta
			int choice = action.getCardPaymentOption();
			//prende la lista associata all'opzione
			ResourcesList cardPayment = card.getRequirements().get(choice);
			//complila la lista di pagamento
			paymentList.addResources(cardPayment);
		}
		//considera i servants extra usati per incrementare il valore
		// dell'azione
		ResourcesList actionValuePayment = new ResourcesList();
		actionValuePayment.set(ResourceType.SERVANT,
				action.getActionValueIncrement());
		paymentList.addResources(actionValuePayment);

	}


	public boolean checkFamilyMember(MemberColor memberColor, Player player) {
		return player.getFamilyMembers().get(memberColor).isAvailable();
	}

	public boolean checkActionSpace(Action action,
	                                Tower tower, Player player) {
		ActionSpaceIds id = action.getActionSpaceId();

		if (Commons.isFloor(id)) {
			//controlla se si puo' entrare nella torre
			if (!(action.getMemberColor() == MemberColor.UNCOLORED)) {
				if (!player.canEnter(tower)) {
					return false;
				}
			}
		}
		//controlla se actionSpace e' accessibile e se il valore dell'azione
		// e' sufficiente
		ActionSpace actionSpace = Game.getInstance().getBoard().getActionSpace(
				id);
		boolean available = actionSpace.isAvailable();
		int requiredActionValue = actionSpace.getRequiredActionValue();
		boolean isSufficient = (action.getActionValue(
				player) >= requiredActionValue);

		return (available && isSufficient);
	}


	public boolean checkProduction(Action action, ResourcesList
			playerResources,
	                               Player player) {
		List<DevelopmentCard> buildingsCard = player.getPersonalBoard()
				.getCards(CardType.BUILDING);
		for (DevelopmentCard card : buildingsCard) {
			// TODO @antonino
			//if (!card.getPermanentEffect().check(playerResources))
			//	return false;

		}
		return true;
	}

	public boolean isProduction(ActionSpaceIds id) {
		return (id.equals(ActionSpaceIds.AS_PRODUCTION) || id.equals(
				ActionSpaceIds.AS_PRODUCTION_BIG)) ? true : false;
	}


}
