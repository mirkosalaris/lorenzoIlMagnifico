package it.polimi.ingsw.GC_36.data.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import it.polimi.ingsw.GC_36.model.ResourcesList;

import java.lang.reflect.Type;

public class ResourcesListDeserializer
		implements JsonDeserializer<ResourcesList> {
	@Override
	public ResourcesList deserialize(JsonElement jsonElement, Type type,
	                                 JsonDeserializationContext
			                                 jsonDeserializationContext)
			throws JsonParseException {
		int woods = jsonElement.getAsJsonObject().get(
				"woods").getAsJsonObject().get("value").getAsInt();
		int stones = jsonElement.getAsJsonObject().get(
				"stones").getAsJsonObject().get("value").getAsInt();
		int servants = jsonElement.getAsJsonObject().get(
				"servants").getAsJsonObject().get("value").getAsInt();
		int coins = jsonElement.getAsJsonObject().get(
				"coins").getAsJsonObject().get("value").getAsInt();
		int victoryPoints = jsonElement.getAsJsonObject().get(
				"victoryPoints").getAsJsonObject().get("value").getAsInt();
		int faithPoints = jsonElement.getAsJsonObject().get(
				"faithPoints").getAsJsonObject().get("value").getAsInt();
		int militaryPoints = jsonElement.getAsJsonObject().get(
				"militaryPoints").getAsJsonObject().get("value").getAsInt();
		return new ResourcesList();
		//return new ResourcesList(woods, stones, servants, coins,
		// victoryPoints,
		//		faithPoints, militaryPoints);
	}
}
