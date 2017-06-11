package it.polimi.ingsw.GC_36.model;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

public class Action implements Serializable, Remote {
	private MemberColor memberColor;
	private ActionSpaceIds actionSpaceIds;
	private ResourcesList paymentList;
	private List<Integer> councilPrivilegeList;
	private ArrayList<ExtraAction> extraActions;

	public Action() {
		extraActions = new ArrayList<ExtraAction>();
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

	public void addExtraAction(ExtraAction extraAction) {
		extraActions.add(extraAction);
	}

	public boolean isAvailable(ActionSpaceIds actionSpaceIds) {
		return true;
	}
}
