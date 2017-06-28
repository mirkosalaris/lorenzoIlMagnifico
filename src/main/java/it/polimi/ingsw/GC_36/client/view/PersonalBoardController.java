package it.polimi.ingsw.GC_36.client.view;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PersonalBoardController {
	private Stage primaryStage;
	private Scene scene;
	private BoardController boardCtrl;

	public void initialize(Stage primaryStage, Parent root,
	                       BoardController boardController)
			throws IOException {
		this.primaryStage = primaryStage;
		this.boardCtrl = boardController;

		scene = new Scene(root);
	}

	public void display() {
		primaryStage.setTitle("Personal Board");
		primaryStage.setScene(scene);
	}

	public void toBoardBTNClick(ActionEvent actionEvent) {
		boardCtrl.display();
	}
}
