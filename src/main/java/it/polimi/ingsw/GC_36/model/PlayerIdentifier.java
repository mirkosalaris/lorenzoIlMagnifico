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

	@Override
	public String toString() {
		return identifier;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PlayerIdentifier that = (PlayerIdentifier) o;

		return this.identifier != null
				? this.identifier.equals(that.identifier)
				: that.identifier == null;
	}

	@Override
	public int hashCode() {
		return this.identifier != null ? this.identifier.hashCode() : 0;
	}
}
