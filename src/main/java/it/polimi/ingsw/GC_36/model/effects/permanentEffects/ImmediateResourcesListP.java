package it.polimi.ingsw.GC_36.model.effects.permanentEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.view.ViewInterface;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * PermanentEffect associated with buildings card, that gives to player some
 * resources for each card of a particular type
 */
public class ImmediateResourcesListP extends PermanentEffect {
	private CardType associatedCardType;
	private ResourcesList resourcesList;
	private int requiredActionValue;

	public ImmediateResourcesListP(ResourcesList resourcesList,
	                               int requiredActionValue, CardType type) {
		this.resourcesList = resourcesList;
		this.requiredActionValue = requiredActionValue;
		this.associatedCardType = type;
	}

	@Override
	public void applyEffect(Action action, Player player)
			throws EffectApplyingException {

		//if building take choice (or null)from productionChoice
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
	                         ActionInterface action, User user)
			throws RemoteException {
		// when the cardType is BUILDING we have to add a null production
		// choice
		// into action to avoid broking the sequence of choices of building's
		// card

		if (associatedCardType.equals(CardType.BUILDING)) {
			action.addProductionChoice(null);
		}
	}

	@Override
	public boolean check(Action action, ResourcesList playerResources) {
		return true;
	}


}
