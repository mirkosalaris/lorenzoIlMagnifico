package it.polimi.ingsw.GC_36.data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.GC_36.model.BonusTile;
import it.polimi.ingsw.GC_36.model.ResourcesList;

import java.lang.reflect.Type;
import java.util.*;


public class Decoder {
	Encoder encoder = new Encoder();
	Gson gson = new Gson();

	public <T> T deserialize(String serializedString, Class<T> type) {
		return new Gson().fromJson(serializedString, type);
	}

	public List<ResourcesList> deserializeResourcesListList(
			String serializedString) {
		Type collectionType = new TypeToken<Collection<ResourcesList>>() {
		}.getType();
		List<ResourcesList> resourcesListList = gson.fromJson(
				serializedString, collectionType);
		return resourcesListList;
	}

	public List<BonusTile> buildBonusTiles(String serializedString) {
		Type collectionType = new TypeToken<Collection<BonusTile>>() {
		}.getType();
		List<BonusTile> bonusTiles = gson.fromJson(serializedString,
				collectionType);
		return bonusTiles;
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
			bonus = this.deserialize(gson.toJson(
					jsonArray.get(i).getAsJsonObject().get(
							"bonus").getAsJsonObject()), ResourcesList.class);
			floorNumber = jsonArray.get(i).getAsJsonObject().get(
					"floorNumber").getAsInt();

			map.put("requiredActionValue", requiredActionValue);
			map.put("bonus", bonus);
			map.put("floorNumber", floorNumber);
			list.add(map);
		}
		return list;
	}

	public List<ResourcesList> buildPersonalBoardList(String serializedString) {
		JsonArray jsonArray = new JsonParser().parse(
				serializedString).getAsJsonArray();
		List<ResourcesList> resourcesListList = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			ResourcesList resourcesList = this.deserialize(gson.toJson(
					jsonArray.get(i).getAsJsonObject()), ResourcesList.class);
			resourcesListList.add(resourcesList);
		}
		return resourcesListList;
	}

	public List<ResourcesList> buildCouncilPrivilege(
			String serializedString) { //parser59
		Type collectionType = new TypeToken<Collection<ResourcesList>>() {
		}.getType();
		List<ResourcesList> councilPrivilege = gson.fromJson(serializedString,
				collectionType);
		return councilPrivilege;
	}


	//TODO delete (if not used)

	public ResourcesList deserializeResourcesList(String serializedString) {
		ResourcesList resourcesList = gson.fromJson(
				serializedString, ResourcesList.class);
		return resourcesList;
	} //deserialize

	private JsonArray deserializeJsonArray(String serializedString) {
		return new JsonParser().parse(
				serializedString).getAsJsonArray();
	}

	private JsonObject deserializeJsonObj(String serializedString) {
		return new JsonParser().parse(
				serializedString).getAsJsonObject();
	}

	// the parameter "type" is type of objects in the list
	public <T> T deserializeList(String serializedString, Class<T> type) {
		Type collectionType = new TypeToken<Collection<T>>() {
		}.getType();
		return new Gson().fromJson(serializedString, collectionType);
	}

}