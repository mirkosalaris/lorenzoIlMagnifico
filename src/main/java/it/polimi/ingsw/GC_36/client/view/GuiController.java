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
	private Button button13;
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
	private ImageView fm01;
	@FXML
	private ImageView fm02;
	@FXML
	private ImageView fm03;
	@FXML
	private ImageView fm04;
	@FXML
	private ImageView fm1;
	@FXML
	private ImageView fm2;
	@FXML
	private ImageView fm3;
	@FXML
	private ImageView fm4;
	@FXML
	private ImageView fm5;
	@FXML
	private ImageView fm6;
	@FXML
	private ImageView fm7;
	@FXML
	private ImageView fm8;
	@FXML
	private ImageView fm9;
	@FXML
	private ImageView fm10;
	@FXML
	private ImageView fm11;
	@FXML
	private ImageView fm12;
	@FXML
	private ImageView fm13;
	@FXML
	private ImageView fm14;
	@FXML
	private ImageView fm15;
	@FXML
	private ImageView fm16;
	@FXML
	private ImageView fm17;
	@FXML
	private ImageView fm181;
	@FXML
	private ImageView fm182;
	@FXML
	private ImageView fm183;
	@FXML
	private ImageView fm19;
	@FXML
	private ImageView fm201;
	@FXML
	private ImageView fm202;
	@FXML
	private ImageView fm203;
	@FXML
	private ImageView fm21;
	@FXML
	private ImageView fm22;
	@FXML
	private ImageView fm23;
	@FXML
	private ImageView fm24;

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
	Button buttonOrangeDie;
	@FXML
	Button buttonBlackDie;
	@FXML
	Button buttonWhiteDie;

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
		System.out.println("Player chose member color");
	}

	@FXML
	void initializeBoard(ActionEvent event) {
		Image lc = new Image(
				"file:src/main/resources/images/leader_cards/Giovanni dalle " +
						"Bande Nere.jpg");
		Image characterCard = new Image(
				"file:src/main/resources/images/cards/Abbess.png");

		Image territoryCard = new Image(
				"file:src/main/resources/images/cards/City.png");
		Image buildingCard = new Image(
				"file:src/main/resources/images/cards/Bank.png");
		Image ventureCard = new Image(
				"file:src/main/resources/images/cards/Crusade.png");
		imageView1.setImage(territoryCard);
		imageView2.setImage(territoryCard);
		imageView3.setImage(territoryCard);
		imageView4.setImage(territoryCard);
		imageView5.setImage(characterCard);
		imageView6.setImage(characterCard);
		imageView7.setImage(characterCard);
		imageView8.setImage(characterCard);
		imageView9.setImage(buildingCard);
		imageView10.setImage(buildingCard);
		imageView11.setImage(buildingCard);
		imageView12.setImage(buildingCard);
		imageView13.setImage(ventureCard);
		imageView14.setImage(ventureCard);
		imageView15.setImage(ventureCard);
		imageView16.setImage(ventureCard);

		imageView1.setVisible(true);
		imageView2.setVisible(true);
		imageView3.setVisible(true);
		imageView4.setVisible(true);
		imageView5.setVisible(true);
		imageView6.setVisible(true);
		imageView7.setVisible(true);
		imageView8.setVisible(true);
		imageView9.setVisible(true);
		imageView10.setVisible(true);
		imageView11.setVisible(true);
		imageView12.setVisible(true);
		imageView13.setVisible(true);
		imageView14.setVisible(true);
		imageView15.setVisible(true);
		imageView16.setVisible(true);

		b1.setVisible(false);
		b2.setVisible(true);

		labelWoodPoints.setText("100");
		labelStonePoints.setText("100");
		labelCoinsPoints.setText("100");
		labelServantPoints.setText("100");
		labelVictoryPoints.setText("100");
		labelFaithPoints.setText("100");
		labelMilitaryPoints.setText("100");

		labelBlackDie.setText("6");
		labelWhiteDie.setText("5");
		labelOrangeDie.setText("6");

		labelhmsdyw.setVisible(true);
		comboBoxFamilyMember.setVisible(true);
		minusServants.setVisible(true);
		labelWoods.setVisible(true);
		plusServants.setVisible(true);
		labelfamilymemberinfo.setVisible(true);
		comboBoxPlayer.setVisible(true);

	}

	@FXML
	void refreshBoard(ActionEvent event) throws IOException {
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

		imageView1.setVisible(false);
		imageView2.setVisible(false);
		imageView3.setVisible(false);
		imageView4.setVisible(false);
		imageView5.setVisible(false);
		imageView6.setVisible(false);
		imageView7.setVisible(false);
		imageView8.setVisible(false);
		imageView9.setVisible(false);
		imageView10.setVisible(false);
		imageView11.setVisible(false);
		imageView12.setVisible(false);
		imageView13.setVisible(false);
		imageView14.setVisible(false);
		imageView15.setVisible(false);
		imageView16.setVisible(false);

		fm1.setImage(img);
		fm2.setImage(img);
		fm3.setImage(img);
		fm4.setImage(img);
		fm5.setImage(img);
		fm6.setImage(img);
		fm7.setImage(img);
		fm8.setImage(img);
		fm9.setImage(img);
		fm10.setImage(img);
		fm11.setImage(img);
		fm12.setImage(img);
		fm13.setImage(img);
		fm14.setImage(img);
		fm15.setImage(img);
		fm16.setImage(img);
		fm17.setImage(img);
		fm19.setImage(img);
		fm21.setImage(img);
		fm22.setImage(img);
		fm23.setImage(img);
		fm24.setImage(img);
		fm01.setImage(img);
		fm02.setImage(img);
		fm03.setImage(img);
		fm04.setImage(img);
		fm181.setImage(img);
		fm182.setImage(img);
		fm183.setImage(img);
		fm201.setImage(img);
		fm202.setImage(img);
		fm203.setImage(img);

		fm1.setVisible(false);
		fm2.setVisible(false);
		fm3.setVisible(false);
		fm4.setVisible(false);
		fm5.setVisible(false);
		fm6.setVisible(false);
		fm7.setVisible(false);
		fm8.setVisible(false);
		fm9.setVisible(false);
		fm10.setVisible(false);
		fm11.setVisible(false);
		fm12.setVisible(false);
		fm13.setVisible(false);
		fm14.setVisible(false);
		fm15.setVisible(false);
		fm16.setVisible(false);
		fm17.setVisible(false);
		fm19.setVisible(false);
		fm21.setVisible(false);
		fm22.setVisible(false);
		fm23.setVisible(false);
		fm24.setVisible(false);
		fm01.setVisible(false);
		fm02.setVisible(false);
		fm03.setVisible(false);
		fm04.setVisible(false);
		fm181.setVisible(false);
		fm182.setVisible(false);
		fm183.setVisible(false);
		fm201.setVisible(false);
		fm202.setVisible(false);
		fm203.setVisible(false);

		button1.setVisible(true);
		button2.setVisible(true);
		button3.setVisible(true);
		button4.setVisible(true);
		button5.setVisible(true);
		button6.setVisible(true);
		button7.setVisible(true);
		button8.setVisible(true);
		button9.setVisible(true);
		button10.setVisible(true);
		button11.setVisible(true);
		button12.setVisible(true);
		button13.setVisible(true);
		button14.setVisible(true);
		button15.setVisible(true);
		button16.setVisible(true);
		button17.setVisible(true);
		button18.setVisible(true);
		button19.setVisible(true);
		button20.setVisible(true);
		button21.setVisible(true);
		button22.setVisible(true);
		button23.setVisible(true);
		button24.setVisible(true);

		buttonOrangeDie.setVisible(true);
		buttonBlackDie.setVisible(true);
		buttonWhiteDie.setVisible(true);

		b2.setVisible(false);
		b1.setVisible(true);

		labelWoodPoints.setText("0");
		labelStonePoints.setText("0");
		labelCoinsPoints.setText("0");
		labelServantPoints.setText("0");
		labelVictoryPoints.setText("0");
		labelFaithPoints.setText("0");
		labelMilitaryPoints.setText("0");

		labelBlackDie.setText("0");
		labelWhiteDie.setText("0");
		labelOrangeDie.setText("0");

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
		String name;
		ImageView personalImg = null;

		String mc = (String) comboBoxFamilyMember.getValue();
		String p = (String) comboBoxPlayer.getValue();
		Button button = (Button) event.getSource();

		if (mc != null && p != null) {
			int i = 0;
			while (!("button" + i).equals(button.getId())) {
				i++;
			}
			familiar = (ImageView) scene.lookup("#fm" + i);
			devCard = (ImageView) scene.lookup("#imageView" + i);
			button.setVisible(false);
			name = "fm" + p + mc + ".png";
			img = new Image(
					"file:src/main/resources/images/familyMember/" + name);
			familiar.setImage(img);
			familiar.setVisible(true);
			if (devCard != null) {
				devCard.setVisible(false);
				if (i >= 1 && i <= 4) {
					personalImg = put("imageViewPersonalT", 6);
				} else if (i >= 5 && i <= 8) {
					personalImg = put("imageViewPersonalC", 6);
				} else if (i >= 9 && i <= 12) {
					personalImg = put("imageViewPersonalB", 6);
				} else if (i >= 13 && i <= 16) {
					personalImg = put("imageViewPersonalV", 6);
				}
				personalImg.setImage(devCard.getImage());
				personalImg.setVisible(true);
			}
		}
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

	ImageView put(String s, int max) {
		Scene scene = anchor.getScene();
		ImageView card;
		for (int i = 1; i <= max; i++) {
			card = (ImageView) scene.lookup("#" + s + i);
			if (!card.isVisible()) {
				return card;
			}
		}
		throw new IllegalStateException();
		//return new ImageView();
	}

	@FXML
	void buttonMultiple(ActionEvent event) {
		String mc = (String) comboBoxFamilyMember.getValue();
		String p = (String) comboBoxPlayer.getValue();
		String name;
		Image img;
		ImageView imageView = null;
		if (mc != null && p != null) {
			Button button = (Button) event.getSource();
			if (button0.equals(button)) {
				imageView = put("fm0", 4);
			} else if (button18.equals(button)) {
				imageView = put("fm18", 3);
			} else if (button20.equals(button)) {
				imageView = put("fm20", 3);
			}
			name = "fm" + p + mc + ".png";
			img = new Image(
					"file:src/main/resources/images/familyMember/" + name);
			imageView.setImage(img);
			imageView.setVisible(true);
		}
	}

	@FXML
	void selectDie(ActionEvent event) {
		String mc = (String) comboBoxFamilyMember.getValue();
		String p = (String) comboBoxPlayer.getValue();
		if (mc != null && p != null) {
			Scene scene = anchor.getScene();
			Button button = (Button) event.getSource();
			button.setVisible(false);

			String buttonId = button.getId();
			String labelId = buttonId.replaceAll("button", "label");
			Label lbl = (Label) scene.lookup("#" + labelId);
			lbl.setText("-");
		}
	}


}



