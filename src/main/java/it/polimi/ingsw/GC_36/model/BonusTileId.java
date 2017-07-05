package it.polimi.ingsw.GC_36.model;

public enum BonusTileId {
	FIRST(0), SECOND(1), THIRD(2), FOURTH(3), DEFAULT(4);

	private int value;

	BonusTileId(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}
}
