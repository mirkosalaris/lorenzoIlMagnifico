package it.polimi.ingsw.GC_36.model;

import java.util.EnumMap;
import java.util.Map;

public class ResourcesList {
	Map<ResourceType, ResourceCounter> map = new EnumMap<>(ResourceType.class);

	public ResourcesList() {
		map.put(ResourceType.WOOD, new ResourceCounter(0));
		map.put(ResourceType.STONE, new ResourceCounter(0));
		map.put(ResourceType.SERVANT, new ResourceCounter(0));
		map.put(ResourceType.COINS, new ResourceCounter(0));
		map.put(ResourceType.VICTORY_POINTS, new ResourceCounter(0));
		map.put(ResourceType.FAITH_POINTS, new ResourceCounter(0));
		map.put(ResourceType.MILITARY_POINTS, new ResourceCounter(0));
	}

	public ResourceCounter get(ResourceType r) {
		return map.get(r);
	}

	public void set(ResourceType r, int value) {
		map.get(r).add(value);
	}

	// add a resourcesList to this, doing the sum of every resource
	public void addResources(ResourcesList resources) {
		for (ResourceType key : this.map.keySet()) {
			this.get(key).add(resources.get(key).getValue());
		}
	}

	// check if every ResourceCounter is >= than the coupled ones
	// in the ResourcesList passed by arguments
	public boolean checkEnoughResources(ResourcesList resources) {
		for (ResourceType key : this.map.keySet()) {
			if (this.get(key).getValue() < resources.get(key).getValue())
				return false;
		}
		return true;
	}

	public void subtractResources(ResourcesList resources) {
		for (ResourceType key : this.map.keySet()) {
			this.get(key).subtract(resources.get(key).getValue());
		}
	}

	// TODO
	//public <T> T iterate(Function<ResourcesList,T> lambda) {


}
