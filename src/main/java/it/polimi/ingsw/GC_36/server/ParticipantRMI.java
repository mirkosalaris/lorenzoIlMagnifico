package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.client.UserInterface;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.utils.ExceptionLogger;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.io.IOException;
import java.util.List;

public class ParticipantRMI implements Participant {
	private final UserInterface user;
	private final Object lockOutOfTime = new Object();
	private boolean playing;

	public ParticipantRMI(UserInterface user) {
		this.user = user;
	}

	@Override
	public void fatalError(String s) throws IOException {
		user.fatalError(s);
	}

	@Override
	public synchronized void play(ActionInterface action) throws IOException,
			ClassNotFoundException {
		playing = true;
		user.play(action);
		playing = false;
		synchronized (lockOutOfTime) {
			lockOutOfTime.notifyAll();
		}
	}

	@Override
	public void exit(String message) throws IOException {
		if (!playing)
			user.exit(message);
	}

	@Override
	public void setIdentifier(PlayerIdentifier identifier) throws IOException {
		user.setIdentifier(identifier);
	}

	@Override
	public int chooseLeaderCard(List<LeaderCard> leaderCards)
			throws IOException, ClassNotFoundException {
		return user.chooseLeaderCard(leaderCards);
	}

	@Override
	public LeaderCard useCard(List<LeaderCard> cardsAvailable)
			throws IOException, ClassNotFoundException {
		return user.useCard(cardsAvailable);
	}

	@Override
	public int chooseBonusTile()
			throws IOException, ClassNotFoundException {
		return user.chooseBonusTile();
	}

	@Override
	public void outOfTime() throws IOException {

		final Board board = Game.getInstance().getBoard();
		Participant thisParticipant = this;
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// synchronized to avoid being called together with play()
					// because it'd cause problems with Scanner
					synchronized (lockOutOfTime) {
						lockOutOfTime.wait();
					}

					user.outOfTime();

					// wait for rejoin request
					user.askRejoin();

					// rejoin
					board.rejoin(thisParticipant);

				} catch (InterruptedException | IOException e) {
					ExceptionLogger.log(e);
				}
			}
		}).start();
	}

	@Override
	public void actionResult(boolean result) throws IOException {
		user.actionResult(result);
	}

	@Override
	public void terminatedRound() throws IOException {
		if (!playing)
			user.terminatedRound();
	}

	@Override
	public void update(BoardState currentState) throws IOException {
		if (!playing)
			user.update(currentState);
	}

	@Override
	public void update(DieColor dieColor, int value) throws IOException {
		if (!playing)
			user.update(dieColor, value);
	}

	@Override
	public void update(PlayerState newState) throws IOException {
		if (!playing)
			user.update(newState);
	}

	@Override
	public void update(ActionSpaceIds id, boolean free) throws
			IOException {
		if (!playing)
			user.update(id, free);
	}

	@Override
	public void update(ActionSpaceIds id, PlayerColor playerColor,
	                   MemberColor memberColor)
			throws IOException {
		if (!playing)
			user.update(id, playerColor, memberColor);
	}

	@Override
	public void update(GameState newState) throws IOException {
		if (!playing)
			user.update(newState);
	}

	@Override
	public void update(int periodNumber) throws IOException {
		if (!playing)
			user.update(periodNumber);
	}

	@Override
	public void update(List<Pair<PlayerIdentifier, Integer>> winningOrderList)
			throws IOException {
		if (!playing)
			user.update(winningOrderList);
	}

	@Override
	public void update(RoundState newState) throws IOException {
		if (!playing)
			user.update(newState);
	}

	@Override
	public void update(PlayerIdentifier newPlayer) throws IOException {
		if (!playing)
			user.update(newPlayer);
	}

	@Override
	public void update(CardType cardType, int floorNumber,
	                   DevelopmentCard developmentCard) throws
			IOException {
		if (!playing)
			user.update(cardType, floorNumber, developmentCard);
	}

	@Override
	public void update(DevelopmentCard card) throws IOException {
		if (!playing)
			user.update(card);
	}

	@Override
	public void update(ResourcesList resourcesList) throws IOException {
		if (!playing)
			user.update(resourcesList);
	}
}
