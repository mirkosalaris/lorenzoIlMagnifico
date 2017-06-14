package it.polimi.ingsw.GC_36.model;

import java.util.Map;

public class CouncilPrivilege {

	Map<Integer, ResourcesList> map;

	public CouncilPrivilege(Map<Integer, ResourcesList> councilPrivilegeMap) {
		map = councilPrivilegeMap;
	}

	public ResourcesList getResourcesList(Integer integer) {
		return map.get(integer);
	}

}
