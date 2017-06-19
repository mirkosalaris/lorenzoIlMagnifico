package it.polimi.ingsw.GC_36.parsers;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import it.polimi.ingsw.GC_36.data.Converter;
import it.polimi.ingsw.GC_36.data.Decoder;
import it.polimi.ingsw.GC_36.data.Encoder;
import it.polimi.ingsw.GC_36.exception.ParsingException;
import it.polimi.ingsw.GC_36.model.BonusTile;
import it.polimi.ingsw.GC_36.model.CardType;
import it.polimi.ingsw.GC_36.model.Deck;
import it.polimi.ingsw.GC_36.model.ResourcesList;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
	List<Map<CardType, Deck>> deckSetList;
	List<BonusTile> bonusTiles;
	List<Map<String, Object>> actionSpace;
	List<ResourcesList> personalBoardList;
	List<ResourcesList> councilPrivileges;
	ResourcesList tax;

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
	}

	//get developmentCards, bonusTiles, personalBoards, councilPrivileges
	public Object get(String s) {
		if (s.contains("tax")) {
			return this.tax;
		}
		int value = Integer.parseInt(s.replaceAll("[\\D]", ""));
		if (s.contains("deckSet")) {
			// return a copy
			Map<CardType, Deck> decks = this.deckSetList.get(value - 1);
			Map<CardType, Deck> newDecks = new HashMap<>();
			for (Map.Entry<CardType, Deck> entry : decks.entrySet()) {
				newDecks.put(entry.getKey(), entry.getValue().copy());
			}
			return newDecks;
		} else if (s.contains("bonusTile")) {
			return this.bonusTiles.get(value - 1);
		} else if (s.contains("personalBoard")) {
			return this.personalBoardList.get(value); //TODO check -1
		} else if (s.contains("councilPrivilege")) {
			return this.councilPrivileges.get(value - 1);
		} else {
			throw new IllegalArgumentException("Parameter not valid");
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
				return "yes".equals(this.actionSpace.get(id).get("isSingle"));
			} else {
				throw new IllegalArgumentException("Parameter not valid");
			}
		} else {
			throw new IllegalArgumentException("Parameter not valid");
		}
	}
}
