package it.polimi.ingsw.GC_36.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private CardType type;
	private int period;
	private List<DevelopmentCard> developmentCardList;

	public Deck(CardType type, int period,
	            List<DevelopmentCard> developmentCardList) {
		this.type = type;
		this.period = period;
		this.developmentCardList = developmentCardList;
	}

	public DevelopmentCard popCard() {

		DevelopmentCard card = developmentCardList.get(
				developmentCardList.size() - 1);
		developmentCardList.remove(developmentCardList.size() - 1);
		return card;
	}

	public Deck copy() {
		List<DevelopmentCard> newCards = new ArrayList<>(developmentCardList);

		return new Deck(type, period, newCards);
	}

	public void shuffle() {
		Collections.shuffle(developmentCardList);
	}
}
