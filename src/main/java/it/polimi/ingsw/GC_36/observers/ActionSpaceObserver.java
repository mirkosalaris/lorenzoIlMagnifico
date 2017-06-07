package it.polimi.ingsw.GC_36.observers;

import it.polimi.ingsw.GC_36.model.ActionSpaceIds;

import java.rmi.RemoteException;

public interface ActionSpaceObserver {
	void update(ActionSpaceIds id, boolean free) throws RemoteException;
}
