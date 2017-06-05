package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.model.MemberColor;
import it.polimi.ingsw.GC_36.server.Participant;

public interface ViewInterface extends Participant {

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
