package it.polimi.ingsw.GC_36.model.effects.immediateEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.ActionInterface;
import it.polimi.ingsw.GC_36.model.Game;
import it.polimi.ingsw.GC_36.model.ResourcesList;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;

public class ImmediateResourceList implements ImmediateEffect {
	ResourcesList resourcesList;

	public ImmediateResourceList(ResourcesList resourcesList) {
		this.resourcesList = resourcesList;
	}

	@Override
	public void applyEffect(Action action) throws IllegalStateException {
		Game.getInstance().getCurrentPeriod().getCurrentRound()
				.getCurrentPlayer().getPersonalBoard().addResources(
				this.resourcesList);
	}

	@Override
	public void chooseOptions(ViewInterface view, ActionInterface action,
	                          User user) {
		// TODO impl
		//penso non debba fare niente
		//view.chooseOptions(...)

		//System.out.println(
		//		"here you'd have to choose option for immediate effect");
	}


}
