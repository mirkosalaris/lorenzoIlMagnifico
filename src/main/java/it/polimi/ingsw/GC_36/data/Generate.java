package it.polimi.ingsw.GC_36.data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.polimi.ingsw.GC_36.model.ActionSpaceIds;
import it.polimi.ingsw.GC_36.model.CardType;
import it.polimi.ingsw.GC_36.model.ResourceType;
import it.polimi.ingsw.GC_36.model.ResourcesList;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_36.model.effects.PermanentEffect;
import it.polimi.ingsw.GC_36.model.effects.immediateEffects.ExtraTurn;
import it.polimi.ingsw.GC_36.model.effects.immediateEffects
		.ImmediateCouncilPrivilegeI;
import it.polimi.ingsw.GC_36.model.effects.immediateEffects
		.ImmediateResourcesListI;
import it.polimi.ingsw.GC_36.model.effects.permanentEffects.*;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.GC_36.utils.ExceptionLogger.log;

public class Generate {

	private String JsonDevelopmentCard() {

		JsonArray developmentCards = new JsonArray();

		JsonObject card = new JsonObject();
		//Pair(resourceToHave, resourceToPay)
		Pair<ResourcesList, ResourcesList> requirements;
		List<Pair<ResourcesList, ResourcesList>> requirementsList;
		JsonObject immediateEffect;
		JsonObject permanentEffect;
		ResourcesList resList;
		ImmediateEffect ie;
		PermanentEffect pe;
		ResourcesList from;
		ResourcesList to;
		ResourcesList toHave;
		ResourcesList toPay;
		Pair<ResourcesList, ResourcesList> match;
		Pair<ResourcesList, Integer> matchPrivilege;
		HashMap<Integer, Pair<ResourcesList, ResourcesList>> matchMap;
		HashMap<Integer, Pair<ResourcesList, Integer>> matchMapPrivilege;

		JsonArray immediateEffectList;
		JsonObject i1, i2;

		Set<ActionSpaceIds> actionSpace;


		// TERRITORY

		//Period 1

		card = new JsonObject();
		// general info
		card.addProperty("name", "Commercial Hub");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 1);
		// requirements
		//immediate effect
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.COINS, 1);
		pe = new ImmediateResourcesListP(resList, 1,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Woods");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 1);
		// requirements
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.WOOD, 1);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.WOOD, 1);
		pe = new ImmediateResourcesListP(resList, 2,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Village");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 1);
		// requirements
		//immediate effect
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.COINS, 1);
		resList.set(ResourceType.SERVANT, 1);
		pe = new ImmediateResourcesListP(resList, 3,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Gravel Pit");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 1);
		// requirements
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.STONE, 2);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.STONE, 2);
		pe = new ImmediateResourcesListP(resList, 4,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Forest");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 1);
		// requirements
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.WOOD, 1);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.WOOD, 3);
		pe = new ImmediateResourcesListP(resList, 5,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Monastery");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 1);
		// requirements
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.MILITARY_POINTS, 2);
		resList.set(ResourceType.SERVANT, 1);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.FAITH_POINTS, 1);
		resList.set(ResourceType.STONE, 1);
		pe = new ImmediateResourcesListP(resList, 6,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Citadel");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 1);
		// requirements
		//immediate effect
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.MILITARY_POINTS, 2);
		resList.set(ResourceType.STONE, 1);
		pe = new ImmediateResourcesListP(resList, 5,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "City");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 1);
		// requirements
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.COINS, 3);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType",
				"ImmediateCouncilPrivilegeP");
		pe = new ImmediateCouncilPrivilegeP(1, false, 6);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);


		//Period 2

		card = new JsonObject();
		// general info
		card.addProperty("name", "Gold Mine");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 2);
		// requirements
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.COINS, 1);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.COINS, 2);
		pe = new ImmediateResourcesListP(resList, 1,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Mountain Town");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 2);
		// requirements
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.SERVANT, 1);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.MILITARY_POINTS, 1);
		resList.set(ResourceType.WOOD, 2);
		pe = new ImmediateResourcesListP(resList, 3,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Mining Town");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 2);
		// requirements
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.SERVANT, 2);
		resList.set(ResourceType.STONE, 1);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.SERVANT, 1);
		resList.set(ResourceType.STONE, 2);
		pe = new ImmediateResourcesListP(resList, 4,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Rock Pit");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 2);
		// requirements
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.WOOD, 1);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.STONE, 3);
		pe = new ImmediateResourcesListP(resList, 3,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Estate");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 2);
		// requirements
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.SERVANT, 2);
		resList.set(ResourceType.WOOD, 1);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.COINS, 1);
		resList.set(ResourceType.WOOD, 2);
		pe = new ImmediateResourcesListP(resList, 4,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Hermitage");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 2);
		// requirements
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.FAITH_POINTS, 1);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.FAITH_POINTS, 1);
		pe = new ImmediateResourcesListP(resList, 2,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Manor House");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 2);
		// requirements
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.MILITARY_POINTS, 2);
		resList.set(ResourceType.SERVANT, 2);
		pe = new ImmediateResourcesListP(resList, 5,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Dukedom");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 2);
		// requirements
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.COINS, 4);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.COINS, 1);
		resList.set(ResourceType.WOOD, 2);
		resList.set(ResourceType.STONE, 1);
		pe = new ImmediateResourcesListP(resList, 6,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);


		//Period 3

		card = new JsonObject();
		// general info
		card.addProperty("name", "Trading Town");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 3);
		// requirements
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.COINS, 1);
		resList.set(ResourceType.SERVANT, 1);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.COINS, 3);
		pe = new ImmediateResourcesListP(resList, 1,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Farm");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 3);
		// requirements
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 1);
		resList.set(ResourceType.WOOD, 1);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 2);
		resList.set(ResourceType.WOOD, 2);
		pe = new ImmediateResourcesListP(resList, 3,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Colony");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 3);
		// requirements
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.MILITARY_POINTS, 2);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 4);
		resList.set(ResourceType.WOOD, 1);
		pe = new ImmediateResourcesListP(resList, 5,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Marble Pit");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 3);
		// requirements
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 3);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 1);
		resList.set(ResourceType.STONE, 2);
		pe = new ImmediateResourcesListP(resList, 2,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Province");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 3);
		// requirements
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "MultipleEffectI");
		immediateEffectList = new JsonArray();
		i1 = new JsonObject();
		i1.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.STONE, 1);
		ie = new ImmediateResourcesListI(resList);
		i1.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i1);
		i2 = new JsonObject();
		i2.addProperty("EffectType", "ImmediateCouncilPrivilege");
		ie = new ImmediateCouncilPrivilegeI(1, false);
		i2.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i2);
		immediateEffect.add("EffectBody", toJsonElement(immediateEffectList));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 4);
		resList.set(ResourceType.STONE, 1);
		pe = new ImmediateResourcesListP(resList, 6,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Sanctuary");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 3);
		// requirements
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.FAITH_POINTS, 1);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.COINS, 1);
		resList.set(ResourceType.FAITH_POINTS, 2);
		pe = new ImmediateResourcesListP(resList, 1,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Castle");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 3);
		// requirements
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 2);
		resList.set(ResourceType.COINS, 2);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.MILITARY_POINTS, 3);
		resList.set(ResourceType.SERVANT, 1);
		pe = new ImmediateResourcesListP(resList, 4,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Fortified Town");
		card.addProperty("type", "" + CardType.TERRITORY);
		card.addProperty("period", 3);
		// requirements
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.MILITARY_POINTS, 2);
		resList.set(ResourceType.SERVANT, 1);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.MILITARY_POINTS, 1);
		resList.set(ResourceType.SERVANT, 2);
		pe = new ImmediateResourcesListP(resList, 2,
				CardType.TERRITORY);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);


		//---------------------------------------------------------------------
		// BUILDING

		// Period 1

		card = new JsonObject();
		// general info
		card.addProperty("name", "Mint");
		card.addProperty("type", "" + CardType.BUILDING);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.WOOD, 1);
		toPay.set(ResourceType.WOOD, 1);
		toHave.set(ResourceType.STONE, 3);
		toPay.set(ResourceType.STONE, 3);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 5);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType",
				"ResourceListBasedOnOwnedCards");
		resList = new ResourcesList();
		resList.set(ResourceType.COINS, 1);
		pe = new ResourceListBasedOnOwnedCards(resList, CardType.BUILDING, 5);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Tax Office");
		card.addProperty("type", "" + CardType.BUILDING);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.WOOD, 3);
		toPay.set(ResourceType.WOOD, 3);
		toHave.set(ResourceType.STONE, 1);
		toPay.set(ResourceType.STONE, 1);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 5);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType",
				"ResourceListBasedOnOwnedCards");
		resList = new ResourcesList();
		resList.set(ResourceType.COINS, 1);
		pe = new ResourceListBasedOnOwnedCards(resList, CardType.TERRITORY, 5);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Triumphal Arch");
		card.addProperty("type", "" + CardType.BUILDING);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 2);
		toPay.set(ResourceType.COINS, 2);
		toHave.set(ResourceType.STONE, 2);
		toPay.set(ResourceType.STONE, 2);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 6);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType",
				"ResourceListBasedOnOwnedCards");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 1);
		pe = new ResourceListBasedOnOwnedCards(resList, CardType.VENTURE, 6);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Theater");
		card.addProperty("type", "" + CardType.BUILDING);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 2);
		toPay.set(ResourceType.COINS, 2);
		toHave.set(ResourceType.WOOD, 2);
		toPay.set(ResourceType.WOOD, 2);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 6);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType",
				"ResourceListBasedOnOwnedCards");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 1);
		pe = new ResourceListBasedOnOwnedCards(resList, CardType.CHARACTER, 6);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Carpenter's Shop");
		card.addProperty("type", "" + CardType.BUILDING);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 1);
		toPay.set(ResourceType.COINS, 1);
		toHave.set(ResourceType.WOOD, 2);
		toPay.set(ResourceType.WOOD, 2);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 3);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ResourcesConverting");
		matchMap = new HashMap<>();
		from = new ResourcesList();
		from.set(ResourceType.WOOD, 1);
		to = new ResourcesList();
		to.set(ResourceType.COINS, 3);
		match = new Pair<>(from, to);
		matchMap.put(0, match);
		from = new ResourcesList();
		from.set(ResourceType.WOOD, 2);
		to = new ResourcesList();
		to.set(ResourceType.COINS, 5);
		match = new Pair<>(from, to);
		matchMap.put(1, match);
		pe = new ResourcesConverting(4, matchMap);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Stonemason's Shop");
		card.addProperty("type", "" + CardType.BUILDING);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 1);
		toPay.set(ResourceType.COINS, 1);
		toHave.set(ResourceType.STONE, 2);
		toPay.set(ResourceType.STONE, 2);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 2);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ResourcesConverting");
		matchMap = new HashMap<>();
		from = new ResourcesList();
		from.set(ResourceType.STONE, 1);
		to = new ResourcesList();
		to.set(ResourceType.COINS, 3);
		match = new Pair<>(from, to);
		matchMap.put(0, match);
		from = new ResourcesList();
		from.set(ResourceType.STONE, 2);
		to = new ResourcesList();
		to.set(ResourceType.COINS, 5);
		match = new Pair<>(from, to);
		matchMap.put(1, match);
		pe = new ResourcesConverting(3, matchMap);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Chapel");
		card.addProperty("type", "" + CardType.BUILDING);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.WOOD, 2);
		toPay.set(ResourceType.WOOD, 2);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.FAITH_POINTS, 1);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ResourcesConverting");
		matchMap = new HashMap<>();
		from = new ResourcesList();
		from.set(ResourceType.COINS, 1);
		to = new ResourcesList();
		to.set(ResourceType.FAITH_POINTS, 1);
		match = new Pair<>(from, to);
		matchMap.put(0, match);
		pe = new ResourcesConverting(2, matchMap);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Residence");
		card.addProperty("type", "" + CardType.BUILDING);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.STONE, 2);
		toPay.set(ResourceType.STONE, 2);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 1);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ResourceToPrivilege");
		from = new ResourcesList();
		from.set(ResourceType.COINS, 1);
		pe = new ResourceToPrivilege(1, from, 1);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		//---------------------------------------------------------------------
		// CHARACTER

		// Period 1
		card = new JsonObject();
		// general info
		card.addProperty("name", "Warlord");
		card.addProperty("type", "" + CardType.CHARACTER);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 2);
		toPay.set(ResourceType.COINS, 2);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.MILITARY_POINTS, 3);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Stonemason");
		card.addProperty("type", "" + CardType.CHARACTER);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 4);
		toPay.set(ResourceType.COINS, 4);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Dame");
		card.addProperty("type", "" + CardType.CHARACTER);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 4);
		toPay.set(ResourceType.COINS, 4);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Knight");
		card.addProperty("type", "" + CardType.CHARACTER);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 2);
		toPay.set(ResourceType.COINS, 2);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateCouncilPrivilege");
		ie = new ImmediateCouncilPrivilegeI(1, false);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Farmer");
		card.addProperty("type", "" + CardType.CHARACTER);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 3);
		toPay.set(ResourceType.COINS, 3);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Artisan");
		card.addProperty("type", "" + CardType.CHARACTER);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 3);
		toPay.set(ResourceType.COINS, 3);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Preacher");
		card.addProperty("type", "" + CardType.CHARACTER);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 2);
		toPay.set(ResourceType.COINS, 2);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.FAITH_POINTS, 4);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Abbess");
		card.addProperty("type", "" + CardType.CHARACTER);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 3);
		toPay.set(ResourceType.COINS, 3);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "MultipleEffectI");
		immediateEffectList = new JsonArray();
		i1 = new JsonObject();
		i1.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.FAITH_POINTS, 1);
		ie = new ImmediateResourcesListI(resList);
		i1.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i1);
		i2 = new JsonObject();
		i2.addProperty("EffectType", "ExtraTurn");
		actionSpace = new HashSet<>();
		for (int i = 1; i <= 16; i++) {
			actionSpace.add(ActionSpaceIds.values()[i]);
		}
		ie = new ExtraTurn(actionSpace, 4);
		i2.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i2);
		immediateEffect.add("EffectBody", toJsonElement(immediateEffectList));
		card.add("immediateEffect", immediateEffect);
		//add card to list
		developmentCards.add(card);


		// Period 2

		card = new JsonObject();
		// general info
		card.addProperty("name", "Captain");
		card.addProperty("type", "" + CardType.CHARACTER);
		card.addProperty("period", 2);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 4);
		toPay.set(ResourceType.COINS, 4);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "MultipleEffectI");
		immediateEffectList = new JsonArray();
		i1 = new JsonObject();
		i1.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.MILITARY_POINTS, 2);
		ie = new ImmediateResourcesListI(resList);
		i1.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i1);
		i2 = new JsonObject();
		i2.addProperty("EffectType", "ExtraTurn");
		actionSpace = new HashSet<>();
		for (int i = 1; i <= 4; i++) {
			actionSpace.add(ActionSpaceIds.values()[i]);
		}
		ie = new ExtraTurn(actionSpace, 6);
		i2.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i2);
		immediateEffect.add("EffectBody", toJsonElement(immediateEffectList));
		card.add("immediateEffect", immediateEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Architect");
		card.addProperty("type", "" + CardType.CHARACTER);
		card.addProperty("period", 2);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 4);
		toPay.set(ResourceType.COINS, 4);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "MultipleEffectI");
		immediateEffectList = new JsonArray();
		i1 = new JsonObject();
		i1.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.WOOD, 1);
		resList.set(ResourceType.STONE, 1);
		ie = new ImmediateResourcesListI(resList);
		i1.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i1);
		i2 = new JsonObject();
		i2.addProperty("EffectType", "ExtraTurn");
		actionSpace = new HashSet<>();
		for (int i = 5; i <= 8; i++) {
			actionSpace.add(ActionSpaceIds.values()[i]);
		}
		ie = new ExtraTurn(actionSpace, 6);
		i2.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i2);
		immediateEffect.add("EffectBody", toJsonElement(immediateEffectList));
		card.add("immediateEffect", immediateEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Patron");
		card.addProperty("type", "" + CardType.CHARACTER);
		card.addProperty("period", 2);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 4);
		toPay.set(ResourceType.COINS, 4);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "MultipleEffectI");
		immediateEffectList = new JsonArray();
		i1 = new JsonObject();
		i1.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.COINS, 1);
		ie = new ImmediateResourcesListI(resList);
		i1.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i1);
		i2 = new JsonObject();
		i2.addProperty("EffectType", "ExtraTurn");
		actionSpace = new HashSet<>();
		for (int i = 9; i <= 12; i++) {
			actionSpace.add(ActionSpaceIds.values()[i]);
		}
		ie = new ExtraTurn(actionSpace, 6);
		i2.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i2);

		immediateEffect.add("EffectBody", toJsonElement(immediateEffectList));
		card.add("immediateEffect", immediateEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Hero");
		card.addProperty("type", "" + CardType.CHARACTER);
		card.addProperty("period", 2);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 4);
		toPay.set(ResourceType.COINS, 4);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "MultipleEffectI");
		immediateEffectList = new JsonArray();
		i1 = new JsonObject();
		i1.addProperty("EffectType", "ImmediateCouncilPrivilege");
		ie = new ImmediateCouncilPrivilegeI(1, false);
		i1.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i1);
		i2 = new JsonObject();
		i2.addProperty("EffectType", "ExtraTurn");
		actionSpace = new HashSet<>();
		for (int i = 13; i <= 16; i++) {
			actionSpace.add(ActionSpaceIds.values()[i]);
		}
		ie = new ExtraTurn(actionSpace, 6);
		i2.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i2);
		immediateEffect.add("EffectBody", toJsonElement(immediateEffectList));
		card.add("immediateEffect", immediateEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Peasant");
		card.addProperty("type", "" + CardType.CHARACTER);
		card.addProperty("period", 2);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 4);
		toPay.set(ResourceType.COINS, 4);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Scholar");
		card.addProperty("type", "" + CardType.CHARACTER);
		card.addProperty("period", 2);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 4);
		toPay.set(ResourceType.COINS, 4);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Papal Messenger");
		card.addProperty("type", "" + CardType.CHARACTER);
		card.addProperty("period", 2);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.FAITH_POINTS, 3);
		toPay.set(ResourceType.FAITH_POINTS, 3);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Royal Messenger");
		card.addProperty("type", "" + CardType.BUILDING);
		card.addProperty("period", 2);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 5);
		toPay.set(ResourceType.COINS, 5);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateCouncilPrivilege");
		ie = new ImmediateCouncilPrivilegeI(3, true);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//add card to list
		developmentCards.add(card);


		// Period 3
		//TODO fix
		card = new JsonObject();
		// general info
		card.addProperty("name", "Noble");
		card.addProperty("type", "" + CardType.BUILDING);
		card.addProperty("period", 3);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 6);
		toPay.set(ResourceType.COINS, 6);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));/*
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType",
		"ResourceListBasedOnOwnedResources");
		ie = new ResourceListBasedOnOwned(ResourceType.VICTORY_POINTS,2,new
		Re);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);*/
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Governor");
		card.addProperty("type", "" + CardType.BUILDING);
		card.addProperty("period", 3);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 6);
		toPay.set(ResourceType.COINS, 6);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Paramour");
		card.addProperty("type", "" + CardType.BUILDING);
		card.addProperty("period", 3);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 7);
		toPay.set(ResourceType.COINS, 7);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Herald");
		card.addProperty("type", "" + CardType.BUILDING);
		card.addProperty("period", 3);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 6);
		toPay.set(ResourceType.COINS, 6);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "General");
		card.addProperty("type", "" + CardType.BUILDING);
		card.addProperty("period", 3);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 5);
		toPay.set(ResourceType.COINS, 5);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		//add card to list
		developmentCards.add(card);
		//TODO FINE

		card = new JsonObject();
		// general info
		card.addProperty("name", "Cardinal");
		card.addProperty("type", "" + CardType.BUILDING);
		card.addProperty("period", 3);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 4);
		toPay.set(ResourceType.COINS, 4);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "MultipleEffectI");
		immediateEffectList = new JsonArray();
		i1 = new JsonObject();
		i1.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.FAITH_POINTS, 2);
		ie = new ImmediateResourcesListI(resList);
		i1.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i1);
		i2 = new JsonObject();
		i2.addProperty("EffectType", "ExtraTurn");
		actionSpace = new HashSet<>();
		actionSpace.add(ActionSpaceIds.values()[17]);
		actionSpace.add(ActionSpaceIds.values()[18]);
		ie = new ExtraTurn(actionSpace, 4);
		i2.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i2);
		immediateEffect.add("EffectBody", toJsonElement(immediateEffectList));
		card.add("immediateEffect", immediateEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Bishop");
		card.addProperty("type", "" + CardType.BUILDING);
		card.addProperty("period", 3);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 5);
		toPay.set(ResourceType.COINS, 5);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "MultipleEffectI");
		immediateEffectList = new JsonArray();
		i1 = new JsonObject();
		i1.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.FAITH_POINTS, 1);
		ie = new ImmediateResourcesListI(resList);
		i1.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i1);
		i2 = new JsonObject();
		i2.addProperty("EffectType", "ExtraTurn");
		actionSpace = new HashSet<>();
		actionSpace.add(ActionSpaceIds.values()[19]);
		actionSpace.add(ActionSpaceIds.values()[20]);
		ie = new ExtraTurn(actionSpace, 4);
		i2.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i2);
		immediateEffect.add("EffectBody", toJsonElement(immediateEffectList));
		card.add("immediateEffect", immediateEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Ambassador");
		card.addProperty("type", "" + CardType.BUILDING);
		card.addProperty("period", 3);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 6);
		toPay.set(ResourceType.COINS, 6);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "MultipleEffectI");
		immediateEffectList = new JsonArray();
		i1 = new JsonObject();
		i1.addProperty("EffectType", "ExtraTurn");
		actionSpace = new HashSet<>();
		for (int i = 1; i <= 16; i++) {
			actionSpace.add(ActionSpaceIds.values()[i]);
		}
		ie = new ExtraTurn(actionSpace, 4);
		i1.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i1);
		i2 = new JsonObject();
		i2.addProperty("EffectType", "ImmediateCouncilPrivilege");
		ie = new ImmediateCouncilPrivilegeI(1, false);
		i2.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i2);
		immediateEffect.add("EffectBody", toJsonElement(immediateEffectList));
		card.add("immediateEffect", immediateEffect);
		//add card to list
		developmentCards.add(card);


		//---------------------------------------------------------------------
		// VENTURE

		// Period 1

		card = new JsonObject();
		// general info
		card.addProperty("name", "Hiring Recruits");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 4);
		toPay.set(ResourceType.COINS, 4);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.MILITARY_POINTS, 5);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 1);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Repairing the Church");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 1);
		toPay.set(ResourceType.COINS, 1);
		toHave.set(ResourceType.WOOD, 1);
		toPay.set(ResourceType.WOOD, 1);
		toHave.set(ResourceType.STONE, 1);
		toPay.set(ResourceType.STONE, 1);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.FAITH_POINTS, 1);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 5);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Building the Walls");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.STONE, 1);
		toPay.set(ResourceType.STONE, 1);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "MultipleEffectI");
		immediateEffectList = new JsonArray();
		i1 = new JsonObject();
		i1.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.MILITARY_POINTS, 2);
		ie = new ImmediateResourcesListI(resList);
		i1.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i1);
		i2 = new JsonObject();
		i2.addProperty("EffectType", "ImmediateCouncilPrivilege");
		ie = new ImmediateCouncilPrivilegeI(1, false);
		i2.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i2);
		immediateEffect.add("EffectBody", toJsonElement(immediateEffectList));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 3);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Raising a Statue");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 2);
		toPay.set(ResourceType.COINS, 2);
		toHave.set(ResourceType.STONE, 2);
		toPay.set(ResourceType.STONE, 2);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateCouncilPrivilege");
		ie = new ImmediateCouncilPrivilegeI(2, true);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 4);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Military Campaign");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.MILITARY_POINTS, 3);
		toPay.set(ResourceType.MILITARY_POINTS, 2);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.COINS, 3);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 5);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Hosting Panhandlers");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.WOOD, 3);
		toPay.set(ResourceType.WOOD, 3);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.SERVANT, 4);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 4);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Fighting Heresies");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 1);
		// requirements
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.MILITARY_POINTS, 5);
		toPay.set(ResourceType.MILITARY_POINTS, 3);
		requirements = new Pair<>(toHave, toPay);
		requirementsList = new ArrayList<>();
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.FAITH_POINTS, 2);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 5);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Support to the Bishop");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 1);
		// requirements
		requirementsList = new ArrayList<>();
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.MILITARY_POINTS, 4);
		toPay.set(ResourceType.MILITARY_POINTS, 2);
		requirements = new Pair<>(toHave, toPay);
		requirementsList.add(requirements);
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.STONE, 1);
		toPay.set(ResourceType.STONE, 1);
		toHave.set(ResourceType.WOOD, 1);
		toPay.set(ResourceType.WOOD, 1);
		toHave.set(ResourceType.COINS, 2);
		toPay.set(ResourceType.COINS, 2);
		requirements = new Pair<>(toHave, toPay);
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.FAITH_POINTS, 3);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 1);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);


		//Period 2

		card = new JsonObject();
		// general info
		card.addProperty("name", "Hiring Soldiers");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 2);
		// requirements
		requirementsList = new ArrayList<>();
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 6);
		toPay.set(ResourceType.COINS, 6);
		requirements = new Pair<>(toHave, toPay);
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.MILITARY_POINTS, 6);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 5);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Reparing the Abbey");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 2);
		// requirements
		requirementsList = new ArrayList<>();
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 2);
		toPay.set(ResourceType.COINS, 2);
		toHave.set(ResourceType.STONE, 2);
		toPay.set(ResourceType.STONE, 2);
		toHave.set(ResourceType.WOOD, 2);
		toPay.set(ResourceType.WOOD, 2);
		requirements = new Pair<>(toHave, toPay);
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.FAITH_POINTS, 2);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 6);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Building the Bastions");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 2);
		// requirements
		requirementsList = new ArrayList<>();
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.STONE, 4);
		toPay.set(ResourceType.STONE, 4);
		requirements = new Pair<>(toHave, toPay);
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "MultipleEffectI");
		immediateEffectList = new JsonArray();
		i1 = new JsonObject();
		i1.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.MILITARY_POINTS, 3);
		ie = new ImmediateResourcesListI(resList);
		i1.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i1);
		i2 = new JsonObject();
		i2.addProperty("EffectType", "ImmediateCouncilPrivilege");
		ie = new ImmediateCouncilPrivilegeI(1, false);
		i2.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i2);
		immediateEffect.add("EffectBody", toJsonElement(immediateEffectList));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 2);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Support to the King");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 2);
		// requirements
		requirementsList = new ArrayList<>();
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.MILITARY_POINTS, 6);
		toPay.set(ResourceType.MILITARY_POINTS, 3);
		requirements = new Pair<>(toHave, toPay);
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "MultipleEffectI");
		immediateEffectList = new JsonArray();
		i1 = new JsonObject();
		i1.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.COINS, 5);
		ie = new ImmediateResourcesListI(resList);
		i1.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i1);
		i2 = new JsonObject();
		i2.addProperty("EffectType", "ImmediateCouncilPrivilege");
		ie = new ImmediateCouncilPrivilegeI(1, false);
		i2.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i2);
		immediateEffect.add("EffectBody", toJsonElement(immediateEffectList));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 3);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Improving the Canals");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 2);
		// requirements
		requirementsList = new ArrayList<>();
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.SERVANT, 2);
		toPay.set(ResourceType.SERVANT, 2);
		toHave.set(ResourceType.COINS, 3);
		toPay.set(ResourceType.COINS, 3);
		requirements = new Pair<>(toHave, toPay);
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ExtraTurn");
		actionSpace = new HashSet<>();
		actionSpace.add(ActionSpaceIds.values()[17]);
		actionSpace.add(ActionSpaceIds.values()[18]);
		ie = new ExtraTurn(actionSpace, 4);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);

		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 5);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Hosting Foreigners");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 2);
		// requirements
		requirementsList = new ArrayList<>();
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.WOOD, 4);
		toPay.set(ResourceType.WOOD, 4);
		requirements = new Pair<>(toHave, toPay);
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.SERVANT, 5);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 4);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Crusade");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 2);
		// requirements
		requirementsList = new ArrayList<>();
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.MILITARY_POINTS, 8);
		toPay.set(ResourceType.MILITARY_POINTS, 4);
		requirements = new Pair<>(toHave, toPay);
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.COINS, 5);
		resList.set(ResourceType.FAITH_POINTS, 1);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 5);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Support to the Cardinal");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 2);
		// requirements
		requirementsList = new ArrayList<>();
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.MILITARY_POINTS, 7);
		toPay.set(ResourceType.MILITARY_POINTS, 4);
		requirements = new Pair<>(toHave, toPay);
		requirementsList.add(requirements);
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.STONE, 2);
		toPay.set(ResourceType.STONE, 2);
		toHave.set(ResourceType.WOOD, 2);
		toPay.set(ResourceType.WOOD, 2);
		toHave.set(ResourceType.COINS, 3);
		toPay.set(ResourceType.COINS, 3);
		requirements = new Pair<>(toHave, toPay);
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.FAITH_POINTS, 3);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 4);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);


		//Period 3

		card = new JsonObject();
		// general info
		card.addProperty("name", "Hiring Mercenaries");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 3);
		// requirements
		requirementsList = new ArrayList<>();
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 8);
		toPay.set(ResourceType.COINS, 8);
		requirements = new Pair<>(toHave, toPay);
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.MILITARY_POINTS, 7);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 6);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);


		card = new JsonObject();
		// general info
		card.addProperty("name", "Repairing the Cathedral");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 3);
		// requirements
		requirementsList = new ArrayList<>();
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.COINS, 3);
		toPay.set(ResourceType.COINS, 3);
		toHave.set(ResourceType.STONE, 3);
		toPay.set(ResourceType.STONE, 3);
		toHave.set(ResourceType.WOOD, 3);
		toPay.set(ResourceType.WOOD, 3);
		requirements = new Pair<>(toHave, toPay);
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "MultipleEffectI");
		immediateEffectList = new JsonArray();
		i1 = new JsonObject();
		i1.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.FAITH_POINTS, 1);
		ie = new ImmediateResourcesListI(resList);
		i1.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i1);
		i2 = new JsonObject();
		i2.addProperty("EffectType", "ExtraTurn");
		actionSpace = new HashSet<>();
		for (int i = 1; i <= 16; i++) {
			actionSpace.add(ActionSpaceIds.values()[i]);
		}
		ie = new ExtraTurn(actionSpace, 4);
		i2.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i2);
		immediateEffect.add("EffectBody", toJsonElement(immediateEffectList));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 5);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Building the Towers");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 3);
		// requirements
		requirementsList = new ArrayList<>();
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.STONE, 6);
		toPay.set(ResourceType.STONE, 6);
		requirements = new Pair<>(toHave, toPay);
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "MultipleEffectI");
		immediateEffectList = new JsonArray();
		i1 = new JsonObject();
		i1.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.MILITARY_POINTS, 4);
		ie = new ImmediateResourcesListI(resList);
		i1.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i1);
		i2 = new JsonObject();
		i2.addProperty("EffectType", "ImmediateCouncilPrivilege");
		ie = new ImmediateCouncilPrivilegeI(1, false);
		i2.add("EffectBody", toJsonElement(ie));
		immediateEffectList.add(i2);
		immediateEffect.add("EffectBody", toJsonElement(immediateEffectList));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 4);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Promoting Sacred Art");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 3);
		// requirements
		requirementsList = new ArrayList<>();
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.WOOD, 6);
		toPay.set(ResourceType.WOOD, 6);
		requirements = new Pair<>(toHave, toPay);
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.FAITH_POINTS, 3);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 3);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Military Conquest");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 3);
		// requirements
		requirementsList = new ArrayList<>();
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.MILITARY_POINTS, 12);
		toPay.set(ResourceType.MILITARY_POINTS, 6);
		requirements = new Pair<>(toHave, toPay);
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.WOOD, 3);
		resList.set(ResourceType.STONE, 3);
		resList.set(ResourceType.COINS, 3);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 7);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Improving the Roads");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 3);
		// requirements
		requirementsList = new ArrayList<>();
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.SERVANT, 3);
		toPay.set(ResourceType.SERVANT, 3);
		toHave.set(ResourceType.COINS, 4);
		toPay.set(ResourceType.COINS, 4);
		requirements = new Pair<>(toHave, toPay);
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ExtraTurn");
		actionSpace = new HashSet<>();
		actionSpace.add(ActionSpaceIds.values()[19]);
		actionSpace.add(ActionSpaceIds.values()[20]);
		ie = new ExtraTurn(actionSpace, 3);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);


		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 5);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Sacred War");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 3);
		// requirements
		requirementsList = new ArrayList<>();
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.MILITARY_POINTS, 15);
		toPay.set(ResourceType.MILITARY_POINTS, 8);
		requirements = new Pair<>(toHave, toPay);
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.FAITH_POINTS, 4);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 8);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);

		card = new JsonObject();
		// general info
		card.addProperty("name", "Support to the Pope");
		card.addProperty("type", "" + CardType.VENTURE);
		card.addProperty("period", 3);
		// requirements
		requirementsList = new ArrayList<>();
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.MILITARY_POINTS, 10);
		toPay.set(ResourceType.MILITARY_POINTS, 5);
		requirements = new Pair<>(toHave, toPay);
		requirementsList.add(requirements);
		toHave = new ResourcesList();
		toPay = new ResourcesList();
		toHave.set(ResourceType.STONE, 3);
		toPay.set(ResourceType.STONE, 3);
		toHave.set(ResourceType.WOOD, 3);
		toPay.set(ResourceType.WOOD, 3);
		toHave.set(ResourceType.COINS, 4);
		toPay.set(ResourceType.COINS, 4);
		requirements = new Pair<>(toHave, toPay);
		requirementsList.add(requirements);
		card.add("requirements", toJsonElement(requirementsList));
		//immediate effect
		immediateEffect = new JsonObject();
		immediateEffect.addProperty("EffectType", "ImmediateResourcesListI");
		resList = new ResourcesList();
		resList.set(ResourceType.FAITH_POINTS, 2);
		ie = new ImmediateResourcesListI(resList);
		immediateEffect.add("EffectBody", toJsonElement(ie));
		card.add("immediateEffect", immediateEffect);
		//permanent effect
		permanentEffect = new JsonObject();
		permanentEffect.addProperty("EffectType", "ImmediateResourcesListP");
		resList = new ResourcesList();
		resList.set(ResourceType.VICTORY_POINTS, 10);
		pe = new ImmediateResourcesListP(resList, 0,
				CardType.VENTURE);
		permanentEffect.add("EffectBody", toJsonElement(pe));
		card.add("permanentEffect", permanentEffect);
		//add card to list
		developmentCards.add(card);


		return new Encoder().serialize(developmentCards);
	}

	public JsonElement toJsonElement(Object obj) {
		return new Gson().fromJson(new Gson().toJson(obj), JsonElement.class);
	}

	public void createCommons() throws IOException {
		String serializedString;
		Gson gson = new Gson();
		Encoder encoder = new Encoder();
		JsonObject jsonObject = new JsonObject();
		JsonElement jsonElement;

		serializedString = new String(
				Files.readAllBytes(Paths.get("DevelopmentCardsList.json")),
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

	public static void main(String[] args) throws IOException {
		Generate generate = new Generate();
		String serializedString = generate.JsonDevelopmentCard();
		FileWriter file = null;
		try {
			file = new FileWriter("DevelopmentCardsList.json");
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
		generate.createCommons();
	}
}

