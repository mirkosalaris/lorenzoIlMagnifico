
package it.polimi.ingsw.GC_36.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.GC_36.model.Deck;
import it.polimi.ingsw.GC_36.model.DeckSet;
import it.polimi.ingsw.GC_36.model.DevelopmentCard;
import it.polimi.ingsw.GC_36.model.ResourcesList;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;


public class Decoder {
	public List<ResourcesList> buildRequirements(String serializedString) {
		Gson deserialize = new Gson();
		Type collectionType = new TypeToken<Collection<ResourcesList>>() {
		}.getType();
		List<ResourcesList> resourcesListList = deserialize.fromJson(
				serializedString, collectionType);
		return resourcesListList;
	}

	public DevelopmentCard buildDevelopmentCard(String serializedString) {
		Gson deserialize = new Gson();
		DevelopmentCard developmentCard = deserialize.fromJson
				(serializedString,
						DevelopmentCard.class);
		return developmentCard;
	}

	public List<DevelopmentCard> buildDevelopmentCardList(
			String serializedString) {
		Gson deserialize = new Gson();
		Type collectionType = new TypeToken<Collection<DevelopmentCard>>() {
		}.getType();
		List<DevelopmentCard> developmentCardList = deserialize.fromJson(
				serializedString, collectionType);
		return developmentCardList;
	}

	public Deck buildDeck(String serializedString) {
		Gson deserialize = new Gson();
		Deck deck = deserialize.fromJson(serializedString, Deck.class);
		return deck;
	}

	public DeckSet buildDeckSet(String serializedString) {
		Gson deserialize = new Gson();
		DeckSet deckSet = deserialize.fromJson(serializedString, DeckSet
				.class);
		return deckSet;
	}
}