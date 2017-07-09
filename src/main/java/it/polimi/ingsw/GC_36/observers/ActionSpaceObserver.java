package it.polimi.ingsw.GC_36.observers;

import it.polimi.ingsw.GC_36.model.ActionSpaceIds;
import it.polimi.ingsw.GC_36.model.MemberColor;
import it.polimi.ingsw.GC_36.model.PlayerColor;

import java.io.IOException;

public interface ActionSpaceObserver {
	void update(ActionSpaceIds id, boolean free)
			throws IOException;

	void update(ActionSpaceIds id, PlayerColor playerColor,
	            MemberColor memberColor) throws IOException;
}
