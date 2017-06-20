package it.polimi.ingsw.GC_36.observers;

import it.polimi.ingsw.GC_36.model.GameState;
import it.polimi.ingsw.GC_36.model.PlayerIdentifier;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public interface GameObserver {

	void update(GameState newState) throws RemoteException, IOException;

	void update(int periodNumber) throws RemoteException, IOException;

	void update(List<Pair<PlayerIdentifier, Integer>> winningOrderList)
			throws IOException;
}
