package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.model.MemberColor;
import it.polimi.ingsw.GC_36.observers.ActionSpaceObserver;
import it.polimi.ingsw.GC_36.observers.BoardObserver;
import it.polimi.ingsw.GC_36.observers.FloorObserver;
import it.polimi.ingsw.GC_36.observers.PlayerObserver;

public interface ViewInterface
		extends FloorObserver,
		BoardObserver,
		ActionSpaceObserver,
		PlayerObserver {

	MemberColor chooseMemberColor();

	int chooseActionSpaceId();

	int choosePrivilege(int n);

	int selectNumberOfWoods();

	int selectNumberOfStones();

	int selectNumberOfServants();

	int selectNumberOfCoins();

	int selectNumberOfVictoryPoints();

	int selectNumberOfMilitaryPoints();

	int selectNumberOfFaithPoints();


}
