package it.polimi.ingsw.GC_36.model.effects;

import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.model.Action;


public interface ImmediateEffect {
	void applyEffect(Action action);

	void chooseOptions(ViewInterface view, Action action);
}
