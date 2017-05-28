package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;

public class DevelopmentCard {

	private CardType type;
	private int period;
	private String name;
	private ResourcesList requirementsList;
	private ImmediateEffect immediateEffect;
	private PermanentEffect permanentEffect;

	public DevelopmentCard(CardType type, int period, String name,
	                       ResourcesList requirementsList,
	                       ImmediateEffect immediateEffect,
	                       PermanentEffect permanentEffect) {
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

	public ImmediateEffect getImmediateEffect() {
		return immediateEffect;
	}

	public PermanentEffect getPermanentEffect() {
		return permanentEffect;
	}

}
