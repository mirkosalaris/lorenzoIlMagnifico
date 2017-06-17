package it.polimi.ingsw.GC_36.model.effects;

import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.ActionInterface;
import it.polimi.ingsw.GC_36.model.Game;
import it.polimi.ingsw.GC_36.model.Player;

import java.io.Serializable;
import java.rmi.RemoteException;

public abstract class PermanentEffect implements Serializable {

	public abstract void applyEffect(Action action, Player player)
			throws EffectApplyingException;

	public abstract void chooseOption(ViewInterface view, ActionInterface
			action) throws RemoteException;

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

}
