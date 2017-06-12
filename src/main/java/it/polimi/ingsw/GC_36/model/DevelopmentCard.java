package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;

import java.io.Serializable;
import java.util.List;

public class DevelopmentCard implements Serializable {

	private CardType type;
	private int period;
	private String name;
	private List<ResourcesList> requirementsList;
	private ImmediateEffect immediateEffect;
	private PermanentEffect permanentEffect;

	public DevelopmentCard(CardType type, int period, String name,
	                       List<ResourcesList> requirementsList,
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

	public List<ResourcesList> getRequirements() {
		return requirementsList;
	}

	public ImmediateEffect getImmediateEffect() {
		return immediateEffect;
	}

	public PermanentEffect getPermanentEffect() {
		return permanentEffect;
	}

	public int getPeriod() {
		return period;
	}

	public CardType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "\n\ttype=" + type +
				"\n\tperiod=" + period +
				"\n\tname='" + name + '\'' +
				"\n\trequirementsList=" + requirementsList +
				"\n\timmediateEffect=" + immediateEffect +
				"\n\tpermanentEffect=" + permanentEffect +
				"\n";
	}
}
