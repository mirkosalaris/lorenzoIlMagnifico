package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.model.ActionSpaceIds;
import it.polimi.ingsw.GC_36.model.MemberColor;
import it.polimi.ingsw.GC_36.model.ResourcesList;
import it.polimi.ingsw.GC_36.server.Participant;

import java.util.HashMap;
import java.util.Set;

public interface ViewInterface extends Participant {

	MemberColor chooseMemberColor();

	int chooseActionSpaceId();

	int choosePrivilege(int n);

	int chooseExtraActioneSpaceId(Set<ActionSpaceIds> actionSpaceIds,
	                              int actionValue);

	int chooseConvertingMethod(
			HashMap<Integer, ResourcesList> fromResourcesListOptions,
			HashMap<Integer, ResourcesList> toResorcesListOptions);

	int selectNumberOfWoods();

	int selectNumberOfStones();

	int selectNumberOfServants();

	int selectNumberOfCoins();

	int selectNumberOfVictoryPoints();

	int selectNumberOfMilitaryPoints();

	int selectNumberOfFaithPoints();

	void start();
}
