package it.polimi.ingsw.GC_36.model;

import java.util.EnumMap;

public class ResourcesList {
	EnumMap<Resource, ResourceCounter> map = new EnumMap<>(Resource.class);

	public ResourcesList() {
		map.put(Resource.WOOD, new ResourceCounter(0));
		map.put(Resource.STONE, new ResourceCounter(0));
		map.put(Resource.SERVANT, new ResourceCounter(0));
		map.put(Resource.COINS, new ResourceCounter(0));
		map.put(Resource.VICTORY_POINTS, new ResourceCounter(0));
		map.put(Resource.FAITH_POINTS, new ResourceCounter(0));
		map.put(Resource.MILITARY_POINTS, new ResourceCounter(0));
	}

	public ResourceCounter get(Resource r) {
		return map.get(r);
	}

	public void set(Resource r, int value) {
		map.get(r).add(value);
	}

	// add a resourcesList to this, doing the sum of every resource
	public void addResources(ResourcesList resources) {
		for (Resource key : this.map.keySet()) {
			this.get(key).add(resources.get(key).getValue());
		}
	}

	// check if every ResourceCounter is >= than the coupled ones
	// in the ResourcesList passed by arguments
	public boolean checkEnoughResources(ResourcesList resources) {
		for (Resource key : this.map.keySet()) {
			if (this.get(key).getValue() < resources.get(key).getValue())
				return false;
		}
		return true;
	}

	public void subtractResources(ResourcesList resources) {
		for (Resource key : this.map.keySet()) {
			this.get(key).subtract(resources.get(key).getValue());
		}
	}

	// TODO
	//public <T> T iterate(Function<ResourcesList,T> lambda) {


}
