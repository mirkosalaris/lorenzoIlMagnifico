package it.polimi.ingsw.GC_36.parsers;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import it.polimi.ingsw.GC_36.data.Converter;
import it.polimi.ingsw.GC_36.data.Decoder;
import it.polimi.ingsw.GC_36.data.Encoder;
import it.polimi.ingsw.GC_36.exception.ParsingException;
import it.polimi.ingsw.GC_36.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
	private List<Map<CardType, Deck>> deckSetList;
	private List<BonusTile> bonusTiles;
	private List<Map<String, Object>> actionSpace;
	private List<ResourcesList> personalBoardList;
	private List<ResourcesList> councilPrivileges;
	private ResourcesList tax;
	private List<LeaderCard> leaderCardList;

	public Parser(File file) {
		String serializedString;
		try {
			serializedString = new String(
					Files.readAllBytes(Paths.get
							(file.getPath())), Charset.defaultCharset());
		} catch (IOException e) {
			throw new ParsingException(e);
		}
		Decoder decoder = new Decoder();
		Encoder encoder = new Encoder();
		Converter converter = new Converter();
		JsonElement jsonElement;

		jsonElement = new JsonParser().parse(
				serializedString).getAsJsonObject().get("developmentCards");
		this.deckSetList = converter.convertDeckSetList(new JsonParser().parse(
				encoder.serialize(jsonElement)).getAsJsonArray());
		jsonElement = new JsonParser().parse(
				serializedString).getAsJsonObject().get("bonusTiles");
		this.bonusTiles = decoder.buildBonusTiles(
				encoder.serialize(jsonElement));
		jsonElement = new JsonParser().parse(
				serializedString).getAsJsonObject().get("personalBoards");
		this.personalBoardList = decoder.buildPersonalBoardList(
				encoder.serialize(jsonElement));
		jsonElement = new JsonParser().parse(
				serializedString).getAsJsonObject().get("councilPrivileges");
		this.councilPrivileges = decoder.buildCouncilPrivilege(
				encoder.serialize(jsonElement));
		jsonElement = new JsonParser().parse(
				serializedString).getAsJsonObject().get("actionSpaces");
		this.actionSpace = decoder.buildActionSpace(
				encoder.serialize(jsonElement));
		jsonElement = new JsonParser().parse(
				serializedString).getAsJsonObject().get("tax");
		this.tax = decoder.buildTax(encoder.serialize(jsonElement));
		jsonElement = new JsonParser().parse(
				serializedString).getAsJsonObject().get("leaderCards");
		this.leaderCardList = converter.convertLeaderCardsList(
				new JsonParser().parse(
						encoder.serialize(jsonElement)).getAsJsonArray());
	}

	//get developmentCards, bonusTiles, personalBoards, councilPrivileges
	public Object get(String s) {
		if (s.contains("tax")) {
			return this.tax;
		}
		if (s.contains("leaderCard")) {
			// return a copy
			return new ArrayList<>(this.leaderCardList);
		} else {
			int value = Integer.parseInt(s.replaceAll("[\\D]", ""));
			if (s.contains("deckSet")) {
				// return a copy
				Map<CardType, Deck> decks = this.deckSetList.get(value - 1);
				Map<CardType, Deck> newDecks = new HashMap<>();
				for (Map.Entry<CardType, Deck> entry : decks.entrySet()) {
					newDecks.put(entry.getKey(), entry.getValue().copy());
				}
				return newDecks;
			} else if (s.contains("personalBoard")) {
				// return a copy
				return this.personalBoardList.get(value - 1).copy();
			} else if (s.contains("councilPrivilege")) {
				// return a copy
				return this.councilPrivileges.get(value).copy();
			} else if (s.contains("bonusTile")) {
				//bonusTile is immutable
				return this.bonusTiles.get(value);
			} else {
				throw new IllegalArgumentException("Parameter not valid");
			}
		}
	}

	// getActionSpace
	public Object get(String s1, String s2) {
		int id = Integer.parseInt(s1.replaceAll("[\\D]", ""));
		if (s1.contains("actionSpace")) {
			if (s2.contains("requiredActionValue")) {
				return this.actionSpace.get(id).get("requiredActionValue");
			} else if (s2.contains("bonus")) {
				return this.actionSpace.get(id).get("bonus");
			} else if (s2.contains("isSingle")) {
				return this.actionSpace.get(id).get("isSingle");
			} else {
				throw new IllegalArgumentException("Parameter not valid");
			}
		} else {
			throw new IllegalArgumentException("Parameter not valid");
		}
	}
}
