package it.polimi.ingsw.GC_36.observers;

import it.polimi.ingsw.GC_36.model.ActionSpaceIds;

public interface ActionSpaceObserver {
	void update(ActionSpaceIds id, boolean free);
}
