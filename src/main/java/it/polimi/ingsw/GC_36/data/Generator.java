package it.polimi.ingsw.GC_36.data;


import com.google.gson.*;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.immediateEffects
		.ImmediateCouncilPrivileges;
import it.polimi.ingsw.GC_36.model.effects.immediateEffects
		.ImmediateResourceList;
import it.polimi.ingsw.GC_36.model.effects.immediateEffects
		.ResourceListBasedOnOwnedResources;
import it.polimi.ingsw.GC_36.model.effects.permanentEffects.*;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.GC_36.utils.ExceptionLogger.log;


public class Generator {
	Encoder encoder = new Encoder();
	Decoder decoder = new Decoder();
	Gson gson = new Gson();

	static public String readFile(String path, Charset encoding)
			throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

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

	private List<ResourcesList> buildResourcesListList() {

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

	private JsonObject buildDevelopmentCard(CardType type, int period) {
		String name;
		Scanner input = new Scanner(System.in);
		JsonObject devCard = new JsonObject();

		devCard.add("type",
				gson.fromJson(gson.toJson(type), JsonElement.class));
		devCard.addProperty("period", period);

		System.out.print("Insert name: ");
		name = input.nextLine();
		devCard.addProperty("name", name);
		System.out.println("Is it need requirements: ");
		if ("y".equals(input.nextLine())) {
			List<ResourcesList> requirements = this.buildResourcesListList();
			devCard.add("requirementsList",
					gson.fromJson(gson.toJson(requirements),
							JsonElement.class));
		}

		JsonObject immediateEffect = buildImmediateEffect();
		devCard.add("immediateEffect", immediateEffect);

		JsonObject permanentEffect = buildPermanentEffect();
		devCard.add("permanentEffect", permanentEffect);


		return devCard;
	}

	public void createDevelopmentCard() throws IOException {
		Scanner input = new Scanner(System.in);
		CardType cardType1 = null;
		String temp;
		int period = 0;
		String choose;
		String onetimeinsert;
		Boolean t = false;
		JsonArray developmentCardList = new JsonArray();
		//List<DevelopmentCard> developmentCardList = new ArrayList<>();
		//JsonArray jsonArray=new JsonArray();
		String serializedString;
		if (new File("cards.json").exists()) {
			String content = this.readFile("cards.json",
					Charset.defaultCharset());
			System.out.println(content);
			developmentCardList = new JsonParser().parse(
					content).getAsJsonArray();
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
			JsonObject developmentCard = this.buildDevelopmentCard
					(cardType1, period);
			developmentCardList.add(developmentCard);
			System.out.print("Insert other DevelopmentCard? (y/n)   ");
			choose = input.nextLine();
		} while ("y".equals(choose));

		serializedString = encoder.serialize(developmentCardList);
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

	private JsonArray buildType(CardType type,
	                            JsonArray developmentCardList) {
		JsonArray developmentCardType = new JsonArray();
		for (int i = 0; i < developmentCardList.size(); i++) {
			JsonObject devCard = developmentCardList.get(i).getAsJsonObject();
			if (type.equals(new Converter().convertCardType(
					devCard.get("type").getAsString()))) {
				developmentCardType.add(devCard);
			}
		}
		return developmentCardType;
	}

	private JsonArray buildPeriod(int period,
	                              JsonArray developmentCardList) {
		JsonArray developmentCardPeriod = new JsonArray();
		for (int i = 0; i < developmentCardList.size(); i++) {
			JsonObject devCard = developmentCardList.get(i).getAsJsonObject();
			if (period == devCard.get("period").getAsInt()) {
				developmentCardPeriod.add(devCard);
			}
		}
		return developmentCardPeriod;
	}

	public void createDeckSetList(JsonArray developmentCardList) //TODO fix
			throws IOException {
		JsonArray developmentCardsPeriod, developmentCardsPeriodType;
		//List<Map<CardType, Deck>> deckSetList = new ArrayList<>();
		JsonArray deckSetList = new JsonArray();

		// period
		for (int i = 1; i <= 3; i++) {
			// give all card of the same period
			developmentCardsPeriod = this.buildPeriod(i, developmentCardList);
			//Map<CardType, Deck> deckSet = new EnumMap<>(CardType.class);
			JsonObject deckSet = new JsonObject();
			// type
			for (CardType cardType : CardType.values()) {
				// give card of the same period and of the same type
				developmentCardsPeriodType = this.buildType(cardType,
						developmentCardsPeriod);
				//add to deckCard all card having same period and type
				JsonArray deckSamePeriodType = new JsonArray();
				for (int j = 0; j < developmentCardsPeriodType.size(); j++) {
					JsonObject jo = developmentCardList.get(
							j).getAsJsonObject();
					deckSamePeriodType.add(jo);
				}
				// add all card of the sampe period and type (deck) in a map
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("type", cardType + "");
				jsonObject.addProperty("period", i);
				jsonObject.add("developmentCardList", deckSamePeriodType);
				deckSet.add(cardType + "", jsonObject);
			}
			deckSetList.add(deckSet);
		}
		String serializedString = encoder.serialize(deckSetList);
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
		int actionId = 0, floorNumber, requiredActionValue;
		boolean isSingle;
		ResourcesList resourcesList = null;

		JsonElement bonus;
		JsonObject properties;
		JsonArray jsonArray = new JsonArray();
		do {
			System.out.print("Insert action ID: ");
			actionId = Integer.parseInt(input.nextLine());
			System.out.print("Insert required action value: ");
			requiredActionValue = Integer.parseInt(input.nextLine());
			System.out.print("Bonus? ");
			if ("y".equals(input.nextLine())) {
				System.out.println("Insert bonus ");
				resourcesList = buildResourcesList();
			}
			System.out.println("Insert associated floor number");
			floorNumber = Integer.parseInt(input.nextLine());
			System.out.println("Insert if is it single: (y/n)");
			isSingle = ("y".equals(input.nextLine()));

			bonus = gson.fromJson(gson.toJson(resourcesList),
					JsonElement.class);
			properties = new JsonObject();
			properties.addProperty("actionId", actionId);
			properties.addProperty("requiredActionValue", requiredActionValue);
			properties.add("bonus", bonus);
			properties.addProperty("floorNumber", floorNumber);
			properties.addProperty("isSingle", isSingle);
			jsonArray.add(properties);

			System.out.print("Insert other action space?   ");
			choose = input.nextLine();
		} while ("y".equals(choose));
		String serializedString = encoder.serialize(jsonArray);
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
		String serializedString = encoder.serialize(bonusTileList);
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

	public ResourcesList buildCouncilPrivilege() {

		System.out.println("Insert council privilege ");
		return buildResourcesList();
	}

	public void createCouncilPrivilege() throws IOException {
		Scanner input = new Scanner(System.in);
		String choose;
		ResourcesList resourcesList;

		List<ResourcesList> resourcesListList = new ArrayList<>();
		do {
			System.out.println("Insert council privilege ");
			resourcesList = buildResourcesList();
			resourcesListList.add(resourcesList);

			System.out.print("Insert other privileges?   ");
			choose = input.nextLine();
		} while ("y".equals(choose));

		String serializedString = encoder.serialize(resourcesListList);

		FileWriter file = null;
		try {
			file = new FileWriter("councilPrivilege.json");
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

		String serializedString = encoder.serialize(resourcesListList);

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

		serializedString = new String(
				Files.readAllBytes(Paths.get("councilPrivilege.json")),
				Charset.defaultCharset());
		jsonElement = gson.fromJson(serializedString,
				JsonElement.class);
		jsonObject.add("councilPrivileges", jsonElement);

		serializedString = encoder.serialize(jsonObject);
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

	public ImmediateResourceList buildImmediateResourceListImm() {
		Scanner input = new Scanner(System.in);
		String temp;

		System.out.println("Insert resourcesList: ");
		ResourcesList resourcesList = buildResourcesList();

		return new ImmediateResourceList(resourcesList);
	}

	public JsonObject buildImmediateEffect() {
		Scanner input = new Scanner(System.in);

		System.out.println(
				"ImmediateEffect: \n\t1. ExtraProduction\n\t2. " +
						"ExtraTurnHarvest\n\t3. " +
						"ExtraTurnTower\n\t4. ImmediateCouncilPrivileges\n\t5." +
						" ImmediateResourcesList\n\t6. " +
						"ResourceListBasedOnOwedResources\nInsert:");
		JsonObject jsonObject = new JsonObject();
		int choose = Integer.parseInt(input.nextLine());
		switch (choose) {
			case 0:
				return null;

			case 4:
				jsonObject.addProperty("EffectType",
						"ImmediateCouncilPrivileges");
				jsonObject.add("EffectBody", gson.fromJson(
						gson.toJson(buildImmediateCouncilPrivileges()),
						JsonElement.class));
				return jsonObject;
			case 5:
				jsonObject.addProperty("EffectType",
						"ImmediateResourceList");
				jsonObject.add("EffectBody", gson.fromJson(
						gson.toJson(buildImmediateResourceListImm()),
						JsonElement.class));
				return jsonObject;
			case 6:
				jsonObject.addProperty("EffectType",
						"ResourceListBasedOnOwedResources");
				jsonObject.add("EffectBody", gson.fromJson(
						gson.toJson(buildResourceListBasedOnOwnedResources()),
						JsonElement.class));
				return jsonObject;


			default:
				return null;
		}
	}

	public ResourceListBasedOnOwnedResources
	buildResourceListBasedOnOwnedResources() {
		ResourceType fromResourceType = null;
		Scanner input = new Scanner(System.in);
		String temp;

		System.out.print(
				"\nw: WOOD\tst: STONE\tse: SERVANT\tc: COINS\tv: " +
						"VICTORY_POINTS\tf: FAITH_POINTS\tm: " +
						"MILITART_POINTS\nInsert typeCard: ");
		temp = input.nextLine();
		if ("w".equals(temp)) {
			fromResourceType = ResourceType.WOOD;
		} else if ("st".equals(temp)) {
			fromResourceType = ResourceType.STONE;
		} else if ("se".equals(temp)) {
			fromResourceType = ResourceType.SERVANT;
		} else if ("c".equals(temp)) {
			fromResourceType = ResourceType.COINS;
		} else if ("v".equals(temp)) {
			fromResourceType = ResourceType.VICTORY_POINTS;
		} else if ("f".equals(temp)) {
			fromResourceType = ResourceType.FAITH_POINTS;
		} else if ("m".equals(temp)) {
			fromResourceType = ResourceType.MILITARY_POINTS;
		}

		System.out.println("Insert from resources value: ");
		int fromResourceValue = Integer.parseInt(input.nextLine());

		ResourcesList toResourcesList = buildResourcesList();
		//
		String s = encoder.serialize(new ResourceListBasedOnOwnedResources(
				fromResourceType, fromResourceValue, toResourcesList));
		System.out.println(encoder.serialize(decoder.deserialize(s,
				ResourceListBasedOnOwnedResources.class)));
		//

		return new ResourceListBasedOnOwnedResources(
				fromResourceType,
				fromResourceValue, toResourcesList);
	}

	public ImmediateCouncilPrivileges buildImmediateCouncilPrivileges() {
		Scanner input = new Scanner(System.in);
		String temp;

		System.out.println("Insert if must differ: ");
		boolean mustDiffer = ("y".equals(input.nextLine()));
		System.out.println("Insert privilege's number: ");
		int numberOfPrivileges = Integer.parseInt(input.nextLine());
		//ResourcesList councilPrivilege = buildCouncilPrivilege();

		return new ImmediateCouncilPrivileges(numberOfPrivileges, mustDiffer);
	}

	private Set<ActionSpaceIds> buildActionSpaces() {
		Scanner input = new Scanner(System.in);
		Set<ActionSpaceIds> actionSpaces = new HashSet<>();
		int tmp;
		ActionSpaceIds actionSpaceIds = null;
		String choice;

		do {
			System.out.println("Insert actionSpaces: ");
			tmp = Integer.parseInt(input.nextLine());
			actionSpaceIds = ActionSpaceIds.values()[tmp];
			actionSpaces.add(actionSpaceIds);
			System.out.println("Insert another action space?");
			choice = input.nextLine();
		} while ("y".equals(choice));
		return actionSpaces;
	}

	private JsonObject buildPermanentEffect() {
		Scanner input = new Scanner(System.in);

		System.out.println(
				"Permanent Effect:\n\t1. ActionSpaceModifier\n\t2. " +
						"CardRequirementsModifier\n\t3. " +
						"HarvestWorkValueModifier\n\t4. " +
						"ImmediateResourcesList\n\t5. " +
						"ProductionWorkValueModifier\n\t6. " +
						"ResourcesListBaseOnOwnedCards\n\t7. " +
						"ResourcesConverting\nInsert:");
		JsonObject jsonObject = new JsonObject();
		int choose = Integer.parseInt(input.nextLine());
		switch (choose) {
			case 0:
				return null;
			case 1:
				jsonObject.addProperty("EffectType",
						"ActionSpaceModifier");
				jsonObject.add("EffectBody", gson.fromJson(
						gson.toJson(buildActionSpacesModifier()),
						JsonElement.class));
				return jsonObject;
			case 2:
				jsonObject.addProperty("EffectType",
						"CardRequirementsModifier");
				jsonObject.add("EffectBody", gson.fromJson(
						gson.toJson(buildCardRequirementsModifier()),
						JsonElement.class));
				return jsonObject;
			case 3:
				jsonObject.addProperty("EffectType",
						"HarvestWorkValueModifier");
				jsonObject.add("EffectBody", gson.fromJson(
						gson.toJson(buildHarvestWorkValueModifier()),
						JsonElement.class));
				return jsonObject;
			case 4:
				jsonObject.addProperty("EffectType",
						"ImmediateResourcesList");
				jsonObject.add("EffectBody", gson.fromJson(
						gson.toJson(buildImmediateResourceListPerm()),
						JsonElement.class));
				return jsonObject;
			case 5:
				jsonObject.addProperty("EffectType",
						"ProductionWorkValueModifier");
				jsonObject.add("EffectBody", gson.fromJson(
						gson.toJson(buildProductionWorkValueModifier()),
						JsonElement.class));
				return jsonObject;
			case 6:
				jsonObject.addProperty("EffectType",
						"ResourcesListBaseOnOwnedCards");
				jsonObject.add("EffectBody", gson.fromJson(
						gson.toJson(buildResourceListBasedOnOwnedCards()),
						JsonElement.class));
				return jsonObject;
			case 7:
				jsonObject.addProperty("EffectType",
						"ResourcesConverting");
				jsonObject.add("EffectBody", gson.fromJson(
						gson.toJson(buildResourcesConverting()),
						JsonElement.class));
				return jsonObject;
			default:
				return null;
		}
	}

	private ResourceListBasedOnOwnedCards buildResourceListBasedOnOwnedCards
			() {
		Scanner input = new Scanner(System.in);

		System.out.println("Insert resourcesList ");
		ResourcesList resourcesList = buildResourcesList();
		CardType cardType = null;
		System.out.println("Insert card type: ");
		String tmp = input.nextLine();
		if ("b".equals(tmp)) {
			cardType = CardType.BUILDING;
		} else if ("c".equals(tmp)) {
			cardType = CardType.CHARACTER;
		} else if ("t".equals(tmp)) {
			cardType = CardType.TERRITORY;
		} else if ("v".equals(tmp)) {
			cardType = CardType.VENTURE;
		}
		System.out.println("Insert requiredActionValue: ");
		int requiredActionValue = Integer.parseInt(input.nextLine());
		return new ResourceListBasedOnOwnedCards(resourcesList, cardType,
				requiredActionValue);
	}

	private CardRequirementsModifier buildCardRequirementsModifier() {
		return null;
		//return new CardRequirementsModifier();
	}

	private HarvestWorkValueModifier buildHarvestWorkValueModifier() {
		return new HarvestWorkValueModifier();
	}

	private ProductionWorkValueModifier buildProductionWorkValueModifier() {
		return new ProductionWorkValueModifier();
	}

	private ResourcesConverting buildResourcesConverting() {
		Scanner input = new Scanner(System.in);

		ResourcesList resourcesList1 = buildResourcesList();
		ResourcesList resourcesList2 = buildResourcesList();
		System.out.println("Insert required action value: ");
		int requiredActionValue = Integer.parseInt(input.nextLine());
		Pair pair = new Pair(resourcesList1, resourcesList2);
		HashMap<Integer, Pair<ResourcesList, ResourcesList>> option = new
				HashMap<>();
		int count = 0;
		do {
			option.put(count, pair);
			count++;
			System.out.println("Insert another pair?");
		} while ("y".equals(input.nextLine()));
		return new ResourcesConverting(requiredActionValue, option);
	}


	private ActionSpaceModifier buildActionSpacesModifier() {
		return new ActionSpaceModifier();
	}

	public ImmediateResourcesList buildImmediateResourceListPerm() {
		Scanner input = new Scanner(System.in);

		CardType cardType = null;
		System.out.println("Insert card type: ");
		String tmp = input.nextLine();
		if ("b".equals(tmp)) {
			cardType = CardType.BUILDING;
		} else if ("c".equals(tmp)) {
			cardType = CardType.CHARACTER;
		} else if ("t".equals(tmp)) {
			cardType = CardType.TERRITORY;
		} else if ("v".equals(tmp)) {
			cardType = CardType.VENTURE;
		}

		System.out.println("Insert resourcesList ");
		ResourcesList resourcesList = buildResourcesList();

		System.out.println("Insert requiredActionValue: ");
		int requiredActionValue = Integer.parseInt(input.nextLine());

		return new ImmediateResourcesList(resourcesList, requiredActionValue,
				cardType);
	}
}