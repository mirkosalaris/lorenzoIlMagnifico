package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.model.ActionSpace;
import it.polimi.ingsw.GC_36.model.ActionSpaceIds;
import it.polimi.ingsw.GC_36.model.FamilyMember;

public class User {
	private ViewInterface view;

	public User(ViewInterface view) {
		this.view = view;
	}

	public void execute(Action action) {
		// this change action through setter. It's to be this way for safety:
		// there can't be a way to construct a new Action. The user has to use
		// the one passed

		chooseFamilyMember(action);
		chooseActionSpace(action);

	}

	private void chooseFamilyMember(Action action) {
		FamilyMember familyMember = view.chooseFamilyMember();
		action.setFamilyMember(familyMember);
	}

	private void chooseActionSpace(Action action) {
		ActionSpace actionSpace = view.chooseActionSpace();
		if (actionSpace.getId() == ActionSpaceIds.AS_COUNCIL) {
			// TODO impl
			// choose privilege
		}

	}

}
