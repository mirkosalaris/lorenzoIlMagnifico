package it.polimi.ingsw.GC_36.model.effects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.view.ViewInterface;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.exception.NotCorrectlyCheckedException;
import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.ActionInterface;
import it.polimi.ingsw.GC_36.model.Player;

import java.io.IOException;
import java.io.Serializable;


public abstract class ImmediateEffect implements Serializable {
	public abstract void applyEffect(Action action, Player player)
			throws IllegalStateException, EffectApplyingException,
			NotCorrectlyCheckedException;

	public abstract void chooseOptions(ViewInterface view,
	                                   ActionInterface action, User user)
			throws IOException, ClassNotFoundException;

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals(Object o);


}
