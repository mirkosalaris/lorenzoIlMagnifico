package it.polimi.ingsw.GC_36.observers;

import it.polimi.ingsw.GC_36.model.PlayerIdentifier;
import it.polimi.ingsw.GC_36.model.RoundState;

import java.io.IOException;
import java.rmi.RemoteException;

public interface RoundObserver {

	void update(RoundState newState) throws RemoteException, IOException;

	void update(PlayerIdentifier identifier)
			throws RemoteException, IOException;

}
