package it.polimi.ingsw.GC_36.model.effects.permanentEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.view.ViewInterface;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.exception.InsufficientResourcesException;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * Permanent effect of Building cards, so this is for production
 */
public class ResourcesConverting extends PermanentEffect {
	private final HashMap<Integer, Pair<ResourcesList, ResourcesList>> options;
	private int requiredActionValue;


	/**
	 * @param requiredActionValue
	 * 		the required action value, int, to execute the effect
	 * @param options
	 * 		the map of options for converting resources
	 */
	public ResourcesConverting(int requiredActionValue,
	                           HashMap<Integer, Pair<ResourcesList,
			                           ResourcesList>> options) {
		this.options = options;
		this.requiredActionValue = requiredActionValue;
	}

	@Override
	public void applyEffect(Action action, Player player)
			throws EffectApplyingException {
		int choice = action.getProductionChoice();
		ResourcesList payResourcesList = options.get(choice).getFirst();
		ResourcesList addResourcesList = options.get(choice).getSecond();

		if (isDoable(requiredActionValue, action)) {
			// pay the first list and add the second
			PersonalBoard personalBoard = player.getPersonalBoard();
			try {
				personalBoard.payResources(payResourcesList);
				personalBoard.addResources(addResourcesList);
			} catch (InsufficientResourcesException | IOException e) {
				throw new EffectApplyingException(e);
			}
		}
	}

	/**
	 * Show to user the options and store it
	 *
	 * @param view
	 * 		the view to invoke to ask for input
	 * @param action
	 * 		the action to modify
	 * @throws RemoteException
	 */
	@Override
	public void chooseOption(ViewInterface view,
	                         ActionInterface action, User user)
			throws RemoteException {
		Integer choice;
		do {
			// pass a copy
			choice = view.chooseConvertingMethod(new HashMap<>(options));
		} while (!checkChoice(choice));
		action.addProductionChoice(choice);
	}

	@Override
	public boolean check(Action action, ResourcesList playerResources) {
		int choice = action.getProductionChoice();
		ResourcesList payResourcesList = options.get(choice).getFirst();
		if (!playerResources.checkEnoughResources(payResourcesList)) {
			return false;
		}
		playerResources.subtractResources(payResourcesList);
		return true;
	}

	private boolean checkChoice(int choice) {
		return (choice >= 0 && choice < options.size());
	}

	@Override
	public String toString() {
		return "ResourcesConverting{" +
				"options=" + options +
				", requiredActionValue=" + requiredActionValue +
				'}';
	}
}
