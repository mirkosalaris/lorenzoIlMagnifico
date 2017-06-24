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
		// this call is necessary because the production choices are stored in
		// sequence, and if we don't access them we broke the sequence

		action.getProductionChoice();
		if (isDoable(requiredActionValue, action)) {
			//TODO:check
			//conta le carte di quel tipo possedute
			//dai al player resourceList x numero di carte
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
		//non fa niente
		// deve aggiungere un elemento nullo in action.productionChoice
		action.addProductionChoice(null);

	}

	@Override
	public boolean check(Action action, ResourcesList playerResources) {
		return false;
	}


}
