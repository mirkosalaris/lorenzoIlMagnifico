package it.polimi.ingsw.GC_36.model;

import java.util.Random;

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

	public void roll() {
		Random rnd = new Random();

		// a value between 0 and 6 inclusive
		this.value = rnd.nextInt(6) + 1;
	}
}
