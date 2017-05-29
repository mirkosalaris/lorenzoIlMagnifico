package it.polimi.ingsw.GC_36.model;

import java.util.List;

public class Deck {
	CardType type;
	int period;
	List<DevelopmentCard> developmentCardList;

	public Deck(CardType type, int period,
	            List<DevelopmentCard> developmentCardList) {
		this.type = type;
		this.period = period;
		this.developmentCardList = developmentCardList;
	}

	public DevelopmentCard popCard() {
		DevelopmentCard card = developmentCardList.get(
				developmentCardList.size());
		developmentCardList.remove(developmentCardList.size());
		return card;
	}
}
