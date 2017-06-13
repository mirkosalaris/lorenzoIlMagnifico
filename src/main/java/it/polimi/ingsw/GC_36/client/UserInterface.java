package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.server.Participant;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserInterface extends Participant, Remote {
	void show(String message) throws RemoteException;
}
