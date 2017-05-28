package it.polimi.ingsw.GC_36.model;

import java.util.HashMap;
import java.util.Map;

public class ResourcesList {
	private final ResourceCounter woods, stones, servants, coins,
			victoryPoints,
			faithPoints,
			militaryPoints;

	Map<Resource, ResourceCounter> map = new HashMap<>();

	public ResourcesList(int woods, int stones, int servants, int coins,
	                     int victoryPoints, int faithPoints,
	                     int militaryPoints) {
		this.woods = new ResourceCounter(woods);
		this.stones = new ResourceCounter(stones);
		this.servants = new ResourceCounter(servants);
		this.coins = new ResourceCounter(coins);
		this.victoryPoints = new ResourceCounter(victoryPoints);
		this.faithPoints = new ResourceCounter(faithPoints);
		this.militaryPoints = new ResourceCounter(militaryPoints);

		map.put(new Resource.Wood(), this.woods);
		map.put(new Resource.Stone(), this.stones);
		map.put(new Resource.Servant(), this.servants);
		map.put(new Resource.Coins(), this.coins);
		map.put(new Resource.VictoryPoints(), this.victoryPoints);
		map.put(new Resource.FaithPoints(), this.faithPoints);
		map.put(new Resource.MilitaryPoints(), this.militaryPoints);
	}

	public ResourceCounter get(Resource r) {
		return map.get(r);
	}

	//somma,confronto,sottrarre

	public void addResources(ResourcesList resources) {
		for (Resource key : this.map.keySet()) {
			this.get(key).add(resources.get(key).getValue());
		}
	}

	//ceck if every Resourcecounter is >= than the coupled ones
	// in the ResourcesList passed by arguments
	public boolean ceckResources(ResourcesList resources) {
		for (Resource key : this.map.keySet()) {
			if (this.get(key).getValue() >= resources.get(key).getValue())
				return false;
		}
		return true;
	}

	public void subtractResources(ResourcesList resources) {
		for (Resource key : this.map.keySet()) {
			this.get(key).subtract(resources.get(key).getValue());
		}
	}

	// TODO
	//public <T> T iterate(Function<ResourcesList,T> lambda) {


}
