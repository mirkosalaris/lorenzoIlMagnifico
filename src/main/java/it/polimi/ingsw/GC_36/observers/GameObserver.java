package it.polimi.ingsw.GC_36.observers;

import it.polimi.ingsw.GC_36.model.GameState;
import it.polimi.ingsw.GC_36.model.Period;

public interface GameObserver {

	void update(GameState newState);

	void update(Period newPeriod);

}
