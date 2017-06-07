package it.polimi.ingsw.GC_36.model;

import java.util.Set;

public class ExtraAction extends Action {
	private Set<ActionSpaceIds> actionSpacesIds;

	public ExtraAction(Set<ActionSpaceIds> actionSpaceIds) {
		this.actionSpacesIds = actionSpaceIds;
	}

	@Override
	public void setActionSpaceIds(ActionSpaceIds actionSpaceIds) {
		//if
		super.setActionSpaceIds(actionSpaceIds);
		// else

	}


}
