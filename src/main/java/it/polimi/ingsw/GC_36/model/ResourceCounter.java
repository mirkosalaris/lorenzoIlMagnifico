package it.polimi.ingsw.GC_36.model;

public class ResourceCounter {
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


}
