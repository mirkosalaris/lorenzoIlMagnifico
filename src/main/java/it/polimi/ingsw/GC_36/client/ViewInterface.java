package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.model.ActionSpace;
import it.polimi.ingsw.GC_36.model.FamilyMember;
import it.polimi.ingsw.GC_36.observers.ActionSpaceObserver;
import it.polimi.ingsw.GC_36.observers.BoardObserver;
import it.polimi.ingsw.GC_36.observers.FloorObserver;
import it.polimi.ingsw.GC_36.observers.PlayerObserver;

public interface ViewInterface
		extends FloorObserver,
		BoardObserver,
		ActionSpaceObserver,
		PlayerObserver {

	FamilyMember chooseFamilyMember();

	ActionSpace chooseActionSpace();
}
