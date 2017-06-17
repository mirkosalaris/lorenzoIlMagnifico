package it.polimi.ingsw.GC_36.model.effects.immediateEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;

public class ResourceListBasedOnOwnedResources implements ImmediateEffect {
	private ResourceType fromResourceType;
	private int fromResourceValue;
	private ResourcesList toResourcesList;

	public ResourceListBasedOnOwnedResources(
			ResourceType fromResourceType, int fromResourceValue,
			ResourcesList toResourcesList) {
		this.fromResourceType = fromResourceType;
		this.fromResourceValue = fromResourceValue;
		this.toResourcesList = toResourcesList;
	}

	@Override
	public void applyEffect(Action action, Player player)
			throws IllegalStateException, EffectApplyingException {
		//ResourcesList playerResources=Game.getInstance().getCurrentPeriod()
		// .getCurrentRound()
		//		.getCurrentPlayer().getPersonalBoard().getResourcesList();
		ResourcesList playerResources = player.getPersonalBoard()
				.getResourcesList();

		int temp = ((playerResources.get(
				fromResourceType).getValue() / this.fromResourceValue));
		for (int i = 0; i < temp; i++) {
			playerResources.addResources(toResourcesList);
		}
	}

	@Override
	public void chooseOptions(ViewInterface view, ActionInterface action,
	                          User user) {

	}

}
