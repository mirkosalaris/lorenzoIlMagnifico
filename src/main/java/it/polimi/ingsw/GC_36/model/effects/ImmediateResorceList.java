package it.polimi.ingsw.GC_36.model.effects;

import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.Game;
import it.polimi.ingsw.GC_36.model.ResourcesList;

public class ImmediateResorceList implements ImmediateEffect {
	ResourcesList resourcesList;

	public ImmediateResorceList(ResourcesList resourcesList) {
		this.resourcesList = resourcesList;
	}

	@Override
	public void applyEffect(Action action) throws IllegalStateException {
		Game.getInstance().getCurrentPeriod().getCurrentRound()
				.getCurrentPlayer().getPersonalBoard().addResources(
				this.resourcesList);
	}

	@Override
	public void chooseOptions(ViewInterface view, Action action) {

	}


}
