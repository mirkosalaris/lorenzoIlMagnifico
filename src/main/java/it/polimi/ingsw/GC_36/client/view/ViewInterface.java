package it.polimi.ingsw.GC_36.client.view;

import it.polimi.ingsw.GC_36.client.Communicator;
import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.model.*;
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

	Communicator chooseCommunicator(User user);

	GameMode chooseMode();

	void askToRejoin();
}
