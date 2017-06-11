package it.polimi.ingsw.GC_36.model;

import java.util.Set;

public class ExtraAction extends Action {

	@Override
	public void setMemberColor(MemberColor MemberColor) {
		//TODO: throws exeption
	}


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

	@Override
	public boolean isAvailable(ActionSpaceIds actionSpaceId) {
		return this.actionSpacesIds.contains(actionSpaceId);
	}

}
