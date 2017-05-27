package it.polimi.ingsw.GC_36.model;

import java.util.List;

public class Action {
	FamilyMember familyMember;
	ActionSpace actionSpace;
	ResourcesList cardPayment;
	private List<Integer> councilPriviledgeList;

	public List<Integer> getCouncilPriviledgeList() {
		return councilPriviledgeList;
	}
}
