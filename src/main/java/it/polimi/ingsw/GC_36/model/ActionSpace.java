package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.observers.ActionSpaceObserver;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ActionSpace implements ActionSpaceInterface {
	private ActionSpaceIds id;
	private boolean free;
	private int requiredActionValue;
	private ResourcesList requiredResourcesList;
	private Floor associatedFloor;
	private Set<ActionSpaceObserver> observers = new HashSet<>();

	public ActionSpace(ActionSpaceIds id, Board board) {
		this.id = id;
		this.requiredActionValue = Commons.getRequiredActionValue(id);
		this.requiredResourcesList = Commons.getResources(id);

		Tower tower = board.getTower(id.getCardType());
		if (tower != null) {
			this.associatedFloor = tower.getFloor(id.getFloorNumber());
		} else {
			this.associatedFloor = null;
		}

		// no need to notify changes during construction
		free = true;
	}

	@Override
	public boolean isAvailable() {
		return free;
	}

	@Override
	public int getRequiredActionValue() {
		return requiredActionValue;
	}

	@Override
	public ResourcesList getRequiredResourcesList() {
		return requiredResourcesList;
	}

	@Override
	public boolean isInTower() {
		return associatedFloor != null;
	}

	@Override
	public Floor getAssociatedFloor() {
		return associatedFloor;
	}

	@Override
	public void subscribe(ActionSpaceObserver o) {
		observers.add(o);
	}

	@Override
	public void reset() throws IOException {
		// TODO impl

		setFree(true);
	}

	@Override
	public ActionSpaceIds getId() {
		return id;
	}

	private void setFree(boolean free) throws IOException {
		this.free = free;

		changeNotify();
	}

	private void changeNotify() throws IOException {
		for (ActionSpaceObserver o : observers) {
			o.update(this.id, free);
		}
	}
}