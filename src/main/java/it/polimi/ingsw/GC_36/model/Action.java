package it.polimi.ingsw.GC_36.model;

import java.io.Serializable;
import java.util.List;

public class Action implements Serializable {
	MemberColor memberColor;
	ActionSpaceIds actionSpaceIds;
	ResourcesList paymentList;
	private List<Integer> councilPrivilegeList;

	public Action() {
	}

	public void setMemberColor(MemberColor MemberColor) {
		this.memberColor = MemberColor;
	}

	public void setActionSpaceIds(ActionSpaceIds actionSpaceIds) {
		this.actionSpaceIds = actionSpaceIds;
	}

	public void setPaymentList(ResourcesList resourcesList) {
		this.paymentList = resourcesList;
	}

	public void setCouncilPrivilegeList(List<Integer> privilegeList) {
		this.councilPrivilegeList = privilegeList;
	}

	public MemberColor getMemberColor() {
		return memberColor;
		//TODO:associated test replacing getFamilyMemberTest
	}

	public ActionSpaceIds getActionSpaceId() {
		return actionSpaceIds;
	}

	public ResourcesList getPaymentList() {
		return paymentList;
	}

	public void putPrivilegeChoice(int choice) {
		councilPrivilegeList.add(choice);
	}

	public List<Integer> getCouncilPrivilegeList() {
		return councilPrivilegeList;
	}

	public void copyFrom(Action action) {
		// TODO test if constraints are the same between this and action

		this.memberColor = action.memberColor;
		this.actionSpaceIds = action.actionSpaceIds;
		this.councilPrivilegeList = action.councilPrivilegeList;
	}
}
