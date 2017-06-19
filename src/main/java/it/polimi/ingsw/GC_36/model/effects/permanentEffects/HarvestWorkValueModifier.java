package it.polimi.ingsw.GC_36.model.effects.permanentEffects;

import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.ActionInterface;
import it.polimi.ingsw.GC_36.model.Player;
import it.polimi.ingsw.GC_36.model.ResourcesList;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;

public class HarvestWorkValueModifier extends PermanentEffect {
	@Override
	public void applyEffect(Action action, Player player) {

	}

	@Override
	public void chooseOption(ViewInterface view, ActionInterface action) {

	}

	@Override
	public boolean isDoable(int requiredActionValue, Action action) {
		return false;
	}

	@Override
	public boolean check(Action action, ResourcesList playerResources) {
		return false;
	}
}
