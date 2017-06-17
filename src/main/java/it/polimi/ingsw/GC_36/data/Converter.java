package it.polimi.ingsw.GC_36.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.polimi.ingsw.GC_36.model.CardType;
import it.polimi.ingsw.GC_36.model.Deck;
import it.polimi.ingsw.GC_36.model.DevelopmentCard;
import it.polimi.ingsw.GC_36.model.ResourcesList;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;
import it.polimi.ingsw.GC_36.model.effects.immediateEffects.*;
import it.polimi.ingsw.GC_36.model.effects.permanentEffects.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Converter {

	public CardType convertCardType(String s) {
		CardType cardType;
		switch (s) {
			case "TERRITORY":
				cardType = CardType.TERRITORY;
				break;
			case "BUILDING":
				cardType = CardType.BUILDING;
				break;
			case "CHARACTER":
				cardType = CardType.CHARACTER;
				break;
			case "VENTURE":
				cardType = CardType.VENTURE;
				break;
			default:
				throw new IllegalArgumentException("unknown Tower type");
		}
		return cardType;

	}

	public ImmediateEffect convertImmediateEffect(String effectType,
	                                              String effectBody) {
		Decoder decoder = new Decoder();
		switch (effectType) {
			case "ExtraProduction":
				return decoder.deserialize(effectBody, ExtraProduction.class);
			case "ExtraTurnHarvest":
				return decoder.deserialize(effectBody, ExtraTurnHarvest.class);
			case "ExtraTurnTower":
				return decoder.deserialize(effectBody, ExtraTurnTower.class);
			case "ImmediateCouncilPrivilege":
				return decoder.deserialize(effectBody,
						ImmediateCouncilPrivileges.class);
			case "ImmediateResourceList":
				return decoder.deserialize(effectBody,
						ImmediateResourceList.class);
			case "ResourceListBasedOnOwnedResources":
				return decoder.deserialize(effectBody,
						ResourceListBasedOnOwnedResources.class);
			default:
				throw new IllegalArgumentException("unknown Effect type");
		}
	}

	public PermanentEffect convertPermanentEffect(String effectType,
	                                              String effectBody) {
		Decoder decoder = new Decoder();
		switch (effectType) {
			case "ActionSpaceModifier":
				return decoder.deserialize(effectBody,
						ActionSpaceModifier.class);
			case "CardRequirementsModifier":
				return decoder.deserialize(effectBody,
						CardRequirementsModifier.class);
			case "HarvestWorkValueModifier":
				return decoder.deserialize(effectBody,
						HarvestWorkValueModifier.class);
			case "ImmediateResourcesList":
				return decoder.deserialize(effectBody,
						ImmediateResourcesList.class);
			case "ProductionWorkValueModifier":
				return decoder.deserialize(effectBody,
						ProductionWorkValueModifier.class);
			case "ResourceListBasedOnOwnedCards":
				return decoder.deserialize(effectBody,
						ResourceListBasedOnOwnedCards.class);
			case "ResourcesConverting":
				return decoder.deserialize(effectBody,
						ResourcesConverting.class);
			default:
				throw new IllegalArgumentException("unknown Effect type");
		}
	}


	public DevelopmentCard convertDevelopmentCard(JsonObject developmentCard) {
		Decoder decoder = new Decoder();
		CardType cardType = convertCardType(
				developmentCard.get("type").getAsString());
		int period = developmentCard.get("period").getAsInt();
		String name = developmentCard.get("name").getAsString();
		List<ResourcesList> requirementsList = decoder
				.deserializeResourcesListList(new Encoder().serialize(
						developmentCard.get("requirementsList")));
		JsonObject effectObj = developmentCard.get(
				"immediateEffect").getAsJsonObject();
		ImmediateEffect immediateEffect = convertImmediateEffect(
				effectObj.get("effectType").getAsString(),
				new Encoder().serialize(effectObj.get("effectBody")));
		PermanentEffect permanentEffect = null;
		return new DevelopmentCard(cardType, period, name, requirementsList,
				immediateEffect, permanentEffect);
	}

	public List<DevelopmentCard> convertDevelopmentCardList(
			JsonArray jsonArray) {
		List<DevelopmentCard> developmentCardList = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			DevelopmentCard developmentCard = this.convertDevelopmentCard(
					jsonArray.get(i).getAsJsonObject());
			developmentCardList.add(developmentCard);
		}
		return developmentCardList;
	}

	public Deck convertDeck(JsonObject deck) {
		CardType type = convertCardType(deck.get("type").getAsString());
		int period = deck.get("period").getAsInt();
		List<DevelopmentCard> developmentCardList = convertDevelopmentCardList(
				deck.get("developmentCardList").getAsJsonArray());
		return new Deck(type, period, developmentCardList);
	}

	//here there are cards of only one period
	public Map<CardType, Deck> convertDeckSet(JsonObject deckSet) {
		Map<CardType, Deck> decks = new EnumMap<>(CardType.class);
		for (CardType type : CardType.values()) {
			Deck deck = convertDeck(deckSet.get(type + "").getAsJsonObject());
			decks.put(type, deck);
		}
		return decks;

	}

	public List<Map<CardType, Deck>> convertDeckSetList(JsonArray jsonArray) {
		List<Map<CardType, Deck>> deckSetList = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			Map<CardType, Deck> deckSet = this.convertDeckSet(
					jsonArray.get(i).getAsJsonObject());
			deckSetList.add(deckSet);
		}
		return deckSetList;
	}

}
