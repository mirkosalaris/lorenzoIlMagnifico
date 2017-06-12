package it.polimi.ingsw.GC_36.parsers;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import it.polimi.ingsw.GC_36.data.Decoder;
import it.polimi.ingsw.GC_36.data.Encoder;
import it.polimi.ingsw.GC_36.model.BonusTile;
import it.polimi.ingsw.GC_36.model.CardType;
import it.polimi.ingsw.GC_36.model.Deck;
import it.polimi.ingsw.GC_36.model.ResourcesList;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Parser {
	private Decoder decoder = new Decoder();
	private Encoder encoder = new Encoder();
	List<Map<CardType, Deck>> deckSetList;
	List<BonusTile> bonusTiles;
	List<Map<String, Object>> actionSpace;
	List<ResourcesList> personalBoardList;
	String serializedString;

	public Parser(File file) {
		try {
			this.serializedString = new String(
					Files.readAllBytes(Paths.get
							(file.getPath())), Charset.defaultCharset());
		} catch (IOException e) {
			throw new Error(e);
		}
	}

	public Object get(String s) {
		JsonElement jsonElement;

		int value = Integer.parseInt(s.replaceAll("[\\D]", ""));
		if (s.contains("deckSet")) {
			jsonElement = new JsonParser().parse(serializedString)
					.getAsJsonObject().get("developmentCards");
			this.deckSetList = decoder.buildDeckSetList(
					encoder.build(jsonElement));
			return this.deckSetList.get(value - 1);
		} else if (s.contains("personalBoard")) {
			jsonElement = new JsonParser().parse(
					serializedString).getAsJsonObject().get(
					"personalBoards");
			this.personalBoardList = decoder.buildPersonalBoardList(
					encoder.build(jsonElement));
			return this.personalBoardList.get(value);
		} else {
			throw new IllegalArgumentException("Parameter not valid");
		}
	}

	public Object get(String s1, String s2) {
		JsonElement jsonElement;
		jsonElement = new JsonParser().parse(
				serializedString).getAsJsonObject().get(
				"actionSpaces");
		this.actionSpace = decoder.buildActionSpace(
				encoder.build(jsonElement));

		int id = Integer.parseInt(s1.replaceAll("[\\D]", ""));
		if (s1.contains("actionSpace") && s2.contains("requiredActionValue")) {
			return this.actionSpace.get(id).get("requiredActionValue");
		} else if (s1.contains("actionSpace") && s2.contains("resources")) {
			return this.actionSpace.get(id).get("resources");
		} else {
			throw new IllegalArgumentException("Parameter not valid");
		}
	}

}
