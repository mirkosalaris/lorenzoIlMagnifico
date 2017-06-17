package it.polimi.ingsw.GC_36.data;

import com.google.gson.Gson;

import java.util.List;

public class Encoder {
	public <T> String serialize(T element) {
		Gson gson = new Gson();
		String serializedString = gson.toJson(element);
		return serializedString;
	}

	public <T> String serialize(List<T> elementList) {
		Gson gson = new Gson();
		String serializedString = gson.toJson(elementList);
		return serializedString;
	}
}
