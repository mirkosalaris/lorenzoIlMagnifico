package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.exception.NotCorrectlyCheckedException;

public class CouncilActionSpace extends ActionSpace {
	public CouncilActionSpace(ActionSpaceIds id, Board board) {
		super(id, board);
	}

	@Override
	public void occupy(FamilyMember member)
			throws NotCorrectlyCheckedException {
		super.occupy(member);
		Player player = Game.getInstance().getCurrentPeriod().getCurrentRound
				().getCurrentPlayer();
		Game.getInstance().getBoard().getTurnOrder().addPlayer(player);
	}
}
