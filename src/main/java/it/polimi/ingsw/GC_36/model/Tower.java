package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Commons;

public class Tower {
	private boolean free;
	private CardType cardType;
	private Floor[] floors = new Floor[Commons.NUMBER_OF_FLOORS];
	//TODO: @antonino vedi e provvedi all'inizializzazione
	private ResourcesList tax;

	Tower(CardType cardType) {
		free = true;

		this.cardType = cardType;

		for (int i = 0; i < floors.length; i++) {
			floors[i] = new Floor(this, i + 1);
		}
	}

	public boolean isFree() {
		return free;
	}

	public Floor getFloor(int floorNumber) {
		return floors[floorNumber - 1];
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

	public ResourcesList getTax(){
		return tax;
	}
}
