package it.polimi.ingsw.GC_36.model.effects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.model.Action;

import java.io.IOException;
import java.io.Serializable;


public interface ImmediateEffect extends Serializable {
	void applyEffect(Action action) throws IllegalStateException;

	void chooseOptions(ViewInterface view, Action action, User user)
			throws IOException, ClassNotFoundException;
}
