package it.polimi.ingsw.GC_36.observers;

import it.polimi.ingsw.GC_36.model.BoardState;

import java.io.IOException;
import java.rmi.RemoteException;

public interface BoardObserver {

	void update(BoardState currentState) throws RemoteException, IOException;
}
