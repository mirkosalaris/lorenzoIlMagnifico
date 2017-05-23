package it.polimi.ingsw.GC_36.model;

import java.util.Map;

public class ResourcesList {
	Map<Resource, ResourceCounter> map;

	public ResourceCounter get(Resource r) {
		return map.get(r);
	}

}
