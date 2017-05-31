package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Commons;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

public class DeckSet {
	int period;
	Map<CardType, Deck> decks;

	public DeckSet(int period) {
		decks = Commons.getInstance().getDeckSet(period);

		// TODO delete when parser is implemented
		decks = new EnumMap<>(CardType.class);
		decks.put(CardType.TERRITORY,
				new Deck(CardType.TERRITORY, period, new ArrayList<>()));
		decks.put(CardType.BUILDING,
				new Deck(CardType.BUILDING, period, new ArrayList<>()));
		decks.put(CardType.CHARACTER,
				new Deck(CardType.CHARACTER, period, new ArrayList<>()));
		decks.put(CardType.VENTURE,
				new Deck(CardType.VENTURE, period, new ArrayList<>()));
		// delete up to here

		this.period = period;
	}

	public Deck getDeck(CardType cardType) {
		return decks.get(cardType);
	}
}
