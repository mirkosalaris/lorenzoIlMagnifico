package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.observers.ActionSpaceObserver;

import java.util.HashSet;
import java.util.Set;

public class ActionSpace implements ActionSpaceInterface {
	private ActionSpaceIds id;
	private boolean free;
	private int requiredActionValue;
	private ResourcesList requiredResourcesList;
	private Floor associatedFloor;
	private Set<ActionSpaceObserver> observers = new HashSet<>();

	public ActionSpace(ActionSpaceIds id) {
		Commons common = Commons.getInstance();

		this.id = id;
		this.requiredActionValue = common.getRequiredActionValue(id);
		this.requiredResourcesList = common.getRequirements(id);
		this.associatedFloor = common.getAssociatedFloor(id);

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
	public void reset() {
		// TODO impl

		setFree(true);
	}

	@Override
	public ActionSpaceIds getId() {
		return id;
	}

	private void setFree(boolean free) {
		this.free = free;

		changeNotify();
	}

	private void changeNotify() {
		for (ActionSpaceObserver o : observers) {
			o.update(this);
		}
	}
}