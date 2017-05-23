package it.polimi.ingsw.GC_36.model;

public class DevelopmentCard {

	private String name;
	private ResourcesList requirementsList;
	private Effect immediateEffect, permanentEffect;

	public DevelopmentCard() {
		//TODO
	}

	public String getName() {
		return name;
	}

	public ResourcesList getRequirements() {
		return requirementsList;
	}

	public Effect getImmediateEffect() {
		return immediateEffect;
	}

	public Effect getPermanentEffect() {
		return permanentEffect;
	}

}
