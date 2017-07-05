package it.polimi.ingsw.GC_36.server;

import it.polimi.ingsw.GC_36.client.UserInterface;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.io.IOException;
import java.util.List;

public class ParticipantRMI implements Participant {
	private final UserInterface user;

	public ParticipantRMI(UserInterface user) {
		this.user = user;
	}

	@Override
	public void fatalError(String s) throws IOException {
		user.fatalError(s);
	}

	@Override
	public void play(ActionInterface action) throws IOException,
			ClassNotFoundException {
		user.play(action);
	}

	@Override
	public void exit(String message) throws IOException {
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
	public void terminatedRound() throws IOException {
		user.terminatedRound();
	}

	@Override
	public void update(BoardState currentState) throws IOException {
		user.update(currentState);
	}

	@Override
	public void update(DieColor dieColor, int value) throws IOException {
		user.update(dieColor, value);
	}

	@Override
	public void update(PlayerState newState) throws IOException {
		user.update(newState);
	}

	@Override
	public void update(ActionSpaceIds id, boolean free) throws
			IOException {
		user.update(id, free);
	}

	@Override
	public void update(ActionSpaceIds id, PlayerColor playerColor)
			throws IOException {
		user.update(id, playerColor);
	}

	@Override
	public void update(GameState newState) throws IOException {
		user.update(newState);
	}

	@Override
	public void update(int periodNumber) throws IOException {
		user.update(periodNumber);
	}

	@Override
	public void update(List<Pair<PlayerIdentifier, Integer>> winningOrderList)
			throws IOException {
		user.update(winningOrderList);
	}

	@Override
	public void update(RoundState newState) throws IOException {
		user.update(newState);
	}

	@Override
	public void update(PlayerIdentifier newPlayer) throws IOException {
		user.update(newPlayer);
	}

	@Override
	public void update(CardType cardType, int floorNumber,
	                   DevelopmentCard developmentCard) throws
			IOException {
		user.update(cardType, floorNumber, developmentCard);
	}

	@Override
	public void update(DevelopmentCard card) throws IOException {
		user.update(card);
	}

	@Override
	public void update(ResourcesList resourcesList) throws IOException {
		user.update(resourcesList);
	}
}
