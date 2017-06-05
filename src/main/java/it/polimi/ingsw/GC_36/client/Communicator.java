package it.polimi.ingsw.GC_36.client;

import java.io.IOException;
import java.rmi.NotBoundException;

public interface Communicator {

	void connect() throws IOException, NotBoundException, InterruptedException;

	void start();
}
