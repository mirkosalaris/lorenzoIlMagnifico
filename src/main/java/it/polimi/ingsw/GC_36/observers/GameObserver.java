package it.polimi.ingsw.GC_36.observers;

import it.polimi.ingsw.GC_36.model.GameState;

import java.rmi.RemoteException;

public interface GameObserver {

	void update(GameState newState) throws RemoteException;

	void update(int periodNumber) throws RemoteException;

}
