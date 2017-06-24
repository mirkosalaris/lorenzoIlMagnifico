package it.polimi.ingsw.GC_36.model.effects.permanentEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.view.ViewInterface;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.exception.NotCorrectlyCheckedException;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;
import it.polimi.ingsw.GC_36.model.effects.immediateEffects
		.ImmediateCouncilPrivilegeI;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class ImmediateCouncilPrivilegeP extends PermanentEffect {
	private Integer numberOfPrivileges;
	private boolean mustDiffer;
	private ImmediateEffect immediateEffect;
	private int actionValue;

	public ImmediateCouncilPrivilegeP(Integer numberOfPrivileges,
	                                  boolean mustDiffer,
	                                  int actionValue) {
		this.numberOfPrivileges = numberOfPrivileges;
		this.mustDiffer = mustDiffer;
		this.actionValue = actionValue;
		this.immediateEffect = new ImmediateCouncilPrivilegeI(
				numberOfPrivileges, false);
	}

	@Override
	public void applyEffect(Action action, Player player)
			throws EffectApplyingException, NotCorrectlyCheckedException {
		if (isDoable(actionValue, action)) {
			immediateEffect.applyEffect(action, player);
		}
	}

	private boolean allDifferent(List<CouncilPrivilege> list) {
		HashSet<CouncilPrivilege> set = new HashSet<>(list);
		return list.size() == set.size();
	}

	private boolean isValid(int choice) {
		return (choice >= 0 && choice < CouncilPrivilege.values().length - 1);
	}

	@Override
	public void chooseOption(ViewInterface view, ActionInterface action,
	                         User user)
			throws IOException, ClassNotFoundException {
		immediateEffect.chooseOptions(view, action, user);
	}

	@Override
	public boolean check(Action action, ResourcesList playerResources) {
		return false;
	}
}
