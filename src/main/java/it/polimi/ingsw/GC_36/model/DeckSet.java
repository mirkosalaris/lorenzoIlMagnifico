package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Commons;

import java.util.Map;

public class DeckSet {
	int period;
	Map<CardType, Deck> decks;

	public DeckSet(int period) {
		decks = Commons.getDeckSet(period);
		this.period = period;
	}

	public Deck getDeck(CardType cardType) {
		Deck deck = decks.get(cardType);
		return deck;
	}
}
