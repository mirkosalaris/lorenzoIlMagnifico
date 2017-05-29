package it.polimi.ingsw.GC_36.model;

import java.util.List;

public class DeckSet {
	int period;
	List<Deck> deckList;
	private Deck territoriesDeck;
	private Deck buildingsDeck;
	private Deck charactersDeck;
	private Deck venturesDeck;

	public DeckSet(int period,
	               List<Deck> deckList) {
		this.period = period;
		this.deckList = deckList;
	}

	public Deck getTerritoriesDeck() {
		return territoriesDeck;
	}

	public Deck getBuildingsDeck() {
		return buildingsDeck;
	}

	public Deck getCharactersDeck() {
		return charactersDeck;
	}

	public Deck getVenturesDeck() {
		return venturesDeck;
	}
}
