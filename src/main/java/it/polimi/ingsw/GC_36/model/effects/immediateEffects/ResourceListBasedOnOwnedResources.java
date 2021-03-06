package it.polimi.ingsw.GC_36.model.effects.immediateEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.view.ViewInterface;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;

import java.io.IOException;

public class ResourceListBasedOnOwnedResources extends ImmediateEffect {
	private ResourceType fromResourceType;
	private int fromResourceValue;
	private ResourcesList toResourcesList;

	public ResourceListBasedOnOwnedResources(
			ResourceType fromResourceType, int fromResourceValue,
			ResourcesList toResourcesList) {
		this.fromResourceType = fromResourceType;
		this.fromResourceValue = fromResourceValue;
		this.toResourcesList = toResourcesList;
	}

	@Override
	public void applyEffect(Action action, Player player)
			throws IllegalStateException, EffectApplyingException {
		//ResourcesList playerResources=Game.getInstance().getCurrentPeriod()
		// .getCurrentRound()
		//		.getCurrentPlayer().getPersonalBoard().getResourcesList();
		ResourcesList playerResources = player.getPersonalBoard()
				.getResourcesList();

		int temp = ((playerResources.get(
				fromResourceType).getValue() / this.fromResourceValue));
		for (int i = 0; i < temp; i++) {
			playerResources.addResources(toResourcesList);
		}
	}

	@Override
	public void chooseOptions(ViewInterface view, ActionInterface action,
	                          User user)
			throws IOException, ClassNotFoundException {

	}

	@Override
	public String toString() {
		return "ResourceListBasedOnOwnedResources{" +
				"fromResourceType=" + fromResourceType +
				", fromResourceValue=" + fromResourceValue +
				", toResourcesList=" + toResourcesList +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ResourceListBasedOnOwnedResources that =
				(ResourceListBasedOnOwnedResources) o;

		if (fromResourceValue != that.fromResourceValue) return false;
		if (fromResourceType != that.fromResourceType) return false;
		return toResourcesList != null ? toResourcesList.equals(
				that.toResourcesList) : that.toResourcesList == null;
	}

	@Override
	public int hashCode() {
		int result = fromResourceType != null ? fromResourceType.hashCode()
				: 0;
		result = 31 * result + fromResourceValue;
		result = 31 * result + (toResourcesList != null ? toResourcesList
				.hashCode() : 0);
		return result;
	}
}

