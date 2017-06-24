package it.polimi.ingsw.GC_36.model.effects.immediateEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.view.ViewInterface;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.exception.NotCorrectlyCheckedException;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.List;

public class ImmediateCouncilPrivilegeI implements ImmediateEffect {
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

				ResourcesList favor = key.getResources();

				try {
					player.getPersonalBoard().addResources(favor);
				} catch (IOException e) {
					throw new EffectApplyingException(e);
				}
			}
		}
	}

	@Override
	public void chooseOptions(ViewInterface view, ActionInterface action,
	                          User user)
			throws RemoteException {
		int choice;
		for (int i = 0; i < numberOfPrivileges; i++) {
			//check if the choice is valid
			do {
				choice = view.choosePrivilege(i + 1);
			} while (!isValid(
					choice) || (mustDiffer && action.getCouncilPrivilegeList()
					.contains(
							choice)));
			//end check
			action.putPrivilegeChoice(choice);
		}
	}

	private boolean allDifferent(List<CouncilPrivilege> list) {
		HashSet<CouncilPrivilege> set = new HashSet<>(list);
		return list.size() == set.size();
	}

	private boolean isValid(int choice) {
		return (choice >= 0 && choice < CouncilPrivilege.values().length - 1);
	}


}