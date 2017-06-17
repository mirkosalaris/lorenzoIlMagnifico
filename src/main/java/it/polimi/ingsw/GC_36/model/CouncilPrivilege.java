package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Commons;

public enum CouncilPrivilege {
	STONE_WOOD(0), SERVANTS(1), COINS(2), MILITARY(3), FAITH(4);
	ResourcesList resources;

	CouncilPrivilege(int value) {
		this.resources = Commons.getPrivilege(value);
	}

	public ResourcesList getResources() {
		return new ResourcesList(resources);
	}

}
