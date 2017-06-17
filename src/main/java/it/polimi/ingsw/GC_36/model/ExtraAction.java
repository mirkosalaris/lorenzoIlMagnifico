package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.exception.NotAvailableException;

import java.rmi.RemoteException;
import java.util.Set;

public class ExtraAction extends Action {

	@Override
	public void setMemberColor(MemberColor MemberColor) {
		//TODO: throws exeption
	}


	private Set<ActionSpaceIds> actionSpacesIds;

	public ExtraAction(Set<ActionSpaceIds> actionSpaceIds)
			throws RemoteException {
		this.actionSpacesIds = actionSpaceIds;
	}

	@Override
	public void setActionSpaceIds(ActionSpaceIds actionSpaceIds)
			throws NotAvailableException {
		if (!isAvailable(actionSpaceIds)) {
			throw new NotAvailableException();
		}
		super.setActionSpaceIds(actionSpaceIds);

	}

	@Override
	public boolean isAvailable(ActionSpaceIds actionSpaceId) {
		return this.actionSpacesIds.contains(actionSpaceId);
	}

}
