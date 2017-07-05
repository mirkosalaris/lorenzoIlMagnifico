package it.polimi.ingsw.GC_36.observers;

import it.polimi.ingsw.GC_36.model.CardType;
import it.polimi.ingsw.GC_36.model.DevelopmentCard;

import java.io.IOException;
import java.rmi.RemoteException;

public interface FloorObserver {

	// the only thing that can change in a floor is the developmentCard
	// (sometimes null)
	void update(CardType cardType, int floorNumber,
	            DevelopmentCard developmentCard)
			throws RemoteException, IOException;
}
