package it.polimi.ingsw.GC_36.model.effects;

import it.polimi.ingsw.GC_36.model.Action;

import java.util.Set;

public class ExtraProduction implements ImmediateEffect {
	private Set<Integer> actionSpaces;

	public ExtraProduction(Set<Integer> actionSpaces) {
		this.actionSpaces = actionSpaces;
	}

	@Override
	public void applyEffect(Action action) {

	}
}
