package it.polimi.ingsw.GC_36.model.effects;

import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.CouncilPriviledge;
import it.polimi.ingsw.GC_36.model.Game;
import it.polimi.ingsw.GC_36.model.ResourcesList;

import java.util.HashSet;
import java.util.List;

public class ImmediateCouncilPriviledges implements ImmediateEffect {
	private boolean mustDiffer;
	private Integer numberOfPriviledges;
	private CouncilPriviledge councilPriviledge;

	public ImmediateCouncilPriviledges(Integer numberOfPriviledges,
	                                   boolean mustDiffer) {
		this.numberOfPriviledges = numberOfPriviledges;
		this.mustDiffer = mustDiffer;
	}


	//accetta le n scelte
	//check sulle scelte
	//le aggiunge alla personal board altrimenti throw exception
	@Override
	public void applyEffect(Action action) {
		if (numberOfPriviledges.equals(
				action.getCouncilPriviledgeList().size()))
			//TODO:eccezione
			if ((mustDiffer) && (!allDifferent(
					action.getCouncilPriviledgeList()))) {

				//TODO:mossa non valida
			} else {
				//aggiungi risorse
				for (Integer key : action.getCouncilPriviledgeList()) {

					ResourcesList favor = councilPriviledge.getResourcesList(
							action.getCouncilPriviledgeList().get(key));

					Game.getInstance().getCurrentPeriod().getCurrentRound()
							.getCurrentPlayer().getPersonalBoard()
							.addResources(favor);
				}
			}

	}

	private boolean allDifferent(List<Integer> list) {
		HashSet<Integer> set = new HashSet<Integer>(list);
		return (list.size() == set.size());

	}


}