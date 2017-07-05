package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.exception.InsufficientResourcesException;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ResourcesList implements Serializable {
	private Map<ResourceType, ResourceCounter> map = new EnumMap<>(
			ResourceType.class);

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
		try {
			for (ResourceType key : this.map.keySet()) {
				this.get(key).subtract(resources.get(key).getValue());
			}
		} catch (InsufficientResourcesException e) {
			e.printStackTrace();
		}
	}

	public void applyDiscount(ResourcesList discount) {
		try {
			for (ResourceType key : this.map.keySet()) {
				int value = this.get(key).getValue();
				int discountedValue = discount.get(key).getValue();

				if (value > discountedValue) {
					// apply the discount
					this.get(key).subtract(discountedValue);
				} else {
					// set it to zero
					this.get(key).subtract(value);
				}
			}
		} catch (InsufficientResourcesException e) {
			// this should not happen, never
			throw new IllegalStateException("cannot apply discount", e);
		}
	}

	public ResourcesList copy() {
		// returns a copy of the current resourcesList
		Map<ResourceType, ResourceCounter> mapCopy = new HashMap<>();

		for (Map.Entry<ResourceType, ResourceCounter> entry : map.entrySet()) {
			mapCopy.put(entry.getKey(),
					new ResourceCounter(entry.getValue().getValue()));
		}

		ResourcesList newResourcesList = new ResourcesList();
		newResourcesList.map = mapCopy;
		return newResourcesList;
	}

	@Override
	public String toString() {
		return "" + map;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ResourcesList that = (ResourcesList) o;

		return map.equals(that.map);
	}

	@Override
	public int hashCode() {
		return map.hashCode();
	}

	// TODO @mirko
	//public <T> T iterate(Function<ResourcesList,T> lambda) {


}
