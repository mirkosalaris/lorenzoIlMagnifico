package it.polimi.ingsw.GC_36.model;

public class ActionSpace {
	private int id;
	private boolean free;
	private int requiredActionValue;
	private ResourcesList requiredResourcesList;
	private Floor associatedFlor;


	public ActionSpace(int id, boolean free, int requiredActionValue,
	                   ResourcesList requiredResourcesList,
	                   Floor associatedFlor) {
		this.id = id;
		this.free = free;
		this.requiredActionValue = requiredActionValue;
		this.requiredResourcesList = requiredResourcesList;
		this.associatedFlor = associatedFlor;
	}

	public boolean isAvailable() {
		return free;
	}

	public int getRequiredActionValue() {
		return requiredActionValue;
	}

	public ResourcesList getRequiredResourcesList() {
		return requiredResourcesList;
	}

	public boolean isInTower() {
		return (associatedFlor != null) ? true : false;
	}

	public Floor getAssociatedFlor() {
		return associatedFlor;
	}

	public void reset() {
		// TODO impl
	}
}