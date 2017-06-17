package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.observers.FloorObserver;

import java.io.IOException;
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
			throws IOException {
		this.developmentCard = developmentCard;
		changeNotify();
	}

	public DevelopmentCard takeCard() throws IOException {
		DevelopmentCard card = developmentCard;
		developmentCard = null;
		changeNotify(); // it will notify <floorNumber - null>
		return card;
	}

	public Tower getAssociatedTower() {
		return associatedTower;
	}

	public void subscribe(FloorObserver o) {
		observers.add(o);
	}

	public DevelopmentCard readAssociatedCard() {
		return developmentCard;
	}

	private void changeNotify() throws IOException {
		for (FloorObserver o : observers) {
			o.update(floorNumber, developmentCard);
		}
	}

}
