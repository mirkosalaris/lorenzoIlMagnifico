package it.polimi.ingsw.GC_36.model;

public class Floor {
	private Tower associatedTower;
	private DevelopmentCard developmentCard;

	public Floor() {
		//TODO
	}

	public boolean isCardTaken() {
		//TODO
		return (developmentCard == null) ? true : false;
	}

	public DevelopmentCard getCard() {
		return developmentCard;
	}
}
