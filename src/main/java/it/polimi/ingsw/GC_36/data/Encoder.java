package it.polimi.ingsw.GC_36.data;

import com.google.gson.Gson;

import java.util.List;

public class Encoder {
	/*
	public String buildRequirements(List<ResourcesList> requirementsList) {
		Gson requirementsGson = new Gson();
		String serializedString = requirementsGson.toJson(requirementsList);
		return serializedString;
	}

	public String buildImmediateEffect(ImmediateEffect immediateEffect) {
		Gson immediateEffectGson=new Gson();
		String serializedString = immediateEffectGson.toJson(immediateEffect);
		return serializedString;
	}

	public String buildDevelopmentCard(DevelopmentCard developmentCard) {
		Gson developmentCardGson=new Gson();
		String serializedString = developmentCardGson.toJson(developmentCard);
		return serializedString;
	}
	*/
	public <T> String build(T element) {
		Gson gson = new Gson();
		String serializedString = gson.toJson(element);
		return serializedString;
	}

	public <T> String build(List<T> elementList) {
		Gson gson = new Gson();
		String serializedString = gson.toJson(elementList);
		return serializedString;
	}
}
