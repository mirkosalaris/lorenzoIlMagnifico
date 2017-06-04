package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.model.Player;
import it.polimi.ingsw.GC_36.observers.ModelObserver;

public interface User extends ModelObserver {
	void setPlayer(Player player);

	void fatalError(String s);
}
