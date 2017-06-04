package it.polimi.ingsw.GC_36.client;

import java.io.IOException;

public class CommunicatorRMI implements Communicator {
	User user;

	public CommunicatorRMI(User user) {
		this.user = user;
	}

	@Override
	public void connect() throws IOException {
		// TODO impl
		// Registry reg = LocateRegistry.getRegistry();
	}

	@Override
	public void start() {

	}
}
