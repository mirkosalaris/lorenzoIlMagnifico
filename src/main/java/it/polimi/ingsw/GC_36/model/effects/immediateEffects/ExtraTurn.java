package it.polimi.ingsw.GC_36.model.effects.immediateEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;

import java.io.IOException;
import java.util.Set;

public class ExtraTurn implements ImmediateEffect {
	private Set<ActionSpaceIds> actionSpaces;
	private int baseActionValue;

	public ExtraTurn(Set<ActionSpaceIds> actionSpaces, int baseActionValue) {
		this.actionSpaces = actionSpaces;
		this.baseActionValue = baseActionValue;
	}

	@Override
	public void applyEffect(Action action, Player player) {

	}

	@Override
	public void chooseOptions(ViewInterface view, ActionInterface action,
	                          User user)
			throws IOException, ClassNotFoundException {
		ExtraAction extraAction = new ExtraAction(actionSpaces);
		user.play(extraAction);
		action.setExtraAction(extraAction);
	}


}