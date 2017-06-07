package it.polimi.ingsw.GC_36.observers;

import it.polimi.ingsw.GC_36.model.PlayerState;

import java.rmi.RemoteException;

public interface PlayerObserver {

	void update(PlayerState newState) throws RemoteException;

}
