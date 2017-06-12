package it.polimi.ingsw.GC_36.model;

import java.io.Serializable;

public class PlayerIdentifier implements Serializable {
	String identifier;

	public PlayerIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String get() {
		return identifier;
	}
}
