package it.polimi.ingsw.GC_36.model.effects.permanentEffects;

import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * PermanentEffect associated with buildings card, that gives to player some
 * resources for each card of a particular type
 */
public class ImmediateResourcesList extends PermanentEffect {
	private CardType associatedCardType;
	private ResourcesList resourcesList;
	private int requiredActionValue;

	public ImmediateResourcesList(ResourcesList resourcesList,
	                              int requiredActionValue, CardType type) {
		this.resourcesList = resourcesList;
		this.requiredActionValue = requiredActionValue;
		this.associatedCardType = type;
	}

	@Override
	public void applyEffect(Action action, Player player)
			throws EffectApplyingException {
		//TODO:implements
		//se building prende la scelta (nulla) da productionChoice
		if (associatedCardType == CardType.BUILDING)
			action.getProductionChoice();

		if (isDoable(requiredActionValue, action)) {
			// add resources to player
			try {
				player.getPersonalBoard().addResources(resourcesList);
			} catch (IOException e) {
				throw new EffectApplyingException(e);
			}
		}

	}

	@Override
	public void chooseOption(ViewInterface view,
	                         ActionInterface action)
			throws RemoteException {
		// when the cardType is BUILDING we have to add a null production
		// choice
		// into action to avoid broking the sequence of choices of building's
		// card

		if (associatedCardType.equals(CardType.BUILDING)) {
			action.addProductionChoice(null);
		}
	}


}
