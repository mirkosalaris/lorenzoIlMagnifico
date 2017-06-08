package it.polimi.ingsw.GC_36.observers;

import it.polimi.ingsw.GC_36.model.PlayerIdentifier;
import it.polimi.ingsw.GC_36.model.RoundState;

import java.rmi.RemoteException;

public interface RoundObserver {

	void update(RoundState newState) throws RemoteException;

	// TODO: vulnerability. We are giving observer the reference to player!!!
	//	void update(Player newPlayer) throws RemoteException;

	void update(PlayerIdentifier identifier) throws RemoteException;

}
