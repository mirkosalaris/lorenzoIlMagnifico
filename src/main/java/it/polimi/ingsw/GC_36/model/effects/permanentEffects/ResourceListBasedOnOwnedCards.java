package it.polimi.ingsw.GC_36.model.effects.permanentEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.ViewInterface;
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

	public ResourceListBasedOnOwnedCards(ResourcesList resourcesList,
	                                     CardType cardType,
	                                     int requiredActionValue) {
		this.resourcesList = resourcesList;
		//tipo di carte da contare
		this.cardType = cardType;
		this.requiredActionValue = requiredActionValue;
	}

	@Override
	public void applyEffect(Action action)
			throws EffectApplyingException {
		//prende una scelta nulla da productionChoice
		action.getProductionChoice();
		if (isDoable(requiredActionValue, action)) {
			//TODO:check
			//conta le carte di quel tipo possedute
			//dai al player resourceList x numero di carte
			PersonalBoard personalBoard = Game.getInstance().getCurrentPeriod()
					.getCurrentRound().getCurrentPlayer().getPersonalBoard();

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
	                         ActionInterface actionInterface, User user)
			throws RemoteException {
		//non fa niente
		// deve aggiungere un elemento nullo in action.productionChoice
		actionInterface.addProductionChoice(null);

	}


}
