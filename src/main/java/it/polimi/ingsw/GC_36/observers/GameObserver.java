package it.polimi.ingsw.GC_36.observers;

import it.polimi.ingsw.GC_36.model.GameState;

import java.io.IOException;
import java.rmi.RemoteException;

public interface GameObserver {

	void update(GameState newState) throws RemoteException, IOException;

	void update(int periodNumber) throws RemoteException, IOException;

}
