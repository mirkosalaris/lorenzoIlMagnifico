package it.polimi.ingsw.GC_36.model.effects;

import it.polimi.ingsw.GC_36.model.Action;

@FunctionalInterface
public interface ImmediateEffect {
	void applyEffect(Action action);
}
