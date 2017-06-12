package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.observers.ActionSpaceObserver;

import java.io.IOException;

public interface ActionSpaceInterface {
	boolean isAvailable();

	int getRequiredActionValue();

	ResourcesList getRequiredResourcesList();

	boolean isInTower();

	Floor getAssociatedFloor();

	void subscribe(ActionSpaceObserver o);

	void reset() throws IOException;

	ActionSpaceIds getId();
}
