package it.polimi.ingsw.GC_36.model;

public class BonusTile {
	private int harvestRequiredActionValue;
	private int productionRequiredActionValue;
	private ResourcesList harvestbonus;
	private ResourcesList productionBonus;

	public BonusTile(int harvestRequiredActionValue,
	                 int productionRequiredActionValue,
	                 ResourcesList harvestbonus,
	                 ResourcesList productionBonus) {
		this.harvestRequiredActionValue = harvestRequiredActionValue;
		this.productionRequiredActionValue = productionRequiredActionValue;
		this.harvestbonus = harvestbonus;
		this.productionBonus = productionBonus;
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
