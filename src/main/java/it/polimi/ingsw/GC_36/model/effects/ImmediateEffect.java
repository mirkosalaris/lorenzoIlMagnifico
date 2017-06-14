package it.polimi.ingsw.GC_36.model.effects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.ActionInterface;

import java.io.IOException;
import java.io.Serializable;


public interface ImmediateEffect extends Serializable {
	void applyEffect(Action action)
			throws IllegalStateException, EffectApplyingException;

	void chooseOptions(ViewInterface view, ActionInterface action, User user)
			throws IOException, ClassNotFoundException;
}
