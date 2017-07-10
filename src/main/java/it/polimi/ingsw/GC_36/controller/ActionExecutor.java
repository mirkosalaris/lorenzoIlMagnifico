package it.polimi.ingsw.GC_36.controller;

import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.exception.InsufficientResourcesException;
import it.polimi.ingsw.GC_36.exception.NotCorrectlyCheckedException;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.io.IOException;
import java.util.List;

public class ActionExecutor {

	private Checker checker;

	public ActionExecutor() {
		this.checker = new Checker();
	}

	public boolean execute(Action action) {
		// we should pass a copy of paymentList, but we don't use it anymore
		if (!checker.check(action)) {
			return false;
		}

		return execute(action, true);
	}

	private boolean execute(Action action, boolean alreadyChecked) {

		Pair<ResourcesList, ResourcesList> paymentList = action
				.compilePaymentList();
		/* NB: there's no way to 'share' the paymentList between the two
		execute() methods, because this method is called even for extraTurn,
		and the paymentList would be different.
		 */

		if (!alreadyChecked) {
			if (!checker.check(action)) {
				return false;
			}
		}


		Player player = Game.getInstance().getCurrentPeriod()
				.getCurrentRound().getCurrentPlayer();
		FamilyMember member = player.getFamilyMember(action.getMemberColor());
		ActionSpace actionSpace = Game.getInstance().getBoard()
				.getActionSpace(action.getActionSpaceId());

		try {
			player.getPersonalBoard().payResources(paymentList.getSecond());

			// member.setLocation is invoked by actionSpace.occupy()
			actionSpace.occupy(player, member);

			if (actionSpace.getBonus() != null) {
				player.getPersonalBoard().addResources(actionSpace.getBonus());
			}
			if (actionSpace.isInTower()) {
				DevelopmentCard card =
						actionSpace.getAssociatedFloor().takeCard();
				Tower tower =
						actionSpace.getAssociatedFloor().getAssociatedTower();
				tower.occupy();

				ImmediateEffect immediateEffect = card.getImmediateEffect();
				if (immediateEffect != null) {
					card.getImmediateEffect().applyEffect(action, player);
				}
				player.getPersonalBoard().addCard(card);
			}

			if (isProduction(actionSpace)) {
				executeProduction(action, player);
			}

			if (isHarvest(actionSpace)) {
				executeHarvest(action, player);
			}

			for (CouncilPrivilege privilege : action.getCouncilPrivilegeList
					()) {
				player.getPersonalBoard().addResources(
						privilege.getResources());
			}


			Action extraAction = action.getExtraAction();
			if (extraAction != null) {
				execute(extraAction, true);
			}

		} catch (InsufficientResourcesException | EffectApplyingException |
				NotCorrectlyCheckedException | IOException e) {
			throw new IllegalStateException(e);
		}

		return true;
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
			throws EffectApplyingException, IOException,
			NotCorrectlyCheckedException {
		// add bonus tile resources
		BonusTile bonusTile = player.getPersonalBoard().getBonusTile();
		player.getPersonalBoard().addResources(bonusTile.getProductionBonus());

		// add cards resources
		List<DevelopmentCard> buildingsCards = player.getPersonalBoard()
				.getCards(CardType.BUILDING);

		for (DevelopmentCard card : buildingsCards) {
			if (card.getPermanentEffect() != null) {
				card.getPermanentEffect().applyEffect(action, player);
			}
		}
	}

	private void executeHarvest(Action action, Player player)
			throws EffectApplyingException, IOException,
			NotCorrectlyCheckedException {
		// add bonus tile resources
		BonusTile bonusTile = player.getPersonalBoard().getBonusTile();
		player.getPersonalBoard().addResources(bonusTile.getHarvestBonus());

		// add cards resources
		List<DevelopmentCard> territoryCards = player.getPersonalBoard()
				.getCards(CardType.TERRITORY);

		for (DevelopmentCard card : territoryCards) {
			if (card.getPermanentEffect() != null) {
				card.getPermanentEffect().applyEffect(action, player);
			}
		}
	}
}
