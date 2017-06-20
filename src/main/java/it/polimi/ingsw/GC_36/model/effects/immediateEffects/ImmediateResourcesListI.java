package it.polimi.ingsw.GC_36.model.effects.immediateEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.ActionInterface;
import it.polimi.ingsw.GC_36.model.Player;
import it.polimi.ingsw.GC_36.model.ResourcesList;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;

import java.io.IOException;

public class ImmediateResourcesListI implements ImmediateEffect {
	ResourcesList resourcesList;

	public ImmediateResourcesListI(ResourcesList resourcesList) {
		this.resourcesList = resourcesList;
	}

	@Override
	public void applyEffect(Action action, Player player)
			throws IllegalStateException, EffectApplyingException {
		try {
			player.getPersonalBoard().addResources(this.resourcesList);
		} catch (IOException e) {
			throw new EffectApplyingException(e);
		}
	}

	@Override
	public void chooseOptions(ViewInterface view, ActionInterface action,
	                          User user)
			throws IOException, ClassNotFoundException {
		// TODO impl
		//penso non debba fare niente
		//view.chooseOptions(...)

		//System.out.println(
		//		"here you'd have to choose option for immediate effect");
	}
}
