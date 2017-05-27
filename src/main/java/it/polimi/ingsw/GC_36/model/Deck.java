package it.polimi.ingsw.GC_36.model;

import java.util.List;
import java.util.Stack;

public class Deck extends Stack {
	String typeDevelopmentCard;
	int period;
	List<DevelopmentCard> developmentCardList;

	public Deck(String typeDevelopmentCard, int period,
	            List<DevelopmentCard> developmentCardList) {
		this.typeDevelopmentCard = typeDevelopmentCard;
		this.period = period;
		this.developmentCardList = developmentCardList;
	}

	public DevelopmentCard popCard() {
		//TODO
		return null;
	}
}
