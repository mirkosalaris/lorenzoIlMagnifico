package it.polimi.ingsw.GC_36.model.effects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.ActionSpaceIds;
import it.polimi.ingsw.GC_36.model.ExtraAction;

import java.io.IOException;
import java.util.Set;

public class ExtraTurnHarvest implements ImmediateEffect {
	private Set<ActionSpaceIds> actionSpaces;

	public ExtraTurnHarvest(Set<ActionSpaceIds> actionSpaces) {
		this.actionSpaces = actionSpaces;
	}

	@Override
	public void applyEffect(Action action) {

	}

	@Override
	public void chooseOptions(ViewInterface view, Action action, User user)
			throws IOException, ClassNotFoundException {
		ExtraAction extraAction = new ExtraAction(actionSpaces);
		user.play(extraAction);
		action.addExtraAction(extraAction);
	}


}
