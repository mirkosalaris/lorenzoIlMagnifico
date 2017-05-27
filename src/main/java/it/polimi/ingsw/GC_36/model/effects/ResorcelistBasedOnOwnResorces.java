package it.polimi.ingsw.GC_36.model.effects;

import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.Resource;
import it.polimi.ingsw.GC_36.model.ResourcesList;

public class ResorcelistBasedOnOwnResorces implements ImmediateEffect {
	ResourcesList resourcesList;
	int factor;
	Resource resource;

	@Override
	public void applyEffect(Action action) {

	}
}
