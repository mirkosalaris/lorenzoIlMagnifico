package it.polimi.ingsw.GC_36.model.effects.permanentEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.Game;
import it.polimi.ingsw.GC_36.model.Player;
import it.polimi.ingsw.GC_36.model.ResourcesList;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;

import java.util.HashMap;

public class ResourcesConverting implements PermanentEffect {
	ResourcesList fromResourcesList, toResourcesList;
	HashMap<Integer, ResourcesList> fromResourcesListOptions;
	HashMap<Integer, ResourcesList> toResourcesListOptions;
	private int requiredActionValue;

	//effetto permanente delle building cards quindi legato alla produzione

	// il costruttore accetta due hashmap, la chiave rappresenta il numero
	// della scelta, il valore rappresenta rispettivamente la lista da pagare
	// e quella che
	// si ottiene
	public ResourcesConverting(int requiredActionValue,
	                           HashMap<Integer, ResourcesList>
			                           fromResourcesListOptions,
	                           HashMap<Integer, ResourcesList>
			                           toResourcesListOptions) {
		this.fromResourcesListOptions = fromResourcesListOptions;
		this.toResourcesListOptions = toResourcesListOptions;
		this.requiredActionValue = requiredActionValue;
	}

	@Override
	public void applyEffect(Action action) {
		//TODO:deve andare a controllare quale sia la scelta del client
		int choise = action.getProductionChoise();
		fromResourcesList = fromResourcesListOptions.get(choise);
		toResourcesList = toResourcesListOptions.get(choise);
		//if isDoable
		if (isDoable(requiredActionValue, action)) {
			// toglie a player la prima lista e aggiunge la seconda
			Game.getInstance().getCurrentPeriod().getCurrentRound()
					.getCurrentPlayer().getPersonalBoard().payResources(
					this.fromResourcesList);
			Game.getInstance().getCurrentPeriod().getCurrentRound()
					.getCurrentPlayer().getPersonalBoard().addResources(
					this.toResourcesList);
		}
	}

	@Override
	public void chooseOption(ViewInterface view, Action action, User user) {
		//elenca al player le varie opzioni
		//memorizza l'intero associato alla scelta
		Integer choice;
		choice = view.chooseConvertingMethod(fromResourcesListOptions,
				toResourcesListOptions);
		//mette in action.productionChoise la scelta
		//TODO:check sulla scelta
		action.addProductionChoise(choice);


	}

	@Override
	public boolean isDoable(int requiredActionValue, Action action) {
		int ActionValue;
		Player player = Game.getInstance().getCurrentPeriod().getCurrentRound()
				.getCurrentPlayer();
		ActionValue = action.getActionValue(player);
		if (ActionValue >= requiredActionValue)
			return true;
		else
			return false;
	}
}
