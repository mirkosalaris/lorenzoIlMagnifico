package it.polimi.ingsw.GC_36.observers;

import it.polimi.ingsw.GC_36.model.DevelopmentCard;
import it.polimi.ingsw.GC_36.model.Tower;

import java.rmi.RemoteException;

public interface FloorObserver {

	// the only thing that can change in a floor is the developmentCard
	// (sometimes null)
	void update(int floorNumber, Tower tower, DevelopmentCard developmentCard)
			throws RemoteException;
}
