package it.polimi.ingsw.GC_36.model;

public enum Tower {
	TERRITORIES(CardType.TERRITORY),
	BUILDINGS(CardType.BUILDING),
	CHARACTERS(CardType.CHARACTER),
	VENTURERS(CardType.VENTURE);

	private boolean free;
	private CardType cardType;
	private Floor[] floors = new Floor[4];

	Tower(CardType cardType) {
		free = true;

		this.cardType = cardType;

		for (int i = 0; i < floors.length; i++) {
			floors[i] = new Floor(this);
		}
	}

	public boolean isFree() {
		return free;
	}

	public Floor getFloor(int piano) {
		return floors[piano];
	}

	public void occupy() {
		free = false;
	}

	public void reset() {
		free = true;
	}

	public CardType getCardType() {
		return cardType;
	}
}
