package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.observers.ModelObserver;

public interface Participant extends ModelObserver {

	void fatalError(String s);
}
