package it.polimi.ingsw.GC_36.client.view;

import it.polimi.ingsw.GC_36.client.Communicator;
import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.utils.Pair;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ViewGUI extends Application implements ViewInterface {
	public static ViewGUI instance;
	private BoardController boardCtrl;
	private SetupController setupController;
	private Stage primaryStage;


	@Override
	public MemberColor chooseMemberColor() {
		return boardCtrl.chooseMemberColor();
	}

	@Override
	public int setActionValueIncrement() {
		System.out.println("select the increment");
		return boardCtrl.setActionValueIncrement();
	}

	@Override
	public int chooseActionSpaceId(Set<ActionSpaceIds> actionSpaceIds) {
		return boardCtrl.chooseActionSpaceId(actionSpaceIds);
	}

	@Override
	public int choosePrivilege(int n) {
		return boardCtrl.choosePrivilege(n);
	}


	@Override
	public int chooseConvertingMethod(
			Map<Integer, Pair<ResourcesList, ResourcesList>> options) {
		if (options.size() == 1)
			return 0;
		return boardCtrl.chooseConvertingMethod(options);
	}

	@Override
	public int chooseCardPaymentOptions(DevelopmentCard card) {
		if (card.getRequirements().size() == 1)
			return 0;
		return boardCtrl.chooseCardPaymentOptions(card);
	}


	@Override
	public void show(String message) {
		boardCtrl.show(message);
	}

	@Override
	public Communicator chooseCommunicator(User user) throws Exception {
		return setupController.chooseCommunicator(user);
	}

	@Override
	public GameMode chooseMode() {
		return GameMode.STANDARD;
	}

	@Override
	public void askToRejoin() {
		boardCtrl.askToRejoin();
	}

	@Override
	public void fatalError(String s) {

	}

	@Override
	public void play(ActionInterface action)
			throws IOException, ClassNotFoundException {
		boardCtrl.play(action);
	}

	@Override
	public void exit(String message) {
		//TODO

	}

	@Override
	public void setIdentifier(PlayerIdentifier identifier) throws IOException {
		boardCtrl.setIdentifier(identifier);

	}

	@Override
	public int chooseLeaderCard(List<LeaderCard> leaderCards)
			throws IOException, ClassNotFoundException {
		return 0;
	}

	@Override
	public LeaderCard useCard(List<LeaderCard> cardsAvailable)
			throws IOException, ClassNotFoundException {
		return null;
	}

	@Override
	public int chooseBonusTile() throws IOException, ClassNotFoundException {
		return 0;
	}

	@Override
	public void outOfTime() throws IOException {
		boardCtrl.outOfTime();
	}

	@Override
	public void actionResult(boolean result) throws IOException {
		boardCtrl.actionResult(result);
	}

	@Override
	public void terminatedRound() throws IOException {
		System.out.println("The round is terminated");
		boardCtrl.show("The round is terminated");
		boardCtrl.terminatedRound();


	}

	@Override
	public void update(BoardState currentState) throws IOException {
		boardCtrl.show(currentState.toString());
		boardCtrl.update(currentState);
	}

	@Override
	public void update(DieColor dieColor, int value) throws IOException {
		boardCtrl.update(dieColor, value);
	}

	@Override
	public void update(PlayerState newState) {
		boardCtrl.show(newState.toString());
	}

	@Override
	public void update(ActionSpaceIds id, boolean free) throws IOException {
		boardCtrl.update(id, free);
	}

	@Override
	public void update(ActionSpaceIds id, PlayerColor playerColor,
	                   MemberColor memberColor)
			throws IOException {
		boardCtrl.update(id, playerColor, memberColor);
	}

	@Override
	public void update(GameState newState) {

	}

	@Override
	public void update(int periodNumber) {
		boardCtrl.show("A new period has started");
		boardCtrl.setCurrentPeriod(periodNumber + "");
		boardCtrl.showCurrent();
	}

	@Override
	public void update(List<Pair<PlayerIdentifier, Integer>>
			                   winningOrderList) {

	}

	@Override
	public void update(RoundState newState) {
		System.out.println("new Round State: " + newState);
		boardCtrl.show("New Round state" + newState.toString());
	}

	@Override
	public void update(PlayerIdentifier newPlayer) {
		System.out.println("Current player: " + newPlayer.get());
		boardCtrl.show("New Player");
		boardCtrl.setCurrentPlayer(newPlayer.toString());
		boardCtrl.showCurrent();
	}

	@Override
	public void update(CardType cardType, int floorNumber,
	                   DevelopmentCard developmentCard) throws IOException {
		boardCtrl.update(cardType, floorNumber, developmentCard);

	}

	@Override
	public void update(DevelopmentCard card) throws IOException {
		boardCtrl.update(card);

	}

	@Override
	public void update(ResourcesList resourcesList) throws IOException {
		System.out.println(resourcesList);
		boardCtrl.update(resourcesList);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("loading...");
		this.primaryStage = primaryStage;
		FXMLLoader loader;
		URL resource;
		resource = getClass().getClassLoader().getResource("setup.fxml");
		loader = new FXMLLoader(resource);
		Parent root = loader.load();
		setupController = loader.getController();

		primaryStage.setTitle("Lorenzo Il Magnifico");
		primaryStage.setScene(new Scene(root));

		primaryStage.setResizable(false);

		primaryStage.show();

		instance = this;

		resource = getClass().getClassLoader().getResource("board.fxml");
		loader = new FXMLLoader(resource);
		Parent boardRoot = loader.load();
		boardCtrl = loader.getController();
		Scene boardScene = new Scene(boardRoot);

		setupController.initialize(primaryStage, boardScene, boardCtrl);
	}

}
