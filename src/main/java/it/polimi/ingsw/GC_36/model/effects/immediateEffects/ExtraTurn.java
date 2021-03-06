package it.polimi.ingsw.GC_36.model.effects.immediateEffects;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.client.view.ViewInterface;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;

import java.io.IOException;
import java.util.Set;

public class ExtraTurn extends ImmediateEffect {
	private Set<ActionSpaceIds> actionSpaces;
	private int baseActionValue;

	public ExtraTurn(Set<ActionSpaceIds> actionSpaces, int baseActionValue) {
		this.actionSpaces = actionSpaces;
		this.baseActionValue = baseActionValue;
	}

	@Override
	public void applyEffect(Action action, Player player) {

	}

	@Override
	public void chooseOptions(ViewInterface view, ActionInterface action,
	                          User user)
			throws IOException, ClassNotFoundException {
		// TODO @antonino: change null to a real discount
		ExtraAction extraAction =
				new ExtraAction(actionSpaces, baseActionValue, null);
		user.play(extraAction);

		action.setExtraAction(extraAction);
	}

	@Override
	public String toString() {
		return "ExtraTurn{" +
				"actionSpaces=" + actionSpaces +
				", baseActionValue=" + baseActionValue +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ExtraTurn extraTurn = (ExtraTurn) o;

		if (baseActionValue != extraTurn.baseActionValue) return false;
		return actionSpaces != null ? actionSpaces.equals(
				extraTurn.actionSpaces) : extraTurn.actionSpaces == null;
	}

	@Override
	public int hashCode() {
		int result = actionSpaces != null ? actionSpaces.hashCode() : 0;
		result = 31 * result + baseActionValue;
		return result;
	}
}



