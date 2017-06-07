package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Commons;

import java.io.Serializable;

public class FamilyMember {
	private boolean Available;
	private PlayerColor playerColor;
	private MemberColor memberColor;
	private Die die;

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
		return Available;
	}

	public void setAvailable() {
		this.Available = true;
	}

	public void setUsed() {
		this.Available = false;
	}

	public PlayerColor getPlayerColor() {
		return playerColor;
	}

	public DieColor getDieColor() {
		return die.getDieColor();
	}
}

