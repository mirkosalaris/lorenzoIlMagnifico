package it.polimi.ingsw.GC_36.model;

public class BonusTile {
	private int harvestRequiredActionValue;
	private int productionRequiredActionValue;
	private ResourcesList harvestBonus;
	private ResourcesList productionBonus;

	public BonusTile(int harvestRequiredActionValue,
	                 int productionRequiredActionValue,
	                 ResourcesList harvestBonus,
	                 ResourcesList productionBonus) {
		this.harvestRequiredActionValue = harvestRequiredActionValue;
		this.productionRequiredActionValue = productionRequiredActionValue;
		this.harvestBonus = harvestBonus;
		this.productionBonus = productionBonus;
	}

	public int getHarvestRequiredActionValue() {
		return harvestRequiredActionValue;
	}

	public int getProductionRequiredActionValue() {
		return productionRequiredActionValue;
	}

	public ResourcesList getHarvestBonus() {
		return harvestBonus;
	}

	public ResourcesList getProductionBonus() {
		return productionBonus;
	}
}
