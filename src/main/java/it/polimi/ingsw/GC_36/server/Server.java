package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.model.Game;
import it.polimi.ingsw.GC_36.model.Player;
import it.polimi.ingsw.GC_36.model.PlayerColor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Server {

	private Server() {}

	public static void main(String[] args) throws IOException {
		Server server = new Server();

		ServerSocket ss;
		Socket[] sockets = new Socket[Commons.MAX_PLAYERS];

		ss = new ServerSocket(Commons.PORT);
		System.out.println("ServerSocket waiting for connections...");

		// add a while(true) and thread management
		boolean full = false;
		int i = 0;
		// TODO add a "timer" to enter a match with less than MAX_PLAYERS
		while (!full) {
			sockets[i] = ss.accept();
			System.out.println("Client accepted " + sockets[i]);
			i++;
			if (i == Commons.MAX_PLAYERS) {
				full = true;
			}
		}

		server.startMatch(sockets);
	}

	private void startMatch(Socket[] sockets) {

		List<UserSOC> users = new ArrayList<>();

		for (Socket s : sockets) {
			try {
				users.add(new UserSOC(s));
			} catch (IOException e) {
				System.out.println("Cannot connect to a user");
				e.printStackTrace();
				try {
					s.close();
				} catch (IOException e1) {
					System.out.println("Cannot close socket");
					e1.printStackTrace();
				}
			}
		}

		if (users.size() < Commons.MIN_PLAYERS) {
			for (UserSOC u : users) {
				u.fatalError(
						"Some players have abandoned the match. Too few " +
								"remaining to play");
			}
		}

		Game game = new Game();

		Map<PlayerColor, Player> players = new EnumMap<>(PlayerColor.class);
		for (int i = 0; i < users.size(); i++) {
			UserSOC u = users.get(i);
			PlayerColor color = PlayerColor.values()[i];
			Player p = new Player(color);
			players.put(color, p);
			u.setPlayer(p);
		}

		game.setPlayers(players);

		for (UserSOC u : users) {
			game.subscribe(u);
		}

		game.start();
	}


}
