package it.polimi.ingsw.GC_36.model;

public abstract class CardType {
	@Override
	public int hashCode() {
		return this.getClass().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		return this.getClass().equals(o.getClass());
	}

	public static class TerritorieCard extends CardType {
	}

	public static class BuildingCard extends CardType {

	}

	public static class CharacterCard extends CardType {

	}

	public static class VentureCard extends CardType {
	}


}