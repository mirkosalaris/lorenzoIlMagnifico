package it.polimi.ingsw.GC_36.client.view;

import it.polimi.ingsw.GC_36.model.MemberColor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class GuiController {
	@FXML
	private Button button0;
	@FXML
	private Button button1;
	@FXML
	private Button button2;
	@FXML
	private Button button3;
	@FXML
	private Button button4;
	@FXML
	private Button button5;
	@FXML
	private Button button6;
	@FXML
	private Button button7;
	@FXML
	private Button button8;
	@FXML
	private Button button9;
	@FXML
	private Button button10;
	@FXML
	private Button button11;
	@FXML
	private Button button12;
	@FXML
	private Button button14;
	@FXML
	private Button button15;
	@FXML
	private Button button16;
	@FXML
	private Button button17;
	@FXML
	private Button button18;
	@FXML
	private Button button19;
	@FXML
	private Button button20;
	@FXML
	private Button button21;
	@FXML
	private Button button22;
	@FXML
	private Button button23;
	@FXML
	private Button button24;

	@FXML
	private AnchorPane anchor;

	@FXML
	private Button plusServants;
	@FXML
	private Button minusServants;

	@FXML
	private ImageView bonusTile;
	@FXML
	private ImageView personalBoardImage;
	@FXML
	private ImageView boardImage;

	@FXML
	private ImageView imageView1;
	@FXML
	private ImageView imageView2;
	@FXML
	private ImageView imageView3;
	@FXML
	private ImageView imageView4;


	@FXML
	private ImageView imageView5;
	@FXML
	private ImageView imageView6;
	@FXML
	private ImageView imageView7;
	@FXML
	private ImageView imageView8;

	@FXML
	private ImageView imageView9;
	@FXML
	private ImageView imageView10;
	@FXML
	private ImageView imageView11;
	@FXML
	private ImageView imageView12;

	@FXML
	private ImageView imageView13;
	@FXML
	private ImageView imageView14;
	@FXML
	private ImageView imageView15;
	@FXML
	private ImageView imageView16;

	@FXML
	private ImageView imageViewPersonalB1;
	@FXML
	private ImageView imageViewPersonalB2;
	@FXML
	private ImageView imageViewPersonalB3;
	@FXML
	private ImageView imageViewPersonalB4;
	@FXML
	private ImageView imageViewPersonalB5;
	@FXML
	private ImageView imageViewPersonalB6;

	@FXML
	private ImageView imageViewPersonalT1;
	@FXML
	private ImageView imageViewPersonalT2;
	@FXML
	private ImageView imageViewPersonalT3;
	@FXML
	private ImageView imageViewPersonalT4;
	@FXML
	private ImageView imageViewPersonalT5;
	@FXML
	private ImageView imageViewPersonalT6;

	@FXML
	private ImageView imageViewPersonalC1;
	@FXML
	private ImageView imageViewPersonalC2;
	@FXML
	private ImageView imageViewPersonalC3;
	@FXML
	private ImageView imageViewPersonalC4;
	@FXML
	private ImageView imageViewPersonalC5;
	@FXML
	private ImageView imageViewPersonalC6;

	@FXML
	private ImageView imageViewPersonalV1;
	@FXML
	private ImageView imageViewPersonalV2;
	@FXML
	private ImageView imageViewPersonalV3;
	@FXML
	private ImageView imageViewPersonalV4;
	@FXML
	private ImageView imageViewPersonalV5;
	@FXML
	private ImageView imageViewPersonalV6;

	@FXML
	private Button b1;
	@FXML
	private Button b2;

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
	ComboBox<?> comboBoxFamilyMember;
	@FXML
	private Label labelhmsdyw;

	@FXML
	private Label victoryPoints;
	@FXML
	private Label militaryPoints;
	@FXML
	private Label faithPoints;

	@FXML
	private Label labelOrangeDie;
	@FXML
	private Label labelBlackDie;
	@FXML
	private Label labelWhiteDie;
	@FXML
	Label labelfamilymemberinfo;

	@FXML
	ImageView fmUncoloredGreen, fmUncoloredBlue, fmUncoloredYellow,
			fmUncoloredRed;

	@FXML
	ComboBox<?> comboBoxPlayer;

	@FXML
	void prova(ActionEvent event) {
		Image img = new Image(
				"file:src/main/resources/images/leader_cards/Giovanni dalle " +
						"Bande Nere.jpg");
		imageView9.setImage(img);
	}

	@FXML
	void choseFamilyMember(ActionEvent event) {
		String mc = (String) comboBoxFamilyMember.getValue();
		//MemberColor.valueOf(mc);
		System.out.println("Player chose member color");
	}

	@FXML
	void p(ActionEvent event) {
		Image ig = new Image(
				"file:src/main/resources/images/leader_cards/Giovanni dalle " +
						"Bande Nere.jpg");
		Image img = new Image(
				"file:src/main/resources/images/cards/Ambassador.png");
		imageView1.setImage(img);
		imageView2.setImage(img);
		imageView3.setImage(img);
		imageView4.setImage(img);
		imageView5.setImage(img);
		imageView6.setImage(img);
		imageView7.setImage(img);
		imageView8.setImage(img);
		imageView9.setImage(img);
		imageView10.setImage(img);
		imageView11.setImage(img);
		imageView12.setImage(img);
		imageView13.setImage(img);
		imageView14.setImage(img);
		imageView15.setImage(img);
		imageView16.setImage(img);

		b1.setVisible(false);
		b2.setVisible(true);

		labelWoodPoints.setText("100");
		labelStonePoints.setText("100");
		labelCoinsPoints.setText("100");
		labelServantPoints.setText("100");
		labelVictoryPoints.setText("100");
		labelFaithPoints.setText("100");
		labelMilitaryPoints.setText("100");

		labelhmsdyw.setVisible(true);
		comboBoxFamilyMember.setVisible(true);
		minusServants.setVisible(true);
		labelWoods.setVisible(true);
		plusServants.setVisible(true);
		labelfamilymemberinfo.setVisible(true);
		comboBoxPlayer.setVisible(true);

	}

	@FXML
	void q(ActionEvent event) throws IOException {

		Image img = new Image("file:");
		imageView1.setImage(img);
		imageView2.setImage(img);
		imageView3.setImage(img);
		imageView4.setImage(img);
		imageView5.setImage(img);
		imageView6.setImage(img);
		imageView7.setImage(img);
		imageView8.setImage(img);
		imageView9.setImage(img);
		imageView10.setImage(img);
		imageView11.setImage(img);
		imageView12.setImage(img);
		imageView13.setImage(img);
		imageView14.setImage(img);
		imageView15.setImage(img);
		imageView16.setImage(img);

		b2.setVisible(false);
		b1.setVisible(true);

		labelWoodPoints.setText("0");
		labelStonePoints.setText("0");
		labelCoinsPoints.setText("0");
		labelServantPoints.setText("0");
		labelVictoryPoints.setText("0");
		labelFaithPoints.setText("0");
		labelMilitaryPoints.setText("0");

		labelhmsdyw.setVisible(false);
		comboBoxFamilyMember.setVisible(false);
		minusServants.setVisible(false);
		labelWoods.setVisible(false);
		plusServants.setVisible(false);
		labelfamilymemberinfo.setVisible(false);
		comboBoxPlayer.setVisible(false);

	}

	@FXML
	void minusServants(ActionEvent event) {
		int value = Integer.parseInt(labelWoods.getText());
		if (value > 0) {
			labelWoods.setText("" + (value - 1));
		}
	}

	@FXML
	void plusServants(ActionEvent event) {
		int value = Integer.parseInt(labelWoods.getText());
		labelWoods.setText("" + (value + 1));

	}

	@FXML
	void buttonSingle(ActionEvent event) {
		Scene scene = anchor.getScene();
		ImageView familiar = new ImageView();
		ImageView devCard = new ImageView();
		Image img = null;
		String mc = (String) comboBoxFamilyMember.getValue();
		String p = (String) comboBoxPlayer.getValue();
		Button button = (Button) event.getSource();
		String name;
		ImageView personalImg;
		if (mc != null && p != null) {
			for (int i = 0; i <= 24; i++) {
				if (("button" + i).equals(button.getId())) {
					familiar = (ImageView) scene.lookup("#fm" + i);
					devCard = (ImageView) scene.lookup("#imageView" + i);
				}
			}
			button.setVisible(false);
			name = "fm" + p + mc + ".png";
			img = new Image(
					"file:src/main/resources/images/familyMember/" + name);
			familiar.setImage(img);
			familiar.setVisible(true);
			if (devCard != null) {
				devCard.setVisible(false);
				personalImg = put("imageViewPersonalB");
				personalImg.setImage(devCard.getImage());
				personalImg.setVisible(true);
			}
		}
	}


	void pressBotton2(ActionEvent event) {
		fmUncoloredGreen.setVisible(true);
	}

	void pressBotton14(ActionEvent event) {
		fmUncoloredRed.setVisible(true);
	}

	MemberColor convertMemberColor(String s) throws IllegalStateException {
		switch (s) {
			case "Orange":
				return MemberColor.ORANGE;
			case "Black":
				return MemberColor.BLACK;
			case "White":
				return MemberColor.WHITE;
			case "Uncolored":
				return MemberColor.UNCOLORED;
			default:
				throw new IllegalStateException();
		}
	}

	ImageView put(String s) {
		Scene scene = anchor.getScene();
		ImageView card;
		for (int i = 1; i <= 6; i++) {
			card = (ImageView) scene.lookup("#" + s + i);
			if (!card.isVisible()) {
				return card;
			}
		}
		throw new IllegalStateException();
	}

	@FXML
	void buttonMultiple(ActionEvent event) {}


}



