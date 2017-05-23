package it.polimi.ingsw.GC_36.model;

public class PersonalBoard {

	private ResourcesList resourcesList;
	private BonusTile bonusTile;
	private DevelopmentCard[] territoriesCards;
	private DevelopmentCard[] buildingsCards;
	private DevelopmentCard[] charactersCards;
	private DevelopmentCard[] venturesCards;

	public PersonalBoard() {
		//TODO
	}

	public ResourcesList getResourcesList() {
		return resourcesList;
	}

	public BonusTile getBonusTile() {
		return bonusTile;
	}

	public DevelopmentCard[] getTerritoriesCard() {
		return territoriesCards;
	}

	public DevelopmentCard[] getBuildingsCard() {
		return buildingsCards;
	}

	public DevelopmentCard[] getCharactersCards() {
		return charactersCards;
	}

	public DevelopmentCard[] getVenturesCards() {
		return venturesCards;
	}
}
