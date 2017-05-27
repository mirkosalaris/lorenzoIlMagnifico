package it.polimi.ingsw.GC_36.model;

import java.util.Map;

public class CouncilPriviledge {

	Map<Integer, ResourcesList> map;

	public CouncilPriviledge(Map<Integer, ResourcesList> councilfavormap) {
		map = councilfavormap;
	}

	public ResourcesList getResourcesList(Integer integer) {
		return map.get(integer);
	}

}
