package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.exception.InsufficientResourcesException;
import it.polimi.ingsw.GC_36.observers.PersonalBoardObserver;

import java.io.IOException;
import java.util.*;

public class PersonalBoard {

	private ResourcesList resourcesList;
	private BonusTile bonusTile; // TODO use it
	private Map<CardType, List<DevelopmentCard>> map =
			new EnumMap<>(CardType.class);
	private Set<PersonalBoardObserver> observers = new HashSet<>();

	public PersonalBoard(int ordinal) {
		this.resourcesList =
				Commons.getInitialResources(ordinal);

		// create 4 empty List<DevelopmentCard>
		map.put(CardType.TERRITORY, new ArrayList<>());
		map.put(CardType.BUILDING, new ArrayList<>());
		map.put(CardType.CHARACTER, new ArrayList<>());
		map.put(CardType.VENTURE, new ArrayList<>());
	}

	public ResourcesList getResourcesList() {
		return resourcesList;
	}

	public void addResources(ResourcesList resources) throws IOException {
		resourcesList.addResources(resources);
		resourcesNotify();
	}

	public void addCard(DevelopmentCard card) throws IOException {
		map.get(card.getType()).add(card);
		newCardNotify(card);
	}

	public void payResources(ResourcesList resources)
			throws InsufficientResourcesException, IOException {
		// TODO: change it to actually check
		if (resourcesList.checkEnoughResources(resources)) {
			resourcesList.subtractResources(resources);
		} else {
			throw new InsufficientResourcesException();
		}
		resourcesNotify();
	}

	public BonusTile getBonusTile() {
		return bonusTile;
	}

	public List<DevelopmentCard> getCards(CardType type) {
		// return a COPY of the list
		return new ArrayList<>(map.get(type));
	}

	public void subscribe(PersonalBoardObserver o) {
		observers.add(o);
	}

	private void newCardNotify(DevelopmentCard card) throws IOException {
		for (PersonalBoardObserver o : observers) {
			o.update(card);
		}
	}

	private void resourcesNotify() throws IOException {
		for (PersonalBoardObserver o : observers) {
			o.update(resourcesList);
		}
	}

}
