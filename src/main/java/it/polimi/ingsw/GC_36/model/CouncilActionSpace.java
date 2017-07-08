package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.exception.NotCorrectlyCheckedException;

import java.io.IOException;

public class CouncilActionSpace extends ActionSpace {
	public CouncilActionSpace(ActionSpaceIds id, Board board) {
		super(id, board);
	}

	@Override
	public void occupy(Player player, FamilyMember member)
			throws NotCorrectlyCheckedException, IOException {
		super.occupy(player, member);
		Game.getInstance().getBoard().getTurnOrder().addPlayer(player);
	}
}
