package it.polimi.ingsw.GC_36.utils;

import java.io.Serializable;

public class Pair<K, V> implements Serializable {

	private final K first;
	private final V second;

	public Pair(K first, V second) {
		this.first = first;
		this.second = second;
	}

	public K getFirst() {
		return first;
	}

	public V getSecond() {
		return second;
	}

	@Override
	public String toString() {
		return "[" + first + ", " + second + "]";

	}
}