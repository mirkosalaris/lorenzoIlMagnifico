## Player
> represents the actual player. Decisions/commands start here.

## BonusTile
> stores additional bonus for production and harvest area
	*methods*:
		* Collection<Resources> getHarvestBonus()
		* Collection<Resources> getProductionBonus()

## Resources
> store a number of resources of a single type (subclass)
> Can be any amount, associated to player, cards, bonuses.
	*methods*:
		* void add()
		* void pay()
## Coins
> store an amount, of coins.
> Can be any amount, associated to player, cards, bonuses.
	*methods*:
		* void add()
		* void pay()
