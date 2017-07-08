package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.model.ActionInterface;
import it.polimi.ingsw.GC_36.model.LeaderCard;
import it.polimi.ingsw.GC_36.model.PlayerIdentifier;
import it.polimi.ingsw.GC_36.observers.ModelObserver;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Participant extends ModelObserver, Remote {

	void fatalError(String s) throws IOException;

	void play(ActionInterface action)
			throws RemoteException, IOException, ClassNotFoundException;

	void exit(String message) throws IOException;

	void setIdentifier(PlayerIdentifier identifier) throws IOException;

	int chooseLeaderCard(List<LeaderCard> leaderCards)
			throws IOException, ClassNotFoundException;

	LeaderCard useCard(List<LeaderCard> cardsAvailable)
			throws IOException, ClassNotFoundException;

	int chooseBonusTile() throws IOException, ClassNotFoundException;

	void outOfTime() throws IOException;

	void actionResult(boolean result) throws IOException;
}
