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


public interface ImmediateEffect extends Serializable {
	void applyEffect(Action action, Player player)
			throws IllegalStateException, EffectApplyingException,
			NotCorrectlyCheckedException;

	void chooseOptions(ViewInterface view, ActionInterface action, User user)
			throws IOException, ClassNotFoundException;
}
