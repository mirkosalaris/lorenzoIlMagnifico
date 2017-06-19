package it.polimi.ingsw.GC_36.model.effects.permanentEffects;

import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;

public class CardRequirementsModifier extends PermanentEffect {
	private CardType type;
	private int actionValueModifier;

	public CardRequirementsModifier(CardType type, int actionValueModifier) {
		this.type = type;
		this.actionValueModifier = actionValueModifier;
	}

	@Override
	public void applyEffect(Action action, Player player) {
		// TODO
	}

	@Override
	public void chooseOption(ViewInterface view,
	                         ActionInterface action) {
		// TODO
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
