package it.polimi.ingsw.GC_36.model;

import java.io.Serializable;

public class ResourceCounter implements Serializable {
	int value;

	public ResourceCounter(int value) {
		this.value = value;

	}

	public int getValue() {
		return value;
	}

	public void subtract(int fee) {
		this.value -= fee;
	}

	public void add(int value) {
		this.value += value;
	}

	@Override
	public String toString() {
		return "" + value;
	}
}
