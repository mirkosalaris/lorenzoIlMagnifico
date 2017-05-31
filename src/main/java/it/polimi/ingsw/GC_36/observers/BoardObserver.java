package it.polimi.ingsw.GC_36.observers;

import it.polimi.ingsw.GC_36.model.BoardState;

public interface BoardObserver {

	void update(BoardState currentState);
}
