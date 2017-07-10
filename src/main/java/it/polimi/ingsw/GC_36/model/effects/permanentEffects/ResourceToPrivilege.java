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
		immediateCouncilPrivilegeI.chooseOptions(view, action, user);
	}

	@Override
	public boolean check(Action action, ResourcesList playerResources) {

		if (isDoable(actionValue, action)) {
			if (playerResources.checkEnoughResources(fromResourcesList)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "ResourceToPrivilege{" +
				"actionValue=" + actionValue +
				", fromResourcesList=" + fromResourcesList +
				", privileges=" + privileges +
				", immediateCouncilPrivilegeI=" + immediateCouncilPrivilegeI +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ResourceToPrivilege that = (ResourceToPrivilege) o;

		if (actionValue != that.actionValue) return false;
		if (privileges != that.privileges) return false;
		if (fromResourcesList != null ? !fromResourcesList.equals(
				that.fromResourcesList) : that.fromResourcesList != null)
			return false;
		return immediateCouncilPrivilegeI != null ? immediateCouncilPrivilegeI
				.equals(
						that.immediateCouncilPrivilegeI) : that
				.immediateCouncilPrivilegeI == null;
	}

	@Override
	public int hashCode() {
		int result = actionValue;
		result = 31 * result + (fromResourcesList != null ? fromResourcesList
				.hashCode() : 0);
		result = 31 * result + privileges;
		result = 31 * result + (immediateCouncilPrivilegeI != null ?
				immediateCouncilPrivilegeI.hashCode() : 0);
		return result;
	}
}
