package it.polimi.ingsw.GC_36.observers;

import it.polimi.ingsw.GC_36.model.GameState;

public interface GameObserver {

	void update(GameState newState);

	void update(int periodNumber);

}
