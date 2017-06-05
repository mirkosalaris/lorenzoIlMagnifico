package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.server.UserIncubator;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CommunicatorRMI implements Communicator {
	User user;

	public CommunicatorRMI(User user) {
		this.user = user;
	}

	@Override
	public void connect()
			throws IOException, NotBoundException, InterruptedException {
		Registry reg = LocateRegistry.getRegistry(Commons.HOST,
				Commons.RMI_PORT);

		UserIncubator server = (UserIncubator) reg.lookup("lorenzoServer");
		server.addUser(user);
	}

	@Override
	public void start() {

	}
}
