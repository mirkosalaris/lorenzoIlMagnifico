package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.model.*;

public class ParticipantRMI implements Participant {
	private final User user;

	public ParticipantRMI(User user) {
		this.user = user;
	}

	@Override
	public void update(Round newRound) {

	}

	@Override
	public void update(BoardState currentState) {

	}

	@Override
	public void update(PlayerState newState) {

	}

	@Override
	public void update(ActionSpaceIds id, boolean free) {

	}

	@Override
	public void update(GameState newState) {

	}

	@Override
	public void update(Period newPeriod) {

	}

	@Override
	public void update(RoundState newState) {

	}

	@Override
	public void update(Player newPlayer) {

	}

	@Override
	public void fatalError(String s) {

	}

	@Override
	public void update(int floorNumber, Tower tower,
	                   DevelopmentCard developmentCard) {

	}
}
