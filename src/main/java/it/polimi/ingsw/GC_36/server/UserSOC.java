package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.model.*;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

public class UserSOC implements User {
	private ObjectOutputStream objOut;
	private Player player;

	public UserSOC(Socket socket) throws IOException {

		objOut = new ObjectOutputStream(
				new BufferedOutputStream(socket.getOutputStream()));
	}


	@Override
	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public void fatalError(String s) {
		sendMessage("fatal_error", s, "Cannot send error to user");
	}

	@Override
	public void update(BoardState currentState) {
		sendMessage("updateBoardState", currentState,
				"cannot update Board State");
	}

	@Override
	public void update(PlayerState newState) {
		sendMessage("updatePlayerState", newState,
				"cannot update Player State");
	}

	@Override
	public void update(ActionSpaceIds id, boolean free) {
		List<Object> obj = new ArrayList<>();
		obj.add(id);
		obj.add(free);
		sendMessage("updateActionSpace", obj, "cannot update ActionSpace");
	}

	@Override
	public void update(int floorNumber, Tower tower,
	                   DevelopmentCard developmentCard) {
		List<Object> obj = new ArrayList<>();
		obj.add(floorNumber);
		obj.add(tower);
		obj.add(developmentCard);
		sendMessage("updateFloor", obj, "cannot update Floor");
	}

	@Override
	public void update(Round newRound) {
		sendMessage("updateNewRound", newRound, "cannot update new Round");
	}

	@Override
	public void update(GameState newState) {
		sendMessage("updateGameState", newState, "cannot update GameState");
	}

	@Override
	public void update(Period newPeriod) {
		sendMessage("updateNewPeriod", newPeriod, "cannot update new Period");
	}

	@Override
	public void update(RoundState newState) {
		sendMessage("updateRoundState", newState, "cannot update RoundState");
	}

	@Override
	public void update(Player newPlayer) {
		if (!newPlayer.equals(this.player)) {
			sendMessage("updateCurrentPlayer", newPlayer,
					"cannot update new Player");
		}
	}

	private void sendMessage(String type, Object obj, String error) {
		SimpleEntry<String, Object> entry;
		entry = new SimpleEntry<>(type, obj);
		try {
			objOut.writeObject(entry);
			objOut.flush();
		} catch (IOException e) {
			System.err.println(error);
			e.printStackTrace();
		}
	}
}
