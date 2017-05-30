package it.polimi.ingsw.GC_36.model;

public class Die {

	private DieColor dieColor;
	private int value;

	public Die(DieColor dieColor) {
		this.dieColor = dieColor;
	}

	public DieColor getDieColor() {

		return dieColor;
	}

	public int getValue() {

		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
