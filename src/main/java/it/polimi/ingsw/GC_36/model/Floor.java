package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.observers.FloorObserver;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

public class Floor {
	private Tower associatedTower;
	private int floorNumber;
	private DevelopmentCard developmentCard;
	private Set<FloorObserver> observers;

	public Floor(Tower tower, int floorNumber) {
		this.associatedTower = tower;
		this.floorNumber = floorNumber;

		// the floor is instantiated once but then the card change at runtime
		this.developmentCard = null;

		observers = new HashSet<>();
	}

	public boolean isCardTaken() {
		return developmentCard == null;
	}

	public void setDevelopmentCard(DevelopmentCard developmentCard)
			throws RemoteException {
		this.developmentCard = developmentCard;
		changeNotify();
	}

	public DevelopmentCard getCard() {
		DevelopmentCard card = developmentCard;
		developmentCard = null;
		return card;
	}

	public Tower getAssociatedTower() {
		return associatedTower;
	}

	public void subscribe(FloorObserver o) {
		observers.add(o);
	}

	private void changeNotify() throws RemoteException {
		for (FloorObserver o : observers) {
			o.update(floorNumber, associatedTower, developmentCard);
		}
	}
}
