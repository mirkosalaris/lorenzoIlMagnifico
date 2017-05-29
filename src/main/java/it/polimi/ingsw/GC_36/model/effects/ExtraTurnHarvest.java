package it.polimi.ingsw.GC_36.model.effects;

import it.polimi.ingsw.GC_36.model.Action;

import java.util.Set;

public class ExtraTurnHarvest implements ImmediateEffect {
	private Set<Integer> actionSpaces;

	public ExtraTurnHarvest(Set<Integer> actionSpaces) {
		this.actionSpaces = actionSpaces;
	}

	@Override
	public void applyEffect(Action action) {

	}
}
