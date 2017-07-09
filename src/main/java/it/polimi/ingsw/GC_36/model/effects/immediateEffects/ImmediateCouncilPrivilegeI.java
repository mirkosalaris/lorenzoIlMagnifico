package it.polimi.ingsw.GC_36.model.effects.immediateEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.view.ViewInterface;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.exception.NotCorrectlyCheckedException;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ImmediateCouncilPrivilegeI extends ImmediateEffect {
	private boolean mustDiffer;
	private Integer numberOfPrivileges;

	public ImmediateCouncilPrivilegeI(Integer numberOfPrivileges,
	                                  boolean mustDiffer) {
		this.numberOfPrivileges = numberOfPrivileges;
		this.mustDiffer = mustDiffer;
	}


	// accept n choices
	// check choices
	// add choices to personaBoard, otherwise throw EffectApplyingException(e)
	@Override
	public void applyEffect(Action action, Player player)
			throws IllegalStateException, EffectApplyingException,
			NotCorrectlyCheckedException {
		if (!numberOfPrivileges.equals(
				action.getCouncilPrivilegeList().size())) {
			throw new NotCorrectlyCheckedException(
					"number of selected privileges is wrong");
		}
		if ((mustDiffer) && (!allDifferent(
				action.getCouncilPrivilegeList()))) {
			throw new NotCorrectlyCheckedException(
					"privilege are not different");
		} else {
			// add resources
			for (CouncilPrivilege key : action.getCouncilPrivilegeList()) {

				ResourcesList privilege = key.getResources();

				try {
					player.getPersonalBoard().addResources(privilege);
				} catch (IOException e) {
					throw new EffectApplyingException(e);
				}
			}
		}
	}

	@Override
	public void chooseOptions(ViewInterface view, ActionInterface action,
	                          User user) throws RemoteException {
		List<Integer> choices = new ArrayList<>();

		int i = 0;
		do {
			int choice = view.choosePrivilege(i + 1);
			if (isValid(choice)
					|| (mustDiffer && !choices.contains(choice))) {
				choices.add(choice);
				i++;
			}
		} while (i < numberOfPrivileges);

		// store choices
		for (Integer choice : choices) {

			action.putPrivilegeChoice(choice);
		}
	}

	private boolean allDifferent(List<CouncilPrivilege> list) {
		HashSet<CouncilPrivilege> set = new HashSet<>(list);
		return list.size() == set.size();
	}

	private boolean isValid(int choice) {
		return (choice >= 0 && choice <= CouncilPrivilege.values().length - 1);
	}

	@Override
	public String toString() {
		return "ImmediateCouncilPrivilegeI{" +
				"mustDiffer=" + mustDiffer +
				", numberOfPrivileges=" + numberOfPrivileges +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ImmediateCouncilPrivilegeI that = (ImmediateCouncilPrivilegeI) o;

		if (mustDiffer != that.mustDiffer) return false;
		return numberOfPrivileges != null ? numberOfPrivileges.equals(
				that.numberOfPrivileges) : that.numberOfPrivileges == null;
	}

	@Override
	public int hashCode() {
		int result = (mustDiffer ? 1 : 0);
		result = 31 * result + (numberOfPrivileges != null ?
				numberOfPrivileges.hashCode() : 0);
		return result;
	}
}