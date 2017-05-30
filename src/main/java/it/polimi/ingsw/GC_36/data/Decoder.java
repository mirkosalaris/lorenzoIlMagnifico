/*
package it.polimi.ingsw.GC_36.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.GC_36.data.deserializer.DevelopmentCardDeserializer;
import it.polimi.ingsw.GC_36.data.deserializer.ResourcesListDeserializer;
import it.polimi.ingsw.GC_36.model.DevelopmentCard;
import it.polimi.ingsw.GC_36.model.ResourcesList;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class Decoder {
	public List<ResourcesList> buildRequirements(String serializedString) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(ResourcesList.class,
				new ResourcesListDeserializer());
		Gson deserializeString = gsonBuilder.create();

		Type collectionType = new TypeToken<Collection<ResourcesList>>() {
		}.getType();
		List<ResourcesList> resourcesListCollection = deserializeString
				.fromJson(serializedString, collectionType);
		return resourcesListCollection;
	}

	public DevelopmentCard buildDevelopmentCard(String serializedString) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(DevelopmentCard.class,
				new DevelopmentCardDeserializer());
		Gson deserializeString = gsonBuilder.create();

		Type collectionType = new TypeToken<Collection<DevelopmentCard>>() {
		}.getType();
		DevelopmentCard developmentCardCollection = deserializeString
				.fromJson(serializedString, collectionType);
		return developmentCardCollection;
	}

}
*/