package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Commons;

public class FamilyMember {
	private PlayerColor playerColor;
	private MemberColor memberColor;
	private Die die;
	private ActionSpaceIds location;

	public FamilyMember(PlayerColor playerColor, MemberColor memberColor)
			throws IllegalStateException {
		this.playerColor = playerColor;

		if (memberColor == MemberColor.UNCOLORED) {
			die = null;
		} else {
			DieColor dieColor = Commons.memberColorToDieColor(memberColor);
			die = Game.getInstance().getBoard().getDice().get(dieColor);
		}
	}

	public int getValue() {
		if (die == null)
			return 1;
		else
			return die.getValue();
	}

	public boolean isAvailable() {
		return location == null;
	}

	public void setAvailable() {
		location = null;
	}

	public void setLocation(ActionSpaceIds location) {
		this.location = location;
	}

	public PlayerColor getPlayerColor() {
		return playerColor;
	}

	public MemberColor getColor() {
		return memberColor;
	}

	public ActionSpaceIds getLocation() {
		return location;
	}
}

