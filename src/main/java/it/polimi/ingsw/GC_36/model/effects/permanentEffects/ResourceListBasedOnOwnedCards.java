package it.polimi.ingsw.GC_36.model.effects.permanentEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;

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
	public void applyEffect(Action action) {
		//prende una scelta nulla da productionChoise
		action.getProductionChoise();
		if (isDoable(requiredActionValue, action)) {
			//TODO:check
			//conta le carte di quel tipo possedute
			//dai al player resourceList x numero di carte
			int numberOfCards;
			Player player = Game.getInstance().getCurrentPeriod()
					.getCurrentRound().getCurrentPlayer();
			numberOfCards = player.getPersonalBoard().getMap().get(cardType)
					.size();
			for (int i = 0; i < numberOfCards; i++) {
				player.getPersonalBoard().addResources(
						resourcesList);
			}
		}
	}

	@Override
	public void chooseOption(ViewInterface view, Action action, User user) {
		//non fa niente
		// deve aggiungere un elemento nullo in action.productionChoise
		action.addProductionChoise(null);

	}


}
