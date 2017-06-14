package it.polimi.ingsw.GC_36.observers;

import it.polimi.ingsw.GC_36.model.DevelopmentCard;
import it.polimi.ingsw.GC_36.model.ResourcesList;

import java.io.IOException;

public interface PersonalBoardObserver {

	void update(DevelopmentCard card) throws IOException;

	void update(ResourcesList resourcesList) throws IOException;
}
