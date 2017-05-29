package it.polimi.ingsw.GC_36.model.effects;

import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.CouncilPrivilege;
import it.polimi.ingsw.GC_36.model.Game;
import it.polimi.ingsw.GC_36.model.ResourcesList;

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
	public void applyEffect(Action action) {
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

	private boolean allDifferent(List<Integer> list) {
		HashSet<Integer> set = new HashSet<>(list);
		return list.size() == set.size();

	}


}