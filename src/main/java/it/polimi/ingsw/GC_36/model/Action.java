package it.polimi.ingsw.GC_36.model;

import java.util.List;

//usare id al posto di actionspace e sostituire l-interfaccia con id

public class Action {
	MemberColor memberColor;
	ActionSpaceIds actionSpaceIds;
	ResourcesList paymentList;
	private List<Integer> councilPriviledgeList;

	public void setMemberColor(MemberColor MemberColor) {
		this.memberColor = MemberColor;
	}

	public void setActionSpaceIds(ActionSpaceIds actionSpaceIds) {
		this.actionSpaceIds=actionSpaceIds;
	}

	public void setPaymentList(ResourcesList resourcesList) {
		this.paymentList = resourcesList;
	}

	public void setCouncilPriviledgeList(List<Integer> privilegeList) {
		this.councilPriviledgeList = privilegeList;
	}

	public MemberColor getMemberColor() {
		return memberColor;
		//TODO:associated test replacing getFamilyMemberTest
	}

	public ActionSpaceIds getActionSpace() {
		return actionSpaceIds;
	}

	public ResourcesList getPaymentList() {
		return paymentList;
	}

	public void putPrivilegeChoose(int choose){
		councilPriviledgeList.add(choose);
	}

	public List<Integer> getCouncilPrivilegeList() {
		return councilPriviledgeList;
	}
}
