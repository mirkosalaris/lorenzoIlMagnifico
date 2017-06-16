package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.observers.ActionSpaceObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActionSpace {
	private final boolean isSingle;
	private ActionSpaceIds id;
	private boolean free;
	private int requiredActionValue;
	private ResourcesList bonus;
	private Floor associatedFloor;
	private List<FamilyMember> familyMembers = new ArrayList<>();
	private Set<ActionSpaceObserver> observers = new HashSet<>();

	public ActionSpace(ActionSpaceIds id, Board board) {
		this.id = id;
		this.requiredActionValue = Commons.getASRequiredActionValue(id);
		this.isSingle = Commons.getASIsSingle(id);
		this.requiredActionValue = Commons.getASRequiredActionValue(id);
		this.bonus = Commons.getASresources(id);

		Tower tower = board.getTower(id.getCardType());
		if (tower != null) {
			this.associatedFloor = tower.getFloor(id.getFloorNumber());
		} else {
			this.associatedFloor = null;
		}

		// no need to notify changes during construction
		free = true;
	}

	public void occupy(FamilyMember member) {
		if (free) {
			familyMembers.add(member);
			if (isSingle) {
				free = false;
			}
		}
	}

	public boolean isAvailable() {
		return free;
	}

	public int getRequiredActionValue() {
		return requiredActionValue;
	}

	public ResourcesList getBonus() {
		return bonus;
	}

	public boolean isInTower() {
		return associatedFloor != null;
	}

	public Floor getAssociatedFloor() {
		return associatedFloor;
	}

	public void subscribe(ActionSpaceObserver o) {
		observers.add(o);
	}

	public void reset() throws IOException {
		// TODO impl

		setFree(true);
	}

	public ActionSpaceIds getId() {
		return id;
	}

	public List<FamilyMember> getOccupants() {
		// return a copy
		return new ArrayList<>(familyMembers);
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