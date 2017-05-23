package it.polimi.ingsw.GC_36.model;

public class Die {

	private Color color;
	private int value;

	public Die(Color color) {
		this.color = color;
	}

	public Color getColor() {

		return color;
	}

	public int getValue() {

		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
