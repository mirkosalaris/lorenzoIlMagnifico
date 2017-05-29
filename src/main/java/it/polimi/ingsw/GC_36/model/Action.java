package it.polimi.ingsw.GC_36.model;

import java.util.List;

public class Action {
	FamilyMember familyMember;
	ActionSpace actionSpace;
	ResourcesList paymentList;
	private List<Integer> councilPriviledgeList;

	public void setFamilyMember(FamilyMember familyMember) {
		this.familyMember = familyMember;
	}

	public void setActionSpace(ActionSpace actionSpace) {
		this.actionSpace = actionSpace;
	}

	public void setPaymentList(ResourcesList resourcesList) {
		this.paymentList = resourcesList;
	}

	public void setCouncilPriviledgeList(List<Integer> privilegeList) {
		this.councilPriviledgeList = privilegeList;
	}

	public FamilyMember getFamilyMember() {
		return familyMember;
	}

	public ActionSpace getActionSpace() {
		return actionSpace;
	}

	public ResourcesList getPaymentList() {
		return paymentList;
	}

	public List<Integer> getCouncilPrivilegeList() {
		return councilPriviledgeList;
	}
}
