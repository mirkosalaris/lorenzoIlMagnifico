package it.polimi.ingsw.GC_36.data;

public class Main {
	private Main() {}

	public static void main(String[] args) {
		/*
		Generator generator=new Generator();
		ResourcesList requirements1= generator.buildRequirements();
		ResourcesList requirements2=generator.buildRequirements();
		*/
		/*
		ResourcesList requirements1 = new ResourcesList(1, 1, 1, 1, 1, 1, 1);
		ResourcesList requirements2 = new ResourcesList(2, 2, 2, 2, 2, 2, 2);
		List<ResourcesList> requirementsList = new ArrayList<>();
		requirementsList.add(requirements1);
		requirementsList.add(requirements2);

		Encoder e = new Encoder();
		String serializedString = e.build(requirementsList);
		System.out.println(serializedString);

		System.out.println("\n--------------------------------------------\n");

		Decoder d = new Decoder();
		List<ResourcesList> resourcesLists = d.buildRequirements(
				serializedString);

		for (ResourcesList req : resourcesLists) {
			System.out.println(req.get(new Resource.Wood()).getValue());
		}

		System.out.println("\n--------------------------------------------\n");
		CardType type=new CardType.TerritoryCard();
		ImmediateEffect immediateEffect = null;
		PermanentEffect permanentEffect = null;

		DevelopmentCard developmentCard=new DevelopmentCard (type, 1,
		"Badessa", requirementsList, immediateEffect, permanentEffect);

		serializedString = e.build(developmentCard);
		System.out.println(serializedString);


		ResourcesList requirements= deserializeString.fromJson
		(serializedString, ResourcesList.class);
		System.out.println(requirements.get(new Resource.Wood()).getValue());
		*/
	}
}