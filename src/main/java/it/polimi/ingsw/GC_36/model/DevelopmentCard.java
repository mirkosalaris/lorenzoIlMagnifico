package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.io.Serializable;
import java.util.List;

public class DevelopmentCard implements Serializable {

	private CardType type;
	private int period;
	private String name;
	private List<Pair<ResourcesList, ResourcesList>> requirements;
	private ImmediateEffect immediateEffect;
	private PermanentEffect permanentEffect;

	public DevelopmentCard(CardType type, int period, String name,
	                       List<Pair<ResourcesList, ResourcesList>>
			                       requirements,
	                       ImmediateEffect immediateEffect,
	                       PermanentEffect permanentEffect) {
		this.type = type;
		this.period = period;
		this.name = name;
		this.requirements = requirements;
		this.immediateEffect = immediateEffect;
		this.permanentEffect = permanentEffect;
	}

	public String getName() {
		return name;
	}

	public List<Pair<ResourcesList, ResourcesList>> getRequirements() {
		return requirements;
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
				"\n\trequirementsList: " + requirementsToString(requirements) +
				"\n\timmediateEffect=" + immediateEffect +
				"\n\tpermanentEffect=" + permanentEffect +
				"\n";
	}

	private String requirementsToString(
			List<Pair<ResourcesList, ResourcesList>> requirements) {
		String s;
		if (requirements == null || requirements.isEmpty()) {
			s = "\tno requirements";
		} else if (requirements.size() == 1) {
			s = "\tto have: " + requirements.get(0).getFirst()
					+ "\n\tto pay: " + requirements.get(0).getSecond();
		} else {
			StringBuilder sBuilder = new StringBuilder();
			for (int i = 0; i < requirements.size(); i++) {
				sBuilder.append("option ").append(i).append(": \n")
						.append("\tto have: ")
						.append(requirements.get(i).getFirst())
						.append("\n\tto pay: ")
						.append(requirements.get(i).getSecond());
			}
			s = sBuilder.toString();
		}

		return s;
	}
}
