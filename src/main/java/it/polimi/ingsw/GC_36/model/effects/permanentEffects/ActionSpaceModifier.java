package it.polimi.ingsw.GC_36.model.effects.permanentEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.view.ViewInterface;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;

import java.util.Set;

public class ActionSpaceModifier extends PermanentEffect {
	private Set<ActionSpaceIds> actionSpaceIdsSet;
	private Integer actionValue;
	private boolean denyBonus;
	private boolean pass;

	public ActionSpaceModifier(
			Set<ActionSpaceIds> actionSpaceIdsSet, Integer actionValue,
			boolean denyBonus, boolean pass) {
		this.actionSpaceIdsSet = actionSpaceIdsSet;
		this.actionValue = actionValue;
		this.denyBonus = denyBonus;
		this.pass = pass;
	}

	public ActionSpaceModifier(boolean denyBonus) {
		this.denyBonus = denyBonus;
	}

	@Override
	public void applyEffect(Action action, Player player) {
	}

	@Override
	public void chooseOption(ViewInterface view,
	                         ActionInterface action, User user) {

	}

	@Override
	public boolean isDoable(int requiredActionValue, Action action) {
		return false;
	}

	@Override
	public boolean check(Action action, ResourcesList playerResources) {
		return true;
	}
}
