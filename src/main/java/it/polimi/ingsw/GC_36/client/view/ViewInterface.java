package it.polimi.ingsw.GC_36.client.view;

import it.polimi.ingsw.GC_36.client.Communicator;
import it.polimi.ingsw.GC_36.client.User;
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

	int chooseActionSpaceId(Set<ActionSpaceIds> actionSpaceIds);

	int choosePrivilege(int n);

	int chooseConvertingMethod(
			Map<Integer, Pair<ResourcesList, ResourcesList>> options);

	int chooseCardPaymentOptions(DevelopmentCard card);

	void show(String message);

	void start();

	Communicator chooseCommunicator(User user);
}
