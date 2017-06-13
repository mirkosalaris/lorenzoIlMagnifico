package it.polimi.ingsw.GC_36.model.effects.permanentEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;

public class HarvestWorkValueModifier extends PermanentEffect {
	@Override
	public void applyEffect(Action action) {

	}

	@Override
	public void chooseOption(ViewInterface view, Action action, User user) {

	}

	@Override
	public boolean isDoable(int requiredActionValue, Action action) {
		return false;
	}
}
