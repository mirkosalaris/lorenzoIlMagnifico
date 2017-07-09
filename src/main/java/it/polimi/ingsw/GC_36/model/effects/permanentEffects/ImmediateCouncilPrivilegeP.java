package it.polimi.ingsw.GC_36.model.effects.permanentEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.view.ViewInterface;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.exception.NotCorrectlyCheckedException;
import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.ActionInterface;
import it.polimi.ingsw.GC_36.model.Player;
import it.polimi.ingsw.GC_36.model.ResourcesList;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;
import it.polimi.ingsw.GC_36.model.effects.immediateEffects
		.ImmediateCouncilPrivilegeI;

import java.io.IOException;

public class ImmediateCouncilPrivilegeP extends PermanentEffect {
	private Integer numberOfPrivileges;
	private boolean mustDiffer;
	private ImmediateCouncilPrivilegeI immediateEffect;
	private int actionValue;

	public ImmediateCouncilPrivilegeP(Integer numberOfPrivileges,
	                                  boolean mustDiffer,
	                                  int actionValue) {
		// stored just for the toString
		this.numberOfPrivileges = numberOfPrivileges;
		this.mustDiffer = mustDiffer;
		this.actionValue = actionValue;

		this.immediateEffect = new ImmediateCouncilPrivilegeI(
				numberOfPrivileges, mustDiffer);
	}

	@Override
	public void applyEffect(Action action, Player player)
			throws EffectApplyingException, NotCorrectlyCheckedException {
		if (isDoable(actionValue, action)) {
			immediateEffect.applyEffect(action, player);
		}
	}

	@Override
	public void chooseOption(ViewInterface view, ActionInterface action,
	                         User user)
			throws IOException, ClassNotFoundException {
		immediateEffect.chooseOptions(view, action, user);
	}

	@Override
	public boolean check(Action action, ResourcesList playerResources) {
		return true;
	}

	@Override
	public String toString() {
		return "ImmediateCouncilPrivilegeP{" +
				"numberOfPrivileges=" + numberOfPrivileges +
				", mustDiffer=" + mustDiffer +
				", actionValue=" + actionValue +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ImmediateCouncilPrivilegeP that = (ImmediateCouncilPrivilegeP) o;

		if (mustDiffer != that.mustDiffer) return false;
		if (actionValue != that.actionValue) return false;
		if (numberOfPrivileges != null ? !numberOfPrivileges.equals(
				that.numberOfPrivileges) : that.numberOfPrivileges != null)
			return false;
		return immediateEffect != null ? immediateEffect.equals(
				that.immediateEffect) : that.immediateEffect == null;
	}

	@Override
	public int hashCode() {
		int result = numberOfPrivileges != null ? numberOfPrivileges.hashCode
				() : 0;
		result = 31 * result + (mustDiffer ? 1 : 0);
		result = 31 * result + (immediateEffect != null ? immediateEffect
				.hashCode() : 0);
		result = 31 * result + actionValue;
		return result;
	}
}
