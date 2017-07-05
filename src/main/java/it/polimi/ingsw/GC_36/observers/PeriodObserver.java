package it.polimi.ingsw.GC_36.observers;

import java.io.IOException;
import java.rmi.RemoteException;

public interface PeriodObserver {

	void terminatedRound() throws RemoteException, IOException;

}
