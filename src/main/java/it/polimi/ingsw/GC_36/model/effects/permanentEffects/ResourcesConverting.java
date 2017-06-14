package it.polimi.ingsw.GC_36.model.effects.permanentEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.ViewInterface;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.exception.InsufficientResourcesException;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;

public class ResourcesConverting extends PermanentEffect {
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
	public void applyEffect(Action action)
			throws EffectApplyingException {
		int choice = action.getProductionChoice();
		fromResourcesList = fromResourcesListOptions.get(choice);
		toResourcesList = toResourcesListOptions.get(choice);

		if (isDoable(requiredActionValue, action)) {
			// toglie a player la prima lista e aggiunge la seconda
			PersonalBoard personalBoard = Game.getInstance().getCurrentPeriod
					().getCurrentRound().getCurrentPlayer().getPersonalBoard();
			try {
				personalBoard.payResources(this.fromResourcesList);
				personalBoard.addResources(this.toResourcesList);
			} catch (InsufficientResourcesException | IOException e) {
				throw new EffectApplyingException(e);
			}

		}
	}

	@Override
	public void chooseOption(ViewInterface view,
	                         ActionInterface actionInterface, User user)
			throws RemoteException {
		//elenca al player le varie opzioni
		//memorizza l'intero associato alla scelta
		Integer choice;
		choice = view.chooseConvertingMethod(fromResourcesListOptions,
				toResourcesListOptions);
		//mette in action.productionChoice la scelta
		//TODO:check sulla scelta
		actionInterface.addProductionChoice(choice);


	}


}
