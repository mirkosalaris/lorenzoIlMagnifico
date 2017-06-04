package it.polimi.ingsw.GC_36.client;

import java.io.IOException;

public interface Communicator {

	void connect() throws IOException;

	void start();
}
