package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.model.effects.LeaderEffect;

import java.util.Map;

public class LeaderCard {
	String name;
	ResourcesList requiredResources;
	Map<CardType, Integer> requiredCards;
	private LeaderEffect effect;

	public LeaderCard(String name, ResourcesList requiredResources,
	                  Map<CardType, Integer> requiredCards, LeaderEffect effect) {
		this.name = name;
		this.requiredResources = requiredResources;
		this.requiredCards = requiredCards;
		this.effect=effect;
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

	public LeaderEffect getEffect(){
		return effect;
	}

}
