package it.polimi.ingsw.GC_36.model;

public class DevelopmentCard {

	private String type;
	private int period;
	private String name;
	private ResourcesList requirementsList;
	private Effect immediateEffect, permanentEffect;

	public DevelopmentCard(String type, int period, String name,
	                       ResourcesList requirementsList,
	                       Effect immediateEffect,
	                       Effect permanentEffect) {
		this.type = type;
		this.period = period;
		this.name = name;
		this.requirementsList = requirementsList;
		this.immediateEffect = immediateEffect;
		this.permanentEffect = permanentEffect;
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
