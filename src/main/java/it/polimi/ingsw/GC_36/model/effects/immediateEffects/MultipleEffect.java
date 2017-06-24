package it.polimi.ingsw.GC_36.model.effects.immediateEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.view.ViewInterface;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.exception.NotCorrectlyCheckedException;
import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.ActionInterface;
import it.polimi.ingsw.GC_36.model.Player;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;

import java.io.IOException;
import java.util.List;

public class MultipleEffect implements ImmediateEffect {
	private List<ImmediateEffect> effects;

	public MultipleEffect(List<ImmediateEffect> effects) {
		this.effects = effects;
	}

	@Override
	public void applyEffect(Action action, Player player)
			throws IllegalStateException, EffectApplyingException,
			NotCorrectlyCheckedException {
		for (ImmediateEffect effect : effects) {
			effect.applyEffect(action, player);
		}
	}

	@Override
	public void chooseOptions(ViewInterface view, ActionInterface action,
	                          User user)
			throws IOException, ClassNotFoundException {
		for (ImmediateEffect effect : effects) {
			effect.chooseOptions(view, action, user);
		}
	}
}
