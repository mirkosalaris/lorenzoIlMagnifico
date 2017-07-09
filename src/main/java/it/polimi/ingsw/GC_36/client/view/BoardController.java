package it.polimi.ingsw.GC_36.client.view;

import it.polimi.ingsw.GC_36.client.Communicator;
import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.utils.ExceptionLogger;
import it.polimi.ingsw.GC_36.utils.Pair;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class BoardController implements ViewInterface {
	private Scene scene;
	private Stage primaryStage;
	private static final double MIN_FIT_HEIGHT = 500.0d;
	private static final double MAX_FIT_HEIGHT = 1500.0d;

	private final Object lockChosenPrivilege = new Object();
	private final Object lockChosenActionSpace = new Object();
	private final Object lockChosenMember = new Object();
	private final Object lockChooseOption = new Object();
	private final Object lockServantIncrement = new Object();
	private int chosenActionSpace;
	private MemberColor chosenMember;
	private CouncilPrivilege chosenPrivilege;
	private int cardPaymentOption;
	private String currentPeriod = "";
	private String currentPlayer = "";
	private AtomicInteger increment = new AtomicInteger(0);
	private AtomicBoolean incrementSelected = new AtomicBoolean(false);
	private AtomicBoolean actionSpaceSelected = new AtomicBoolean(false);

	@FXML
	private Label labelOrangeDie;
	@FXML
	private Label labelBlackDie;
	@FXML
	private Label labelWhiteDie;
	@FXML
	private Label label;
	@FXML
	private Label playerColor;
	@FXML
	private Label CURRENT;
	@FXML
	private AnchorPane servantsAnchor;
	@FXML
	private AnchorPane optionsAnchor;
	@FXML
	private Label servants;
	@FXML
	private Label privilege;
	@FXML
	private AnchorPane privilegeAnchor;
	private PlayerIdentifier identifier;

	@FXML
	private AnchorPane anchor;
	@FXML
	private Label labelWoodPoints;
	@FXML
	private Label labelServantPoints;
	@FXML
	private Label labelCoinsPoints;
	@FXML
	private Label labelStonePoints;
	@FXML
	private Label labelVictoryPoints;
	@FXML
	private Label labelFaithPoints;
	@FXML
	private Label labelMilitaryPoints;
	@FXML
	private Label labelWoods;

	@FXML
	private Button ORANGE;
	@FXML
	private Button WHITE;
	@FXML
	private Button BLACK;
	@FXML
	private Button UNCOLORED;

	@FXML
	private ImageView fm01;
	@FXML
	private ImageView fm02;
	@FXML
	private ImageView fm03;
	@FXML
	private ImageView fm04;
	@FXML
	private ImageView fm181;
	@FXML
	private ImageView fm182;
	@FXML
	private ImageView fm183;
	@FXML
	private ImageView fm201;
	@FXML
	private ImageView fm202;
	@FXML
	private ImageView fm203;

	private MemberColor memberColor;

	public BoardController() {
	}

	public void setCurrentPeriod(String currentPeriod) {
		this.currentPeriod = currentPeriod;
	}


	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	@Override
	public void update(PlayerState newState)
			throws IOException {
	}

	@Override
	public void update(DevelopmentCard card) throws IOException {
		String nameDevCard = card.getName();
		String firstLetterCardType = String.valueOf(
				card.getType().name().charAt(0));
		Image imgDevCard = new Image(
				"file:src/main/resources/images/cards/" + nameDevCard + "" +
						".png");
		ImageView personalImg = getImageView(
				"imageViewPersonal" + firstLetterCardType,
				6);
		personalImg.setImage(imgDevCard);
		personalImg.setVisible(true);
	}

	@Override
	public void update(ResourcesList resourcesList) throws IOException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				labelWoodPoints.setText(
						"" + resourcesList.get(ResourceType.WOOD).getValue());
				labelStonePoints.setText(
						"" + resourcesList.get(ResourceType.STONE).getValue());
				labelServantPoints.setText(
						"" + resourcesList.get(ResourceType.STONE).getValue());
				labelCoinsPoints.setText(
						"" + resourcesList.get(ResourceType.COINS).getValue());
				labelVictoryPoints.setText(
						"" + resourcesList.get(
								ResourceType.VICTORY_POINTS).getValue
								());
				labelFaithPoints.setText(
						"" + resourcesList.get(
								ResourceType.FAITH_POINTS).getValue());
				labelMilitaryPoints.setText(
						"" + resourcesList.get(
								ResourceType.MILITARY_POINTS).getValue());
			}
		});
	}

	@Override
	public void update(BoardState currentState)
			throws IOException {
		if (currentState.equals(BoardState.PREPARING)) {
			ORANGE.setVisible(true);
			WHITE.setVisible(true);
			BLACK.setVisible(true);
			UNCOLORED.setVisible(true);
		}
	}

	@Override
	public void update(DieColor dieColor, int value) throws IOException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				switch (dieColor) {
					case BLACK:
						labelBlackDie.setText("" + value);
						break;
					case WHITE:
						labelWhiteDie.setText("" + value);
						break;
					case ORANGE:
						labelOrangeDie.setText("" + value);
						break;
				}
			}
		});
	}

	@Override
	public void update(RoundState newState)
			throws IOException {
		//not shown to user
	}

	private void resetFm() {
		Image img = new Image("file:");
		for (int i = 1; i <= 24; i++) {
			if (i != 18 && i != 20) {
				Scene scene = anchor.getScene();
				ImageView fm = (ImageView) scene.lookup(
						"#fm" + i);
				fm.setImage(img);
				fm.setVisible(false);
			} else {
				fm01.setImage(img);
				fm01.setVisible(false);
				fm02.setImage(img);
				fm02.setVisible(false);
				fm03.setImage(img);
				fm03.setVisible(false);
				fm04.setImage(img);
				fm04.setVisible(false);
				fm181.setImage(img);
				fm181.setVisible(false);
				fm182.setImage(img);
				fm182.setVisible(false);
				fm183.setImage(img);
				fm183.setVisible(false);
				fm201.setImage(img);
				fm201.setVisible(false);
				fm202.setImage(img);
				fm202.setVisible(false);
				fm203.setImage(img);
				fm203.setVisible(false);
			}
		}

	}

	@Override
	public void update(PlayerIdentifier identifier)
			throws IOException {

	}

	@Override
	public void update(ActionSpaceIds id, boolean free)
			throws IOException {
		Scene scene = anchor.getScene();
		int actionSpaceId = id.value();
		Button actionSpace = (Button) scene.lookup(
				"#button" + actionSpaceId);
		if (!free) {
			actionSpace.setVisible(false);
		} else {
			actionSpace.setVisible(true);
		}
	}

	@Override
	public void update(ActionSpaceIds id, PlayerColor playerColor,
	                   MemberColor memberColor)
			throws IOException {
		Scene scene = anchor.getScene();
		String p = playerColor.name();
		p = p.substring(0, 1).toUpperCase() + p.substring(1,
				p.length()).toLowerCase();
		String mc = memberColor.name();
		mc = mc.substring(0, 1).toUpperCase() + mc.substring(1,
				mc.length()).toLowerCase();
		String name = "fm" + p + mc + ".png";
		int actionSpaceId = id.value();
		ImageView fm = null;
		Image img = new Image(
				"file:src/main/resources/images/familyMember/" + name);
		if (actionSpaceId == 0) {
			fm = getImageView("fm" + actionSpaceId, 4);
		} else if (actionSpaceId == 18 || actionSpaceId == 20) {
			fm = getImageView("fm" + actionSpaceId, 3);
		} else {
			fm = (ImageView) scene.lookup("#fm" + actionSpaceId);
		}
		fm.setImage(img);
		fm.setVisible(true);
	}

	@Override
	public void update(GameState newState) throws RemoteException,
			IOException {

	}

	@Override
	public void update(int periodNumber) throws RemoteException, IOException {

	}

	@Override
	public void update(List<Pair<PlayerIdentifier, Integer>> winningOrderList)
			throws IOException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				final Stage dialog = new Stage();
				dialog.initModality(Modality.APPLICATION_MODAL);
				dialog.initOwner(primaryStage);
				VBox dialogVbox = new VBox(20);

				dialogVbox.getChildren().add(new Text("The winner is "
						+ winningOrderList.get(0).getFirst() + " with "
						+ winningOrderList.get(0).getSecond() + " points"));
				winningOrderList.remove(0);
				Scene dialogScene = new Scene(dialogVbox, 300, 200);
				dialog.setScene(dialogScene);
				dialog.show();
				for (Pair<PlayerIdentifier, Integer> pair : winningOrderList) {


					Label label = new Label("Player " + pair.getFirst()
							+ " got " + pair.getSecond() + " victory points");
					dialogVbox.getChildren().add(label);
				}

			}
		});
	}

	@Override
	public void fatalError(String s) throws IOException {
		label.setText(s);

	}

	@Override
	public void play(ActionInterface action)
			throws RemoteException, IOException, ClassNotFoundException {
		setCurrentPlayer(identifier.toString());
		showCurrent();
	}

	@Override
	public void exit(String message) throws IOException {
		label.setText(message);

	}

	@Override
	public void setIdentifier(PlayerIdentifier identifier) throws IOException {
		this.identifier = identifier;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				playerColor.setText("Your color is: " + identifier.get());
			}
		});

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
		// TODO
		System.out.println("you took too much to play your action");
	}

	@Override
	public void actionResult(boolean result) throws IOException {
		MemberColor member = this.memberColor;
		// if last action was successful
		if (result) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Scene scene = anchor.getScene();
					Button mc = (Button) scene.lookup("#" + member.name());
					mc.setVisible(false);
				}
			});
		}
	}

	@Override
	public MemberColor chooseMemberColor() {
		System.out.println("select the family member");
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				label.setText("select the Family Member");
			}
		});
		MemberColor choice = (MemberColor) waitForChoice(lockChosenMember);
		this.memberColor = choice;
		System.out.println(choice.toString());
		return choice;

	}

	@Override
	public int setActionValueIncrement() {
		incrementSelected.set(false);
		int numberOfServants = 0;
		increment.set(0);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				servantsAnchor.setVisible(true);

			}
		});
		synchronized (lockServantIncrement) {
			try {
				while (incrementSelected.get() == false) {
					lockServantIncrement.wait();
				}
			} catch (InterruptedException e) {
				ExceptionLogger.log(e);
			}
		}
		numberOfServants = increment.get();
		System.out.println("number of servants: " + numberOfServants);


		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				servantsAnchor.setVisible(false);
				servants.setText("servants: 0");
			}
		});
		return numberOfServants;
	}

	@Override
	public int chooseActionSpaceId(Set<ActionSpaceIds>
			                               actionSpaceIds) {
		System.out.println("select action space");
		actionSpaceSelected.set(false);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				label.setText("select the Action Space");
			}
		});

		int choice = (int) waitForChoice(lockChosenActionSpace);

		System.out.println(choice);
		return choice;
	}

	@Override
	public int choosePrivilege(int n) {
		//TODO
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				privilegeAnchor.setVisible(true);
				privilege.setText(
						"choice number " + n + ": select the " +
								"privilege");
			}
		});
		CouncilPrivilege choice =
				(CouncilPrivilege) waitForChoice(lockChosenPrivilege);

		System.out.println(choice);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				privilegeAnchor.setVisible(false);
			}
		});

		return choice.ordinal();
	}

	private Object waitForChoice(Object lock) {
		Object choice;

		do {
			try {
				synchronized (lock) {
					lock.wait();
				}
			} catch (InterruptedException e) {
				ExceptionLogger.log(e);
			}
			if (lockChosenPrivilege.equals(lock)) {
				choice = chosenPrivilege;
			} else if (lockChosenActionSpace.equals(lock)) {
				choice = chosenActionSpace;
			} else if (lockChosenMember.equals(lock)) {
				choice = chosenMember;
			} else {
				throw new IllegalArgumentException(
						"lock is not of a known type");
			}
		} while (choice == null);

		return choice;
	}

	@Override
	public int chooseConvertingMethod(
			Map<Integer, Pair<ResourcesList, ResourcesList>> options) {
		int choice;
		int size = options.size();
		ArrayList<String> choices = new ArrayList<>();
		for (Integer i = 0; i < options.size(); i++) {
			choices.add("pay: " + options.get(i).getFirst()
					+ "\nget: " + options.get(i).getSecond());
		}
		choice = chooseOption(size, choices);
		return choice;
	}

	@Override
	public int chooseCardPaymentOptions(DevelopmentCard card) {
		int choice;
		int size = card.getRequirements().size();
		ArrayList<String> options = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			options.add(card.getRequirements().get(i).getSecond().toString());
		}
		choice = chooseOption(size, options);
		return choice;
	}

	@Override
	public void show(String message) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				label.setText(message);
			}
		});


	}

	@Override
	public Communicator chooseCommunicator(User user) {
		return null;
	}

	@Override
	public GameMode chooseMode() {
		return null;
	}

	@Override
	public void askToRejoin() {
		AtomicBoolean done = new AtomicBoolean(false);
		final Object lock = new Object();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				final Stage dialog = new Stage();
				dialog.initModality(Modality.APPLICATION_MODAL);
				dialog.initOwner(primaryStage);
				AnchorPane anchorPane = new AnchorPane();
				dialog.setResizable(false);
				dialog.setTitle("Rejoin");
				dialog.setMaxHeight(180.0);
				dialog.setMaxWidth(270.0);
				Label label = new Label(
						"We're sorry!\nYou took too much to play your " +
								"action!\nClick to rejoins!");
				label.setLayoutX(10);
				label.setLayoutY(20);
				label.setFont(new Font(14));
				Image img = new Image(
						"file:src/main/resources/images/wallpaper.png");
				ImageView imageView = new ImageView(img);
				anchorPane.getChildren().add(imageView);
				anchorPane.getChildren().add(label);
				Button button = new Button("rejoin");
				button.setLayoutX(100);
				button.setLayoutY(100);
				button.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						done.set(true);
						lock.notifyAll();
						synchronized (lock) {
							done.set(true);
							lock.notifyAll();
							dialog.close();
						}
					}
				});
				anchorPane.getChildren().add(button);
				Scene dialogScene = new Scene(anchorPane, 300, 200);
				dialog.setScene(dialogScene);
				dialog.show();
			}
		});


		synchronized (lock) {
			try {
				while (!done.get()) {
					lock.wait();
				}

			} catch (InterruptedException e) {
				ExceptionLogger.log(e);
			}
		}
		System.out.println("rejoining...");
	}

	public void actionSpaceButtonClick(ActionEvent actionEvent) {
		Button button = (Button) actionEvent.getSource();
		chosenActionSpace = Integer.parseInt(
				button.getId().replaceAll("[\\D]", ""));
		;
		if (ActionSpaceIds.checkId(chosenActionSpace)) {
			synchronized (lockChosenActionSpace) {
				actionSpaceSelected.set(true);
				lockChosenActionSpace.notifyAll();
			}
			System.out.println("action space selected");
		}
	}

	public void familyMemberButtonClick(ActionEvent actionEvent) {
		Button button = (Button) actionEvent.getSource();
		chosenMember = MemberColor.valueOf(button.getId());

		synchronized (lockChosenMember) {
			lockChosenMember.notifyAll();

			System.out.println("member selected");
		}
	}

	public void privilegeButtonClick(ActionEvent actionEvent) {
		Button button = (Button) actionEvent.getSource();
		chosenPrivilege = CouncilPrivilege.valueOf(button.getId());

		synchronized (lockChosenPrivilege) {
			lockChosenPrivilege.notifyAll();
		}
		System.out.println("privilege selected");

	}

	public void servantsButtonClick(ActionEvent actionEvent) {
		Button button = (Button) actionEvent.getSource();
		String string = button.getId();
		if (string.equals("plus")) {
			increment.incrementAndGet();
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					servants.setText(
							"servants: " + increment.toString());
				}
			});

		}
		if (string.equals("minus") && increment.get() > 0) {
			increment.decrementAndGet();
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					servants.setText(
							"servants: " + increment.toString());
				}
			});

		}
		if (string.equals("done")) {
			synchronized (lockServantIncrement) {
				incrementSelected.set(true);
				lockServantIncrement.notifyAll();
			}

		}
	}


	public void display() {
		primaryStage.setTitle("Lorenzo il Magnifico");
		primaryStage.setScene(scene);
	}

	@Override
	public void terminatedRound() throws RemoteException, IOException {
		Scene scene = anchor.getScene();
		Button actionSpace;
		for (int i = 0; i <= 24; i++) {
			actionSpace = (Button) scene.lookup(
					"#button" + i);
			actionSpace.setVisible(true);
		}
		resetFm();
	}

	public int chooseOption(int size, ArrayList<String> options) {
		final AtomicInteger choice = new AtomicInteger(-1);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				optionsAnchor.setVisible(true);

				for (Integer i = 0; i < size; i++) {
					final int value = i;
					Button button = new Button("options " + i.toString());
					Label label = new Label(options.get(i));
					optionsAnchor.getChildren().add(label);
					optionsAnchor.getChildren().add(button);
					label.setFont(new Font(10));
					label.setMaxWidth(150);
					label.setWrapText(true);

					label.setLayoutX(i * 150);
					button.setLayoutX(i * 150);

					button.setLayoutY(10);
					label.setLayoutY(40);

					button.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							choice.set(value);
							synchronized (lockChooseOption) {
								lockChooseOption.notifyAll();
								optionsAnchor.setVisible(false);
							}

						}
					});
				}
			}
		});
		synchronized (lockChooseOption) {
			try {
				while (choice.get() == -1) {
					lockChooseOption.wait();
				}
			} catch (InterruptedException e) {
				ExceptionLogger.log(e);
			}
		}
		int option = choice.get();
		System.out.println("option " + option);
		return option;
	}

	public void showCurrent() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				CURRENT.setText(
						"Current period:" + currentPeriod + " Current " +
								"Player:" +
								" " +
								currentPlayer);
			}
		});
	}

	@Override
	public void update(CardType cardType, int floorNumber,
	                   DevelopmentCard developmentCard)
			throws RemoteException, IOException {
		Scene scene = anchor.getScene();
		ImageView devCard = null;
		Image imgDevCard = null;
		if (CardType.TERRITORY.equals(cardType)) {
			devCard = (ImageView) scene.lookup("#imageView" + floorNumber);
		} else if (CardType.CHARACTER.equals(cardType)) {
			devCard = (ImageView) scene.lookup(
					"#imageView" + (floorNumber + 4));
		} else if (CardType.BUILDING.equals(cardType)) {
			devCard = (ImageView) scene.lookup(
					"#imageView" + (floorNumber + 8));
		} else if (CardType.VENTURE.equals(cardType)) {
			devCard = (ImageView) scene.lookup(
					"#imageView" + (floorNumber + 12));
		}
		if (developmentCard != null) {
			String nameDevCard = developmentCard.getName();
			imgDevCard = new Image(
					"file:src/main/resources/images/cards/" + nameDevCard +
							"" +
							".png");
		} else {
			imgDevCard = new Image("file:");
		}
		devCard.setImage(imgDevCard);
		devCard.setVisible(true);
	}

	ImageView getImageView(String s, int max) {
		Scene scene = anchor.getScene();
		ImageView imageView;
		for (int i = 1; i <= max; i++) {
			imageView = (ImageView) scene.lookup("#" + s + i);
			if (!imageView.isVisible()) {
				return imageView;
			}
		}
		throw new IllegalStateException();
		//return new ImageView();
	}

	public void cardZoom(MouseEvent event) {
		ImageView cardView = (ImageView) event.getSource();
		cardView.setFitWidth(cardView.getFitWidth() * 2);
	}

	public void cardZoomOut(MouseEvent event) {
		ImageView cardView = (ImageView) event.getSource();
		cardView.setFitWidth(cardView.getFitWidth() / 2);
	}
}
