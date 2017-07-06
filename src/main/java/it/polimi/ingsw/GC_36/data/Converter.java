package it.polimi.ingsw.GC_36.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_36.model.effects.LeaderEffect;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;
import it.polimi.ingsw.GC_36.model.effects.immediateEffects.*;
import it.polimi.ingsw.GC_36.model.effects.permanentEffects.*;
import it.polimi.ingsw.GC_36.utils.Pair;

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
	                                              JsonElement eBody) {
		Decoder decoder = new Decoder();
		String effectBody = new Encoder().serialize(eBody);
		switch (effectType) {
			case "ExtraTurn":
				return decoder.deserialize(effectBody,
						ExtraTurn.class);
			case "ImmediateCouncilPrivilege":
				return decoder.deserialize(effectBody,
						ImmediateCouncilPrivilegeI.class);
			case "ImmediateResourcesListI":
				return decoder.deserialize(effectBody,
						ImmediateResourcesListI.class);
			case "ResourceListBasedOnOwnedResources":
				return decoder.deserialize(effectBody,
						ResourceListBasedOnOwnedResources.class);
			case "MultipleEffectI":
				List<ImmediateEffect> immediateEffectList = new
						ArrayList<>();
				JsonArray effectArray = eBody.getAsJsonArray();
				for (int i = 0; i < effectArray.size(); i++) {
					JsonObject jo = effectArray.get(
							i).getAsJsonObject();
					ImmediateEffect ie1 = convertImmediateEffect(
							jo.get("EffectType").getAsString(),
							jo.get("EffectBody"));
					immediateEffectList.add(ie1);
				}
				return new MultipleEffect(immediateEffectList);
			default:
				throw new IllegalArgumentException("unknown Effect type");
		}
	}

	public PermanentEffect convertPermanentEffect(String effectType,
	                                              JsonElement eBody) {
		Decoder decoder = new Decoder();
		String effectBody = new Encoder().serialize(eBody);
		switch (effectType) {
			case "ActionSpaceModifier":
				return decoder.deserialize(effectBody,
						ActionSpaceModifier.class);
			case "CardRequirementsModifier":
				return decoder.deserialize(effectBody,
						CardRequirementsModifier.class);
			case "ImmediateCouncilPrivilegeP":
				return decoder.deserialize(effectBody,
						ImmediateCouncilPrivilegeP.class);
			case "HarvestWorkValueModifier":
				return decoder.deserialize(effectBody,
						HarvestWorkValueModifier.class);
			case "ImmediateResourcesListP":
				return decoder.deserialize(effectBody,
						ImmediateResourcesListP.class);
			case "ProductionWorkValueModifier":
				return decoder.deserialize(effectBody,
						ProductionWorkValueModifier.class);
			case "ResourceListBasedOnOwnedCards":
				return decoder.deserialize(effectBody,
						ResourceListBasedOnOwnedCards.class);
			case "ResourcesConverting":
				return decoder.deserialize(effectBody,
						ResourcesConverting.class);
			case "ResourceToPrivilege":
				return decoder.deserialize(effectBody,
						ResourceToPrivilege.class);
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
		List<Pair<ResourcesList, ResourcesList>> requirementsList =
				initRequirements();
		if (developmentCard.has(
				"requirements")) {
			requirementsList = decoder
					.deserializeResourcesListList(new Encoder().serialize(
							developmentCard.get(
									"requirements").getAsJsonArray()));
		}
		ImmediateEffect immediateEffect = null;
		if (developmentCard.has(
				"immediateEffect")) {
			JsonObject effectObj = developmentCard.get(
					"immediateEffect").getAsJsonObject();
			immediateEffect = convertImmediateEffect(
					effectObj.get("EffectType").getAsString(),
					effectObj.get("EffectBody"));
		}
		PermanentEffect permanentEffect = null;
		if (developmentCard.has(
				"permanentEffect")) {
			JsonObject effectObj = developmentCard.get(
					"permanentEffect").getAsJsonObject();
			permanentEffect = convertPermanentEffect(
					effectObj.get("EffectType").getAsString(),
					effectObj.get("EffectBody"));
		}
		return new DevelopmentCard(cardType, period, name, requirementsList,
				immediateEffect, permanentEffect);
	}

	public List<DevelopmentCard> convertDevelopmentCardsList(
			JsonArray jsonArray) {
		List<DevelopmentCard> developmentCardList = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			developmentCardList.add(
					convertDevelopmentCard(jsonArray.get(i).getAsJsonObject
							()));
		}
		return developmentCardList;
	}

	public List<Map<CardType, Deck>> convertDeckSetList(JsonArray jsonArray) {
		List<DevelopmentCard> developmentCardList =
				convertDevelopmentCardsList(
						jsonArray);
		List<DevelopmentCard> developmentCardsPeriod,
				developmentCardsPeriodType;
		List<Map<CardType, Deck>> deckSetList = new ArrayList<>();

		for (int i = 1; i <= 3; i++) {
			// give all card of the same period
			developmentCardsPeriod = this.buildPeriod(i, developmentCardList);
			Map<CardType, Deck> deckSet = new EnumMap<>(CardType.class);
			// type
			for (CardType cardType : CardType.values()) {
				// give card of the same period and of the same type
				developmentCardsPeriodType = this.buildType(cardType,
						developmentCardsPeriod);
				//add to deckCard all card having same period and type
				List<DevelopmentCard> deckSamePeriodType = new ArrayList<>();
				for (DevelopmentCard dc : developmentCardsPeriodType) {
					deckSamePeriodType.add(dc);
				}
				// add all card of the same period and type (deck) in a map
				deckSet.put(cardType,
						new Deck(cardType, i, deckSamePeriodType));
			}
			deckSetList.add(deckSet);
		}
		return deckSetList;
	}

	public List<DevelopmentCard> buildType(CardType type,
	                                       List<DevelopmentCard>
			                                       developmentCardList) {
		List<DevelopmentCard> developmentCardType = new ArrayList<>();
		for (DevelopmentCard developmentCard : developmentCardList) {
			if (type.equals(developmentCard.getType())) {
				developmentCardType.add(developmentCard);
			}
		}
		return developmentCardType;
	}


	public List<DevelopmentCard> buildPeriod(int period,
	                                         List<DevelopmentCard>
			                                         developmentCardList) {
		List<DevelopmentCard> developmentCardPeriod = new ArrayList<>();
		for (DevelopmentCard developmentCard : developmentCardList) {
			if (period == developmentCard.getPeriod()) {
				developmentCardPeriod.add(developmentCard);
			}
		}
		return developmentCardPeriod;
	}

	public List<Pair<ResourcesList, ResourcesList>> initRequirements() {
		Pair<ResourcesList, ResourcesList> p = new Pair<>(new ResourcesList(),
				new ResourcesList());
		List<Pair<ResourcesList, ResourcesList>> lp = new ArrayList<>();
		lp.add(p);
		return lp;
	}

	public LeaderCard convertLeaderCard(JsonObject leaderCard) {
		Decoder decoder = new Decoder();
		String name = leaderCard.get("name").getAsString();
		ResourcesList requiredResources = new ResourcesList();
		if (leaderCard.has("requiredResources")) {
			requiredResources = decoder.deserialize(new Encoder().serialize(
					leaderCard.get("requiredResources").getAsJsonObject()),
					ResourcesList.class);
		}
		Map<CardType, Integer> requiredCards = new EnumMap<>(CardType.class);
		if (leaderCard.has("requiredCards")) {
			requiredCards = decoder.deserializeMapCardTypeInteger(
					new Encoder().serialize(
							leaderCard.get("requiredCards").getAsJsonObject
									()));
		}
		ImmediateEffect effect = null;
		if (leaderCard.has(
				"effect")) {
			JsonObject effectObj = leaderCard.get(
					"effect").getAsJsonObject();
			effect = convertImmediateEffect(
					effectObj.get("EffectType").getAsString(),
					effectObj.get("EffectBody"));
		}
		return new LeaderCard(name, requiredResources, requiredCards,
				new LeaderEffect(effect));
	}

	public List<LeaderCard> convertLeaderCardsList(
			JsonArray jsonArray) {
		List<LeaderCard> leaderCardList = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			leaderCardList.add(
					convertLeaderCard(jsonArray.get(i).getAsJsonObject()));
		}
		return leaderCardList;
	}
}