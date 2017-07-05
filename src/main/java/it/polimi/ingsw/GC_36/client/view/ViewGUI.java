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
	private GuiController ctrl;

	@Override
	public MemberColor chooseMemberColor() {
		return null;
	}

	@Override
	public int setActionValueIncrement() {
		return 0;
	}

	@Override
	public int chooseActionSpaceId(Set<ActionSpaceIds> actionSpaceIds) {
		return 0;
	}

	@Override
	public int choosePrivilege(int n) {
		return 0;
	}


	@Override
	public int chooseConvertingMethod(
			Map<Integer, Pair<ResourcesList, ResourcesList>> options) {
		return 0;
	}

	@Override
	public int chooseCardPaymentOptions(DevelopmentCard card) {
		return 0;
	}


	@Override
	public void show(String message) {

	}

	@Override
	public void start() {
		String args = "";
		launch(args);
	}

	@Override
	public Communicator chooseCommunicator(User user) {
		return null;
	}

	@Override
	public GameMode chooseMode() {
		return GameMode.STANDARD;
	}

	@Override
	public void fatalError(String s) {

	}

	@Override
	public void play(ActionInterface action) {

	}

	@Override
	public void exit(String message) {

	}

	@Override
	public void setIdentifier(PlayerIdentifier identifier) {

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
	public void terminatedRound() {

	}

	@Override
	public void update(BoardState currentState) {

	}

	@Override
	public void update(DieColor dieColor, int value) {

	}

	@Override
	public void update(PlayerState newState) {

	}

	@Override
	public void update(ActionSpaceIds id, boolean free) {

	}

	@Override
	public void update(ActionSpaceIds id, PlayerColor playerColor) {

	}

	@Override
	public void update(GameState newState) {

	}

	@Override
	public void update(int periodNumber) {

	}

	@Override
	public void update(List<Pair<PlayerIdentifier, Integer>>
			                   winningOrderList) {

	}

	@Override
	public void update(RoundState newState) {

	}

	@Override
	public void update(PlayerIdentifier newPlayer) {

	}

	@Override
	public void update(int floorNumber, DevelopmentCard developmentCard) {

	}

	@Override
	public void update(DevelopmentCard card) {

	}

	@Override
	public void update(ResourcesList resourcesList) {

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("loading...");

		FXMLLoader loader;

		URL resource;
		resource = getClass().getClassLoader().getResource("board.fxml");

		loader = new FXMLLoader(resource);
		Parent root = loader.load();
		ctrl = loader.getController();

		primaryStage.setTitle("Lorenzo Il Magnifico");
		primaryStage.setScene(new Scene(root));

		primaryStage.setResizable(false);

		primaryStage.show();
	}
}
