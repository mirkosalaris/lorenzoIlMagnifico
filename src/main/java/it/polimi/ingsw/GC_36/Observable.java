package it.polimi.ingsw.GC_36;

public interface Observable {
	public void subscribe(Observer o);

	void changeNotify();
}
