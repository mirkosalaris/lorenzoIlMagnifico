package it.polimi.ingsw.GC_36.model.effects.permanentEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.view.ViewInterface;
import it.polimi.ingsw.GC_36.exception.EffectApplyingException;
import it.polimi.ingsw.GC_36.exception.NotCorrectlyCheckedException;
import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.ActionInterface;
import it.polimi.ingsw.GC_36.model.Player;
import it.polimi.ingsw.GC_36.model.ResourcesList;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;
import it.polimi.ingsw.GC_36.model.effects.immediateEffects
		.ImmediateCouncilPrivilegeI;

import java.io.IOException;

public class ResourceToPrivilege extends PermanentEffect {
	private int actionValue;
	private ResourcesList fromResourcesList;
	private int privileges;
	private ImmediateCouncilPrivilegeI immediateCouncilPrivilegeI;

	public ResourceToPrivilege(int actionValue,
	                           ResourcesList resourcesList, int privileges) {
		this.actionValue = actionValue;
		this.fromResourcesList = resourcesList;
		this.privileges = privileges;
		this.immediateCouncilPrivilegeI = new ImmediateCouncilPrivilegeI(
				privileges, false);
	}

	@Override
	public void applyEffect(Action action, Player player)
			throws EffectApplyingException, NotCorrectlyCheckedException {
		action.getProductionChoice();
		ResourcesList playerResourcesList = player.getPersonalBoard()
				.getResourcesList();
		if (isDoable(actionValue, action)) {
			if (playerResourcesList.checkEnoughResources(fromResourcesList)) {
				playerResourcesList.subtractResources(fromResourcesList);
				immediateCouncilPrivilegeI.applyEffect(action, player);
			}
		}
	}

	@Override
	public void chooseOption(ViewInterface view, ActionInterface action,
	                         User user)
			throws IOException, ClassNotFoundException {
		action.addProductionChoice(null);
		immediateCouncilPrivilegeI.chooseOptions(view, action, user);
	}

	@Override
	public boolean check(Action action, ResourcesList playerResources) {
		/*ResourcesList playerResourcesList=player.getPersonalBoard()
		.getResourcesList();
		ResourcesList fromResourcesList=this.matchMapPrivilege.get(0)
		.getFirst();
		if (isDoable(actionValue, action)){
			if (playerResourcesList.checkEnoughResources(fromResourcesList)){

			}
		}*/
		return false;
	}
}
