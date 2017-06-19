package it.polimi.ingsw.GC_36.data;

import com.google.gson.*;
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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.GC_36.utils.ExceptionLogger.log;
import static java.nio.charset.Charset.defaultCharset;

public class StartScript {
	private StartScript() {}

	public static void main(String[] args) throws IOException {
		Generator g = new Generator();
		Decoder d = new Decoder();
		Encoder e = new Encoder();
		Gson gson = new Gson();
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
		JsonElement jsonElement;
		Scanner input = new Scanner(System.in);
		System.out.print("Choose: ");
		int choose = Integer.parseInt(input.nextLine());
		ResourcesList requirements = new ResourcesList();
		switch (choose) {
			case 1:
				// BUILD DECK!
				ImmediateEffect immediateEffect;
				JsonObject immediateEffect1 = new JsonObject();
				JsonObject immediateEffect2 = new JsonObject();

				System.out.println("Insert effect 1");
				immediateEffect = g.buildResourceListBasedOnOwnedResources();
				jsonElement = gson.fromJson(gson.toJson(immediateEffect),
						JsonElement.class);
				immediateEffect1.addProperty("effectType",
						"ImmediateCouncilPrivilege");
				immediateEffect1.add("effectBody", jsonElement);

				System.out.println("Insert effect 2");
				immediateEffect = g.buildImmediateCouncilPrivileges();
				jsonElement = gson.fromJson(gson.toJson(immediateEffect),
						JsonElement.class);
				immediateEffect2.addProperty("effectType",
						"ResourceListBasedOnOwnedResources");
				immediateEffect2.add("effectBody", jsonElement);

				PermanentEffect permanentEffect = null;

				JsonArray developmentCardList = new JsonArray();
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
						for (int j = 0; j < 4; j++) {
							JsonObject card = new JsonObject();
							card.add("type", gson.fromJson(gson.toJson(type),
									JsonElement.class));
							card.addProperty("period", i);
							card.addProperty("name", "CommercialHub");
							card.add("requirementsList", gson.fromJson(
									gson.toJson(resourcesListList1),
									JsonElement.class));
							card.add("immediateEffect", immediateEffect1);
							developmentCardList.add(card);
						}
					}
				}
				serializedString = e.serialize(developmentCardList);
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
					String s = e.serialize(bonusTiles);
					System.out.println(s);
				}
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
			case 7:
				g.createCouncilPrivilege();
				break;
			case 8:
				String ab = g.readFile("bonusTile.json",
						defaultCharset());
				bonusTiles = d.buildBonusTiles(ab);
				String sa = e.serialize(bonusTiles);
				System.out.println(sa);
				break;

			case 9:
				requirements.set(ResourceType.WOOD, 0);
				requirements.set(ResourceType.STONE, 0);
				requirements.set(ResourceType.SERVANT, 0);
				requirements.set(ResourceType.COINS, 0);
				requirements.set(ResourceType.VICTORY_POINTS, 0);
				requirements.set(ResourceType.FAITH_POINTS, 0);
				requirements.set(ResourceType.MILITARY_POINTS, 0);
				String ac = e.serialize(requirements);
				ResourcesList req = d.deserialize(ac, ResourcesList.class);
				List<ResourcesList> resourcesListList = new ArrayList<>();
				resourcesListList.add(requirements);
				resourcesListList.add(requirements);
				String ae = e.serialize(resourcesListList);
				List<ResourcesList> rs = d.buildPersonalBoardList(ae);
				String ad = e.serialize(req);
				System.out.println(rs);
				break;
			case 10:
				serializedString = new String(
						Files.readAllBytes(Paths.get
								("cardob.json")), Charset.defaultCharset());
				DevelopmentCard dv = new Converter().convertDevelopmentCard(
						d.deserialize(serializedString, JsonObject.class));
				System.out.println(e.serialize(dv));
				//g.buildResourceListBasedOnOwnedResources();
				break;
			case 11:
				g.createDevelopmentCard();
				break;
			case 12:
				g.createActionSpaces();
				break;
			case 13:
				g.createCommons();
				break;
			case 14:
				if (new File("cards.json").exists()) {
					String content = g.readFile("cards.json",
							Charset.defaultCharset());
					JsonArray devCardList = new JsonParser().parse(
							content).getAsJsonArray();
					g.createDeckSetList(devCardList);

				}
				break;
		}
	}
		/*
		File fileProva=new File("commons.json");
		byte[] encoded = Files.readAllBytes(Paths.get(fileProva.getPath()));
		return new String(encoded, encoding);
		*/
}