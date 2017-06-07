package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.client.UserInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserIncubatorInterface extends Remote {
	void addUser(UserInterface user)
			throws InterruptedException, RemoteException;
}
