package it.polimi.ingsw.GC_36.model;

public enum Tower {
	TERRITORIES, VENTURERS, CHARACTERS, BUILDINGS;

	private boolean free;
	private Floor[] floors = new Floor[4];

	Tower() {
		for (int i = 0; i < floors.length; i++) {
			floors[i] = new Floor(this);
		}
	}

	public boolean isFree() {
		return free;
	}

	public Floor getFloor(int piano) {
		return floors[piano - 1];

	}

	public void occupy() {
		free = false;
	}

	public void reset() {
		free = true;
	}
}
