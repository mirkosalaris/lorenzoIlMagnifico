package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.model.MemberColor;

public interface ViewInterface {

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

	void start();
}
