package it.polimi.ingsw.GC_36.data;

import com.google.gson.Gson;
import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.model.effects.ImmediateEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Generator {

	public ResourcesList buildRequirements() {
		int woods, stones, servants, coins, victoryPoints, faithPoints,
				militaryPoints;
		Scanner input = new Scanner(System.in);

		System.out.print("Insert woods: ");
		woods = Integer.parseInt(input.nextLine());
		System.out.print("Insert stones: ");
		stones = Integer.parseInt(input.nextLine());
		System.out.print("Insert servants: ");
		servants = Integer.parseInt(input.nextLine());
		System.out.print("Insert coins: ");
		coins = Integer.parseInt(input.nextLine());
		System.out.print("Insert vitoryPoints: ");
		victoryPoints = Integer.parseInt(input.nextLine());
		System.out.print("Insert faithPoints: ");
		faithPoints = Integer.parseInt(input.nextLine());
		System.out.print("Insert militaryPoints: ");
		militaryPoints = Integer.parseInt(input.nextLine());

		ResourcesList requirements = new ResourcesList(woods, stones, servants,
				coins, victoryPoints, faithPoints,
				militaryPoints);

		return requirements;
	}

/*
	private DevelopmentCard buildDevelopmentCard(String type, int period) {
		String name;
		Scanner input = new Scanner(System.in);

		System.out.print("Insert name: ");
		name = input.nextLine();

		ResourcesList requirements = buildRequirements();

		//buil permanent effect
		Effect immediateEffect = null, permanentEffect = null; //TODO fix

		DevelopmentCard developmentCard = new DevelopmentCard(type, period,
				name,
				requirements, immediateEffect, permanentEffect);

		return developmentCard;
	}

	//take a period and make all cart of that period.
	//dovrebbe prendere un tipo? add type and remove from buildDeckSet
	private Deck buildDeck(String typeDevelopmentCard, int period) {
		//TODO add control json exist
		//List
		Deck deck = new Deck();
		Scanner input = new Scanner(System.in);
		String choose;
		do {
			// Add developmentCard of the same period
			DevelopmentCard developmentCard = buildDevelopmentCard();
			//deck.add(developmentCard); <--IMPORTANTE nono
			//CREA LISTA DEV CARD E PASSO NEL COSTRUTTORE DI DECK
			System.out.print("Finish?   ");
			choose = input.nextLine();
		} while (choose.equals("n"));
		// COSTRUISCO IL DECK - NEW DECK LISTA
		//TODO generate json
		return deck;
	}

	private DeckSet buildDeckSet(String typeDevelopmentCard, int period) {
		DeckSet deckSet = new DeckSet();
		Scanner input = new Scanner(System.in);
		String choose;
		do {
			// Add deck in a set deck of equals typeDevelopmentCard
			Deck deck = buildDeck(period);
			//deckSet.add(deck); <-- IMPORTANTW

			System.out.print("Finish?   ");
			choose = input.nextLine();
		} while (choose.equals("n"));
		return deckSet;
	}

	public List<DeckSet> buildTotalDevelopmentDeck() {
		List<DeckSet> tot = new ArrayList<DeckSet>();
		String typeDevelopmentCard;
		int period;
		Scanner input = new Scanner(System.in);
		String choose;
		do { //4volte
			System.out.print("Insert type of Deck: ");
			typeDevelopmentCard = input.nextLine();
			System.out.print("Insert period: ");
			period = Integer.parseInt(input.nextLine());
			tot.add(buildDeckSet("TerritoriesDeck", period));

			System.out.print("Finish?   ");
			choose = input.nextLine();
		} while (choose.equals("n"));
		//ADD TO MAIN
		Gson card2gson = new Gson();
		String u = card2gson.toJson(tot);
		System.out.println(u);
		return tot;
	}

	public void iWantToBeSmart() {
	}
	*/
}
