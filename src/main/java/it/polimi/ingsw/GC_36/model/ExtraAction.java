package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.exception.NotAvailableException;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

public class ExtraAction extends Action {
	private Set<ActionSpaceIds> actionSpacesIds;

	public ExtraAction(Set<ActionSpaceIds> actionSpaceIds, int baseActionValue,
	                   ResourcesList discount) throws RemoteException {

		super(baseActionValue, discount);
		this.actionSpacesIds = actionSpaceIds;
	}

	@Override
	public void setMemberColor(MemberColor MemberColor) {
		throw new UnsupportedOperationException();
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

	public Set<ActionSpaceIds> getActionSpacesIds() {
		return new HashSet<>(actionSpacesIds);
	}


}
