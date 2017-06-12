package it.polimi.ingsw.GC_36.data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.GC_36.model.*;

import java.lang.reflect.Type;
import java.util.*;


public class Decoder {
	Encoder encoder = new Encoder();
	Gson gson = new Gson();

	public ResourcesList buildResourcesList(String serializedString) {
		ResourcesList resourcesList = gson.fromJson(
				serializedString, ResourcesList.class);
		return resourcesList;
	}

	public List<ResourcesList> buildRequirements(String serializedString) {
		Type collectionType = new TypeToken<Collection<ResourcesList>>() {
		}.getType();
		List<ResourcesList> resourcesListList = gson.fromJson(
				serializedString, collectionType);
		return resourcesListList;
	}

	public DevelopmentCard buildDevelopmentCard(String serializedString) {
		DevelopmentCard developmentCard = gson.fromJson
				(serializedString,
						DevelopmentCard.class);
		return developmentCard;
	}

	public List<DevelopmentCard> buildDevelopmentCardList(
			String serializedString) {
		Type collectionType = new TypeToken<Collection<DevelopmentCard>>() {
		}.getType();
		List<DevelopmentCard> developmentCardList = gson.fromJson(
				serializedString, collectionType);
		return developmentCardList;
	}

	public Deck buildDeck(String serializedString) {
		Deck deck = gson.fromJson(serializedString, Deck.class);
		return deck;
	}

	public DeckSet buildDeckSet(String serializedString) {
		DeckSet deckSet = gson.fromJson(serializedString, DeckSet
				.class);
		return deckSet;
	}

	public List<BonusTile> buildBonusTiles(String serializedString) {
		Type collectionType = new TypeToken<Collection<BonusTile>>() {
		}.getType();
		List<BonusTile> bonusTiles = gson.fromJson(serializedString,
				collectionType);
		return bonusTiles;
	}

	public List<Map<CardType, Deck>> buildDeckSetList(String
			                                                  serializedString) {
		Type collectionType = new TypeToken<Collection<Map<CardType, Deck>>>
				() {
		}.getType();
		List<Map<CardType, Deck>> deckSetList = gson.fromJson(serializedString,
				collectionType);
		return deckSetList;
	}

	public List<Map<String, Object>> buildActionSpace(String
			                                                  serializedString) {
		int requiredActionValue, floorNumber;
		ResourcesList bonus;
		JsonArray jsonArray = new JsonParser().parse(
				serializedString).getAsJsonArray();
		List<Map<String, Object>> list = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			requiredActionValue = jsonArray.get(i).getAsJsonObject().get(
					"requiredActionValue").getAsInt();
			bonus = this.buildResourcesList(gson.toJson(
					jsonArray.get(i).getAsJsonObject().get(
							"bonus").getAsJsonObject()));
			floorNumber = jsonArray.get(i).getAsJsonObject().get(
					"floorNumber").getAsInt();

			map.put("requiredActionValue", requiredActionValue);
			map.put("bonus", bonus);
			map.put("floorNumber", floorNumber);
			list.add(map);
		}
		return list;
	}

	public List<ResourcesList> buildPersonalBoardList(String
			                                                  serializedString) {
		JsonArray jsonArray = new JsonParser().parse(
				serializedString).getAsJsonArray();
		List<ResourcesList> resourcesListList = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			ResourcesList resourcesList = this.buildResourcesList(gson.toJson(
					jsonArray.get(i).getAsJsonObject()));
			resourcesListList.add(resourcesList);
		}
		return resourcesListList;
	}
}