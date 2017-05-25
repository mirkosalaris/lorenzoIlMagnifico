package it.polimi.ingsw.GC_36.model;

import java.util.HashMap;
import java.util.Map;

public class ResourcesList {
	Map<Resource, ResourceCounter> map = new HashMap<>();

	//TODO choose if use setters or implement this as final and use constructor

	public ResourceCounter get(Resource r) {
		return map.get(r);
	}

}
