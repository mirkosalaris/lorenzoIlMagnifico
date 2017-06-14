package it.polimi.ingsw.GC_36.model.effects.permanentEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;

import java.rmi.RemoteException;

public class ImmediateResourcesList extends PermanentEffect {
	//effetto delle territory/building cards che da al player una resourcesList
	//l'effetto viene attivato se il valore dell'azione e' sufficiente
	CardType associatedCardType;
	private ResourcesList resourcesList;
	private int requiredActionValue;

	public ImmediateResourcesList(ResourcesList resourcesList,
	                              int requiredActionValue, CardType type) {
		this.resourcesList = resourcesList;
		this.requiredActionValue = requiredActionValue;
		this.associatedCardType = type;
	}

	@Override
	public void applyEffect(Action action) {
		//TODO:implements
		//se building prende la scelta (nulla) da productionChoice
		if (associatedCardType == CardType.BUILDING)
			action.getProductionChoice();

		Player player = Game.getInstance().getCurrentPeriod().getCurrentRound()
				.getCurrentPlayer();
		if (isDoable(requiredActionValue, action)) {
			// add resources to player
			player.getPersonalBoard().addResources(resourcesList);
		}

	}

	@Override
	public void chooseOption(ViewInterface view,
	                         ActionInterface actionInterface, User user)
			throws RemoteException {
		//se di tipo building aggiunge una scelta nulla a productionChoice
		if (associatedCardType.equals(CardType.BUILDING))
			actionInterface.addProductionChoice(null);

	}


}
