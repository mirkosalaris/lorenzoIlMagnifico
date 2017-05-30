package it.polimi.ingsw.GC_36.model;

public class FamilyMember {
	private boolean Available;
	private PlayerColor color;
	private Die die;

	public FamilyMember(PlayerColor color, DieColor dieColor) {
		// TODO get the right die from board
		this.color = color;
		//this.die = die;
	}

	public int getValue() {
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

	public PlayerColor getColor() {
		return color;
	}

	public DieColor getDieColor() {
		return die.getDieColor();
	}
}

