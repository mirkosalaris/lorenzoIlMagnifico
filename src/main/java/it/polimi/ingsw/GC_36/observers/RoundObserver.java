package it.polimi.ingsw.GC_36.observers;

import it.polimi.ingsw.GC_36.model.Player;
import it.polimi.ingsw.GC_36.model.RoundState;

public interface RoundObserver {

	void update(RoundState newState);

	void update(Player newPlayer);

}
