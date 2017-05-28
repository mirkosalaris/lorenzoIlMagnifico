package it.polimi.ingsw.GC_36.model;

public class Floor {
	private Tower associatedTower;
	private DevelopmentCard developmentCard;

	public Floor(Tower tower) {
		this.associatedTower = tower;

		// the floor is instantiated once but then the card change at runtime
		this.developmentCard = null;
	}

	public boolean isCardTaken() {
		return developmentCard == null;
	}

	public void setDevelopmentCard(DevelopmentCard developmentCard) {
		this.developmentCard = developmentCard;
	}

	public DevelopmentCard getCard() {
		DevelopmentCard card = developmentCard;
		developmentCard = null;
		return card;
	}

	public Tower getAssociatedTower() {
		return associatedTower;
	}
}
