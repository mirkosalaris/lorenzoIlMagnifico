package it.polimi.ingsw.GC_36.model.effects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.model.Action;

import java.io.Serializable;

public interface PermanentEffect extends Serializable {

	public void applyEffect(Action action);

	public void chooseOption(ViewInterface view, Action action, User user);

	public boolean isDoable(int requiredActionValue, Action action);
}
