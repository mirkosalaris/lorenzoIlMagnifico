package it.polimi.ingsw.GC_36.model;

public class BonusTile {
	private int harvestRequiredActionValue;
	private int productionRequiredActionValue;
	private ResourcesList harvestbonus;
	private ResourcesList productionBonus;

	public BonusTile(int harvest, int production) {
		this.harvestRequiredActionValue = harvest;
		this.productionRequiredActionValue = production;
	}

	public int getHarvestRequiredActionValue() {
		return harvestRequiredActionValue;
	}

	public int getProductionRequiredActionValue() {
		return productionRequiredActionValue;
	}

	public ResourcesList getHarvestBonus() {
		return harvestbonus;
	}

	public ResourcesList getProductionBonus() {
		return productionBonus;
	}
}
