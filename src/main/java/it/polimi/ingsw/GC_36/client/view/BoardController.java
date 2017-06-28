package it.polimi.ingsw.GC_36.client.view;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;

public class BoardController {
	private PersonalBoardController personalBoardCtrl;
	private Scene scene;
	private Stage primaryStage;

	private static final double MIN_FIT_HEIGHT = 500.0d;
	private static final double MAX_FIT_HEIGHT = 1500.0d;

	public BoardController() {
	}

	public void initialize(Stage primaryStage, Parent root,
	                       PersonalBoardController pbController) {
		this.primaryStage = primaryStage;
		this.personalBoardCtrl = pbController;

		scene = new Scene(root);
	}

	public void display() {
		primaryStage.setTitle("Board");
		primaryStage.setScene(scene);
	}

	public void toPersonalBoardBTNClick(ActionEvent actionEvent) {
		personalBoardCtrl.display();
	}

	public void boardZoom(ScrollEvent event) {
		if (event.isControlDown()) {
			System.out.println("zooming");
			ImageView image = (ImageView) scene.lookup("#boardImage");
			double newHeight = calculateFitHeight(image, event);
			System.out.println("before " + image.getFitHeight());
			image.setFitHeight(newHeight);
			System.out.println("after " + image.getFitHeight());
		}
	}

	private double calculateFitHeight(ImageView image,
	                                  ScrollEvent scrollEvent) {
		double newHeight = image.getFitHeight() + scrollEvent.getDeltaY();

		if (newHeight <= MIN_FIT_HEIGHT) {
			newHeight = MIN_FIT_HEIGHT;
		} else if (newHeight >= MAX_FIT_HEIGHT) {
			newHeight = MAX_FIT_HEIGHT;
		}
		return newHeight;
	}
}
