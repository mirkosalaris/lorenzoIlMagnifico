package it.polimi.ingsw.GC_36.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonalBoard {

	private ResourcesList resourcesList;
	private BonusTile bonusTile;
	Map<CardType, List<DevelopmentCard>> map = new HashMap<>();


	public PersonalBoard() {
		//TODO
		//aggiungere 4 liset di carte vuote
	}

	public ResourcesList getResourcesList() {
		return resourcesList;
	}

	public void addResources(ResourcesList resources) {
		resourcesList.addResources(resources);
	}

	public void payResources(ResourcesList resources) {
		// TODO: change it to actually check
		resourcesList.checkEnoughResources(resources);
		resourcesList.subtractResources(resources);
	}

	public BonusTile getBonusTile() {
		return bonusTile;
	}

}
