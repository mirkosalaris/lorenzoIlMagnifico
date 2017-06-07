package it.polimi.ingsw.GC_36.observers;

import it.polimi.ingsw.GC_36.model.Player;
import it.polimi.ingsw.GC_36.model.RoundState;

import java.rmi.RemoteException;

public interface RoundObserver {

	void update(RoundState newState) throws RemoteException;

	void update(Player newPlayer) throws RemoteException;

}
