package it.polimi.ingsw.GC_36.model.effects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.view.ViewInterface;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.exception.NotCorrectlyCheckedException;
import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.ActionInterface;
import it.polimi.ingsw.GC_36.model.Player;

import java.io.IOException;

public class LeaderEffect {
	private ImmediateEffect effect;


	public LeaderEffect(ImmediateEffect effect) {
		this.effect = effect;
	}

	public void applyEffect(Action action, Player player)
			throws EffectApplyingException, NotCorrectlyCheckedException {
		effect.applyEffect(action, player);
	}

	public void chooseOption(ViewInterface view, ActionInterface action,
	                         User user)
			throws IOException, ClassNotFoundException {
		effect.chooseOptions(view, action, user);
	}
}
