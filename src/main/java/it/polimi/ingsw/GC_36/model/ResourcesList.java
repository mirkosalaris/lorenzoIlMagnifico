package it.polimi.ingsw.GC_36.model;

import java.util.HashMap;
import java.util.Map;

public class ResourcesList {
	private final int woods, stones, servants, coins, victoryPoints,
			faithPoints,
			militaryPoints;

	Map<Resource, ResourceCounter> map = new HashMap<>();

	public ResourcesList(int woods, int stones, int servants, int coins,
	                     int victoryPoints, int faithPoints,
	                     int militaryPoints) {
		this.woods = woods;
		this.stones = stones;
		this.servants = servants;
		this.coins = coins;
		this.victoryPoints = victoryPoints;
		this.faithPoints = faithPoints;
		this.militaryPoints = militaryPoints;
	}

	public ResourceCounter get(Resource r) {
		return map.get(r);
	}


}
