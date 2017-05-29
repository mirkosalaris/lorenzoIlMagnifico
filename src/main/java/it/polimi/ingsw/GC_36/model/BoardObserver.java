package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.observers.BoardState;

public interface BoardObserver {

	void update(BoardState currentState);
}
