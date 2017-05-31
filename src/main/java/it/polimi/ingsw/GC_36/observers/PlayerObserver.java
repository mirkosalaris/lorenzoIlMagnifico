package it.polimi.ingsw.GC_36.observers;

import it.polimi.ingsw.GC_36.model.PlayerState;

public interface PlayerObserver {

	void update(PlayerState newState);

}
