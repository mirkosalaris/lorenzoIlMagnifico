package it.polimi.ingsw.GC_36.model.effects.permanentEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.view.ViewInterface;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;

import java.io.IOException;
import java.rmi.RemoteException;

public class ResourceListBasedOnOwnedCards extends PermanentEffect {
	private ResourcesList resourcesList;
	private CardType cardType;
	private int requiredActionValue;

	//effetto permanente delle BUILDING cards quindi legato alla produzione
	//da al player una resourcesList per ogni carta di un particolare tipo
	// posseduta

	/**
	 * @param resourcesList
	 * 		resourcesList to give to player for each card
	 * @param cardType
	 * 		the card type to count
	 * @param requiredActionValue
	 * 		required action value, int, to execute this effect
	 */
	public ResourceListBasedOnOwnedCards(ResourcesList resourcesList,
	                                     CardType cardType,
	                                     int requiredActionValue) {
		this.resourcesList = resourcesList;
		this.cardType = cardType;
		this.requiredActionValue = requiredActionValue;
	}

	@Override
	public void applyEffect(Action action, Player player)
			throws EffectApplyingException {

		if (isDoable(requiredActionValue, action)) {
			//count the number of owned cards
			//give to player resourcesList x number of cards
			PersonalBoard personalBoard = player.getPersonalBoard();

			int numberOfCards;
			numberOfCards = personalBoard.getCards(cardType).size();
			try {
				for (int i = 0; i < numberOfCards; i++) {
					personalBoard.addResources(resourcesList);
				}
			} catch (IOException e) {
				throw new EffectApplyingException(e);
			}
		}
	}

	@Override
	public void chooseOption(ViewInterface view,
	                         ActionInterface action, User user)
			throws RemoteException {
		// no need to do anything
	}

	@Override
	public boolean check(Action action, ResourcesList playerResources) {
		return true;
	}

	@Override
	public String toString() {
		return "ResourceListBasedOnOwnedCardsI{" +
				"resourcesList=" + resourcesList +
				", cardType=" + cardType +
				", requiredActionValue=" + requiredActionValue +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ResourceListBasedOnOwnedCards that = (ResourceListBasedOnOwnedCards) o;

		if (requiredActionValue != that.requiredActionValue) return false;
		if (resourcesList != null ? !resourcesList.equals(
				that.resourcesList) : that.resourcesList != null) return false;
		return cardType == that.cardType;
	}

	@Override
	public int hashCode() {
		int result = resourcesList != null ? resourcesList.hashCode() : 0;
		result = 31 * result + (cardType != null ? cardType.hashCode() : 0);
		result = 31 * result + requiredActionValue;
		return result;
	}
}
