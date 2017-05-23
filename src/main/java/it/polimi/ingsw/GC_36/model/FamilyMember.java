package it.polimi.ingsw.GC_36.model;

public class FamilyMember {
	private boolean Available;
	private Die die;

	public FamilyMember(Die die) {
		this.die = die;
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

	public Color getColor() {
		return die.getColor();
	}
}

