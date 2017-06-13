package it.polimi.ingsw.GC_36.model.effects.immediateEffects;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.CouncilPrivilege;
import it.polimi.ingsw.GC_36.model.Game;
import it.polimi.ingsw.GC_36.model.ResourcesList;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;

import java.util.HashSet;
import java.util.List;

public class ImmediateCouncilPrivileges implements ImmediateEffect {
	private boolean mustDiffer;
	private Integer numberOfPrivileges;
	private CouncilPrivilege councilPrivilege;

	public ImmediateCouncilPrivileges(Integer numberOfPrivileges,
	                                  boolean mustDiffer) {
		this.numberOfPrivileges = numberOfPrivileges;
		this.mustDiffer = mustDiffer;
	}


	//accetta le n scelte
	//check sulle scelte
	//le aggiunge alla personal board altrimenti throw exception
	@Override
	public void applyEffect(Action action) throws IllegalStateException {
		if (!numberOfPrivileges.equals(
				action.getCouncilPrivilegeList().size()))
			//TODO:eccezione
			if ((mustDiffer) && (!allDifferent(
					action.getCouncilPrivilegeList()))) {

				//TODO:mossa non valida
			} else {
				//aggiungi risorse
				for (Integer key : action.getCouncilPrivilegeList()) {

					ResourcesList favor = councilPrivilege.getResourcesList(
							action.getCouncilPrivilegeList().get(key));

					Game.getInstance().getCurrentPeriod().getCurrentRound()
							.getCurrentPlayer().getPersonalBoard()
							.addResources(favor);
				}
			}

	}


	@Override
	public void chooseOptions(ViewInterface view, Action action, User user) {
		int choice;
		for (int i = 0; i < numberOfPrivileges; i++) {
			//check if the choice is valid
			do {
				choice = view.choosePrivilege(i + 1);
			} while (!Isvalid(
					choice) || (mustDiffer && action.getCouncilPrivilegeList()
					.contains(
							choice)));
			//end check
			action.putPrivilegeChoice(choice);
		}
	}

	private boolean allDifferent(List<Integer> list) {
		HashSet<Integer> set = new HashSet<>(list);
		return list.size() == set.size();

	}

	private boolean Isvalid(int choice) {
		return (choice >= 0 && choice < Commons.councilPrivilegeMap.size());
	}


}