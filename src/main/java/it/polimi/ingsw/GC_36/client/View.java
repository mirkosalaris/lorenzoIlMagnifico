package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.model.*;

public class View implements ViewInterface {

	@Override
	public void update(int floorNumber, Tower tower,
	                   DevelopmentCard developmentCard) {
		updateFloor(floorNumber, tower, developmentCard);
	}

	@Override
	public void update(BoardState currentState) {
		updateBoardState(currentState);
	}

	@Override
	public void update(ActionSpaceInterface actionSpace) {
		updateActionSpace(actionSpace);
	}

	@Override
	public void update(PlayerState newState) {
		// TODO impl
		if (newState == PlayerState.PLAYING) {

		}
	}

	@Override
	public FamilyMember chooseFamilyMember() {
		return null;
	}

	@Override
	public ActionSpace chooseActionSpace() {
		return null;
	}

	private void updateActionSpace(ActionSpaceInterface actionSpace) {
		// TODO impl
	}

	private void updateFloor(int floorNumber, Tower tower,
	                         DevelopmentCard developmentCard) {
		// TODO impl
	}

	private void updateBoardState(BoardState currentState) {
		// TODO impl
	}
}
