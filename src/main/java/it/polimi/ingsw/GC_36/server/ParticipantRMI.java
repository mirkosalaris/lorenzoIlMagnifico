package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.client.UserInterface;
import it.polimi.ingsw.GC_36.model.*;

import java.io.IOException;

public class ParticipantRMI implements Participant {
	private final UserInterface user;

	public ParticipantRMI(UserInterface user) {
		this.user = user;
	}

	@Override
	public void fatalError(String s) throws IOException {
		user.fatalError(s);
	}

	@Override
	public void play(Action action) throws IOException,
			ClassNotFoundException {
		user.play(action);
		// TODO impl
	}

	@Override
	public void exit(String message) throws IOException {
		user.exit(message);
	}

	@Override
	public void setIdentifier(PlayerIdentifier identifier) throws IOException {
		user.setIdentifier(identifier);
	}

	@Override
	public void update() throws IOException {
		user.update();
	}

	@Override
	public void update(BoardState currentState) throws IOException {
		user.update(currentState);
	}

	@Override
	public void update(PlayerState newState) throws IOException {
		user.update(newState);
	}

	@Override
	public void update(ActionSpaceIds id, boolean free) throws
			IOException {
		user.update(id, free);
	}

	@Override
	public void update(GameState newState) throws IOException {
		user.update(newState);
	}

	@Override
	public void update(int periodNumber) throws IOException {
		user.update(periodNumber);
	}

	@Override
	public void update(RoundState newState) throws IOException {
		user.update(newState);
	}

	@Override
	public void update(PlayerIdentifier newPlayer) throws IOException {
		user.update(newPlayer);
	}

	@Override
	public void update(int floorNumber, DevelopmentCard developmentCard) throws
			IOException {
		user.update(floorNumber, developmentCard);
	}
}
