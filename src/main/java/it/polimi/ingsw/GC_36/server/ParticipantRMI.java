package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.client.UserInterface;
import it.polimi.ingsw.GC_36.model.*;

import java.io.IOException;
import java.rmi.RemoteException;

public class ParticipantRMI implements Participant {
	private final UserInterface user;

	public ParticipantRMI(UserInterface user) {
		this.user = user;
	}

	@Override
	public void exit() throws RemoteException {
		user.exit();
	}

	@Override
	public void play(Action action) throws IOException,
			ClassNotFoundException {
		user.play(action);
		// TODO impl
	}

	@Override
	public void update() throws RemoteException {
		user.update();
	}

	@Override
	public void update(BoardState currentState) throws RemoteException {
		user.update(currentState);
	}

	@Override
	public void update(PlayerState newState) throws RemoteException {
		user.update(newState);
	}

	@Override
	public void update(ActionSpaceIds id, boolean free) throws
			RemoteException {
		user.update(id, free);
	}

	@Override
	public void update(GameState newState) throws RemoteException {
		user.update(newState);
	}

	@Override
	public void update(int periodNumber) throws RemoteException {
		user.update(periodNumber);
	}

	@Override
	public void update(RoundState newState) throws RemoteException {
		user.update(newState);
	}

	@Override
	public void update(PlayerIdentifier newPlayer) throws RemoteException {
		user.update(newPlayer);
	}

	@Override
	public void fatalError(String s) throws RemoteException {
		user.fatalError(s);
	}

	@Override
	public void update(int floorNumber, DevelopmentCard developmentCard) throws
			RemoteException {
		user.update(floorNumber, developmentCard);
	}
}
