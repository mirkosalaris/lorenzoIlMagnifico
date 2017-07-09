package it.polimi.ingsw.GC_36.model.effects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.view.ViewInterface;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.exception.NotCorrectlyCheckedException;
import it.polimi.ingsw.GC_36.model.*;

import java.io.IOException;
import java.io.Serializable;

public abstract class PermanentEffect implements Serializable {

	public abstract void applyEffect(Action action, Player player)
			throws EffectApplyingException, NotCorrectlyCheckedException;

	public abstract void chooseOption(ViewInterface view, ActionInterface
			action, User user) throws IOException, ClassNotFoundException;

	public boolean isDoable(int requiredActionValue, Action action) {
		int ActionValue;
		Player player = Game.getInstance().getCurrentPeriod().getCurrentRound()
				.getCurrentPlayer();
		ActionValue = action.getActionValue(player);
		if (ActionValue >= requiredActionValue)
			return true;
		else
			return false;
	}

	@Override
	public abstract boolean equals(Object obj);

	public abstract boolean check(Action action, ResourcesList
			playerResources);

}
