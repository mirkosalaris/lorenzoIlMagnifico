package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.exception.InsufficientResourcesException;
import it.polimi.ingsw.GC_36.observers.PersonalBoardObserver;

import java.io.IOException;
import java.util.*;

public class PersonalBoard {

	private ResourcesList resourcesList;
	private BonusTile bonusTile;
	private Map<CardType, List<DevelopmentCard>> cards =
			new EnumMap<>(CardType.class);
	private Set<PersonalBoardObserver> observers = new HashSet<>();

	public PersonalBoard(int ordinal) {
		this.resourcesList =
				Commons.getInitialResources(ordinal);

		// create 4 empty List<DevelopmentCard>
		cards.put(CardType.TERRITORY, new ArrayList<>());
		cards.put(CardType.BUILDING, new ArrayList<>());
		cards.put(CardType.CHARACTER, new ArrayList<>());
		cards.put(CardType.VENTURE, new ArrayList<>());
	}

	public ResourcesList getResourcesList() {
		// return a copy
		return resourcesList.copy();
	}

	public void addResources(ResourcesList resources) throws IOException {
		resourcesList.addResources(resources);
		resourcesNotify();
	}

	public void payResources(ResourcesList resources)
			throws InsufficientResourcesException, IOException {

		if (resourcesList.checkEnoughResources(resources)) {
			resourcesList.subtractResources(resources);
		} else {
			throw new InsufficientResourcesException();
		}
		resourcesNotify();
	}


	/**
	 * Check if you can add a card of the specified type
	 *
	 * @param type
	 * 		the type of card to add
	 * @return null if you can't add a card of the specified type, a
	 * resourcesList required otherwise
	 */
	public ResourcesList canAddCard(CardType type) {
		ResourcesList required = new ResourcesList();

		int ownedCardsOfType = cards.get(type).size();
		if (!(ownedCardsOfType < 6)) {
			return null;
		}

		if (CardType.TERRITORY.equals(type)) {
			switch (ownedCardsOfType) {
				case 2:
					required.set(ResourceType.MILITARY_POINTS, 3);
					break;
				case 3:
					required.set(ResourceType.MILITARY_POINTS, 7);
					break;
				case 4:
					required.set(ResourceType.MILITARY_POINTS, 12);
					break;
				case 5:
					required.set(ResourceType.MILITARY_POINTS, 18);
					break;

				default:
					break;
			}
		}

		return required;
	}

	public void addCard(DevelopmentCard card) throws IOException {
		cards.get(card.getType()).add(card);
		newCardNotify(card);
	}

	public List<DevelopmentCard> getCards(CardType type) {
		// return a COPY of the list
		return new ArrayList<>(cards.get(type));
	}

	public void setBonusTile(BonusTileId id) {
		bonusTile = Commons.getBonusTile(id);
	}

	public BonusTile getBonusTile() {
		return bonusTile;
	}

	public void subscribe(PersonalBoardObserver o) throws IOException {
		observers.add(o);
		resourcesNotify();
	}

	private void newCardNotify(DevelopmentCard card) throws IOException {
		for (PersonalBoardObserver o : observers) {
			o.update(card);
		}
	}

	private void resourcesNotify() throws IOException {
		for (PersonalBoardObserver o : observers) {
			o.update(resourcesList.copy());
		}
	}

}
