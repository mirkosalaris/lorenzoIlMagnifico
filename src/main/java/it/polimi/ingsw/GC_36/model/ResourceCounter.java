package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.exception.InsufficientResourcesException;

import java.io.Serializable;

public class ResourceCounter implements Serializable {
	int value;

	public ResourceCounter(int value) {
		if (value >= 0) {
			this.value = value;
		} else {
			throw new IllegalArgumentException(
					"value must be greater than or equal to 0");
		}
	}

	public int getValue() {
		return value;
	}

	public void subtract(int fee) throws InsufficientResourcesException {
		if (value - fee < 0) {
			throw new InsufficientResourcesException();
		}
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
