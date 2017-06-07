package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class PersonalBoard {

	private ResourcesList resourcesList;
	private BonusTile bonusTile; // TODO use it
	Map<CardType, List<DevelopmentCard>> map =
			new EnumMap<>(CardType.class);


	public PersonalBoard(int ordinal) {
		this.resourcesList =
				Commons.getInstance().getInitialResources(ordinal);

		// create 4 empty List<DevelopmentCard>
		map.put(CardType.TERRITORY, new ArrayList<>());
		map.put(CardType.BUILDING, new ArrayList<>());
		map.put(CardType.CHARACTER, new ArrayList<>());
		map.put(CardType.VENTURE, new ArrayList<>());
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
