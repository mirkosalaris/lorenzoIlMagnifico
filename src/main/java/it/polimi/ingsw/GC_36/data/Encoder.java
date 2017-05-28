package it.polimi.ingsw.GC_36.data;

import com.google.gson.Gson;
import it.polimi.ingsw.GC_36.model.ResourcesList;

import java.util.List;

public class Encoder {
	public String buildRequirements(List<ResourcesList> requirementsList) {
		Gson requirementsGson = new Gson();
		String serializedString = requirementsGson.toJson(requirementsList);
		return serializedString;
	}
}
