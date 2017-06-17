package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.model.ActionSpaceIds;
import it.polimi.ingsw.GC_36.model.DevelopmentCard;
import it.polimi.ingsw.GC_36.model.MemberColor;
import it.polimi.ingsw.GC_36.model.ResourcesList;
import it.polimi.ingsw.GC_36.server.Participant;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.util.Map;
import java.util.Set;

public interface ViewInterface extends Participant {

	MemberColor chooseMemberColor();

	int setActionValueIncrement();

	int chooseActionSpaceId();

	int choosePrivilege(int n);

	int chooseExtraActionSpaceId(Set<ActionSpaceIds> actionSpaceIds,
	                             int actionValue);

	int chooseConvertingMethod(
			Map<Integer, Pair<ResourcesList, ResourcesList>> options);

	int chooseCardPaymentOptions(DevelopmentCard card);

	int selectNumberOfWoods();

	int selectNumberOfStones();

	int selectNumberOfServants();

	int selectNumberOfCoins();

	int selectNumberOfVictoryPoints();

	int selectNumberOfMilitaryPoints();

	int selectNumberOfFaithPoints();

	void show(String message);
}
