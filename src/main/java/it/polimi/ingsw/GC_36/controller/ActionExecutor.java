package it.polimi.ingsw.GC_36.controller;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.exception.InsufficientResourcesException;
import it.polimi.ingsw.GC_36.model.*;

import java.io.IOException;
import java.util.List;

public class ActionExecutor {

	private Checker checker;

	public ActionExecutor() {
		this.checker = new Checker();
	}

	public boolean execute(Action action) {
		// we should pass a copy of paymentList, but we don't use it anymore
		if (!checker.check(action, compilePaymentList(action))) {
			return false;
		}

		return execute(action, true);
	}

	private boolean execute(Action action, boolean alreadyChecked) {
		// TODO: manage action value. How do we take into account penalties?

		ResourcesList paymentList = compilePaymentList(action);
		/* NB: there's no way to 'share' the paymentList between the two
		execute() methods, because this method is called even for extraTurn,
		and the paymentList would be different.
		 */

		if (!alreadyChecked) {
			if (!checker.check(action, compilePaymentList(action).copy())) {
				return false;
			}
		}


		Player player = Game.getInstance().getCurrentPeriod()
				.getCurrentRound().getCurrentPlayer();
		FamilyMember member = player.getFamilyMember(action.getMemberColor());
		ActionSpace actionSpace = Game.getInstance().getBoard()
				.getActionSpace(action.getActionSpaceId());

		try {
			player.getPersonalBoard().payResources(paymentList);

			// member.setLocation is invoked by actionSpace.occupy()
			actionSpace.occupy(member);

			player.getPersonalBoard().addResources(actionSpace.getBonus());
			if (actionSpace.isInTower()) {
				DevelopmentCard card =
						actionSpace.getAssociatedFloor().takeCard();

				card.getImmediateEffect().applyEffect(action, player);
			}

			if (isProduction(actionSpace)) {
				executeProduction(action, player);
			}

			if (isHarvest(actionSpace)) {
				executeHarvest(action, player);
			}

			// TODO @mirko council privileges

			Action extraAction = action.getExtraAction();
			if (extraAction != null) {
				execute(extraAction, true);
			}

		} catch (InsufficientResourcesException | EffectApplyingException |
				IOException e) {
			throw new IllegalStateException(e);
		}

		return true;
	}

	/**
	 * compile the whole list of resources to pay, taking into account tower
	 * taxes, development card, increased action value.
	 * NB: this does not take into account the ExtraAction!
	 *
	 * @param action
	 * 		the user's action
	 * @return the resourcesList to pay
	 */
	private ResourcesList compilePaymentList(Action action) {
		ResourcesList paymentList = new ResourcesList();
		ActionSpaceIds id = action.getActionSpaceId();


		if (Commons.isFloor(id)) {
			Tower tower = Game.getInstance().getBoard().getActionSpace(
					id).getAssociatedFloor().getAssociatedTower();

			if (!(tower.isFree())) {
				// the tower is not free, pay the tax
				paymentList.addResources(tower.getTax());
			}
			DevelopmentCard card = Game.getInstance().getBoard()
					.getActionSpace(
							id).getAssociatedFloor().readAssociatedCard();

			// get from action the user's choice on how to pay the card
			int choice = action.getCardPaymentOption();

			// take the resourcesList associated with the user's choice
			ResourcesList cardPayment = card.getRequirements().get(choice);

			// add to the payment list
			paymentList.addResources(cardPayment);
		}

		// pay the extra servants used to increase the action value
		ResourcesList actionValuePayment = new ResourcesList();
		actionValuePayment.set(ResourceType.SERVANT,
				action.getActionValueIncrement());
		paymentList.addResources(actionValuePayment);

		return paymentList;
	}

	private boolean isProduction(ActionSpace actionSpace) {
		ActionSpaceIds id = actionSpace.getId();
		return id.equals(ActionSpaceIds.AS_PRODUCTION)
				|| id.equals(ActionSpaceIds.AS_PRODUCTION_BIG);
	}

	private boolean isHarvest(ActionSpace actionSpace) {
		ActionSpaceIds id = actionSpace.getId();
		return id.equals(ActionSpaceIds.AS_HARVEST)
				|| id.equals(ActionSpaceIds.AS_HARVEST_BIG);
	}

	private void executeProduction(Action action, Player player)
			throws EffectApplyingException, IOException {
		// add bonus tile resources
		BonusTile bonusTile = player.getPersonalBoard().getBonusTile();
		player.getPersonalBoard().addResources(bonusTile.getProductionBonus());

		// add cards resources
		List<DevelopmentCard> buildingsCards = player.getPersonalBoard()
				.getCards(CardType.BUILDING);

		for (DevelopmentCard card : buildingsCards) {
			card.getPermanentEffect().applyEffect(action, player);
		}
	}

	private void executeHarvest(Action action, Player player)
			throws EffectApplyingException, IOException {
		// add bonus tile resources
		BonusTile bonusTile = player.getPersonalBoard().getBonusTile();
		player.getPersonalBoard().addResources(bonusTile.getHarvestBonus());

		// add cards resources
		List<DevelopmentCard> territoryCards = player.getPersonalBoard()
				.getCards(CardType.TERRITORY);

		for (DevelopmentCard card : territoryCards) {
			card.getPermanentEffect().applyEffect(action, player);
		}
	}
}
