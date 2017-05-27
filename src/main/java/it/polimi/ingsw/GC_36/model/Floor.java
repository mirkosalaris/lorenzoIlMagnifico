package it.polimi.ingsw.GC_36.model;

public class Floor {
	private Tower associatedTower;
	private DevelopmentCard developmentCard;

	public Floor(Tower tower) {
		this.associatedTower = tower;
		this.developmentCard = null;
	}

	public boolean isCardTaken() {
		return (developmentCard == null) ? true : false;
	}

	public DevelopmentCard getCard() {
		DevelopmentCard card = developmentCard;
		developmentCard = null;
		return card;
	}
}
