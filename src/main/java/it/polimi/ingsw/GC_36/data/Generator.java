package it.polimi.ingsw.GC_36.data;


import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Generator {

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

		List<ResourcesList> requirements = new ArrayList<>();

		String choose;
		Scanner input = new Scanner(System.in);
		do {
			ResourcesList resourcesList = buildResourcesList();
			requirements.add(resourcesList);

			System.out.print("Insert other requirements list?   ");
			choose = input.nextLine();
		} while (choose.equals("y"));
		return requirements;
	}

	private DevelopmentCard buildDevelopmentCard(CardType type, int period) {
		String name;
		Scanner input = new Scanner(System.in);

		System.out.print("Insert name: ");
		name = input.nextLine();

		List<ResourcesList> requirements = this.buildResourcesListList();
		ImmediateEffect immediateEffect = null;
		PermanentEffect permanentEffect = null;

		return new DevelopmentCard(type, period, name, requirements,
				immediateEffect, permanentEffect);
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
		Encoder e = new Encoder();
		Decoder d = new Decoder();
		if (new File("cards.json").exists()) {
			String content = this.readFile("cards.json",
					Charset.defaultCharset());
			System.out.println(content);
			developmentCardList = d.buildDevelopmentCardList(content);
			System.out.println(developmentCardList.get(0).getName().toString
					());
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

				if (temp.equals("b")) {
					cardType1 = CardType.BUILDING;
				} else if (temp.equals("c")) {
					cardType1 = CardType.CHARACTER;
				} else if (temp.equals("t")) {
					cardType1 = CardType.TERRITORY;
				} else if (temp.equals("v")) {
					cardType1 = CardType.VENTURE;
				}
				System.out.print("Insert card period: ");
				period = Integer.parseInt(input.nextLine());
				if (onetimeinsert.equals("y")) {
					t = true;
				}
			}
			DevelopmentCard developmentCard = this.buildDevelopmentCard
					(cardType1, period);
			developmentCardList.add(developmentCard);
			System.out.print("Insert other DevelopmentCard? (y/n)   ");
			choose = input.nextLine();
		} while (choose.equals("y"));
		serializedString = e.build(developmentCardList);

		try {
			FileWriter fileUser = new FileWriter("cards.json");
			fileUser.write(serializedString);
			fileUser.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	static String readFile(String path, Charset encoding)
			throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	public void createDeckSetList(List<DevelopmentCard> developmentCardList) {
		List<DevelopmentCard> developmentCardsPeriod,
				developmentCardsPeriodType;
		List<Map<CardType, Deck>> deckSetList = new ArrayList<>();
		Encoder e = new Encoder();

		// period
		for (int i = 1; i <= 3; i++) {
			// give all card of the same period
			developmentCardsPeriod = this.buildPeriod(i, developmentCardList);
			Map<CardType, Deck> deckSet = new HashMap<>();
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
		String serializedString = e.build(deckSetList);
		try {
			FileWriter fileUser = new FileWriter("cards1.json");
			fileUser.write(serializedString);
			fileUser.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	/*
	public void buildActionSpaceN() {
		Scanner input = new Scanner(System.in);
		System.out.print("Insert action ID: ");
		int actionId = Integer.parseInt(input.nextLine());
		System.out.println("Insert bonus: ");
		ResourcesList resourcesList = buildResourcesList();
	}
	*/

}