package it.polimi.ingsw.GC_36.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.GC_36.ExceptionLogger.log;
import static java.nio.charset.Charset.defaultCharset;

public class Main {
	private Main() {}

	public static void main(String[] args) throws IOException {
		Generator g = new Generator();
		Decoder d = new Decoder();
		Encoder e = new Encoder();
		String serializedString;
		//g.createDevelopmentCard();

		/*
		String s="Vi1ncAM2are e4 no5i a studiare ";
		if (s.contains("studiare")) {
			int a=Integer.parseInt(s.replaceAll("[\\D]", ""));
			System.out.println(a);
		}

		Generator g=new Generator();
		JsonArray jsonObject= g.buildPersonaBoard();

		Gson gson=new Gson();
		String s=gson.toJson(jsonObject);
		System.out.println(s);
		*/
		//g.createBonusTile();
		//g.createDevelopmentCard();
		Scanner input = new Scanner(System.in);
		System.out.print("Choose: ");
		int choose = Integer.parseInt(input.nextLine());
		switch (choose) {
			case 1:
				// BUILD DECK!
				ImmediateEffect immediateEffect = null;
				PermanentEffect permanentEffect = null;
				ResourcesList requirements = new ResourcesList();
				CardType cardType1, cardType2, cardType3;
				DevelopmentCard developmentCard1, developmentCard2,
						developmentCard3;
				List<DevelopmentCard> developmentCardList = new ArrayList<>();
				List<ResourcesList> resourcesListList1 = new ArrayList<>();
				requirements.set(ResourceType.WOOD, 0);
				requirements.set(ResourceType.STONE, 0);
				requirements.set(ResourceType.SERVANT, 0);
				requirements.set(ResourceType.COINS, 0);
				requirements.set(ResourceType.VICTORY_POINTS, 0);
				requirements.set(ResourceType.FAITH_POINTS, 0);
				requirements.set(ResourceType.MILITARY_POINTS, 0);
				resourcesListList1.add(requirements);

				for (int i = 1; i <= 3; i++) {
					for (CardType type : CardType.values()) {
						for (int j = 0; j < 8; j++) {
							developmentCardList.add(new DevelopmentCard
									(type, i, "Commercial Hub",
											resourcesListList1,
											immediateEffect, permanentEffect));
						}
					}
				}
				List<Object> obj = new ArrayList<>();
				obj.add(developmentCardList);
				serializedString = e.build(obj);
				FileWriter file = null;
				try {
					file = new FileWriter("card.json");
					file.write(serializedString);

				} catch (IOException ex) {
					Logger logger = Logger.getLogger("logger");
					logger.log(Level.SEVERE, "File cards cannot be opened",
							ex);
					log(ex);
				} finally {
					if (file != null) {
						file.close();
					}
				}
				g.createDeckSetList(developmentCardList);

				g.createCommons();
				break;
			case 2:
				List<BonusTile> bonusTiles;
				if (new File("bonusTile.json").exists()) {
					String content = g.readFile("bonusTile.json",
							defaultCharset());
					//System.out.println(content);
					bonusTiles = d.buildBonusTiles(content);
					String s = e.build(bonusTiles);
					System.out.println(s);
				}
				break;

			case 3:
				serializedString = new String(
						Files.readAllBytes(Paths.get
								("commons.json")), Charset.defaultCharset());
				//JsonElement jsonElement=new JsonParser().parse
				// (serializedString).getAsJsonObject().remove
				// ("developmentCards");
				JsonElement jsonElement = new JsonParser().parse(
						serializedString).getAsJsonObject().get(
						"developmentCards");
				List<Map<CardType, Deck>> deckSetList = d.buildDeckSetList(
						e.build(jsonElement));
				String s = e.build(deckSetList);
				System.out.println(s);
				break;

			case 4:
				g.createPersonaBoard();
				break;
			case 5:
				g.createActionSpaces();
				break;
			case 6:
				g.createCommons();
				break;
		}
		/*
		File fileProva=new File("commons.json");
		byte[] encoded = Files.readAllBytes(Paths.get(fileProva.getPath()));
		return new String(encoded, encoding);
		*/
	}
}