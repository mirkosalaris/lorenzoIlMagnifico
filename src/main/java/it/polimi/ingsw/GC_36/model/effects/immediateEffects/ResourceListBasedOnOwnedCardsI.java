package it.polimi.ingsw.GC_36.model.effects.immediateEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.view.ViewInterface;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.exception.NotCorrectlyCheckedException;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_36.model.effects.permanentEffects
		.ResourceListBasedOnOwnedCards;

import java.io.IOException;

public class ResourceListBasedOnOwnedCardsI extends ImmediateEffect {
	private ResourceListBasedOnOwnedCards effect;

	public ResourceListBasedOnOwnedCardsI(ResourcesList resourcesList,
	                                      CardType cardType,
	                                      int requiredActionValue) {
		effect = new ResourceListBasedOnOwnedCards(
				resourcesList, cardType, requiredActionValue);
	}

	@Override
	public void applyEffect(Action action, Player player)
			throws IllegalStateException, EffectApplyingException,
			NotCorrectlyCheckedException {
		effect.applyEffect(action, player);
	}

	@Override
	public void chooseOptions(ViewInterface view, ActionInterface action,
	                          User user)
			throws IOException, ClassNotFoundException {
		effect.chooseOption(view, action, user);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ResourceListBasedOnOwnedCardsI that =
				(ResourceListBasedOnOwnedCardsI) o;

		return effect != null ? effect.equals(
				that.effect) : that.effect == null;
	}

	@Override
	public int hashCode() {
		return effect != null ? effect.hashCode() : 0;
	}
}
