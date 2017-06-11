package it.polimi.ingsw.GC_36.model.effects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.ActionSpaceIds;

import java.util.Set;

public class ExtraTurnTower implements ImmediateEffect {
	private Set<ActionSpaceIds> actionSpaces;
	private int actionValue;

	public ExtraTurnTower(Set<ActionSpaceIds> actionSpaces, int actionValue) {
		this.actionSpaces = actionSpaces;
		this.actionValue = actionValue;
	}

	@Override
	public void applyEffect(Action action) {

	}

	@Override
	public void chooseOptions(ViewInterface view, Action action, User user) {


	}


}
