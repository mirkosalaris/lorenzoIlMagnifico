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

		// TODO uncomment when Parser is implemented
		/*DevelopmentCard card = developmentCardList.get(developmentCardList
		.size() - 1);
		developmentCardList.remove(developmentCardList.size() - 1);
		return card;*/

		return null;
	}
}
