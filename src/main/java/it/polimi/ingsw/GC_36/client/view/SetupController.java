package it.polimi.ingsw.GC_36.client.view;

import it.polimi.ingsw.GC_36.client.Communicator;
import it.polimi.ingsw.GC_36.client.CommunicatorRMI;
import it.polimi.ingsw.GC_36.client.CommunicatorSocket;
import it.polimi.ingsw.GC_36.client.User;
import it.polimi.ingsw.GC_36.utils.ExceptionLogger;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class SetupController {
	private Scene boardScene;
	private Stage primaryStage;
	private BoardController guiController;
	private AtomicInteger comunicator;
	private Parent root;


	public void initialize(Stage primaryStage, Scene boardScene,
	                       BoardController guiController) throws IOException {
		this.primaryStage = primaryStage;
		this.guiController = guiController;
		this.boardScene = boardScene;
	}

	public SetupController() {
		this.comunicator = new AtomicInteger();
	}

	public void toBoard(ActionEvent actionEvent) {
		guiController.display();
	}


	public void setupButtonClick(javafx.event.ActionEvent actionEvent)
			throws Exception {
		Button button = (Button) actionEvent.getSource();
		comunicator.set(Integer.parseInt(button.getId()));
		synchronized (comunicator) {
			comunicator.notifyAll();
		}
		launchBoard();

	}

	public Communicator chooseCommunicator(User user) {
		Communicator communicato = null;
		int choice = 0;
		do {
			synchronized (comunicator) {
				do {
					try {
						comunicator.wait();
						choice = comunicator.get();
					} catch (InterruptedException e) {
						ExceptionLogger.log(e);
					}
				} while (choice == 0);
			}

			if (321 == choice) {
				communicato = new CommunicatorSocket(user);
			} else if (322 == choice) {
				communicato = new CommunicatorRMI(user);
			}
		} while (communicato == null);
		return communicato;
	}

	public void launchBoard() throws Exception {

		primaryStage.setTitle("Lorenzo Il Magnifico");
		primaryStage.setScene(this.boardScene);

		primaryStage.setResizable(false);

		primaryStage.show();
	}


}
