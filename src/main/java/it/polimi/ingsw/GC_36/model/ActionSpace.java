package it.polimi.ingsw.GC_36.model;

public class ActionSpace {

	private boolean free;
	private int requiredActionValue;
	private ResourcesList resourcesList; //requisiti o ricompensa?
	private Floor associatedFlor;

	public ActionSpace() {
		//TODO
	}

	public boolean isAvailable() {
		return free;
	}

	public int getRequiredActionValue() {
		return requiredActionValue;
	}

	public ResourcesList getResourcesList() {
		return resourcesList;
	}

	public boolean isInTower() {
		return (associatedFlor != null) ? true : false;
	}

	public Floor getAssociatedFlor() {
		return associatedFlor;
	}
}