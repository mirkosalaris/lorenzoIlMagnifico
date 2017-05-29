package it.polimi.ingsw.GC_36.model.effects;

import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.ResourcesList;

public class ResourceListBasedOnOwnedResources implements ImmediateEffect {
	ResourcesList fromResourcesList;
	ResourcesList toResourcesList;

	public ResourceListBasedOnOwnedResources(ResourcesList fromResourcesList,
	                                         ResourcesList toResourcesList) {
		this.fromResourcesList = fromResourcesList;
		this.toResourcesList = toResourcesList;
	}

	@Override
	public void applyEffect(Action action) {

	}
}