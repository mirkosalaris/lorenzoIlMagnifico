package it.polimi.ingsw.GC_36.model.effects.immediateEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.view.ViewInterface;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.ActionInterface;
import it.polimi.ingsw.GC_36.model.Player;
import it.polimi.ingsw.GC_36.model.ResourcesList;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;

import java.io.IOException;

public class ImmediateResourcesListI extends ImmediateEffect {
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
			throws IOException, ClassNotFoundException {}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ImmediateResourcesListI that = (ImmediateResourcesListI) o;

		return resourcesList != null ? resourcesList.equals(
				that.resourcesList) : that.resourcesList == null;
	}

	@Override
	public int hashCode() {
		return resourcesList != null ? resourcesList.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "ImmediateResourcesListI{" +
				"resourcesList=" + resourcesList +
				'}';
	}
}
