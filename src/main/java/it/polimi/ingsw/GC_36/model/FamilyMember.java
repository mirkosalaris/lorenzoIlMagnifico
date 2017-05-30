package it.polimi.ingsw.GC_36.model;

public class FamilyMember {
	private boolean Available;
	private PlayerColor playerColor;
	private Die die;

	public FamilyMember(PlayerColor playerColor, DieColor dieColor) {
		this.playerColor = playerColor;
		die = Game.getInstance().getBoard().getDice().get(dieColor);
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

	public PlayerColor getPlayerColor() {
		return playerColor;
	}

	public DieColor getDieColor() {
		return die.getDieColor();
	}
}

