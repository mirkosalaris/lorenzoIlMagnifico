/*package it.polimi.ingsw.GC_36.data.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import it.polimi.ingsw.GC_36.data.Decoder;
import it.polimi.ingsw.GC_36.model.CardType;
import it.polimi.ingsw.GC_36.model.DevelopmentCard;
import it.polimi.ingsw.GC_36.model.ResourcesList;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;

import java.lang.reflect.Type;
import java.util.List;

public class DevelopmentCardDeserializer
		implements JsonDeserializer<DevelopmentCard> {

	@Override
	public DevelopmentCard deserialize(JsonElement jsonElement, Type type,
	                                   JsonDeserializationContext
			                                   jsonDeserializationContext)
			throws JsonParseException {
		String typeDevelopmentCard1 = jsonElement.getAsJsonObject().get(
				"type").getAsJsonObject().get("value").getAsString();
		String periood = jsonElement.getAsJsonObject().get(
				"period").getAsJsonObject().get("value").getAsString();
		String name = jsonElement.getAsJsonObject().get(
				"name").getAsJsonObject().get("value").getAsString();
		CardType typeDevelopmentCard = null;

		String resourceListString = jsonElement.getAsJsonObject().get(
				"resourcesList").getAsString();
		List<ResourcesList> requirementsList = new Decoder()
				.buildRequirements(resourceListString);

		ImmediateEffect immediateEffect = null;
		PermanentEffect permanentEffect = null;

		return new DevelopmentCard(typeDevelopmentCard, 1, name,
				requirementsList, immediateEffect,
				permanentEffect);
	}

}

*/