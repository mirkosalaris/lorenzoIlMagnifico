package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.model.CardType;
import it.polimi.ingsw.GC_36.model.DevelopmentCard;
import it.polimi.ingsw.GC_36.server.Participant;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserInterface extends Participant, Remote {
	void show(String message) throws RemoteException;

	List<DevelopmentCard> getCards(CardType type) throws RemoteException;
}
