package it.polimi.ingsw.GC_36.data;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.ActionSpaceModifier;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;
import it.polimi.ingsw.GC_36.model.effects.ResourceListBasedOnOwnedResources;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.GC_36.ExceptionLogger.log;


public class Generator {
	Encoder encoder = new Encoder();
	Decoder decoder = new Decoder();
	Gson gson = new Gson();

	private ResourcesList buildResourcesList() {
		Scanner input = new Scanner(System.in);
		ResourcesList resourcesList = new ResourcesList();

		System.out.print("Insert woods: ");
		resourcesList.set(ResourceType.WOOD,
				Integer.parseInt(input.nextLine()));
		System.out.print("Insert stones: ");
		resourcesList.set(ResourceType.STONE,
				Integer.parseInt(input.nextLine()));
		System.out.print("Insert servants: ");
		resourcesList.set(ResourceType.SERVANT,
				Integer.parseInt(input.nextLine()));
		System.out.print("Insert coins: ");
		resourcesList.set(ResourceType.COINS,
				Integer.parseInt(input.nextLine()));
		System.out.print("Insert vitoryPoints: ");
		resourcesList.set(ResourceType.VICTORY_POINTS,
				Integer.parseInt(input.nextLine()));
		System.out.print("Insert faithPoints: ");
		resourcesList.set(ResourceType.FAITH_POINTS,
				Integer.parseInt(input.nextLine()));
		System.out.print("Insert militaryPoints: ");
		resourcesList.set(ResourceType.MILITARY_POINTS,
				Integer.parseInt(input.nextLine()));
		return resourcesList;
	}

	private ArrayList<ResourcesList> buildResourcesListList() {

		ArrayList<ResourcesList> requirements = new ArrayList<>();

		String choose;
		Scanner input = new Scanner(System.in);
		do {
			ResourcesList resourcesList = buildResourcesList();
			requirements.add(resourcesList);

			System.out.print("Insert other requirements list?   ");
			choose = input.nextLine();
		} while ("y".equals(choose));
		return requirements;
	}

	private DevelopmentCard buildDevelopmentCard(CardType type, int period) {
		String name;
		Scanner input = new Scanner(System.in);

		System.out.print("Insert name: ");
		name = input.nextLine();

		ArrayList<ResourcesList> requirements = this.buildResourcesListList();
		ImmediateEffect immediateEffect = buildImmediateEffect();
		PermanentEffect permanentEffect = new ActionSpaceModifier();

		return new DevelopmentCard(type, period, name, requirements,
				immediateEffect, permanentEffect);
	}

	public ImmediateEffect buildImmediateEffect() {
		ResourcesList requirements1 = new ResourcesList();
		requirements1.set(ResourceType.WOOD, 1);
		requirements1.set(ResourceType.STONE, 1);
		requirements1.set(ResourceType.SERVANT, 1);
		requirements1.set(ResourceType.COINS, 1);
		requirements1.set(ResourceType.VICTORY_POINTS, 1);
		requirements1.set(ResourceType.FAITH_POINTS, 1);
		requirements1.set(ResourceType.MILITARY_POINTS, 1);

		ResourcesList requirements2 = new ResourcesList();
		requirements2.set(ResourceType.WOOD, 2);
		requirements2.set(ResourceType.STONE, 2);
		requirements2.set(ResourceType.SERVANT, 2);
		requirements2.set(ResourceType.COINS, 2);
		requirements2.set(ResourceType.VICTORY_POINTS, 2);
		requirements2.set(ResourceType.FAITH_POINTS, 2);
		requirements2.set(ResourceType.MILITARY_POINTS, 2);

		ImmediateEffect immediateEffect = new
				ResourceListBasedOnOwnedResources(
				requirements1, requirements2);
		return immediateEffect;
	}

	public List<DevelopmentCard> buildType(CardType type,
	                                       List<DevelopmentCard>
			                                       developmentCardList) {
		List<DevelopmentCard> developmentCardType = new ArrayList<>();
		for (DevelopmentCard developmentCard : developmentCardList) {
			if (type.equals(developmentCard.getType())) {
				developmentCardType.add(developmentCard);
			}
		}
		return developmentCardType;
	}

	public List<DevelopmentCard> buildPeriod(int period,
	                                         List<DevelopmentCard>
			                                         developmentCardList) {
		List<DevelopmentCard> developmentCardPeriod = new ArrayList<>();
		for (DevelopmentCard developmentCard : developmentCardList) {
			if (period == developmentCard.getPeriod()) {
				developmentCardPeriod.add(developmentCard);
			}
		}
		return developmentCardPeriod;
	}

	public void createDevelopmentCard() throws IOException {
		Scanner input = new Scanner(System.in);
		CardType cardType1 = null;
		String temp;
		int period = 0;
		String choose;
		String onetimeinsert;
		Boolean t = false;
		List<DevelopmentCard> developmentCardList = new ArrayList<>();
		String serializedString;
		if (new File("cards.json").exists()) {
			String content = this.readFile("cards.json",
					Charset.defaultCharset());
			System.out.println(content);
			developmentCardList = decoder.buildDevelopmentCardList(content);
			System.out.println(developmentCardList.get(0).getName());
		}
		System.out.print(
				"Do you want to insert period and type only one time? (y/n) ");
		onetimeinsert = input.nextLine();
		do {
			if (t == false) {
				System.out.print(
						"\nb: BUILDING\tc: CHARACTER\tt: TERRITORY\tv: " +
								"VENTURE\nInsert typeCard: ");
				temp = input.nextLine();

				if ("b".equals(temp)) {
					cardType1 = CardType.BUILDING;
				} else if ("c".equals(temp)) {
					cardType1 = CardType.CHARACTER;
				} else if ("t".equals(temp)) {
					cardType1 = CardType.TERRITORY;
				} else if ("v".equals(temp)) {
					cardType1 = CardType.VENTURE;
				}
				System.out.print("Insert card period: ");
				period = Integer.parseInt(input.nextLine());
				if ("y".equals(onetimeinsert)) {
					t = true;
				}
			}
			DevelopmentCard developmentCard = this.buildDevelopmentCard
					(cardType1, period);
			developmentCardList.add(developmentCard);
			System.out.print("Insert other DevelopmentCard? (y/n)   ");
			choose = input.nextLine();
		} while ("y".equals(choose));
		serializedString = encoder.build(developmentCardList);
		FileWriter file = null;
		try {
			file = new FileWriter("cards.json");
			file.write(serializedString);

		} catch (IOException ex) {
			Logger logger = Logger.getLogger("logger");
			logger.log(Level.SEVERE, "File cards cannot be opened", ex);
			log(ex);
		} finally {
			if (file != null) {
				file.close();
			}
		}
	}

	static public String readFile(String path, Charset encoding)
			throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	public void createDeckSetList(List<DevelopmentCard> developmentCardList)
			throws IOException {
		List<DevelopmentCard> developmentCardsPeriod,
				developmentCardsPeriodType;
		List<Map<CardType, Deck>> deckSetList = new ArrayList<>();

		// period
		for (int i = 1; i <= 3; i++) {
			// give all card of the same period
			developmentCardsPeriod = this.buildPeriod(i, developmentCardList);
			Map<CardType, Deck> deckSet = new EnumMap<>(CardType.class);
			// type
			for (CardType cardType : CardType.values()) {
				// give card of the same period and of the same type
				developmentCardsPeriodType = this.buildType(cardType,
						developmentCardsPeriod);
				//add to deckCard all card having same period and type
				List<DevelopmentCard> deckSamePeriodType = new ArrayList<>();
				for (DevelopmentCard dc : developmentCardsPeriodType) {
					deckSamePeriodType.add(dc);
				}
				// add all card of the sampe period and type (deck) in a map
				deckSet.put(cardType,
						new Deck(cardType, i, deckSamePeriodType));
			}
			deckSetList.add(deckSet);
		}
		String serializedString = encoder.build(deckSetList);
		FileWriter file = null;
		try {
			file = new FileWriter("deckSetList.json");
			file.write(serializedString);
		} catch (IOException ex) {
			Logger logger = Logger.getLogger("logger");
			logger.log(Level.SEVERE, "File cards cannot be opened", ex);
			log(ex);
		} finally {
			if (file != null) {
				file.close();
			}
		}
	}

	public void createActionSpaces() throws IOException {
		Scanner input = new Scanner(System.in);
		String choose;
		int actionId = 0, floorNumber, towerNumber;
		ResourcesList resourcesList;

		JsonElement bonus;
		JsonObject properties;
		JsonArray jsonArray = new JsonArray();
		do {
			System.out.print("Insert action ID: ");
			actionId = Integer.parseInt(input.nextLine());
			System.out.println("Insert bonus ");
			resourcesList = buildResourcesList();
			System.out.println("Insert associated floor number");
			floorNumber = Integer.parseInt(input.nextLine());

			bonus = gson.fromJson(gson.toJson((resourcesList)),
					JsonElement.class);
			properties = new JsonObject();
			properties.addProperty("requiredActionValue", actionId);
			properties.add("bonus", bonus);
			properties.addProperty("floorNumber", floorNumber);
			jsonArray.add(properties);

			System.out.print("Insert other action space?   ");
			choose = input.nextLine();
		} while ("y".equals(choose));
		String serializedString = encoder.build(jsonArray);
		FileWriter file = null;
		try {
			file = new FileWriter("actionSpace.json");
			file.write(serializedString);
		} catch (IOException ex) {
			Logger logger = Logger.getLogger("logger");
			logger.log(Level.SEVERE, "File cards cannot be opened", ex);
			log(ex);
		} finally {
			if (file != null) {
				file.close();
			}
		}
	}

	public BonusTile buildBonusTile() {
		Scanner input = new Scanner(System.in);
		ResourcesList harvestBonus;
		ResourcesList productionBonus;
		int harvestRequiredActionValue, productionRequiredActionValue;


		System.out.println("Insert harvest required action value: ");
		harvestRequiredActionValue = Integer.parseInt(input.nextLine());
		System.out.println("Insert production required action value: ");
		productionRequiredActionValue = Integer.parseInt(input.nextLine());
		System.out.println("Insert harvest bonus ");
		harvestBonus = buildResourcesList();
		System.out.println("Insert production bonus ");
		productionBonus = buildResourcesList();
		return new BonusTile(harvestRequiredActionValue,
				productionRequiredActionValue, harvestBonus, productionBonus);
	}

	public void createBonusTile() throws IOException {
		Scanner input = new Scanner(System.in);
		String choose;
		List<BonusTile> bonusTileList = new ArrayList<>();

		do {
			bonusTileList.add(buildBonusTile());
			System.out.print("Insert other bonus tile?   ");
			choose = input.nextLine();
		} while ("y".equals(choose));
		String serializedString = encoder.build(bonusTileList);
		FileWriter file = null;
		try {
			file = new FileWriter("bonusTile.json");
			file.write(serializedString);
		} catch (IOException ex) {
			Logger logger = Logger.getLogger("logger");
			logger.log(Level.SEVERE, "File bonus tile cannot be opened", ex);
			log(ex);
		} finally {
			if (file != null) {
				file.close();
			}
		}
	}

	public void createCommons() throws IOException {
		String serializedString;
		JsonObject jsonObject = new JsonObject();
		JsonElement jsonElement;

		serializedString = new String(
				Files.readAllBytes(Paths.get("deckSetList.json")),
				Charset.defaultCharset());
		jsonElement = gson.fromJson(serializedString,
				JsonElement.class);
		jsonObject.add("developmentCards", jsonElement);

		serializedString = new String(
				Files.readAllBytes(Paths.get("bonusTile.json")),
				Charset.defaultCharset());
		jsonElement = gson.fromJson(serializedString,
				JsonElement.class);
		jsonObject.add("bonusTiles", jsonElement);

		serializedString = new String(
				Files.readAllBytes(Paths.get("actionSpace.json")),
				Charset.defaultCharset());
		jsonElement = gson.fromJson(serializedString,
				JsonElement.class);
		jsonObject.add("actionSpaces", jsonElement);

		serializedString = new String(
				Files.readAllBytes(Paths.get("personalBoard.json")),
				Charset.defaultCharset());
		jsonElement = gson.fromJson(serializedString,
				JsonElement.class);
		jsonObject.add("personalBoards", jsonElement);

		serializedString = encoder.build(jsonObject);
		FileWriter file = null;
		try {
			file = new FileWriter("commons.json");
			file.write(serializedString);
		} catch (IOException ex) {
			Logger logger = Logger.getLogger("logger");
			logger.log(Level.SEVERE, "File cards cannot be opened", ex);
			log(ex);
		} finally {
			if (file != null) {
				file.close();
			}
		}
	}

	public void createPersonaBoard() throws IOException {
		Scanner input = new Scanner(System.in);
		String choose;
		ResourcesList resourcesList;

		List<ResourcesList> resourcesListList = new ArrayList<>();
		do {
			System.out.println("Insert player resources list ");
			resourcesList = buildResourcesList();
			resourcesListList.add(resourcesList);

			System.out.print("Insert other personal board?   ");
			choose = input.nextLine();
		} while ("y".equals(choose));

		String serializedString = encoder.build(resourcesListList);

		FileWriter file = null;
		try {
			file = new FileWriter("personalBoard.json");
			file.write(serializedString);
		} catch (IOException ex) {
			Logger logger = Logger.getLogger("logger");
			logger.log(Level.SEVERE, "File cards cannot be opened", ex);
			log(ex);
		} finally {
			if (file != null) {
				file.close();
			}
		}
	}
}