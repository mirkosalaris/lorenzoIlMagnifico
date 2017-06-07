package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.model.Action;
import it.polimi.ingsw.GC_36.observers.ModelObserver;

import java.io.IOException;
import java.io.Serializable;

public interface Participant extends ModelObserver, Serializable {

	void fatalError(String s);

	void play(Action action) throws IOException, ClassNotFoundException;

	void exit();
}
