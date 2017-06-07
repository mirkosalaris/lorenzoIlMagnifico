package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.observers.ModelObserver;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Participant extends ModelObserver, Remote {

	void fatalError(String s) throws RemoteException;

	void play(Action action)
			throws RemoteException, IOException, ClassNotFoundException;

	void exit() throws RemoteException;
}
