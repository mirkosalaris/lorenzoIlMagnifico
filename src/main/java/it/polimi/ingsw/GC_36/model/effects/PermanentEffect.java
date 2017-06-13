package it.polimi.ingsw.GC_36.model.effects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.Game;
import it.polimi.ingsw.GC_36.model.Player;

import java.io.Serializable;

public abstract class PermanentEffect implements Serializable {

	public abstract void applyEffect(Action action);

	public abstract void chooseOption(ViewInterface view, Action action,
	                                  User user);

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
