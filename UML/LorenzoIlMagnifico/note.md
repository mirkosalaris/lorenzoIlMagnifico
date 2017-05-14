# Open problems
* How to implement actionHandler pairings with actionSpace
	- we could instantiate an actionHandler for each actionSpace,
		but every instance would be exactly the same (no data stored)
	- using a static actionHandler, but how can we distinguish between
		simple actionSpace and tower actionSpace? There would have to be
		a method .getActionHandler() that would return the reference to
		the static simple ActionHandler or to TowerActionHandler.
		Moreover we have to manage production and harvest.
* immediate effects sometimes are resourcesList, sometimes a new card. How do we
	model them?
	
# New ideas
* what if we add an OptionListManager, whose work is to merge options and so on?
* ActionSpace have methods like "isHarvest" and "isProduction". In this way 
	ActionHandler knows when to do harvest and production operations.

# Controller Package
> contains the logic of the game and the method to execute it.
> NB: 	every class inside the controller accept as first parameter (it WON'T BE
> 	indicated in the following part of this document) a ModelCommunicator
> 	needed to know how to access the model part.

## Game
> this is a class, one instance for each actual game. 
	
	+run():
		the only public method. This starts the game calling all other
		method of the class
	-initialize():
		* creates an instance of the model, instantiating Board and then
			calling board.initialize().
		* creates an instance of ModelCommunicator and save into it the
			instance of the board
	-play():
		start the game logic, instantiating period by period
	-finalScoring():
		this is called at the end of the game to know the winner

## Period
> a class instantiated by game and characterized by a periodNumber

	Constructor: accept a periodNumber

	+start():
		gets the deckset through ModelCommunicator and Board, calling 
		board.getDeckSet(periodNumber)
		create two instances of Round, passing them the deckSet.
		Then it invokes round.startRound()

## Round
> it manages the whole round, from the initialization, through the execution,
> to the termination.
	
	Constructor: accept a deckSet as parameter

	+startRound():
		the only public method. It starts the round's logic.
		It calls the other methods of Round. Before the calls it has to
		get a reference to board and then, through board, to deckSet
		dice, turnOrder.
	
	-allocateCards(deckSet):
		takes 4 cards from each deck of the deckSet calling
		getTYPEDeck and using the method deck.popCard()
	-rollDice(Die[n]):
		call roll() on each die.
	-executeRound():
		while(turnOrder.hasNext()) {
			player = tom.getNextPlayer();
			TurnExecutor.play(player);
		}
		NB: turnOrder is taken through ModelCommunicator and Board
	-adjustTurnOrder(turnOrder):
		it simply calls turnOrder.adjustTurnOrder()
	-cleanBoard(board)
		this will call board.clean()

## TurnOrder
> contains an ordered set of players. This set represents the whole round, so, 
> for instance, with 4 players this is a 16 items set; with 2 players this is a 
> 8 items set.

	+hasNext()
		return true if there's still someone that has to play,
		false otherwise
	+getNextPlayer()
		return the next player who has to play
	+setNewOrder(orderedPlayers[n])
		it simply calls turnOrder.setNewOrder(orderedPlayers[n])
	
## TurnExecutor (static)
> execute a turn of a certain player

	+play(Player player)
		activates the logic to make the player play: tell the client to
		invoke user.chooseActionSpace(), then pass the retrieved
		actionSpace to ActionHandler.getOptionList(), along with a
		reference of the player

## ActionHandler (static)
> has methods to check if an action is legal and to execute an action

	+getOptionList(player, actionSpace):optionList
		call EffectManager.applyEffect(player, actionSpace)
		Then it checks if player can enter the actionSpace. If he can't,
		it raises an exception (IllegalActionException, to define).
		If he can, it returns all the possible options in terms of 
		pair familyMember+servants and in terms of resources to pay
	+execute(player, actionSpace, option)
		call getOptionList, check if option belong to optionList and
		then apply the action.
		If option does not belong to optionList it raise an
		IllegalStateException

## EffectManager (static)
> has methods to transform the state of something, taking into account permanent
> effects
	+applyEffect(player, something) //overloaded
		'something' should be, for the moment, an actionPlace or a Card.
		This calls player.getPersonalBoard() and persBoard.getTYPECard()
		and then for each card calls card.applyEffect(something), which
		returns 'something' modified
	+applyEffect(something) //overloaded, advanced mode
		this apply effects on things not related to single players (like
		turnOrder)


# Model package
> contains the data. The main class is "Board" which contains a reference to
> everything else inside model.

## Board
> has basic method to initialize the game board and to do operation on it (like 
> to clean it)
> From here everything inside model can be reached through reference (maybe not
> directly from this class)

	TODO: Constructor: accept info about the game, like number of player etc.
	
	+initialize()
		create an instance of every referentiable model's class
	+clean()
		reset the board cleaning actionSpaces and towers
	+getDeckSet(periodNumber)
		return the deckSet corresponding to periodNumber
	+getTurnOrder()
		return the reference to TurnOrder
## Player
> contains the data about a player. 

	Constructor: 
		initialize the player instantiating familymembers and passing
		them their corresponding die (got from board.getDiceList)

	+getPersonalBoard()
		return an instance of PersonalBoard associated with this player
	+getAvailableFamilyMembers()
		return a set of still available family members for this player

## FamilyMember
> represents a family members

	Constructor: accept a die
	
	+getValue()
		returns the value associated with this member, done calling
		die.getValue()
	+isAvailable()
		returns true if not already used (in this round)
	+setAvailable()
		sets the member as available
	+setUsed()
		sets the member as not more available
	+getColor()
		return die.getColor()

## Die
> represents a single die

	Constructor: accept a string 'color'
	
	+getColor()
		return the color (as string)
	+getValue()
		return the associated value
	+setValue()
		store the new value
	
## DeckSet
> it is a set of decks. This set is related to a certain period, but the deckset
> is not aware of this fact, it is just a set of 4 decks.

	getTerritoriesDeck()
		return the associated territories deck
	...()

## Deck
> it contains a set of development cards

	Constructor: accept a set of cards
		It has to shuffle and to store the received cards.

	+popCard()
		pop the first card of the deck

## DevelopmentCard
> each instance contains data of an actual development card.

	Constructor: accept a JSON/XML representation of the card and convert it
	into an in memory representation
	
	+getName()
		return the name of the card
	+getRequirements()
		return a resourceList corresponding to the requirements of the card
	+getImmediateEffect()
		TODO

## ActionSpace
> represents an actionSpac that could be real or virtual. Since ActionSpace is
> immutable, Virtual actionSpaces are created by controller computation to
> manipulate actionSpace

	+isAvailable()
		return the value of 'free' attribute. free is true if there's
		space available for players, false otherwise
	+getRequiredActionValue()
		return an integer representing the required action value
	+getResourcesList()
		return a resourcesList representing the bonus to give to players
	isInTower()
		return true if the actionSpace is associated to a floor
	getAssociatedFloor()
		return the associated floor

## Floor
> represents a tower's floor. It contains a developement card.

	+isCardAvailable()
		return true if the player can take the card. Return false otherwise
	+getDevelopmentCard()
		return a reference to the associated card
	+getTower()
		return the floor's tower

## Tower
> represents a tower. This is an enumeration with 4 instances.

	+isFree()
		return true if the tower is not already occupied
	~reset()
		set the tower to free

## PersonalBoard
> stores the data of the players, like resources, owned cards, points.

	+getTYPECards()
		return the set of cards of type TYPE owned by the player
	+getResourcesList()
		return a resourcesList with the resources of the player
	+getBonusTile()
		return a reference to the associated bonusTile

## BonusTile
> represents the bonus tile associated with a personal board.

	+getHarvestBonus()
		return a the ResourcesList associated with Harvest action
	+getProductionBonus()
		return a the ResourcesList associated with Production action
	+getHarvestRequiredActionValue()
		return the required action value as integer
	+getProductionRequiredActionValue()
		return the required action value as integer
		
## ResourceList
> represents a set of resources. It contains a field for each type of resource
> This is immutable.

	Constructor: accept an integer for each type of resource.
		in order: coins, stone, wood, servant, victory points, military
		points, faith points
	+get(Coins/Stone/Wood/Servant)Counter()
		return an integer
	+get(Victory/Military/Faith)Points()
		return an integer
















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
