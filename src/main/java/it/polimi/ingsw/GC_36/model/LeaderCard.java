package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.model.effects.LeaderEffect;

import java.io.Serializable;
import java.util.Map;

public class LeaderCard implements Serializable {
	private String name;
	private ResourcesList requiredResources;
	private Map<CardType, Integer> requiredCards;
	private LeaderEffect effect;

	public LeaderCard(String name, ResourcesList requiredResources,
	                  Map<CardType, Integer> requiredCards,
	                  LeaderEffect effect) {
		this.name = name;
		this.requiredResources = requiredResources;
		this.requiredCards = requiredCards;
		this.effect = effect;
	}

	private boolean canUse(Player player) {
		ResourcesList playerResources = player.getPersonalBoard()
				.getResourcesList();

		return ((playerResources.checkEnoughResources(
				requiredResources)) && (checkCardsRequirements(player)));
	}


	private boolean checkCardsRequirements(Player player) {
		for (CardType cardType : requiredCards.keySet()) {
			if (requiredCards.get(
					cardType) > player.getPersonalBoard().getCards(
					cardType).size()) {
				return false;
			}
		}
		return true;
	}

	public LeaderEffect getEffect() {
		return effect;
	}

	@Override
	public String toString() {
		return "LeaderCard: '" + name + "'\n" +
				"\trequiredResources: " + requiredResources +
				"\trequiredCards: " + requiredCards +
				"\teffect: " + effect;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		LeaderCard that = (LeaderCard) o;

		if (name != null ? !name.equals(that.name) : that.name != null)
			return false;
		if (requiredResources != null ? !requiredResources.equals(
				that.requiredResources) : that.requiredResources != null)
			return false;
		if (requiredCards != null ? !requiredCards.equals(
				that.requiredCards) : that.requiredCards != null) return false;
		return effect != null ? effect.equals(
				that.effect) : that.effect == null;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (requiredResources != null ? requiredResources
				.hashCode() : 0);
		result = 31 * result + (requiredCards != null ? requiredCards.hashCode
				() : 0);
		result = 31 * result + (effect != null ? effect.hashCode() : 0);
		return result;
	}
}
