package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.observers.ActionSpaceObserver;

public interface ActionSpaceInterface {
	boolean isAvailable();

	int getRequiredActionValue();

	ResourcesList getRequiredResourcesList();

	boolean isInTower();

	Floor getAssociatedFloor();

	void subscribe(ActionSpaceObserver o);

	void reset();
}
