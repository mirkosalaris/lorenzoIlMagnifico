package it.polimi.ingsw.GC_36.model;

public abstract class Resource {
	@Override
	public int hashCode() {
		return this.getClass().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;

		return this.getClass().equals(o.getClass());
	}

	public static class Wood extends Resource {
	}

	public static class Stone extends Resource {
	}

	public static class Servant extends Resource {
	}

	public static class Coins extends Resource {
	}

	public static class VictoryPoints extends Resource {
	}

	public static class FaithPoints extends Resource {
	}

	public static class MilitaryPoints extends Resource {
	}

}
