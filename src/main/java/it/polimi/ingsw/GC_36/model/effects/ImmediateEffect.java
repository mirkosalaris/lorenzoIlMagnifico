package it.polimi.ingsw.GC_36.model.effects;

import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.model.Action;

import java.io.Serializable;


public interface ImmediateEffect extends Serializable {
	void applyEffect(Action action);

	void chooseOptions(ViewInterface view, Action action);
}
